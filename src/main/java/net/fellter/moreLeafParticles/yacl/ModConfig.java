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

	/* OAK */
	@SerialEntry(value = "enable_oak")
	public static boolean enableOak = true;

	@SerialEntry(value = "enable_oak_size")
	public static boolean enableOakSize = false;

	@SerialEntry(value = "oak_size")
	public static float oakSize = 2.0F;

	@SerialEntry(value = "enable_oak_custom_color")
	public static boolean enableOakCustomColor = false;

	@SerialEntry(value = "oak_color")
	public static Color oakColor = new Color(0, 0, 0, 0);

	@SerialEntry(value = "enable_oak_tint")
	public static boolean useOakTint = true;

	@SerialEntry(value = "enable_oak_gravity")
	public static boolean enableOakGravity = false;

	@SerialEntry(value = "oak_gravity")
	public static float oakGravity = 0.07F;

	@SerialEntry(value = "oak_wind")
	public static float oakWind = 10.0F;

	@SerialEntry(value = "enable_oak_wind")
	public static boolean enableOakWind = false;

	@SerialEntry(value = "oak_flow_away")
	public static boolean oakFlowAway = false;

	@SerialEntry(value = "enable_oak_init_velocity")
	public static boolean enableOakInitialVelocity = false;

	@SerialEntry(value = "oak_init_velocity")
	public static float oakInitialVelocity = 0.021F;

	/* DARK OAK */
	@SerialEntry(value = "enable_dark_oak")
	public static boolean enableDarkOak = true;

	@SerialEntry(value = "enable_dark_oak_size")
	public static boolean enableDarkOakSize = false;

	@SerialEntry(value = "dark_oak_size")
	public static float darkOakSize = 2.0F;

	@SerialEntry(value = "enable_dark_oak_custom_color")
	public static boolean enableDarkOakCustomColor = false;

	@SerialEntry(value = "dark_oak_color")
	public static Color darkOakColor = new Color(0, 0, 0, 0);

	@SerialEntry(value = "enable_dark_oak_tint")
	public static boolean useDarkOakTint = true;

	@SerialEntry(value = "enable_dark_oak_gravity")
	public static boolean enableDarkOakGravity = false;

	@SerialEntry(value = "dark_oak_gravity")
	public static float darkOakGravity = 0.07F;

	@SerialEntry(value = "dark_oak_wind")
	public static float darkOakWind = 10F;

	@SerialEntry(value = "enable_dark_oak_wind")
	public static boolean enableDarkOakWind = false;

	@SerialEntry(value = "dark_oak_flow_away")
	public static boolean darkOakFlowAway = false;

	@SerialEntry(value = "enable_dark_oak_init_velocity")
	public static boolean enableDarkOakInitialVelocity = false;

	@SerialEntry(value = "dark_oak_init_velocity")
	public static float darkOakInitialVelocity = 0.021F;

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

	@SerialEntry(value = "enable_spruce_gravity")
	public static boolean enableSpruceGravity = false;

	@SerialEntry(value = "spruce_gravity")
	public static float spruceGravity = 0.07F;

	@SerialEntry(value = "spruce_wind")
	public static float spruceWind = 10F;

	@SerialEntry(value = "enable_spruce_wind")
	public static boolean enableSpruceWind = false;

	@SerialEntry(value = "spruce_flow_away")
	public static boolean spruceFlowAway = false;

	@SerialEntry(value = "enable_spruce_init_velocity")
	public static boolean enableSpruceInitialVelocity = false;

	@SerialEntry(value = "spruce_init_velocity")
	public static float spruceInitialVelocity = 0.021F;

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

	@SerialEntry(value = "enable_birch_gravity")
	public static boolean enableBirchGravity = false;

	@SerialEntry(value = "birch_gravity")
	public static float birchGravity = 0.07F;

	@SerialEntry(value = "birch_wind")
	public static float birchWind = 10F;

	@SerialEntry(value = "enable_birch_wind")
	public static boolean enableBirchWind = false;

	@SerialEntry(value = "birch_flow_away")
	public static boolean birchFlowAway = false;

	@SerialEntry(value = "enable_birch_init_velocity")
	public static boolean enableBirchInitialVelocity = false;

	@SerialEntry(value = "birch_init_velocity")
	public static float birchInitialVelocity = 0.021F;

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

	@SerialEntry(value = "enable_mangrove_gravity")
	public static boolean enableMangroveGravity = false;

	@SerialEntry(value = "mangrove_gravity")
	public static float mangroveGravity = 0.07F;

	@SerialEntry(value = "mangrove_wind")
	public static float mangroveWind = 10F;

	@SerialEntry(value = "enable_mangrove_wind")
	public static boolean enableMangroveWind = false;

	@SerialEntry(value = "mangrove_flow_away")
	public static boolean mangroveFlowAway = false;

	@SerialEntry(value = "enable_mangrove_init_velocity")
	public static boolean enableMangroveInitialVelocity = false;

	@SerialEntry(value = "mangrove_init_velocity")
	public static float mangroveInitialVelocity = 0.021F;

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

	@SerialEntry(value = "enable_jungle_gravity")
	public static boolean enableJungleGravity = false;

	@SerialEntry(value = "jungle_gravity")
	public static float jungleGravity = 0.07F;

	@SerialEntry(value = "jungle_wind")
	public static float jungleWind = 10F;

	@SerialEntry(value = "enable_jungle_wind")
	public static boolean enableJungleWind = false;

	@SerialEntry(value = "jungle_flow_away")
	public static boolean jungleFlowAway = false;

	@SerialEntry(value = "enable_jungle_init_velocity")
	public static boolean enableJungleInitialVelocity = false;

	@SerialEntry(value = "jungle_init_velocity")
	public static float jungleInitialVelocity = 0.021F;

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

	@SerialEntry(value = "enable_acacia_gravity")
	public static boolean enableAcaciaGravity = false;

	@SerialEntry(value = "acacia_gravity")
	public static float acaciaGravity = 0.07F;

	@SerialEntry(value = "acacia_wind")
	public static float acaciaWind = 10F;

	@SerialEntry(value = "enable_acacia_wind")
	public static boolean enableAcaciaWind = false;

	@SerialEntry(value = "acacia_flow_away")
	public static boolean acaciaFlowAway = false;

	@SerialEntry(value = "enable_acacia_init_velocity")
	public static boolean enableAcaciaInitialVelocity = false;

	@SerialEntry(value = "acacia_init_velocity")
	public static float acaciaInitialVelocity = 0.021F;

	/* AZALEA */
	@SerialEntry(value = "enable_azalea")
	public static boolean enableAzalea = true;

	@SerialEntry(value = "enable_azalea_size")
	public static boolean enableAzaleaSize = false;

	@SerialEntry(value = "azalea_size")
	public static float azaleaSize = 2.0F;

	@SerialEntry(value = "enable_azalea_gravity")
	public static boolean enableAzaleaGravity = false;

	@SerialEntry(value = "azalea_gravity")
	public static float azaleaGravity = 0.07F;

	@SerialEntry(value = "azalea_wind")
	public static float azaleaWind = 10F;

	@SerialEntry(value = "enable_azalea_wind")
	public static boolean enableAzaleaWind = false;

	@SerialEntry(value = "azalea_flow_away")
	public static boolean azaleaFlowAway = false;

	@SerialEntry(value = "enable_azalea_init_velocity")
	public static boolean enableAzaleaInitialVelocity = false;

	@SerialEntry(value = "azalea_init_velocity")
	public static float azaleaInitialVelocity = 0.021F;

	/* PALE OAK */
	@SerialEntry(value = "enable_pale_oak")
	public static boolean enablePaleOak = true;

	@SerialEntry(value = "enable_pale_oak_size")
	public static boolean enablePaleOakSize = false;

	@SerialEntry(value = "pale_oak_size")
	public static float paleOakSize = 2.0F;

	@SerialEntry(value = "enable_pale_oak_gravity")
	public static boolean enablePaleOakGravity = false;

	@SerialEntry(value = "pale_oak_gravity")
	public static float paleOakGravity = 0.07F;

	@SerialEntry(value = "pale_oak_wind")
	public static float paleOakWind = 10F;

	@SerialEntry(value = "enable_pale_oak_wind")
	public static boolean enablePaleOakWind = false;

	@SerialEntry(value = "pale_oak_flow_away")
	public static boolean paleOakFlowAway = false;

	@SerialEntry(value = "enable_pale_oak_init_velocity")
	public static boolean enablePaleOakInitialVelocity = false;

	@SerialEntry(value = "pale_oak_init_velocity")
	public static float paleOakInitialVelocity = 0.021F;

	/* CHERRY */
	@SerialEntry(value = "enable_cherry")
	public static boolean enableCherry = true;

	@SerialEntry(value = "enable_cherry_size")
	public static boolean enableCherrySize = false;

	@SerialEntry(value = "cherry_size")
	public static float cherrySize = 2.0F;

	@SerialEntry(value = "enable_cherry_gravity")
	public static boolean enableCherryGravity = false;

	@SerialEntry(value = "cherry_gravity")
	public static float cherryGravity = 0.07F;

	@SerialEntry(value = "cherry_wind")
	public static float cherryWind = 10F;

	@SerialEntry(value = "enable_cherry_wind")
	public static boolean enableCherryWind = false;

	@SerialEntry(value = "cherry_flow_away")
	public static boolean cherryFlowAway = false;

	@SerialEntry(value = "enable_cherry_init_velocity")
	public static boolean enableCherryInitialVelocity = false;

	@SerialEntry(value = "cherry_init_velocity")
	public static float cherryInitialVelocity = 0.021F;

	/* FLOWERING AZALEA */
	@SerialEntry(value = "enable_flowering_azalea")
	public static boolean enableFloweringAzalea = true;

	@SerialEntry(value = "enable_flowering_azalea_size")
	public static boolean enableFloweringAzaleaSize = false;

	@SerialEntry(value = "flowering_azalea_size")
	public static float floweringAzaleaSize = 2.0F;

	@SerialEntry(value = "enable_flowering_azalea_gravity")
	public static boolean enableFloweringAzaleaGravity = false;

	@SerialEntry(value = "flowering_azalea_gravity")
	public static float floweringAzaleaGravity = 0.07F;

	@SerialEntry(value = "flowering_azalea_wind")
	public static float floweringAzaleaWind = 10F;

	@SerialEntry(value = "enable_flowering_azalea_wind")
	public static boolean enableFloweringAzaleaWind = false;

	@SerialEntry(value = "flowering_azalea_flow_away")
	public static boolean floweringAzaleaFlowAway = false;

	@SerialEntry(value = "enable_flowering_azalea_init_velocity")
	public static boolean enableFloweringAzaleaInitialVelocity = false;

	@SerialEntry(value = "flowering_azalea_init_velocity")
	public static float floweringAzaleaInitialVelocity = 0.021F;
}
