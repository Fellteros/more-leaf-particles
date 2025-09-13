package net.fellter.moreLeafParticles.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.fellter.moreLeafParticles.ModParticles;
import net.fellter.moreLeafParticles.MoreLeafParticles;
import net.fellter.moreLeafParticles.yacl.ModConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.TintedParticleLeavesBlock;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

@Mixin(TintedParticleLeavesBlock.class)
public abstract class TintedParticleLeavesBlockMixin extends LeavesBlock {
	public TintedParticleLeavesBlockMixin(float leafParticleChance, Settings settings) {
		super(leafParticleChance, settings);
	}

	@WrapOperation(method = "spawnLeafParticle", at = @At(value = "INVOKE", target = "Lnet/minecraft/particle/ParticleUtil;spawnParticle(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/particle/ParticleEffect;)V"))
	private void fellter$spawnLeafParticle(World world, BlockPos pos, Random random, ParticleEffect effect, Operation<Void> original) {
		if (MoreLeafParticles.isYACLPresent()) {
			if (this == Blocks.SPRUCE_LEAVES) {
				effect = ModConfig.enableSpruce ? TintedParticleEffect.create(ModParticles.SPRUCE_NEEDLES, world.getBlockColor(pos)) : null;
			} else if (this == Blocks.BIRCH_LEAVES) {
				effect = ModConfig.enableBirch ? TintedParticleEffect.create(ModParticles.BIRCH_LEAVES, world.getBlockColor(pos)) : null;
			} else if (this == Blocks.MANGROVE_LEAVES) {
				effect = ModConfig.enableMangrove ? TintedParticleEffect.create(ModParticles.MANGROVE_LEAVES, world.getBlockColor(pos)) : null;
			} else if (this == Blocks.JUNGLE_LEAVES) {
				effect = ModConfig.enableJungle ? TintedParticleEffect.create(ModParticles.JUNGLE_LEAVES, world.getBlockColor(pos)) : null;
			} else if (this == Blocks.ACACIA_LEAVES) {
				effect = ModConfig.enableAcacia ? TintedParticleEffect.create(ModParticles.ACACIA_LEAVES, world.getBlockColor(pos)) : null;
			} else if (this == Blocks.DARK_OAK_LEAVES) {
				effect = ModConfig.enableDarkOak ? TintedParticleEffect.create(ModParticles.DARK_OAK_LEAVES, world.getBlockColor(pos)) : null;
			} else if (this == Blocks.OAK_LEAVES) {
				effect = ModConfig.enableOak ? TintedParticleEffect.create(ParticleTypes.TINTED_LEAVES, world.getBlockColor(pos)) : null;
			}

			if (effect != null) {
				original.call(world, pos, random, effect);
			}
		} else {
			if (this == Blocks.SPRUCE_LEAVES) {
				effect = TintedParticleEffect.create(ModParticles.SPRUCE_NEEDLES, world.getBlockColor(pos));
			} else if (this == Blocks.BIRCH_LEAVES) {
				effect = TintedParticleEffect.create(ModParticles.BIRCH_LEAVES, world.getBlockColor(pos));
			} else if (this == Blocks.MANGROVE_LEAVES) {
				effect = TintedParticleEffect.create(ModParticles.MANGROVE_LEAVES, world.getBlockColor(pos));
			} else if (this == Blocks.JUNGLE_LEAVES) {
				effect = TintedParticleEffect.create(ModParticles.JUNGLE_LEAVES, world.getBlockColor(pos));
			} else if (this == Blocks.ACACIA_LEAVES) {
				effect = TintedParticleEffect.create(ModParticles.ACACIA_LEAVES, world.getBlockColor(pos));
			} else if (this == Blocks.DARK_OAK_LEAVES) {
				effect = TintedParticleEffect.create(ModParticles.DARK_OAK_LEAVES, world.getBlockColor(pos));
			}

			original.call(world, pos, random, effect);
		}
	}
}
