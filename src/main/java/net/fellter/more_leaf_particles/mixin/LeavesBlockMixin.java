package net.fellter.more_leaf_particles.mixin;

import net.fellter.more_leaf_particles.MoreLeafParticles;
import net.fellter.more_leaf_particles.config.ConfigFields;
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
				this.leafParticleChance = (float) ConfigFields.oakMultiplier / 100;
			} else if (this == Blocks.SPRUCE_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.spruceMultiplier / 100;
			} else if (this == Blocks.BIRCH_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.birchMultiplier / 100;
			} else if (this == Blocks.JUNGLE_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.jungleMultiplier / 100;
			} else if (this == Blocks.ACACIA_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.acaciaMultiplier / 100;
			} else if (this == Blocks.DARK_OAK_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.darkOakMultiplier / 100;
			} else if (this == Blocks.MANGROVE_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.mangroveMultiplier / 100;
			} else if (this == Blocks.CHERRY_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.cherryMultiplier / 100;
			} else if (this == Blocks.PALE_OAK_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.paleOakMultiplier / 100;
			} else if (this == Blocks.AZALEA_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.azaleaMultiplier / 100;
			} else if (this == Blocks.FLOWERING_AZALEA_LEAVES) {
				this.leafParticleChance = (float) ConfigFields.floweringAzaleaMultiplier / 100;
			}
		}
	}
}
