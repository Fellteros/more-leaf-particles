package net.fellter.moreLeafParticles.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.sugar.Local;
import net.fellter.moreLeafParticles.ModParticles;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.TintedParticleLeavesBlock;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

@Mixin(TintedParticleLeavesBlock.class)
public abstract class TintedParticleLeavesBlockMixin extends LeavesBlock {
	public TintedParticleLeavesBlockMixin(float leafParticleChance, Settings settings) {
		super(leafParticleChance, settings);
	}

	@Definition(id = "tintedParticleEffect", local = @Local(type = TintedParticleEffect.class))
	@Expression("tintedParticleEffect")
	@ModifyVariable(method = "spawnLeafParticle", at = @At("MIXINEXTRAS:EXPRESSION"))
	private TintedParticleEffect fellter$spawnLeafParticle(TintedParticleEffect value, World world, BlockPos pos) {
		if (this == Blocks.SPRUCE_LEAVES) {
			return TintedParticleEffect.create(ModParticles.SPRUCE_NEEDLES, world.getBlockColor(pos));
		} else if (this == Blocks.BIRCH_LEAVES) {
			return TintedParticleEffect.create(ModParticles.BIRCH_LEAVES, world.getBlockColor(pos));
		} else if (this == Blocks.MANGROVE_LEAVES) {
			return TintedParticleEffect.create(ModParticles.MANGROVE_LEAVES, world.getBlockColor(pos));
		}

		return value;
	}
}
