package net.fellter.more_leaf_particles

//? if fabric {
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
//?}
import net.fellter.more_leaf_particles.MoreLeafParticles.Companion.isYACLPresent
import net.fellter.more_leaf_particles.config.ConfigFields
import net.minecraft.client.multiplayer.ClientLevel
import net.minecraft.client.particle.*
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.particles.SimpleParticleType
//? if >1.21.8
import net.minecraft.util.RandomSource
import java.awt.Color

@Environment(EnvType.CLIENT)
class SpruceLeavesFactory(private val provider: SpriteSet) : FallingLeavesParticle.TintedLeavesProvider(provider) {
	override fun createParticle(tinted: ColorParticleOption, clientLevel: ClientLevel, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double /*? if >1.21.8 {*/, random: RandomSource /*?}*/): Particle {
		return if (isYACLPresent) {
			FallingLeavesParticle(
				clientLevel,
				d, e, f,
				/*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/
				ConfigFields.spruceGravity,
				ConfigFields.spruceWind,
				true,
				ConfigFields.spruceFlowAway,
				ConfigFields.spruceSize,
				ConfigFields.spruceInitialVelocity
			).also {
				it.`more_leaf_particles$setParent`(tinted.`more_leaf_particles$getParent`())
				tintParticle(it, tinted, ConfigFields.enableSpruceCustomColor, ConfigFields.useSpruceTint, ConfigFields.spruceColor)
			}
		} else {
			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 2f, 0.021f).also {
				it.setColor(tinted.red, tinted.green, tinted.blue)
			}
		}
	}
}

@Environment(EnvType.CLIENT)
class BirchLeavesFactory(private val provider: SpriteSet) : FallingLeavesParticle.TintedLeavesProvider(provider) {
	override fun createParticle(tinted: ColorParticleOption, clientLevel: ClientLevel, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double /*? if >1.21.8 {*/, random: RandomSource /*?}*/): Particle {
		return if (isYACLPresent) {
			FallingLeavesParticle(
				clientLevel,
				d, e, f,
				/*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/
				ConfigFields.birchGravity,
				ConfigFields.birchWind,
				true,
				ConfigFields.birchFlowAway,
				ConfigFields.birchSize,
				ConfigFields.birchInitialVelocity
			).also {
				it.`more_leaf_particles$setParent`(tinted.`more_leaf_particles$getParent`())
				tintParticle(it, tinted, ConfigFields.enableBirchCustomColor, ConfigFields.useBirchTint, ConfigFields.birchColor)
			}
		} else {
			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 2f, 0.021f).also {
				it.setColor(tinted.red, tinted.green, tinted.blue)
			}
		}
	}
}

@Environment(EnvType.CLIENT)
class MangroveLeavesFactory(private val provider: SpriteSet) : FallingLeavesParticle.TintedLeavesProvider(provider) {
	override fun createParticle(tinted: ColorParticleOption, clientLevel: ClientLevel, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double /*? if >1.21.8 {*/, random: RandomSource /*?}*/): Particle {
		return if (isYACLPresent) {
			FallingLeavesParticle(
				clientLevel,
				d, e, f,
				/*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/
				ConfigFields.mangroveGravity,
				ConfigFields.mangroveWind,
				true,
				ConfigFields.mangroveFlowAway,
				ConfigFields.mangroveSize,
				ConfigFields.mangroveInitialVelocity
			).also {
				it.`more_leaf_particles$setParent`(tinted.`more_leaf_particles$getParent`())
				tintParticle(it, tinted, ConfigFields.enableMangroveCustomColor, ConfigFields.useMangroveTint, ConfigFields.mangroveColor)
			}
		} else {
			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 2f, 0.021f).also {
				it.setColor(tinted.red, tinted.green, tinted.blue)
			}
		}
	}
}

@Environment(EnvType.CLIENT)
class JungleLeavesFactory(private val provider: SpriteSet) : FallingLeavesParticle.TintedLeavesProvider(provider) {
	override fun createParticle(tinted: ColorParticleOption, clientLevel: ClientLevel, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double /*? if >1.21.8 {*/, random: RandomSource /*?}*/): Particle {
		return if (isYACLPresent) {
			FallingLeavesParticle(
				clientLevel,
				d, e, f,
				/*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/
				ConfigFields.jungleGravity,
				ConfigFields.jungleWind,
				true,
				ConfigFields.jungleFlowAway,
				ConfigFields.jungleSize,
				ConfigFields.jungleInitialVelocity
			).also {
				it.`more_leaf_particles$setParent`(tinted.`more_leaf_particles$getParent`())
				tintParticle(it, tinted, ConfigFields.enableJungleCustomColor, ConfigFields.useJungleTint, ConfigFields.jungleColor)
			}
		} else {
			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 2f, 0.021f).also {
				it.setColor(tinted.red, tinted.green, tinted.blue)
			}
		}
	}
}

@Environment(EnvType.CLIENT)
class AcaciaLeavesFactory(private val provider: SpriteSet) : FallingLeavesParticle.TintedLeavesProvider(provider) {
	override fun createParticle(tinted: ColorParticleOption, clientLevel: ClientLevel, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double /*? if >1.21.8 {*/, random: RandomSource /*?}*/): Particle {
		return if (isYACLPresent) {
			FallingLeavesParticle(
				clientLevel,
				d, e, f,
				/*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/
				ConfigFields.acaciaGravity,
				ConfigFields.acaciaWind,
				true,
				ConfigFields.acaciaFlowAway,
				ConfigFields.acaciaSize,
				ConfigFields.acaciaInitialVelocity
			).also {
				it.`more_leaf_particles$setParent`(tinted.`more_leaf_particles$getParent`())
				tintParticle(it, tinted, ConfigFields.enableAcaciaCustomColor, ConfigFields.useAcaciaTint, ConfigFields.acaciaColor)
			}
		} else {
			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 1.66f, 0.021f).also {
				it.setColor(tinted.red, tinted.green, tinted.blue)
			}
		}
	}
}

@Environment(EnvType.CLIENT)
class DarkOakLeavesFactory(private val provider: SpriteSet) : FallingLeavesParticle.TintedLeavesProvider(provider) {
	override fun createParticle(tinted: ColorParticleOption, clientLevel: ClientLevel, d: Double, e: Double, f: Double, g: Double, h: Double, i: Double /*? if >1.21.8 {*/, random: RandomSource /*?}*/): Particle {
		return if (isYACLPresent) {
			FallingLeavesParticle(
				clientLevel,
				d, e, f,
				/*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/
				ConfigFields.darkOakGravity,
				ConfigFields.darkOakWind,
				true,
				ConfigFields.darkOakFlowAway,
				ConfigFields.darkOakSize,
				ConfigFields.darkOakInitialVelocity
			).also {
				it.`more_leaf_particles$setParent`(tinted.`more_leaf_particles$getParent`())
				tintParticle(it, tinted, ConfigFields.enableDarkOakCustomColor, ConfigFields.useDarkOakTint, ConfigFields.darkOakColor)
			}
		} else {
			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 2f, 0.021f).also {
				it.setColor(tinted.red, tinted.green, tinted.blue)
			}
		}
	}
}

@Environment(EnvType.CLIENT)
class AzaleaLeavesFactory(private val provider: SpriteSet) : ParticleProvider<SimpleParticleType> {
	override fun createParticle(parameters: SimpleParticleType, world: ClientLevel, x: Double, y: Double, z: Double, velocityX: Double, velocityY: Double, velocityZ: Double /*? if >1.21.8 {*/, random: RandomSource /*?}*/): Particle {
		return if (isYACLPresent) {
			FallingLeavesParticle(
				world, x, y, z,
				/*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/
				ConfigFields.azaleaGravity,
				ConfigFields.azaleaWind,
				true,
				ConfigFields.azaleaFlowAway,
				ConfigFields.azaleaSize,
				ConfigFields.azaleaInitialVelocity
			).also {
				it.`more_leaf_particles$setParent`(parameters.`more_leaf_particles$getParent`())
			}
		} else {
			FallingLeavesParticle(world, x, y, z,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 2.0f, 0.021f)
		}
	}
}

@Environment(EnvType.CLIENT)
class FloweringAzaleaLeavesFactory(private val provider: SpriteSet) : ParticleProvider<SimpleParticleType> {
	override fun createParticle(parameters: SimpleParticleType, world: ClientLevel, x: Double, y: Double, z: Double, velocityX: Double, velocityY: Double, velocityZ: Double /*? if >1.21.8 {*/, random: RandomSource /*?}*/): Particle {
		return if (isYACLPresent) {
			FallingLeavesParticle(
				world,
				x, y, z,
				/*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/
				ConfigFields.floweringAzaleaGravity,
				ConfigFields.floweringAzaleaWind,
				true,
				ConfigFields.floweringAzaleaFlowAway,
				ConfigFields.floweringAzaleaSize,
				ConfigFields.floweringAzaleaInitialVelocity
			).also {
				it.`more_leaf_particles$setParent`(parameters.`more_leaf_particles$getParent`())
			}
		} else {
			FallingLeavesParticle(world, x, y, z,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 2.0f, 0.021f)
		}
	}
}

fun tintParticle(
	particle: SingleQuadParticle,
	tinted: ColorParticleOption,
	enableCustomColor: Boolean,
	useTint: Boolean,
	color: Color
) {
	var red = tinted.red
	var green = tinted.green
	var blue = tinted.blue

	if (!enableCustomColor) {
		particle.setColor(red, green, blue)
		return
	}

	if (!useTint) {
		particle.setColor(color.red.toFloat() / 255, color.green.toFloat() / 255, color.blue.toFloat() / 255)
	} else {
		red = ((red + color.red.toFloat() / 255) / 2)
		green = ((green + color.red.toFloat() / 255) / 2)
		blue = ((blue + color.blue.toFloat() / 255) / 2)

		particle.setColor(red, green, blue)
	}
}
