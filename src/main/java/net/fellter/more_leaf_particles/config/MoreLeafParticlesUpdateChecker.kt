package net.fellter.more_leaf_particles.config

//? if fabric {
import com.google.gson.*
import com.terraformersmc.modmenu.api.UpdateChannel
import com.terraformersmc.modmenu.api.UpdateChecker
import com.terraformersmc.modmenu.api.UpdateInfo
import com.terraformersmc.modmenu.util.HttpUtil
import net.fabricmc.loader.api.FabricLoader
import net.fabricmc.loader.api.SemanticVersion
import net.fabricmc.loader.api.Version
import net.fellter.more_leaf_particles.MoreLeafParticles
import net.minecraft.network.chat.Component
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.IOException
import java.net.URI
import java.net.http.HttpRequest
import java.net.http.HttpResponse

class MoreLeafParticlesUpdateChecker : UpdateChecker {
	override fun checkForUpdates(): UpdateInfo? {
		var result: UpdateInfo? = null

		try {
			result = check()
		} catch (_: InterruptedException) {
			Thread.currentThread().interrupt()
		} catch (_: IOException) {
			LOGGER.error("Failed More Leaf Particles update check")
		}

		return result
	}

	class MoreLeafParticlesUpdateInfo internal constructor(modVersion: String, mcVersion: String, id: String) : UpdateInfo {
		private val version: String = stripQuotes(modVersion)
		private val mcVersion: String = stripQuotes(mcVersion)
		private val id: String = stripQuotes(id)

		override fun getUpdateMessage(): Component {
			return Component.translatable("modmenu.install_version", this.version + '+' + mcVersion)
		}

		override fun isUpdateAvailable(): Boolean {
			return true
		}

		override fun getDownloadLink(): String {
			return "https://cdn.modrinth.com/data/HwWDzPBa/versions/$id/more_leaf_particles-$version%2B$mcVersion.jar"
		}

		override fun getUpdateChannel(): UpdateChannel {
			return UpdateChannel.RELEASE
		}
	}

	companion object {
		val LOGGER: Logger = LoggerFactory.getLogger("Mod Menu/More Leaf Particles Update Checker")
		private val PROJECT_URI: URI = URI.create("https://api.modrinth.com/v2/project/HwWDzPBa")

		@Throws(IOException::class, InterruptedException::class) private fun check(): UpdateInfo? {
			val request = HttpRequest.newBuilder().GET().uri(PROJECT_URI)
			val response = HttpUtil.request(request, HttpResponse.BodyHandlers.ofString())

			val status = response.statusCode()

			if (status != 200) {
				LOGGER.warn("Modrinth API responded with a non-200 status: {}, couldn't retrieve project information", status)
				return null
			}

			val contentType = response.headers().firstValue("Content-Type")

			if (contentType.isEmpty || !contentType.get().contains("application/json")) {
				LOGGER.warn("Modrinth API responded with a non-json content type, aborting check.")
				return null
			}

			val data: JsonElement

			try {
				data = JsonParser.parseString(response.body())
			} catch (_: JsonSyntaxException) {
				LOGGER.warn("Tried to parse malformed JSON data, aborting check.")
				return null
			}

			//Get all version ids. Implicitly not null, since at least one version is always available
			val versions: JsonArray = getVersions(data.getAsJsonObject())

			var match: SemanticVersion? = null
			var versionId: String? = null

			val currentModVer: SemanticVersion?
			val currentMcVer: SemanticVersion?

			try {
				val split: Array<String> = currentVersion.friendlyString.split("\\+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
				currentModVer = SemanticVersion.parse(split[0])
				currentMcVer = SemanticVersion.parse(split[1])
			} catch (_: Exception) {
				LOGGER.warn("Couldn't parse current version information, aborting check.")
				return null
			}

			for (version in versions.asList().stream().map { element -> stripQuotes(element.toString()) }.toList()) {
				val versionURI = URI.create("https://api.modrinth.com/v2/version/" + version.replace("\"", ""))
				val versionRequest = HttpRequest.newBuilder().GET().uri(versionURI)
				val versionResponse = HttpUtil.request(versionRequest, HttpResponse.BodyHandlers.ofString())

				if (versionResponse.statusCode() != 200) {
					LOGGER.warn("Modrinth API responded with a non-200 status: {}, couldn't retrieve information for version: {}", versionResponse.statusCode(), version)
					return null
				}

				val versionData = JsonParser.parseString(versionResponse.body())

				val versionNumber = versionData.getAsJsonObject().get("version_number")
				val id = versionData.getAsJsonObject().get("id")
				val gameVersions = versionData.getAsJsonObject().get("game_versions").getAsJsonArray().asList()

				//skip to next version if it isn't for the current Minecraft version
				if (!gameVersions.stream().map { element -> stripQuotes(element.toString()) }.toList().contains(currentMcVer.friendlyString)) {
					continue
				}

				val parsedModVer: SemanticVersion?

				try {
					parsedModVer = SemanticVersion.parse(stripQuotes(versionNumber.toString()).split("\\+".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray().first())
				} catch (_: Exception) {
					LOGGER.info("Couldn't parse mod version")
					continue
				}

				if (match == null || parsedModVer > (match as Version)) {
					match = parsedModVer
					versionId = id.toString()
				}
			}

			if (match == null || match <= (currentModVer as Version) || versionId == null) {
				LOGGER.info("More Leaf Particles is up to date.")
				return null
			}

			return MoreLeafParticlesUpdateInfo(match.friendlyString, currentMcVer.friendlyString, versionId)
		}

		private val currentVersion: Version
			get() {
				val mod = FabricLoader.getInstance().getModContainer(MoreLeafParticles.MOD_ID)

				if (mod.isPresent) {
					return mod.get().metadata.version
				} else {
					throw NullPointerException()
				}
			}

		private fun getVersions(jsonObject: JsonObject): JsonArray {
			if (!jsonObject.has("versions")) {
				throw NullPointerException("No versions could be found.")
			}

			return jsonObject.get("versions").getAsJsonArray()
		}

		private fun stripQuotes(toStrip: String): String {
			return toStrip.replace("\"", "")
		}
	}
}
//?}
