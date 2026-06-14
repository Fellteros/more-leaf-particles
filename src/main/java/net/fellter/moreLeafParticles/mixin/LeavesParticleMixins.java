package net.fellter.moreLeafParticles.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fellter.moreLeafParticles.MoreLeafParticles;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.SimpleParticleType;
//? if >1.21.8
import net.minecraft.util.RandomSource;

import static net.fellter.moreLeafParticles.ModParticleFactoriesKt.tintParticle;
import static net.fellter.moreLeafParticles.config.ModConfig.*;

@SuppressWarnings("unused")
final class LeavesParticleMixins {
	@Mixin(FallingLeavesParticle.TintedLeavesProvider.class)
	abstract static class TintedLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteSet sprites;

		@WrapMethod(method = "createParticle(Lnet/minecraft/core/particles/ColorParticleOption;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD" /*? if >1.21.8 {*/ + "Lnet/minecraft/util/RandomSource;" /*?}*/ + ")Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyOak(ColorParticleOption options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, /*? if >1.21.8 {*/ RandomSource random, /*?}*/ Operation<Particle> original) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableOakSize ? oakSize : 2.0F;
				float gravity = enableOakGravity ? oakGravity : 0.07F;
				float initVelocityY = enableOakInitialVelocity ? oakInitialVelocity : 0.021F;
				float wind = enableOakWind ? oakWind : 10.0F;

				FallingLeavesParticle particle = new FallingLeavesParticle(level, x, y, z, /*? if <=1.21.8 {*//*sprites*//*?} else {*/ sprites.get(random) /*?}*/, gravity, wind, true, oakFlowAway, size, initVelocityY);
				tintParticle(particle, options, enableOakCustomColor, useOakTint, oakColor);
				return particle;
			} else {
				return original.call(options, level, x, y, z, xAux, yAux, zAux/*? if >=1.21.10 {*/, random/*?}*/);
			}
		}
	}

	@Mixin(FallingLeavesParticle.PaleOakProvider.class)
	abstract static class PaleOakLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteSet sprites;

		@WrapMethod(method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD" /*? if >1.21.8 {*/ + "Lnet/minecraft/util/RandomSource;" /*?}*/ + ")Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyPaleOak(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, /*? if >1.21.8 {*/ RandomSource random, /*?}*/ Operation<Particle> original) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enablePaleOakSize ? paleOakSize : 2.0F;
				float gravity = enablePaleOakGravity ? paleOakGravity : 0.07F;
				float initVelocityY = enablePaleOakInitialVelocity ? paleOakInitialVelocity : 0.021F;
				float wind = enablePaleOakWind ? paleOakWind : 10F;

				return new FallingLeavesParticle(level, x, y, z, /*? <=1.21.8 {*//*sprites*//*?} else {*/ sprites.get(random) /*?}*/, gravity, wind, true, paleOakFlowAway, size, initVelocityY);
			} else {
				return original.call(options, level, x, y, z, xAux, yAux, zAux/*? if >=1.21.10 {*/, random/*?}*/);
			}
		}
	}

	@Mixin(FallingLeavesParticle.CherryProvider.class)
	abstract static class CherryLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteSet sprites;

		@WrapMethod(method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD" /*? if >1.21.8 {*/ + "Lnet/minecraft/util/RandomSource;" /*?}*/ + ")Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyCherry(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, /*? if >1.21.8 {*/ RandomSource random, /*?}*/ Operation<Particle> original) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableCherrySize ? cherrySize : 2.0F;
				float gravity = enableCherryGravity ? cherryGravity : 0.07F;
				float initVelocityY = enableCherryInitialVelocity ? cherryInitialVelocity : 0.021F;
				float wind = enableCherryWind ? cherryWind : 10F;

				return new FallingLeavesParticle(level, x, y, z, /*? <=1.21.8 {*//*sprites*//*?} else {*/ sprites.get(random) /*?}*/, gravity, wind, true, cherryFlowAway, size, initVelocityY);
			} else {
				return original.call(options, level, x, y, z, xAux, yAux, zAux/*? if >=1.21.10 {*/, random/*?}*/);
			}
		}
	}
}