package net.fellter.moreLeafParticles.mixin;

import net.fellter.moreLeafParticles.MoreLeafParticles;
import net.fellter.moreLeafParticles.config.ModConfig;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;

@SuppressWarnings("unused")
@Mixin(LeavesBlock.class)
abstract class LeavesBlockMixin extends Block {
	@Final
	@Mutable
	@Shadow
	protected float leafParticleChance;

	public LeavesBlockMixin(Properties properties) {
		super(properties);
	}

	@Inject(method = "makeFallingLeavesParticles", at = @At("HEAD"))
	private void fellter$modifyParticleProbability(Level level, BlockPos pos, RandomSource random, BlockState belowState, BlockPos below, CallbackInfo ci) {
		if (MoreLeafParticles.isYACLPresent()) {
			if (this == Blocks.OAK_LEAVES) {
				this.leafParticleChance = (float) ModConfig.oakMultiplier / 100;
			} else if (this == Blocks.SPRUCE_LEAVES) {
				this.leafParticleChance = (float) ModConfig.spruceMultiplier / 100;
			} else if (this == Blocks.BIRCH_LEAVES) {
				this.leafParticleChance = (float) ModConfig.birchMultiplier / 100;
			} else if (this == Blocks.JUNGLE_LEAVES) {
				this.leafParticleChance = (float) ModConfig.jungleMultiplier / 100;
			} else if (this == Blocks.ACACIA_LEAVES) {
				this.leafParticleChance = (float) ModConfig.acaciaMultiplier / 100;
			} else if (this == Blocks.DARK_OAK_LEAVES) {
				this.leafParticleChance = (float) ModConfig.darkOakMultiplier / 100;
			} else if (this == Blocks.MANGROVE_LEAVES) {
				this.leafParticleChance = (float) ModConfig.mangroveMultiplier / 100;
			} else if (this == Blocks.CHERRY_LEAVES) {
				this.leafParticleChance = (float) ModConfig.cherryMultiplier / 100;
			} else if (this == Blocks.PALE_OAK_LEAVES) {
				this.leafParticleChance = (float) ModConfig.paleOakMultiplier / 100;
			} else if (this == Blocks.AZALEA_LEAVES) {
				this.leafParticleChance = (float) ModConfig.azaleaMultiplier / 100;
			} else if (this == Blocks.FLOWERING_AZALEA_LEAVES) {
				this.leafParticleChance = (float) ModConfig.floweringAzaleaMultiplier / 100;
			}
		}
	}
}
