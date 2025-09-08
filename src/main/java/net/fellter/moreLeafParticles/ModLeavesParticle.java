package net.fellter.moreLeafParticles;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.particle.LeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModLeavesParticle extends LeavesParticle {
	public ModLeavesParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, float gravity, float f, boolean bl, boolean bl2, float size, float initialYVelocity) {
		super(world, x, y, z, spriteProvider, gravity, f, bl, bl2, size, initialYVelocity);
	}

	@Environment(EnvType.CLIENT)
	public static class SimpleLeavesFactory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;

		public SimpleLeavesFactory(SpriteProvider provider) {
			this.spriteProvider = provider;
		}

		@Override
		public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			return new ModLeavesParticle(world, x, y, z, this.spriteProvider, 0.07F, 10.0F, true, false, 2.0F, 0.021F);
		}
	}
}
