package net.fellter.moreLeafParticles.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fellter.moreLeafParticles.ModParticles;
import net.fellter.moreLeafParticles.MoreLeafParticles;
import net.fellter.moreLeafParticles.config.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.UntintedParticleLeavesBlock;

@SuppressWarnings("unused")
@Mixin(UntintedParticleLeavesBlock.class)
abstract class UntintedParticleLeavesBlockMixin extends LeavesBlock {
	private UntintedParticleLeavesBlockMixin(float leafParticleChance, Properties settings) {
		super(leafParticleChance, settings);
	}

	@WrapOperation(method = "spawnFallingLeavesParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ParticleUtils;spawnParticleBelow(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/particles/ParticleOptions;)V"))
	private void fellter$spawnLeafParticle(Level level, BlockPos blockPos, RandomSource randomSource, ParticleOptions effect, Operation<Void> original) {
		if (MoreLeafParticles.isYACLPresent()) {
			if (this == Blocks.AZALEA_LEAVES) {
				effect = ModConfig.enableAzalea ? ModParticles.AZALEA_LEAVES : null;
			} else if (this == Blocks.FLOWERING_AZALEA_LEAVES) {
				effect = ModConfig.enableFloweringAzalea ? ModParticles.FLOWERING_AZALEA_PARTICLES : null;
			} else if (this == Blocks.CHERRY_LEAVES) {
				effect = ModConfig.enableCherry ? ParticleTypes.CHERRY_LEAVES : null;
			} else if (this == Blocks.PALE_OAK_LEAVES) {
				effect = ModConfig.enablePaleOak ? ParticleTypes.PALE_OAK_LEAVES : null;
			}

			if (effect != null) {
				original.call(level, blockPos, randomSource, effect);
			}
		} else {
			if (this == Blocks.AZALEA_LEAVES) {
				effect = ModParticles.AZALEA_LEAVES;
			} else if (this == Blocks.FLOWERING_AZALEA_LEAVES) {
				effect = ModParticles.FLOWERING_AZALEA_PARTICLES;
			}

			original.call(level, blockPos, randomSource, effect);
		}
	}
}
