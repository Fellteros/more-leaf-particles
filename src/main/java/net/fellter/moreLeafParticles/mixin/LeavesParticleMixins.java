package net.fellter.moreLeafParticles.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fellter.moreLeafParticles.ModLeavesParticle;
import net.fellter.moreLeafParticles.MoreLeafParticles;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.particle.LeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.particle.TintedParticleEffect;

import static net.fellter.moreLeafParticles.yacl.ModConfig.*;

public final class LeavesParticleMixins {
	@Mixin(LeavesParticle.TintedLeavesFactory.class)
	public static class TintedLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteProvider spriteProvider;

		@WrapMethod(method = "createParticle(Lnet/minecraft/particle/TintedParticleEffect;Lnet/minecraft/client/world/ClientWorld;DDDDDD)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyOak(TintedParticleEffect tintedParticleEffect, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Operation<Particle> original) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableOakSize ? oakSize : 2.0F;
				float gravity = enableOakGravity ? oakGravity : 0.07F;
				float initVelocityY = enableOakInitialVelocity ? oakInitialVelocity : 0.021F;
				float wind = enableOakWind ? oakWind : 10.0F;

				LeavesParticle particle = new LeavesParticle(clientWorld, d, e, f, spriteProvider, gravity, wind, true, oakFlowAway, size, initVelocityY);
				ModLeavesParticle.tintParticle(particle, tintedParticleEffect, enableOakCustomColor, useOakTint, oakColor);
				return particle;
			} else {
				return original.call(tintedParticleEffect, clientWorld, d, e, f, g, h, i);
			}
		}
	}

	@Mixin(LeavesParticle.PaleOakLeavesFactory.class)
	public static class PaleOakLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteProvider spriteProvider;

		@WrapMethod(method = "createParticle(Lnet/minecraft/particle/SimpleParticleType;Lnet/minecraft/client/world/ClientWorld;DDDDDD)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyPaleOak(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Operation<Particle> original) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enablePaleOakSize ? paleOakSize : 2.0F;
				float gravity = enablePaleOakGravity ? paleOakGravity : 0.07F;
				float initVelocityY = enablePaleOakInitialVelocity ? paleOakInitialVelocity : 0.021F;
				float wind = enablePaleOakWind ? paleOakWind : 10F;

				return new LeavesParticle(clientWorld, d, e, f, spriteProvider, gravity, wind, true, paleOakFlowAway, size, initVelocityY);
			} else {
				return original.call(simpleParticleType, clientWorld, d, e, f, g, h, i);
			}
		}
	}

	@Mixin(LeavesParticle.CherryLeavesFactory.class)
	public static class CherryLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteProvider spriteProvider;

		@WrapMethod(method = "createParticle(Lnet/minecraft/particle/SimpleParticleType;Lnet/minecraft/client/world/ClientWorld;DDDDDD)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyCherry(SimpleParticleType simpleParticleType, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Operation<Particle> original) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableCherrySize ? cherrySize : 2.0F;
				float gravity = enableCherryGravity ? cherryGravity : 0.07F;
				float initVelocityY = enableCherryInitialVelocity ? cherryInitialVelocity : 0.021F;
				float wind = enableCherryWind ? cherryWind : 10F;

				return new LeavesParticle(clientWorld, d, e, f, spriteProvider, gravity, wind, true, cherryFlowAway, size, initVelocityY);
			} else {
				return original.call(simpleParticleType, clientWorld, d, e, f, g, h, i);
			}
		}
	}
}