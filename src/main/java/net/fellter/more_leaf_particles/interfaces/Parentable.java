package net.fellter.more_leaf_particles.interfaces;

import org.jspecify.annotations.Nullable;

public interface Parentable<P> {
	default void more_leaf_particles$setParent(@Nullable P parent) {}

	@Nullable
	default P more_leaf_particles$getParent() {
		return null;
	}
}
