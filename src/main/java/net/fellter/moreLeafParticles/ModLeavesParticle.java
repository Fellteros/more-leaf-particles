package net.fellter.moreLeafParticles;

import java.awt.*;

import net.fellter.moreLeafParticles.yacl.ModConfig;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.util.math.random.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class ModLeavesParticle {
	public static class SpruceLeavesFactory extends LeavesParticle.TintedLeavesFactory {
		private final SpriteProvider provider;

		public SpruceLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
			LeavesParticle particle = MoreLeafParticles.isYACLPresent()
					? new Builder(clientWorld, provider, d, e, f)
					.gravity(ModConfig.spruceGravity)
					.initVelocityY(ModConfig.spruceInitialVelocity)
					.size(ModConfig.spruceSize)
					.build(ModConfig.enableSpruceGravity, ModConfig.enableSpruceSize, ModConfig.enableSpruceInitialVelocity)
					: (LeavesParticle) super.createParticle(tinted, clientWorld, d, e, f, g, h, i, random);
			if (MoreLeafParticles.isYACLPresent()) {
				tintParticle(particle, tinted, ModConfig.enableSpruceCustomColor, ModConfig.useSpruceTint, ModConfig.spruceColor);
			} else {
				assert particle != null;
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}
			return particle;
		}
	}

	public static class BirchLeavesFactory extends LeavesParticle.TintedLeavesFactory {
		private final SpriteProvider provider;

		public BirchLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
			LeavesParticle particle = MoreLeafParticles.isYACLPresent()
					? new Builder(clientWorld, provider, d, e, f)
					.gravity(ModConfig.birchGravity)
					.initVelocityY(ModConfig.birchInitialVelocity)
					.size(ModConfig.birchSize)
					.build(ModConfig.enableBirchGravity, ModConfig.enableBirchSize, ModConfig.enableBirchInitialVelocity)
					: (LeavesParticle) super.createParticle(tinted, clientWorld, d, e, f, g, h, i, random);
			if (MoreLeafParticles.isYACLPresent()) {
				tintParticle(particle, tinted, ModConfig.enableBirchCustomColor, ModConfig.useBirchTint, ModConfig.birchColor);
			} else {
				assert particle != null;
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}
			return particle;
		}
	}

	public static class MangroveLeavesFactory extends LeavesParticle.TintedLeavesFactory {
		private final SpriteProvider provider;

		public MangroveLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
			LeavesParticle particle = MoreLeafParticles.isYACLPresent()
					? new Builder(clientWorld, provider, d, e, f)
					.gravity(ModConfig.mangroveGravity)
					.initVelocityY(ModConfig.mangroveInitialVelocity)
					.size(ModConfig.mangroveSize)
					.build(ModConfig.enableMangroveGravity, ModConfig.enableMangroveSize, ModConfig.enableMangroveInitialVelocity)
					: (LeavesParticle) super.createParticle(tinted, clientWorld, d, e, f, g, h, i, random);
			if (MoreLeafParticles.isYACLPresent()) {
				tintParticle(particle, tinted, ModConfig.enableMangroveCustomColor, ModConfig.useMangroveTint, ModConfig.mangroveColor);
			} else {
				assert particle != null;
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}
			return particle;
		}
	}

	public static class JungleLeavesFactory extends LeavesParticle.TintedLeavesFactory {
		private final SpriteProvider provider;

		public JungleLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
			LeavesParticle particle = MoreLeafParticles.isYACLPresent()
					? new Builder(clientWorld, provider, d, e, f)
					.gravity(ModConfig.jungleGravity)
					.initVelocityY(ModConfig.jungleInitialVelocity)
					.size(ModConfig.jungleSize)
					.build(ModConfig.enableJungleGravity, ModConfig.enableJungleSize, ModConfig.enableJungleInitialVelocity)
					: (LeavesParticle) super.createParticle(tinted, clientWorld, d, e, f, g, h, i, random);
			if (MoreLeafParticles.isYACLPresent()) {
				tintParticle(particle, tinted, ModConfig.enableJungleCustomColor, ModConfig.useJungleTint, ModConfig.jungleColor);
			} else {
				assert particle != null;
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}
			return particle;
		}
	}

	public static class AcaciaLeavesFactory extends LeavesParticle.TintedLeavesFactory {
		private final SpriteProvider provider;

		public AcaciaLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
			LeavesParticle particle = MoreLeafParticles.isYACLPresent()
					? new Builder(clientWorld, provider, d, e, f)
					.gravity(ModConfig.acaciaGravity)
					.initVelocityY(ModConfig.acaciaInitialVelocity)
					.size(ModConfig.acaciaSize)
					.build(ModConfig.enableAcaciaGravity, ModConfig.enableAcaciaSize, ModConfig.enableAcaciaInitialVelocity)
					: new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), 0.07F, 10.0F, true, false, 1.66F, 0.021F);
			if (MoreLeafParticles.isYACLPresent()) {
				tintParticle(particle, tinted, ModConfig.enableAcaciaCustomColor, ModConfig.useAcaciaTint, ModConfig.acaciaColor);
			} else {
				assert particle != null;
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}
			return particle;
		}
	}

	public static class DarkOakLeavesFactory extends LeavesParticle.TintedLeavesFactory {
		private final SpriteProvider provider;

		public DarkOakLeavesFactory(SpriteProvider spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public Particle createParticle(TintedParticleEffect tinted, ClientWorld clientWorld, double d, double e, double f, double g, double h, double i, Random random) {
			LeavesParticle particle = MoreLeafParticles.isYACLPresent()
					? new Builder(clientWorld, provider, d, e, f)
					.gravity(ModConfig.darkOakGravity)
					.initVelocityY(ModConfig.darkOakInitialVelocity)
					.size(ModConfig.darkOakSize)
					.build(ModConfig.enableDarkOakGravity, ModConfig.enableDarkOakSize, ModConfig.enableDarkOakInitialVelocity)
					: (LeavesParticle) super.createParticle(tinted, clientWorld, d, e, f, g, h, i, random);
			if (MoreLeafParticles.isYACLPresent()) {
				tintParticle(particle, tinted, ModConfig.enableDarkOakCustomColor, ModConfig.useDarkOakTint, ModConfig.darkOakColor);
			} else {
				assert particle != null;
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}
			return particle;
		}
	}

	public static class AzaleaLeavesFactory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;

		public AzaleaLeavesFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = ModConfig.enableAzaleaSize ? ModConfig.azaleaSize : 2.0F;
				float initVelocityY = ModConfig.enableAzaleaInitialVelocity ? ModConfig.azaleaInitialVelocity : 0.021F;
				float gravity = ModConfig.enableAzaleaGravity ? ModConfig.azaleaGravity : 0.07F;

				return new LeavesParticle(world, x, y, z, spriteProvider.getSprite(random), gravity, 10.0F, true, false, size, initVelocityY);
			} else {
				return new LeavesParticle(world, x, y, z, spriteProvider.getSprite(random), 0.07F, 10.0F, true, false, 2.0F, 0.021F);
			}
		}
	}

	public static class FloweringAzaleaLeavesFactory implements ParticleFactory<SimpleParticleType> {
		private final SpriteProvider spriteProvider;

		public FloweringAzaleaLeavesFactory(SpriteProvider spriteProvider) {
			this.spriteProvider = spriteProvider;
		}

		@Override
		public @Nullable Particle createParticle(SimpleParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ, Random random) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = ModConfig.enableFloweringAzaleaSize ? ModConfig.floweringAzaleaSize : 2.0F;
				float initVelocityY = ModConfig.enableFloweringAzaleaInitialVelocity ? ModConfig.floweringAzaleaInitialVelocity : 0.021F;
				float gravity = ModConfig.enableFloweringAzaleaGravity ? ModConfig.floweringAzaleaGravity : 0.07F;

				return new LeavesParticle(world, x, y, z, spriteProvider.getSprite(random), gravity, 10.0F, true, false, size, initVelocityY);
			} else {
				return new LeavesParticle(world, x, y, z, spriteProvider.getSprite(random), 0.07F, 10.0F, true, false, 2.0F, 0.021F);
			}
		}
	}

	public static void tintParticle(
			BillboardParticle particle,
			TintedParticleEffect tinted,
			boolean enableCustomColor,
			boolean useTint,
			Color color
	) {
		float red = tinted.getRed();
		float green = tinted.getGreen();
		float blue = tinted.getBlue();

		if (!enableCustomColor) {
			particle.setColor(red, green, blue);
			return;
		}

		if (!useTint) {
			particle.setColor((float) color.getRed() / 255, (float) color.getGreen() / 255, (float) color.getBlue() / 255);
		} else {
			red = ((red + (float) color.getRed() / 255) / 2);
			green = ((green + (float) color.getRed() / 255) / 2);
			blue = ((blue + (float) color.getBlue() / 255) / 2);

			particle.setColor(red, green, blue);
		}
	}

	public static class Builder {
		private final ClientWorld world;
		private final SpriteProvider provider;
		private final double x;
		private final double y;
		private final double z;
		private float gravity = 0.07F;
		private float initVelocityY = 0.021F;
		private float size = 2.0F;

		public Builder(ClientWorld world, SpriteProvider provider, double x, double y, double z) {
			this.world = world;
			this.provider = provider;
			this.x = x;
			this.y = y;
			this.z = z;
		}

		public LeavesParticle build(boolean enableGrav, boolean enableSize, boolean enableInitVelocityY) {
			float gravity = enableGrav ? this.gravity : 0.07F;
			float size = enableSize ? this.size : 2.0F;
			float initVelocityY = enableInitVelocityY ? this.initVelocityY : 0.021F;

			return new LeavesParticle(world, x, y, z, provider.getSprite(Random.createLocal()), gravity, 10.0F, true, false, size, initVelocityY);
		}

		public Builder gravity(float gravity) {
			this.gravity = gravity;
			return this;
		}

		public Builder initVelocityY(float initVelocityY) {
			this.initVelocityY = initVelocityY;
			return this;
		}

		public Builder size(float size) {
			this.size = size;
			return this;
		}
	}
}
