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
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;

@SuppressWarnings({"unused", "unchecked", "MixinExtrasOperationParameters"})
@Mixin(UntintedParticleLeavesBlock.class)
abstract class UntintedParticleLeavesBlockMixin extends LeavesBlock {
	private UntintedParticleLeavesBlockMixin(float leafParticleChance, Properties settings) {
		super(leafParticleChance, settings);
	}

	@WrapOperation(method = "spawnFallingLeavesParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ParticleUtils;spawnParticleBelow(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/particles/ParticleOptions;)V"))
	private void fellter$spawnLeafParticle(Level level, BlockPos blockPos, RandomSource randomSource, ParticleOptions effect, Operation<Void> original) {
		if (MoreLeafParticles.isYACLPresent()) {
			if (this == Blocks.AZALEA_LEAVES) {
				effect = ConfigFields.enableAzalea ? ModParticles.AZALEA_LEAVES/*? if neoforge {*//*.get()*//*?}*/ : null;
			} else if (this == Blocks.FLOWERING_AZALEA_LEAVES) {
				effect = ConfigFields.enableFloweringAzalea ? ModParticles.FLOWERING_AZALEA_PARTICLES/*? if neoforge {*//*.get()*//*?}*/ : null;
			} else if (this == Blocks.CHERRY_LEAVES) {
				effect = ConfigFields.enableCherry ? ParticleTypes.CHERRY_LEAVES : null;
			} else if (this == Blocks.PALE_OAK_LEAVES) {
				effect = ConfigFields.enablePaleOak ? ParticleTypes.PALE_OAK_LEAVES : null;
			}

			if (effect != null) {
				((Parentable<Block>) effect).more_leaf_particles$setParent(this);
				original.call(level, blockPos, randomSource, effect);
			}
		} else {
			if (this == Blocks.AZALEA_LEAVES) {
				effect = ModParticles.AZALEA_LEAVES/*? if neoforge {*//*.get()*//*?}*/;
			} else if (this == Blocks.FLOWERING_AZALEA_LEAVES) {
				effect = ModParticles.FLOWERING_AZALEA_PARTICLES/*? if neoforge {*//*.get()*//*?}*/;
			}

			((Parentable<Block>) effect).more_leaf_particles$setParent(this);

			original.call(level, blockPos, randomSource, effect);
		}
	}
}
