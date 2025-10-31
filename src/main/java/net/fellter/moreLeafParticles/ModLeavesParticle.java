package net.fellter.moreLeafParticles;

import java.awt.*;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.util.math.random.Random;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static net.fellter.moreLeafParticles.yacl.ModConfig.*;

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
			LeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableSpruceSize ? spruceSize : 2F;
				float gravity = enableSpruceGravity ? spruceGravity : 0.07F;
				float initYVelocity = enableSpruceInitialVelocity ? spruceInitialVelocity : 0.021F;
				float wind = enableSpruceWind ? spruceWind : 10F;

				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), gravity, wind, true, spruceFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableSpruceCustomColor, useSpruceTint, spruceColor);
			} else {
				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), 0.07F, 10.0F, true, false, 2F, 0.021F);
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
			LeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableBirchSize ? birchSize : 2F;
				float gravity = enableBirchGravity ? birchGravity : 0.07F;
				float initYVelocity = enableBirchInitialVelocity ? birchInitialVelocity : 0.021F;
				float wind = enableBirchWind ? birchWind : 10F;

				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), gravity, wind, true, birchFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableBirchCustomColor, useBirchTint, birchColor);
			} else {
				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), 0.07F, 10.0F, true, false, 2F, 0.021F);
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
			LeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableMangroveSize ? mangroveSize : 2F;
				float gravity = enableMangroveGravity ? mangroveGravity : 0.07F;
				float initYVelocity = enableMangroveInitialVelocity ? mangroveInitialVelocity : 0.021F;
				float wind = enableMangroveWind ? mangroveWind : 10F;

				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), gravity, wind, true, mangroveFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableMangroveCustomColor, useMangroveTint, mangroveColor);
			} else {
				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), 0.07F, 10.0F, true, false, 2F, 0.021F);
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
			LeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableJungleSize ? jungleSize : 2F;
				float gravity = enableJungleGravity ? jungleGravity : 0.07F;
				float initYVelocity = enableJungleInitialVelocity ? jungleInitialVelocity : 0.021F;
				float wind = enableJungleWind ? jungleWind : 10F;

				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), gravity, wind, true, jungleFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableJungleCustomColor, useJungleTint, jungleColor);
			} else {
				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), 0.07F, 10.0F, true, false, 2F, 0.021F);
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
			LeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableAcaciaSize ? acaciaSize : 1.66F;
				float gravity = enableAcaciaGravity ? acaciaGravity : 0.07F;
				float initYVelocity = enableAcaciaInitialVelocity ? acaciaInitialVelocity : 0.021F;
				float wind = enableAcaciaWind ? acaciaWind : 10F;

				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), gravity, wind, true, acaciaFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableAcaciaCustomColor, useAcaciaTint, acaciaColor);
			} else {
				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), 0.07F, 10.0F, true, false, 1.66F, 0.021F);
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
			LeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableDarkOakSize ? darkOakSize : 2F;
				float gravity = enableDarkOakGravity ? darkOakGravity : 0.07F;
				float initYVelocity = enableDarkOakInitialVelocity ? darkOakInitialVelocity : 0.021F;
				float wind = enableDarkOakWind ? darkOakWind : 10F;

				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), gravity, wind, true, darkOakFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableDarkOakCustomColor, useDarkOakTint, darkOakColor);
			} else {
				particle = new LeavesParticle(clientWorld, d, e, f, provider.getSprite(random), 0.07F, 10.0F, true, false, 2F, 0.021F);
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
				float size = enableAzaleaSize ? azaleaSize : 2.0F;
				float initVelocityY = enableAzaleaInitialVelocity ? azaleaInitialVelocity : 0.021F;
				float gravity = enableAzaleaGravity ? azaleaGravity : 0.07F;
				float wind = enableAzaleaWind ? azaleaWind : 10F;

				return new LeavesParticle(world, x, y, z, spriteProvider.getSprite(random), gravity, wind, true, azaleaFlowAway, size, initVelocityY);
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
				float size = enableFloweringAzaleaSize ? floweringAzaleaSize : 2.0F;
				float initVelocityY = enableFloweringAzaleaInitialVelocity ? floweringAzaleaInitialVelocity : 0.021F;
				float gravity = enableFloweringAzaleaGravity ? floweringAzaleaGravity : 0.07F;
				float wind = enableFloweringAzaleaWind ? floweringAzaleaWind : 10F;

				return new LeavesParticle(world, x, y, z, spriteProvider.getSprite(random), gravity, wind, true, floweringAzaleaFlowAway, size, initVelocityY);
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
