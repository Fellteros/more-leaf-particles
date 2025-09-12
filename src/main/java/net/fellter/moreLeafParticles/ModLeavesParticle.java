package net.fellter.moreLeafParticles;

import java.awt.*;

import net.fellter.moreLeafParticles.yacl.ModConfig;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.particle.LeavesParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.particle.TintedParticleEffect;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModLeavesParticle extends LeavesParticle {
	public ModLeavesParticle(ClientWorld world, double x, double y, double z, SpriteProvider spriteProvider, float gravity, float f, boolean bl, boolean bl2, float size, float initialYVelocity) {
		super(world, x, y, z, spriteProvider, gravity, f, bl, bl2, size, initialYVelocity);
	}

	public static class SpruceLeavesFactory extends TintedLeavesFactory {
		private final SpriteProvider provider;

		public SpruceLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			return createTintedParticle(tinted, clientWorld, provider, d, e, f, ModConfig.enableSpruceSize, ModConfig.spruceSize, ModConfig.enableSpruceCustomColor, ModConfig.useSpruceTint, ModConfig.spruceColor);
		}
	}

	public static class BirchLeavesFactory extends TintedLeavesFactory {
		private final SpriteProvider provider;

		public BirchLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			return createTintedParticle(tinted, clientWorld, provider, d, e, f, ModConfig.enableBirchSize, ModConfig.birchSize, ModConfig.enableBirchCustomColor, ModConfig.useBirchTint, ModConfig.birchColor);
		}
	}

	public static class MangroveLeavesFactory extends TintedLeavesFactory {
		private final SpriteProvider provider;

		public MangroveLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			return createTintedParticle(tinted, clientWorld, provider, d, e, f, ModConfig.enableMangroveSize, ModConfig.mangroveSize, ModConfig.enableMangroveCustomColor, ModConfig.useMangroveTint, ModConfig.mangroveColor);
		}
	}

	public static class JungleLeavesFactory extends TintedLeavesFactory {
		private final SpriteProvider provider;

		public JungleLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			return createTintedParticle(tinted, clientWorld, provider, d, e, f, ModConfig.enableJungleSize, ModConfig.jungleSize, ModConfig.enableJungleCustomColor, ModConfig.useJungleTint, ModConfig.jungleColor);
		}
	}

	public static class AcaciaLeavesFactory extends TintedLeavesFactory {
		private final SpriteProvider provider;

		public AcaciaLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i) {
			return createTintedParticle(tinted, clientWorld, provider, d, e, f, ModConfig.enableAcaciaSize, ModConfig.acaciaSize, ModConfig.enableAcaciaCustomColor, ModConfig.useAcaciaTint, ModConfig.acaciaColor);
		}
	}

	public static class AzaleaLeavesFactory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;

		public AzaleaLeavesFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = ModConfig.enableAzaleaSize ? ModConfig.azaleaSize : 2.0F;
				return new ModLeavesParticle(world, x, y, z, spriteProvider, 0.07F, 10.0F, true, false, size, 0.021F);
			} else {
				return new ModLeavesParticle(world, x, y, z, spriteProvider, 0.07F, 10.0F, true, false, 2.0F, 0.021F);
			}
		}
	}

	public static class FloweringAzaleaLeavesFactory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;

		public FloweringAzaleaLeavesFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = ModConfig.enableFloweringAzaleaSize ? ModConfig.floweringAzaleaSize : 2.0F;
				return new ModLeavesParticle(world, x, y, z, spriteProvider, 0.07F, 10.0F, true, false, size, 0.021F);
			} else {
				return new ModLeavesParticle(world, x, y, z, spriteProvider, 0.07F, 10.0F, true, false, 2.0F, 0.021F);
			}
		}
	}

	static Particle createTintedParticle(
			TintedParticleEffect tinted,
			ClientWorld clientWorld,
			final SpriteProvider provider,
			double d,
			double e,
			double f,
			boolean enableSize,
			float customSize,
			boolean enableCustomColor,
			boolean useTint,
			Color customColor
	) {
		if (MoreLeafParticles.isYACLPresent()) {
			float size = enableSize ? customSize : 2.0F;
			Particle particle = new ModLeavesParticle(clientWorld, d, e, f, provider, 0.07F, 10.0F, true, false, size, 0.021F);

			if (enableCustomColor) {
				if (useTint) {
					particle.setColor(
							Math.clamp((tinted.getRed() + customColor.getRed()) / 510, 0, 255),
							Math.clamp((tinted.getGreen() + customColor.getGreen()) / 510, 0, 255),
							Math.clamp((tinted.getBlue() + customColor.getBlue()) / 510, 0, 255)
					);
				} else {
					particle.setColor(
							(float) customColor.getRed() / 255,
							(float) customColor.getGreen() / 255,
							(float) customColor.getBlue() / 255
					);
				}
			} else {
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}

			return particle;
		} else {
			Particle particle = new ModLeavesParticle(clientWorld, d, e, f, provider, 0.07F, 10.0F, true, false, 2.0F, 0.021F);
			particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			return particle;
		}
	}
}
