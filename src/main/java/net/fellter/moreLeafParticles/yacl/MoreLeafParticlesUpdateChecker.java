package net.fellter.moreLeafParticles.yacl;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import com.google.gson.*;
import com.terraformersmc.modmenu.api.UpdateChannel;
import com.terraformersmc.modmenu.api.UpdateChecker;
import com.terraformersmc.modmenu.api.UpdateInfo;
import com.terraformersmc.modmenu.util.HttpUtil;
import net.fellter.moreLeafParticles.MoreLeafParticles;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.minecraft.network.chat.Component;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.SemanticVersion;
import net.fabricmc.loader.api.Version;

public class MoreLeafParticlesUpdateChecker implements UpdateChecker {
	public static final Logger LOGGER = LoggerFactory.getLogger("Mod Menu/More Leaf Particles Update Checker");
	private static final URI PROJECT_URI = URI.create("https://api.modrinth.com/v2/project/HwWDzPBa");

	@Override
	public UpdateInfo checkForUpdates() {
		UpdateInfo result = null;

		try {
			result = check();
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		} catch (IOException e) {
			LOGGER.error("Failed More Leaf Particles update check");
		}

		return result;
	}

	private static UpdateInfo check() throws IOException, InterruptedException {
		HttpRequest.Builder request = HttpRequest.newBuilder().GET().uri(PROJECT_URI);
		HttpResponse<String> response = HttpUtil.request(request, HttpResponse.BodyHandlers.ofString());

		int status = response.statusCode();

		if (status != 200) {
			LOGGER.warn("Modrinth API responded with a non-200 status: {}, couldn't retrieve project information", status);
			return null;
		}

		Optional<String> contentType = response.headers().firstValue("Content-Type");

		if (contentType.isEmpty() || !contentType.get().contains("application/json")) {
			LOGGER.warn("Modrinth API responded with a non-json content type, aborting check.");
			return null;
		}

		JsonElement data;

		try {
			data = JsonParser.parseString(response.body());
		} catch (JsonSyntaxException e) {
			LOGGER.warn("Tried to parse malformed JSON data, aborting check.");
			return null;
		}

		//Get all version ids. Implicitly not null, since at least one version is always available
		JsonArray versions = getVersions(data.getAsJsonObject());

		SemanticVersion match = null;
		String versionId = null;

		SemanticVersion currentModVer;
		SemanticVersion currentMcVer;

		try {
			String[] split = getCurrentVersion().getFriendlyString().split("\\+");
			currentModVer = SemanticVersion.parse(split[0]);
			currentMcVer = SemanticVersion.parse(split[1]);
		} catch (Exception e) {
			LOGGER.warn("Couldn't parse current version information, aborting check.");
			return null;
		}

		for (String version : versions.asList().stream().map(element -> stripQuotes(element.toString())).toList()) {
			URI versionURI = URI.create("https://api.modrinth.com/v2/version/" + version.replace("\"", ""));
			HttpRequest.Builder versionRequest = HttpRequest.newBuilder().GET().uri(versionURI);
			HttpResponse<String> versionResponse = HttpUtil.request(versionRequest, HttpResponse.BodyHandlers.ofString());

			if (versionResponse.statusCode() != 200) {
				LOGGER.warn("Modrinth API responded with a non-200 status: {}, couldn't retrieve information for version: {}", versionResponse.statusCode(), version);
				return null;
			}

			JsonElement versionData = JsonParser.parseString(versionResponse.body());

			JsonElement versionNumber = versionData.getAsJsonObject().get("version_number");
			JsonElement id = versionData.getAsJsonObject().get("id");
			List<JsonElement> gameVersions = versionData.getAsJsonObject().get("game_versions").getAsJsonArray().asList();

			//skip to next version if it isn't for the current Minecraft version
			if (!gameVersions.stream().map(element -> stripQuotes(element.toString())).toList().contains(currentMcVer.getFriendlyString())) {
				continue;
			}

			SemanticVersion parsedModVer;

			try {
				parsedModVer = SemanticVersion.parse(stripQuotes(versionNumber.toString()).split("\\+")[0]);
			} catch (Exception e) {
				LOGGER.info("Couldn't parse mod version");
				continue;
			}

			if (match == null || isNewer(parsedModVer, match)) {
				match = parsedModVer;
				versionId = id.toString();
			}
		}

		if (match == null || !isNewer(match, currentModVer) || versionId == null) {
			LOGGER.info("More Leaf Particles is up to date.");
			return null;
		}

		return new MoreLeafParticlesUpdateInfo(match.getFriendlyString(), currentMcVer.getFriendlyString(), versionId);
	}

	private static Version getCurrentVersion() {
		var	mod = FabricLoader.getInstance().getModContainer(MoreLeafParticles.MOD_ID);

		if(mod.isPresent()) {
			return mod.get().getMetadata().getVersion();
		} else {
			throw new NullPointerException();
		}
	}

	private static JsonArray getVersions(JsonObject object) {
		if (!object.has("versions")) {
			throw new NullPointerException("No versions could be found.");
		}

		return object.get("versions").getAsJsonArray();
	}

	private static String stripQuotes(String toStrip) {
		return toStrip.replace("\"", "");
	}

	private static boolean isNewer(Version self, Version other) {
		return self.compareTo(other) > 0;
	}

	public static class MoreLeafParticlesUpdateInfo implements UpdateInfo {
		private final String version;
		private final String mcVersion;
		private final String id;

		private MoreLeafParticlesUpdateInfo(String modVersion, String mcVersion, String id) {
			this.version = stripQuotes(modVersion);
			this.mcVersion = stripQuotes(mcVersion);
			this.id = stripQuotes(id);
		}

		@Override
		public @Nullable Component getUpdateMessage() {
			return Component.translatable("modmenu.install_version", this.version + '+' + mcVersion);
		}

		@Override
		public boolean isUpdateAvailable() {
			return true;
		}

		@Override
		public String getDownloadLink() {
			return "https://cdn.modrinth.com/data/HwWDzPBa/versions/" + id + "/more_leaf_particles-" + version + "%2B" + mcVersion + ".jar";
		}

		@Override
		public UpdateChannel getUpdateChannel() {
			return UpdateChannel.RELEASE;
		}
	}
}
