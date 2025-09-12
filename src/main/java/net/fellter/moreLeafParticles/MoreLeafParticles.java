package net.fellter.moreLeafParticles;

import net.fellter.moreLeafParticles.yacl.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

public class MoreLeafParticles implements ModInitializer {
	public static final String MOD_ID = "more-leaf-particles";
	public static final Logger LOGGER = LoggerFactory.getLogger("More Leaf Particles");

	@Override
	public void onInitialize() {
		ModParticles.init();

		if (FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) {
			ModConfig.initConfig();

			if (!FabricLoader.getInstance().isModLoaded("modmenu")) {
				LOGGER.warn("Couldn't load ModMenu - install it to be able to configurate More Leaf Particles via the Modmenu config screen!");
				ModConfig.HANDLER.defaults();
			}
		} else if (FabricLoader.getInstance().isModLoaded("modmenu")) {
			if (!FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")) {
				LOGGER.warn("Couldn't load YACL - install it to be able to use More Leaf Particles' config!");
			} else {
				ModConfig.initConfig();
			}
		}
	}

	public static boolean isYACLPresent() {
		return FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3");
	}
}
