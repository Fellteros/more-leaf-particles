package net.fellter.moreLeafParticles.mixin;

import net.fellter.moreLeafParticles.MoreLeafParticles;
import net.fellter.moreLeafParticles.yacl.ModConfig;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.FallingLeavesParticle;
//? if >1.21.8 {
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//?} else {
/*import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
*///?}
import net.minecraft.util.Mth;

@Mixin(FallingLeavesParticle.class)
abstract class FallingLeavesParticleMixin extends /*? if <=1.21.8 {*//*TextureSheetParticle*//*?} else {*/SingleQuadParticle/*?}*/ {
	@Unique
	private static double weatherMultiplier = 0.0;

	protected FallingLeavesParticleMixin(ClientLevel clientLevel, double d, double e, double f/*? if >1.21.8 {*/, TextureAtlasSprite sprite/*?}*/) {
		super(clientLevel, d, e, f/*? if >1.21.8 {*/, sprite/*?}*/);
	}

	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/FallingLeavesParticle;move(DDD)V"))
	private void fellter$modifyCoords(CallbackInfo ci) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
		if (MoreLeafParticles.isParticleRainPresent() && ModConfig.enableParticleRainWind) {
			double multiplierStep = 0.1 / ModConfig.ticksToBlend;
			// fuck reflection
			var config = Class.forName("pigcart.particlerain.config.ConfigManager").getDeclaredField("config").get(null);
			var wind = Class.forName("pigcart.particlerain.config.ConfigData").getDeclaredField("wind").get(config);
			var windClass = Class.forName("pigcart.particlerain.config.ConfigData$WindOptions");
			float frequency = (float) windClass.getDeclaredField("gustFrequency").get(wind);
			float shift = (int) Class.forName("pigcart.particlerain.ParticleRain").getDeclaredField("clientTicks").get(null) +
					(float) windClass.getDeclaredField("modulationSpeed").get(wind);
			float variance = (float) windClass.getDeclaredField("strengthVariance").get(wind);
			float strength = (float) windClass.getDeclaredField("strength").get(wind);

			float maxMtp = level.isThundering() ? 0.2F : 0.1F;

			if (level.isThundering() && weatherMultiplier < 0.2) {
				weatherMultiplier += multiplierStep;
			} else if (level.isRaining() && weatherMultiplier < 0.1) {
				weatherMultiplier += multiplierStep;
			} else if (!level.isRaining() && weatherMultiplier > 0) {
				weatherMultiplier -= multiplierStep;
			}

			if (level.isRaining() || weatherMultiplier > 0) {
				this.xd = (((Mth.abs(Mth.sin((float) x * frequency + shift)) * variance) + variance + strength) *
						(Math.clamp(weatherMultiplier, 0, maxMtp) * ModConfig.particleRainWindMultiplier)) + 0.001F;
				this.zd = (((Mth.abs(Mth.sin((float) z * frequency + shift)) * variance) + variance + strength) *
						(Math.clamp(weatherMultiplier, 0, maxMtp) * ModConfig.particleRainWindMultiplier)) + 0.001F;
			}
		}
	}

	//? if <=1.21.8 {
	/*@Unique
	public @NotNull ParticleRenderType getRenderType() {
		return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}
	*///?} else {
	@Unique
	@Override
	public @NotNull Layer getLayer() {
		return Layer.OPAQUE;
	}//?}
}

