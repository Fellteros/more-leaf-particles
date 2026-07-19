package net.fellter.more_leaf_particles.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fellter.more_leaf_particles.MoreLeafParticles;
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

import static net.fellter.more_leaf_particles.ModParticleFactoriesKt.tintParticle;
import static net.fellter.more_leaf_particles.config.ConfigFields.*;

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
				FallingLeavesParticle particle = new FallingLeavesParticle(
						level,
						x, y, z,
						/*? if <=1.21.8 {*//*sprites*//*?} else {*/ sprites.get(random) /*?}*/,
						oakGravity,
						oakWind,
						true,
						oakFlowAway,
						oakSize,
						oakInitialVelocity
				);
				tintParticle(
						particle,
						options,
						enableOakCustomColor,
						useOakTint,
						oakColor
				);
				particle.more_leaf_particles$setParent(options.more_leaf_particles$getParent());
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
				FallingLeavesParticle particle = new FallingLeavesParticle(
						level, x, y, z,
						/*? <=1.21.8 {*//*sprites*//*?} else {*/ sprites.get(random) /*?}*/,
						paleOakGravity,
						paleOakWind,
						true,
						paleOakFlowAway,
						paleOakSize,
						paleOakInitialVelocity
				);
				particle.more_leaf_particles$setParent(options.more_leaf_particles$getParent());
				return particle;
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
				FallingLeavesParticle particle = new FallingLeavesParticle(
						level,
						x, y, z,
						/*? <=1.21.8 {*//*sprites*//*?} else {*/ sprites.get(random) /*?}*/,
						cherryGravity,
						cherryWind,
						true,
						cherryFlowAway,
						cherrySize,
						cherryInitialVelocity
				);
				particle.more_leaf_particles$setParent(options.more_leaf_particles$getParent());
				return particle;
			} else {
				return original.call(options, level, x, y, z, xAux, yAux, zAux/*? if >=1.21.10 {*/, random/*?}*/);
			}
		}
	}
}