package net.fellter.moreLeafParticles.mixin;

import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.fellter.moreLeafParticles.ModLeavesParticle;
import net.fellter.moreLeafParticles.MoreLeafParticles;
import net.fellter.moreLeafParticles.yacl.ModConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.minecraft.client.particle.LeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.particle.TintedParticleEffect;

public class LeavesParticleMixins {
	@Mixin(LeavesParticle.TintedLeavesFactory.class)
	public static class TintedLeavesFactoryMixin {
		@Shadow
		@Final
		private SpriteProvider spriteProvider;

		@WrapMethod(method = "createParticle(Lnet/minecraft/particle/TintedParticleEffect;Lnet/minecraft/client/world/ClientWorld;DDDDDD)Lnet/minecraft/client/particle/Particle;")
		private Particle fellter$modifyOak(TintedParticleEffect tintedParticleEffect, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Operation<Particle> original) {
			if (MoreLeafParticles.isYACLPresent()) {
				Particle particle = new ModLeavesParticle.Builder(clientWorld, spriteProvider, d, e, f)
						.gravity(ModConfig.oakGravity)
						.initVelocityY(ModConfig.oakInitialVelocity)
						.size(ModConfig.oakSize)
						.build(ModConfig.enableOakGravity, ModConfig.enableOakSize, ModConfig.enableOakInitialVelocity);
				ModLeavesParticle.tintParticle(particle, tintedParticleEffect, ModConfig.enableSpruceCustomColor, ModConfig.useSpruceTint, ModConfig.spruceColor);
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
				float size = ModConfig.enablePaleOakSize ? ModConfig.paleOakSize : 2.0F;
				float gravity = ModConfig.enablePaleOakGravity ? ModConfig.paleOakGravity : 0.07F;
				float initVelocityY = ModConfig.enablePaleOakInitialVelocity ? ModConfig.paleOakInitialVelocity : 0.021F;

				return new LeavesParticle(clientWorld, d, e, f, spriteProvider, gravity, 10.0F, true, false, size, initVelocityY);
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
				float size = ModConfig.enableCherrySize ? ModConfig.cherrySize : 2.0F;
				float gravity = ModConfig.enableCherryGravity ? ModConfig.cherryGravity : 0.07F;
				float initVelocityY = ModConfig.enableCherryInitialVelocity ? ModConfig.cherryInitialVelocity : 0.021F;

				return new LeavesParticle(clientWorld, d, e, f, spriteProvider, gravity, 10.0F, true, false, size, initVelocityY);
			} else {
				return original.call(simpleParticleType, clientWorld, d, e, f, g, h, i);
			}
		}
	}
}
