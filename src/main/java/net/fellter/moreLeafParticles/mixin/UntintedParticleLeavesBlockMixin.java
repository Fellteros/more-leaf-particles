package net.fellter.moreLeafParticles.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fellter.moreLeafParticles.ModParticles;
import net.fellter.moreLeafParticles.MoreLeafParticles;
import net.fellter.moreLeafParticles.yacl.YACLImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.UntintedParticleLeavesBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

@Mixin(UntintedParticleLeavesBlock.class)
public abstract class UntintedParticleLeavesBlockMixin extends LeavesBlock {
	public UntintedParticleLeavesBlockMixin(float leafParticleChance, Settings settings) {
		super(leafParticleChance, settings);
	}

	@WrapOperation(method = "spawnLeafParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/particle/ParticleUtil;spawnParticle(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/particle/ParticleEffect;)V"))
	private void fellter$spawnLeafParticle(World world, BlockPos pos, Random random, ParticleEffect effect, Operation<Void> original) {
		if (MoreLeafParticles.isYACLPresent()) {
			ParticleEffect particle = YACLImpl.spawnParticleFromConfig(this);

			if (particle != null) {
				ParticleUtil.spawnParticle(world, pos, random, particle);
			} else {
				original.call(world, pos, random, effect);
			}

			return;
		}

		if (this == Blocks.AZALEA_LEAVES) {
			ParticleUtil.spawnParticle(world, pos, random, ModParticles.AZALEA_LEAVES);
		} else if (this == Blocks.FLOWERING_AZALEA_LEAVES) {
			ParticleUtil.spawnParticle(world, pos, random, ModParticles.FLOWERING_AZALEA_PARTICLES);
		} else {
			original.call(world, pos, random, effect);
		}
	}
}
