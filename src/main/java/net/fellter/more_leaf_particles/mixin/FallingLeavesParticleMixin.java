package net.fellter.more_leaf_particles.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import net.fellter.more_leaf_particles.MoreLeafParticles;
import net.fellter.more_leaf_particles.config.ConfigFields;
import net.fellter.more_leaf_particles.interfaces.Parentable;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.multiplayer.ClientLevel;
//? if >1.21.8 {
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
//?} else
//import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.client.particle.FallingLeavesParticle;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

@SuppressWarnings("unused")
@Mixin(FallingLeavesParticle.class)
abstract class FallingLeavesParticleMixin extends /*? if <=1.21.8 {*//*TextureSheetParticle*//*?} else {*/SingleQuadParticle/*?}*/ implements Parentable<Block> {
	@Shadow
	private float rotSpeed;

	@Unique
	private static double weatherMultiplier = 0.0;

	@Unique
	private Block parent = null;

	protected FallingLeavesParticleMixin(ClientLevel clientLevel, double d, double e, double f/*? if >1.21.8 {*/, TextureAtlasSprite sprite/*?}*/) {
		super(clientLevel, d, e, f/*? if >1.21.8 {*/, sprite/*?}*/);
	}

	@Override
	public void more_leaf_particles$setParent(@Nullable Block parent) {
		this.parent = parent;
	}

	@Override
	public @Nullable Block more_leaf_particles$getParent() {
		return parent;
	}

	//? if fabric || <= 1.21.8 {
	@Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/particle/FallingLeavesParticle;move(DDD)V"))
	private void fellter$modifyCoords(CallbackInfo ci) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
		if (MoreLeafParticles.isParticleRainPresent() && ConfigFields.enableParticleRainWind) {
			double multiplierStep = 0.1 / ConfigFields.ticksToBlend;
			// fuck reflection
			var config = Class.forName("pigcart.particlerain.config.ConfigManager").getDeclaredField("config").get(null);
			var wind = Class.forName("pigcart.particlerain.config.ConfigData").getDeclaredField("wind").get(config);
			var windClass = Class.forName("pigcart.particlerain.config.ConfigData$WindOptions");
			float frequency = (float) windClass.getDeclaredField("gustFrequency").get(wind);
			float shift = /*? if <=1.21.5 {*//*(int) Class.forName("pigcart.particlerain.ParticleRain").getDeclaredField("clientTicks").get(null) + *//*?}*/
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
						(Math.clamp(weatherMultiplier, 0, maxMtp) * ConfigFields.particleRainWindMultiplier)) + 0.001F;
				this.zd = (((Mth.abs(Mth.sin((float) z * frequency + shift)) * variance) + variance + strength) *
						(Math.clamp(weatherMultiplier, 0, maxMtp) * ConfigFields.particleRainWindMultiplier)) + 0.001F;
			}
		}
	}
	//?}

	@Definition(id = "rotSpeed", field = "Lnet/minecraft/client/particle/FallingLeavesParticle;rotSpeed:F")
	@Expression("this.rotSpeed")
	@Inject(method = "tick", at = @At(value = "MIXINEXTRAS:EXPRESSION", shift = At.Shift.AFTER))
	private void more_leaf_particles$changeRotSpeed(CallbackInfo ci) {
		if (this.parent == Blocks.OAK_LEAVES && !ConfigFields.oakRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.BIRCH_LEAVES && !ConfigFields.birchRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.SPRUCE_LEAVES && !ConfigFields.spruceRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.JUNGLE_LEAVES && !ConfigFields.jungleRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.ACACIA_LEAVES && !ConfigFields.acaciaRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.MANGROVE_LEAVES && !ConfigFields.mangroveRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.DARK_OAK_LEAVES && !ConfigFields.darkOakRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.PALE_OAK_LEAVES && !ConfigFields.paleOakRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.CHERRY_LEAVES && !ConfigFields.cherryRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.AZALEA_LEAVES && !ConfigFields.azaleaRotate) {
			rotSpeed = 0F;
		} else if (this.parent == Blocks.FLOWERING_AZALEA_LEAVES && !ConfigFields.floweringAzaleaRotate) {
			rotSpeed = 0F;
		}
	}
}

