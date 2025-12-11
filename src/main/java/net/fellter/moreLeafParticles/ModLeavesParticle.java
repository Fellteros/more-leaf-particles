//~ not_null
//~ non_null_import

package net.fellter.moreLeafParticles;

import java.awt.*;

import org.jspecify.annotations.NonNull;
import org.jetbrains.annotations.Nullable;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import static net.fellter.moreLeafParticles.yacl.ModConfig.*;

@Environment(EnvType.CLIENT)
public class ModLeavesParticle {
	public static class SpruceLeavesFactory extends FallingLeavesParticle.TintedLeavesProvider {
		private final SpriteSet provider;

		public SpruceLeavesFactory(SpriteSet spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public @NonNull Particle createParticle(@NonNull ColorParticleOption tinted, @NonNull ClientLevel clientLevel, double d, double e, double f, double g, double h, double i/*? if >=1.21.10 {*/, @NonNull RandomSource random/*?}*/) {
			FallingLeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableSpruceSize ? spruceSize : 2F;
				float gravity = enableSpruceGravity ? spruceGravity : 0.07F;
				float initYVelocity = enableSpruceInitialVelocity ? spruceInitialVelocity : 0.021F;
				float wind = enableSpruceWind ? spruceWind : 10F;

				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, gravity, wind, true, spruceFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableSpruceCustomColor, useSpruceTint, spruceColor);
			} else {
				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, 0.07F, 10.0F, true, false, 2F, 0.021F);
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}

			return particle;
		}
	}

	public static class BirchLeavesFactory extends FallingLeavesParticle.TintedLeavesProvider {
		private final SpriteSet provider;

		public BirchLeavesFactory(SpriteSet spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public @NonNull Particle createParticle(@NonNull ColorParticleOption tinted, @NonNull ClientLevel clientLevel, double d, double e, double f, double g, double h, double i/*? if >=1.21.10 {*/, @NonNull RandomSource random/*?}*/) {
			FallingLeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableBirchSize ? birchSize : 2F;
				float gravity = enableBirchGravity ? birchGravity : 0.07F;
				float initYVelocity = enableBirchInitialVelocity ? birchInitialVelocity : 0.021F;
				float wind = enableBirchWind ? birchWind : 10F;

				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, gravity, wind, true, birchFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableBirchCustomColor, useBirchTint, birchColor);
			} else {
				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, 0.07F, 10.0F, true, false, 2F, 0.021F);
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}

			return particle;
		}
	}

	public static class MangroveLeavesFactory extends FallingLeavesParticle.TintedLeavesProvider {
		private final SpriteSet provider;

		public MangroveLeavesFactory(SpriteSet spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public @NonNull Particle createParticle(@NonNull ColorParticleOption tinted, @NonNull ClientLevel clientLevel, double d, double e, double f, double g, double h, double i/*? if >=1.21.10 {*/, @NonNull RandomSource random/*?}*/) {
			FallingLeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableMangroveSize ? mangroveSize : 2F;
				float gravity = enableMangroveGravity ? mangroveGravity : 0.07F;
				float initYVelocity = enableMangroveInitialVelocity ? mangroveInitialVelocity : 0.021F;
				float wind = enableMangroveWind ? mangroveWind : 10F;

				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, gravity, wind, true, mangroveFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableMangroveCustomColor, useMangroveTint, mangroveColor);
			} else {
				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, 0.07F, 10.0F, true, false, 2F, 0.021F);
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}

			return particle;
		}
	}

	public static class JungleLeavesFactory extends FallingLeavesParticle.TintedLeavesProvider {
		private final SpriteSet provider;

		public JungleLeavesFactory(SpriteSet spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public @NonNull Particle createParticle(@NonNull ColorParticleOption tinted, @NonNull ClientLevel clientLevel, double d, double e, double f, double g, double h, double i/*? if >=1.21.10 {*/, @NonNull RandomSource random/*?}*/) {
			FallingLeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableJungleSize ? jungleSize : 2F;
				float gravity = enableJungleGravity ? jungleGravity : 0.07F;
				float initYVelocity = enableJungleInitialVelocity ? jungleInitialVelocity : 0.021F;
				float wind = enableJungleWind ? jungleWind : 10F;

				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, gravity, wind, true, jungleFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableJungleCustomColor, useJungleTint, jungleColor);
			} else {
				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, 0.07F, 10.0F, true, false, 2F, 0.021F);
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}

			return particle;
		}
	}

	public static class AcaciaLeavesFactory extends FallingLeavesParticle.TintedLeavesProvider {
		private final SpriteSet provider;

		public AcaciaLeavesFactory(SpriteSet spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public @NonNull Particle createParticle(@NonNull ColorParticleOption tinted, @NonNull ClientLevel clientLevel, double d, double e, double f, double g, double h, double i/*? if >=1.21.10 {*/, @NonNull RandomSource random/*?}*/) {
			FallingLeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableAcaciaSize ? acaciaSize : 1.66F;
				float gravity = enableAcaciaGravity ? acaciaGravity : 0.07F;
				float initYVelocity = enableAcaciaInitialVelocity ? acaciaInitialVelocity : 0.021F;
				float wind = enableAcaciaWind ? acaciaWind : 10F;

				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, gravity, wind, true, acaciaFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableAcaciaCustomColor, useAcaciaTint, acaciaColor);
			} else {
				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, 0.07F, 10.0F, true, false, 1.66F, 0.021F);
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}

			return particle;
		}
	}

	public static class DarkOakLeavesFactory extends FallingLeavesParticle.TintedLeavesProvider {
		private final SpriteSet provider;

		public DarkOakLeavesFactory(SpriteSet spriteProvider) {
			super(spriteProvider);
			this.provider = spriteProvider;
		}

		@Override
		public @NonNull Particle createParticle(@NonNull ColorParticleOption tinted, @NonNull ClientLevel clientLevel, double d, double e, double f, double g, double h, double i/*? if >=1.21.10 {*/, @NonNull RandomSource random/*?}*/) {
			FallingLeavesParticle particle;

			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableDarkOakSize ? darkOakSize : 2F;
				float gravity = enableDarkOakGravity ? darkOakGravity : 0.07F;
				float initYVelocity = enableDarkOakInitialVelocity ? darkOakInitialVelocity : 0.021F;
				float wind = enableDarkOakWind ? darkOakWind : 10F;

				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, gravity, wind, true, darkOakFlowAway, size, initYVelocity);
				tintParticle(particle, tinted, enableDarkOakCustomColor, useDarkOakTint, darkOakColor);
			} else {
				particle = new FallingLeavesParticle(clientLevel, d, e, f, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, 0.07F, 10.0F, true, false, 2F, 0.021F);
				particle.setColor(tinted.getRed(), tinted.getGreen(), tinted.getBlue());
			}

			return particle;
		}
	}

	public static class AzaleaLeavesFactory implements ParticleProvider<@NonNull SimpleParticleType> {
		private final SpriteSet provider;

		public AzaleaLeavesFactory(SpriteSet provider) {
			this.provider = provider;
		}

		@Override
		public @Nullable Particle createParticle(SimpleParticleType parameters, @NonNull ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ/*? if >=1.21.10 {*/, @NonNull RandomSource random/*?}*/) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableAzaleaSize ? azaleaSize : 2.0F;
				float initVelocityY = enableAzaleaInitialVelocity ? azaleaInitialVelocity : 0.021F;
				float gravity = enableAzaleaGravity ? azaleaGravity : 0.07F;
				float wind = enableAzaleaWind ? azaleaWind : 10F;

				return new FallingLeavesParticle(world, x, y, z, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, gravity, wind, true, azaleaFlowAway, size, initVelocityY);
			} else {
				return new FallingLeavesParticle(world, x, y, z, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, 0.07F, 10.0F, true, false, 2.0F, 0.021F);
			}
		}
	}

	public static class FloweringAzaleaLeavesFactory implements ParticleProvider<@NonNull SimpleParticleType> {
		private final SpriteSet provider;

		public FloweringAzaleaLeavesFactory(SpriteSet provider) {
			this.provider = provider;
		}

		@Override
		public @Nullable Particle createParticle(SimpleParticleType parameters, @NonNull ClientLevel world, double x, double y, double z, double velocityX, double velocityY, double velocityZ/*? if >=1.21.10 {*/, @NonNull RandomSource random/*?}*/) {
			if (MoreLeafParticles.isYACLPresent()) {
				float size = enableFloweringAzaleaSize ? floweringAzaleaSize : 2.0F;
				float initVelocityY = enableFloweringAzaleaInitialVelocity ? floweringAzaleaInitialVelocity : 0.021F;
				float gravity = enableFloweringAzaleaGravity ? floweringAzaleaGravity : 0.07F;
				float wind = enableFloweringAzaleaWind ? floweringAzaleaWind : 10F;

				return new FallingLeavesParticle(world, x, y, z, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, gravity, wind, true, floweringAzaleaFlowAway, size, initVelocityY);
			} else {
				return new FallingLeavesParticle(world, x, y, z, /*? <=1.21.8 {*//*provider*//*?} else {*/ provider.get(random) /*?}*/, 0.07F, 10.0F, true, false, 2.0F, 0.021F);
			}
		}
	}

	public static void tintParticle(
			SingleQuadParticle particle,
			ColorParticleOption tinted,
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
}
