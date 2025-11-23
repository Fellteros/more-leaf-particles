package net.fellter.moreLeafParticles.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fellter.moreLeafParticles.ModLeavesParticle;
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
import net.minecraft.util.RandomSource;

import static net.fellter.moreLeafParticles.yacl.ModConfig.*;

final class LeavesParticleMixins {
	@Mixin(FallingLeavesParticle.TintedLeavesProvider.class)
	abstract static class TintedLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteSet sprites;

		//? if <=1.21.8 {
		/*@WrapMethod(method = "createParticle(Lnet/minecraft/core/particles/ColorParticleOption;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyOak(ColorParticleOption tintedParticleEffect, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, Operation<Particle> original) {
		 *///?} else {
		@WrapMethod(method = "createParticle(Lnet/minecraft/core/particles/ColorParticleOption;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDDLnet/minecraft/util/RandomSource;)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyOak(ColorParticleOption tintedParticleEffect, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, RandomSource random, Operation<Particle> original) { //?}
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableOakSize ? oakSize : 2.0F;
				float gravity = enableOakGravity ? oakGravity : 0.07F;
				float initVelocityY = enableOakInitialVelocity ? oakInitialVelocity : 0.021F;
				float wind = enableOakWind ? oakWind : 10.0F;

				FallingLeavesParticle particle = new FallingLeavesParticle(clientWorld, d, e, f, /*? if <=1.21.8 {*//*sprites*//*?} else {*/ sprites.get(random) /*?}*/, gravity, wind, true, oakFlowAway, size, initVelocityY);
				ModLeavesParticle.tintParticle(particle, tintedParticleEffect, enableOakCustomColor, useOakTint, oakColor);
				return particle;
			} else {
				return original.call(tintedParticleEffect, clientWorld, d, e, f, g, h, i/*? if >=1.21.10 {*/, random/*?}*/);
			}
		}
	}

	@Mixin(FallingLeavesParticle.PaleOakProvider.class)
	abstract static class PaleOakLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteSet sprites;

		//? if <=1.21.8 {
		/*@WrapMethod(method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyPaleOak(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, Operation<Particle> original) {
		*///?} else {
		@WrapMethod(method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDDLnet/minecraft/util/RandomSource;)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyPaleOak(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, RandomSource random, Operation<Particle> original) {//?}
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enablePaleOakSize ? paleOakSize : 2.0F;
				float gravity = enablePaleOakGravity ? paleOakGravity : 0.07F;
				float initVelocityY = enablePaleOakInitialVelocity ? paleOakInitialVelocity : 0.021F;
				float wind = enablePaleOakWind ? paleOakWind : 10F;

				return new FallingLeavesParticle(clientWorld, d, e, f, /*? <=1.21.8 {*//*sprites*//*?} else {*/ sprites.get(random) /*?}*/, gravity, wind, true, paleOakFlowAway, size, initVelocityY);
			} else {
				return original.call(simpleParticleType, clientWorld, d, e, f, g, h, i/*? if >=1.21.10 {*/, random/*?}*/);
			}
		}
	}

	@Mixin(FallingLeavesParticle.CherryProvider.class)
	abstract static class CherryLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteSet sprites;

		//? if <=1.21.8 {
		/*@WrapMethod(method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDD)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyCherry(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, Operation<Particle> original) {
		*///?} else {
		@WrapMethod(method = "createParticle(Lnet/minecraft/core/particles/SimpleParticleType;Lnet/minecraft/client/multiplayer/ClientLevel;DDDDDDLnet/minecraft/util/RandomSource;)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyCherry(SimpleParticleType simpleParticleType, ClientLevel clientWorld, double d, double e, double f, double g, double h, double i, RandomSource random, Operation<Particle> original) { //?}
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableCherrySize ? cherrySize : 2.0F;
				float gravity = enableCherryGravity ? cherryGravity : 0.07F;
				float initVelocityY = enableCherryInitialVelocity ? cherryInitialVelocity : 0.021F;
				float wind = enableCherryWind ? cherryWind : 10F;

				return new FallingLeavesParticle(clientWorld, d, e, f, /*? <=1.21.8 {*//*sprites*//*?} else {*/ sprites.get(random) /*?}*/, gravity, wind, true, cherryFlowAway, size, initVelocityY);
			} else {
				return original.call(simpleParticleType, clientWorld, d, e, f, g, h, i/*? if >=1.21.10 {*/, random/*?}*/);
			}
		}
	}
}