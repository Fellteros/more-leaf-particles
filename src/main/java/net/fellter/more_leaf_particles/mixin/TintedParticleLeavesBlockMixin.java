package net.fellter.more_leaf_particles.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fellter.more_leaf_particles.ModParticles;
import net.fellter.more_leaf_particles.MoreLeafParticles;
import net.fellter.more_leaf_particles.config.ConfigFields;
import net.fellter.more_leaf_particles.interfaces.Parentable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.TintedParticleLeavesBlock;

@SuppressWarnings({"unused", "unchecked", "MixinExtrasOperationParameters"})
@Mixin(TintedParticleLeavesBlock.class)
abstract class TintedParticleLeavesBlockMixin extends LeavesBlock {
	private TintedParticleLeavesBlockMixin(float leafParticleChance, Properties settings) {
		super(leafParticleChance, settings);
	}

	@WrapOperation(method = "spawnFallingLeavesParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ParticleUtils;spawnParticleBelow(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/particles/ParticleOptions;)V"))
	private void fellter$spawnLeafParticle(Level level, BlockPos blockPos, RandomSource randomSource, ParticleOptions effect, Operation<Void> original) {
		if (MoreLeafParticles.isYACLPresent()) {
			if (this == Blocks.SPRUCE_LEAVES) {
				effect = ConfigFields.enableSpruce ? ColorParticleOption.create(ModParticles.SPRUCE_NEEDLES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.BIRCH_LEAVES) {
				effect = ConfigFields.enableBirch ? ColorParticleOption.create(ModParticles.BIRCH_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.MANGROVE_LEAVES) {
				effect = ConfigFields.enableMangrove ? ColorParticleOption.create(ModParticles.MANGROVE_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.JUNGLE_LEAVES) {
				effect = ConfigFields.enableJungle ? ColorParticleOption.create(ModParticles.JUNGLE_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.ACACIA_LEAVES) {
				effect = ConfigFields.enableAcacia ? ColorParticleOption.create(ModParticles.ACACIA_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.DARK_OAK_LEAVES) {
				effect = ConfigFields.enableDarkOak ? ColorParticleOption.create(ModParticles.DARK_OAK_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos)) : null;
			} else if (this == Blocks.OAK_LEAVES) {
				effect = ConfigFields.enableOak ? ColorParticleOption.create(ParticleTypes.TINTED_LEAVES, level.getClientLeafTintColor(blockPos)) : null;
			}

			if (effect != null) {
				((Parentable<Block>) effect).more_leaf_particles$setParent(this);
				original.call(level, blockPos, randomSource, effect);
			}
		} else {
			if (this == Blocks.SPRUCE_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.SPRUCE_NEEDLES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.BIRCH_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.BIRCH_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.MANGROVE_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.MANGROVE_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.JUNGLE_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.JUNGLE_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.ACACIA_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.ACACIA_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos));
			} else if (this == Blocks.DARK_OAK_LEAVES) {
				effect = ColorParticleOption.create(ModParticles.DARK_OAK_LEAVES/*? if neoforge {*//*.get()*//*?}*/, level.getClientLeafTintColor(blockPos));
			}

			((Parentable<Block>) effect).more_leaf_particles$setParent(this);

			original.call(level, blockPos, randomSource, effect);
		}
	}
}
