package net.fellter.more_leaf_particles

//? if fabric {
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.FabricLoader
import org.slf4j.Logger
import org.slf4j.LoggerFactory
//?} else if neoforge {
/*import net.neoforged.fml.common.Mod
import net.neoforged.fml.ModList
*///?}
import net.fellter.more_leaf_particles.config.ModConfig

//? if neoforge
//@Mod("more_leaf_particles")
class MoreLeafParticles /*? if fabric {*/ : ModInitializer /*?}*/ {
	/*? if fabric {*/override fun onInitialize()/*?} else if neoforge {*//*init*//*?}*/ {
		ModParticles.init()

		if (isYACLPresent) {
			ModConfig.initConfig()

			//? if fabric {
			if (!isModMenuPresent) {
				LOGGER.warn("Couldn't load ModMenu - install it to be able to configure More Leaf Particles via the Modmenu config screen!")
				ModConfig.HANDLER.defaults()
			}
			//?}
		} /*? if fabric {*/ else if (isModMenuPresent) {
			LOGGER.warn("Couldn't load YACL - install it to be able to use More Leaf Particles' config!")
		}
		//?}
	}

	companion object {
		const val MOD_ID: String = "more_leaf_particles"

		//? if fabric {
		val LOGGER: Logger = LoggerFactory.getLogger("More Leaf Particles")

		@JvmStatic val isModMenuPresent: Boolean = FabricLoader.getInstance().isModLoaded("modmenu")
		//?}

		@JvmStatic val isYACLPresent: Boolean =
			/*? if fabric {*/FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")/*?} else if neoforge {*/ /*ModList.get().isLoaded("yet_another_config_lib_v3")*//*?}*/

		@JvmStatic val isParticleRainPresent: Boolean =
			/*? if fabric {*/FabricLoader.getInstance().isModLoaded("particlerain")/*?} else if neoforge {*/ /*ModList.get().isLoaded("particlerain")*//*?}*/
	}
}
