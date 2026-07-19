package net.fellter.more_leaf_particles.mixin;

import net.fellter.more_leaf_particles.interfaces.Parentable;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.block.Block;

@Mixin(SimpleParticleType.class)
abstract class SimpleParticleTypeMixin implements Parentable<Block> {
	@Unique
	@Nullable
	private Block parent = null;

	@Override
	public void more_leaf_particles$setParent(@Nullable Block parent) {
		this.parent = parent;
	}

	@Override
	public @Nullable Block more_leaf_particles$getParent() {
		return this.parent;
	}
}
