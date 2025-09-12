package net.fellter.moreLeafParticles.yacl;

import java.awt.*;

import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import dev.isxander.yacl3.config.v2.impl.ConfigClassHandlerImpl;
import dev.isxander.yacl3.gui.YACLScreen;
import net.fellter.moreLeafParticles.MoreLeafParticles;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.Identifier;

import net.fabricmc.loader.api.FabricLoader;

public class ModConfig {
	public static ConfigClassHandler<ModConfig> HANDLER = new ConfigClassHandlerImpl<>(
			ModConfig.class,
			Identifier.of(MoreLeafParticles.MOD_ID, "config"),
			config -> GsonConfigSerializerBuilder.create(config)
					.setPath(FabricLoader.getInstance().getConfigDir().resolve("more-leaf-particles.json5"))
					.appendGsonBuilder(GsonBuilder::setPrettyPrinting)
					.setJson5(true)
					.build()
	) {
		@Override
		public void save() {
			super.save();
			MinecraftClient client = MinecraftClient.getInstance();

			if (client.currentScreen == null) {
				return;
			}

			if (client.currentScreen instanceof YACLScreen screen) {
				int index = screen.tabNavigationBar.getTabs().indexOf(screen.tabManager.getCurrentTab());
				int scrollOffset = screen.tabNavigationBar.getScrollOffset();
				client.currentScreen.close();

				YACLScreen newScreen = (YACLScreen) YACLImpl.create().generateScreen(client.currentScreen);
				newScreen.init(client, client.getWindow().getScaledWidth(), client.getWindow().getScaledHeight());

				newScreen.tabNavigationBar.selectTab(index, false);
				newScreen.tabNavigationBar.init();
				newScreen.tabNavigationBar.setScrollOffset(scrollOffset);

				client.setScreen(newScreen);
			} else {
				client.currentScreen.close();
				client.setScreen(YACLImpl.create().generateScreen(client.currentScreen));
			}
		}
	};

	public static void initConfig() {
		ModConfig.HANDLER.load();
		YACLImpl.create();
	}

	/* SPRUCE */
	@SerialEntry(value = "enable_spruce")
	public static boolean enableSpruce = true;

	@SerialEntry(value = "enable_spruce_size")
	public static boolean enableSpruceSize = false;

	@SerialEntry(value = "spruce_size")
	public static float spruceSize = 2.0F;

	@SerialEntry(value = "enable_spruce_custom_color")
	public static boolean enableSpruceCustomColor = false;

	@SerialEntry(value = "spruce_color")
	public static Color spruceColor = new Color(0, 0, 0, 0);

	@SerialEntry(value = "enable_spruce_tint")
	public static boolean useSpruceTint = true;

	/* BIRCH */
	@SerialEntry(value = "enable_birch")
	public static boolean enableBirch = true;

	@SerialEntry(value = "enable_birch_size")
	public static boolean enableBirchSize = false;

	@SerialEntry(value = "birch_size")
	public static float birchSize = 2.0F;

	@SerialEntry(value = "enable_birch_custom_color")
	public static boolean enableBirchCustomColor = false;

	@SerialEntry(value = "birch_color")
	public static Color birchColor = new Color(0, 0, 0, 0);

	@SerialEntry(value = "enable_birch_tint")
	public static boolean useBirchTint = true;

	/* MANGROVE */
	@SerialEntry(value = "enable_mangrove")
	public static boolean enableMangrove = true;

	@SerialEntry(value = "enable_mangrove_size")
	public static boolean enableMangroveSize = false;

	@SerialEntry(value = "mangrove_size")
	public static float mangroveSize = 2.0F;

	@SerialEntry(value = "enable_mangrove_custom_color")
	public static boolean enableMangroveCustomColor = false;

	@SerialEntry(value = "mangrove_color")
	public static Color mangroveColor = new Color(0, 0, 0, 0);

	@SerialEntry(value = "enable_mangrove_tint")
	public static boolean useMangroveTint = true;

	/* JUNGLE */
	@SerialEntry(value = "enable_jungle")
	public static boolean enableJungle = true;

	@SerialEntry(value = "enable_jungle_size")
	public static boolean enableJungleSize = false;

	@SerialEntry(value = "jungle_size")
	public static float jungleSize = 2.0F;

	@SerialEntry(value = "enable_jungle_custom_color")
	public static boolean enableJungleCustomColor = false;

	@SerialEntry(value = "jungle_color")
	public static Color jungleColor = new Color(0, 0, 0, 0);

	@SerialEntry(value = "enable_jungle_tint")
	public static boolean useJungleTint = true;

	/* ACACIA */
	@SerialEntry(value = "enable_acacia")
	public static boolean enableAcacia = true;

	@SerialEntry(value = "enable_acacia_size")
	public static boolean enableAcaciaSize = false;

	@SerialEntry(value = "acacia_size")
	public static float acaciaSize = 1.66F;

	@SerialEntry(value = "enable_acacia_custom_color")
	public static boolean enableAcaciaCustomColor = false;

	@SerialEntry(value = "acacia_color")
	public static Color acaciaColor = new Color(0, 0, 0, 0);

	@SerialEntry(value = "enable_acacia_tint")
	public static boolean useAcaciaTint = true;

	/* AZALEA */
	@SerialEntry(value = "enable_azalea")
	public static boolean enableAzalea = true;

	@SerialEntry(value = "enable_azalea_size")
	public static boolean enableAzaleaSize = false;

	@SerialEntry(value = "azalea_size")
	public static float azaleaSize = 2.0F;

	/* FLOWERING AZALEA */
	@SerialEntry(value = "enable_flowering_azalea")
	public static boolean enableFloweringAzalea = true;

	@SerialEntry(value = "enable_flowering_azalea_size")
	public static boolean enableFloweringAzaleaSize = false;

	@SerialEntry(value = "flowering_azalea_size")
	public static float floweringAzaleaSize = 2.0F;
}
