package net.fellter.moreLeafParticles

import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fellter.moreLeafParticles.MoreLeafParticles.Companion.isYACLPresent
import net.fellter.moreLeafParticles.config.ModConfig
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
			val size = if (ModConfig.enableSpruceSize) ModConfig.spruceSize else 2f
			val gravity = if (ModConfig.enableSpruceGravity) ModConfig.spruceGravity else 0.07f
			val initYVelocity = if (ModConfig.enableSpruceInitialVelocity) ModConfig.spruceInitialVelocity else 0.021f
			val wind = if (ModConfig.enableSpruceWind) ModConfig.spruceWind else 10f

			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/gravity, wind, true, ModConfig.spruceFlowAway, size, initYVelocity).also {
				tintParticle(it, tinted, ModConfig.enableSpruceCustomColor, ModConfig.useSpruceTint, ModConfig.spruceColor)
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
			val size = if (ModConfig.enableBirchSize) ModConfig.birchSize else 2f
			val gravity = if (ModConfig.enableBirchGravity) ModConfig.birchGravity else 0.07f
			val initYVelocity = if (ModConfig.enableBirchInitialVelocity) ModConfig.birchInitialVelocity else 0.021f
			val wind = if (ModConfig.enableBirchWind) ModConfig.birchWind else 10f

			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/gravity, wind, true, ModConfig.birchFlowAway, size, initYVelocity).also {
				tintParticle(it, tinted, ModConfig.enableBirchCustomColor, ModConfig.useBirchTint, ModConfig.birchColor)
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
			val size = if (ModConfig.enableMangroveSize) ModConfig.mangroveSize else 2f
			val gravity = if (ModConfig.enableMangroveGravity) ModConfig.mangroveGravity else 0.07f
			val initYVelocity = if (ModConfig.enableMangroveInitialVelocity) ModConfig.mangroveInitialVelocity else 0.021f
			val wind = if (ModConfig.enableMangroveWind) ModConfig.mangroveWind else 10f

			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/gravity, wind, true, ModConfig.mangroveFlowAway, size, initYVelocity).also {
				tintParticle(it, tinted, ModConfig.enableMangroveCustomColor, ModConfig.useMangroveTint, ModConfig.mangroveColor)
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
			val size = if (ModConfig.enableJungleSize) ModConfig.jungleSize else 2f
			val gravity = if (ModConfig.enableJungleGravity) ModConfig.jungleGravity else 0.07f
			val initYVelocity = if (ModConfig.enableJungleInitialVelocity) ModConfig.jungleInitialVelocity else 0.021f
			val wind = if (ModConfig.enableJungleWind) ModConfig.jungleWind else 10f

			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/ gravity, wind, true, ModConfig.jungleFlowAway, size, initYVelocity).also {
				tintParticle(it, tinted, ModConfig.enableJungleCustomColor, ModConfig.useJungleTint, ModConfig.jungleColor)
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
			val size = if (ModConfig.enableAcaciaSize) ModConfig.acaciaSize else 1.66f
			val gravity = if (ModConfig.enableAcaciaGravity) ModConfig.acaciaGravity else 0.07f
			val initYVelocity = if (ModConfig.enableAcaciaInitialVelocity) ModConfig.acaciaInitialVelocity else 0.021f
			val wind = if (ModConfig.enableAcaciaWind) ModConfig.acaciaWind else 10f

			FallingLeavesParticle(clientLevel, d, e, f,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/gravity, wind, true, ModConfig.acaciaFlowAway, size, initYVelocity).also {
				tintParticle(it, tinted, ModConfig.enableAcaciaCustomColor, ModConfig.useAcaciaTint, ModConfig.acaciaColor)
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
			val size = if (ModConfig.enableDarkOakSize) ModConfig.darkOakSize else 2f
			val gravity = if (ModConfig.enableDarkOakGravity) ModConfig.darkOakGravity else 0.07f
			val initYVelocity = if (ModConfig.enableDarkOakInitialVelocity) ModConfig.darkOakInitialVelocity else 0.021f
			val wind = if (ModConfig.enableDarkOakWind) ModConfig.darkOakWind else 10f

			FallingLeavesParticle(
				clientLevel,
				d, e, f,
				/*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/
				gravity,
				wind, true, ModConfig.darkOakFlowAway, size, initYVelocity
			).also {
				tintParticle(it, tinted, ModConfig.enableDarkOakCustomColor, ModConfig.useDarkOakTint, ModConfig.darkOakColor)
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
		if (isYACLPresent) {
			val size = if (ModConfig.enableAzaleaSize) ModConfig.azaleaSize else 2.0f
			val initVelocityY = if (ModConfig.enableAzaleaInitialVelocity) ModConfig.azaleaInitialVelocity else 0.021f
			val gravity = if (ModConfig.enableAzaleaGravity) ModConfig.azaleaGravity else 0.07f
			val wind = if (ModConfig.enableAzaleaWind) ModConfig.azaleaWind else 10f

			return FallingLeavesParticle(world, x, y, z,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/ gravity, wind, true, ModConfig.azaleaFlowAway, size, initVelocityY)
		} else {
			return FallingLeavesParticle(world, x, y, z,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 2.0f, 0.021f)
		}
	}
}

@Environment(EnvType.CLIENT)
class FloweringAzaleaLeavesFactory(private val provider: SpriteSet) : ParticleProvider<SimpleParticleType> {
	override fun createParticle(parameters: SimpleParticleType, world: ClientLevel, x: Double, y: Double, z: Double, velocityX: Double, velocityY: Double, velocityZ: Double /*? if >1.21.8 {*/, random: RandomSource /*?}*/): Particle {
		if (isYACLPresent) {
			val size = if (ModConfig.enableFloweringAzaleaSize) ModConfig.floweringAzaleaSize else 2.0f
			val initVelocityY = if (ModConfig.enableFloweringAzaleaInitialVelocity) ModConfig.floweringAzaleaInitialVelocity else 0.021f
			val gravity = if (ModConfig.enableFloweringAzaleaGravity) ModConfig.floweringAzaleaGravity else 0.07f
			val wind = if (ModConfig.enableFloweringAzaleaWind) ModConfig.floweringAzaleaWind else 10f

			return FallingLeavesParticle(world, x, y, z,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/ gravity, wind, true, ModConfig.floweringAzaleaFlowAway, size, initVelocityY)
		} else {
			return FallingLeavesParticle(world, x, y, z,  /*? <=1.21.8 {*//*provider,  *//*?} else {*/ provider.get(random),  /*?}*/0.07f, 10.0f, true, false, 2.0f, 0.021f)
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
