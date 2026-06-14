package net.fellter.moreLeafParticles.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fellter.moreLeafParticles.ModParticles;
import net.fellter.moreLeafParticles.MoreLeafParticles;
import net.fellter.moreLeafParticles.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;

@SuppressWarnings("unused")
@Mixin(TintedParticleLeavesBlock.class)
abstract class TintedParticleLeavesBlockMixin extends LeavesBlock {
	private TintedParticleLeavesBlockMixin(float leafParticleChance, Properties settings) {
		super(leafParticleChance, settings);
	}

	@WrapOperation(method = "spawnFallingLeavesParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ParticleUtils;spawnParticleBelow(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/particles/ParticleOptions;)V"))
	private void fellter$spawnLeafParticle(Level level, BlockPos blockPos, RandomSource randomSource, ParticleOptions effect, Operation<Void> original) {
		if (MoreLeafParticles.isYACLPresent()) {
			if (this == Blocks.SPRUCE_LEAVES) {
				effect = ModConfig.enableSpruce ? ColorParticleOption.create(ModParticles.SPRUCE_NEEDLES, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.BIRCH_LEAVES) {
				effect = ModConfig.enableBirch ? ColorParticleOption.create(ModParticles.BIRCH_LEAVES, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.MANGROVE_LEAVES) {
				effect = ModConfig.enableMangrove ? ColorParticleOption.create(ModParticles.MANGROVE_LEAVES, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.JUNGLE_LEAVES) {
				effect = ModConfig.enableJungle ? ColorParticleOption.create(ModParticles.JUNGLE_LEAVES, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.ACACIA_LEAVES) {
				effect = ModConfig.enableAcacia ? ColorParticleOption.create(ModParticles.ACACIA_LEAVES, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.DARK_OAK_LEAVES) {
				effect = ModConfig.enableDarkOak ? ColorParticleOption.create(ModParticles.DARK_OAK_LEAVES, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.OAK_LEAVES) {
				effect = ModConfig.enableOak ? ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, level.getClientLeafTintColor(blockPos)) : null;
			}

			if (effect != null) {
				original.call(level, blockPos, randomSource, effect);
			}
		} else {
			if (this == Blocks.SPRUCE_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.SPRUCE_NEEDLES, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.BIRCH_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.BIRCH_LEAVES, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.MANGROVE_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.MANGROVE_LEAVES, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.JUNGLE_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.JUNGLE_LEAVES, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.ACACIA_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.ACACIA_LEAVES, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.DARK_OAK_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.DARK_OAK_LEAVES, level.getClientLeafTintColor(blockPos));
			}

			original.call(level, blockPos, randomSource, effect);
		}
	}
}
