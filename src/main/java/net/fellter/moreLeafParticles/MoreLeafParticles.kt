package net.fellter.moreLeafParticles

import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import net.fellter.moreLeafParticles.config.ModConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class MoreLeafParticles : ModInitializer {
	override fun onInitialize() {
		ModParticles.init()

		if (isYACLPresent) {
			ModConfig.initConfig()

			if (!isModMenuPresent) {
				LOGGER.warn("Couldn't load ModMenu - install it to be able to configure More Leaf Particles via the Modmenu config screen!")
				ModConfig.HANDLER.defaults()
			}
		} else if (isModMenuPresent) {
			LOGGER.warn("Couldn't load YACL - install it to be able to use More Leaf Particles' config!")
		}
	}

	companion object {
		const val MOD_ID: String = "more-leaf-particles"
		val LOGGER: Logger = LoggerFactory.getLogger("More Leaf Particles")

		@JvmStatic val isModMenuPresent: Boolean = FabricLoader.getInstance().isModLoaded("modmenu")

		@JvmStatic val isYACLPresent: Boolean = FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")

		@JvmStatic val isParticleRainPresent: Boolean = FabricLoader.getInstance().isModLoaded("particlerain")
	}
}
