//~ not_null
//~ non_null_import

package net.fellter.moreLeafParticles;

import org.jspecify.annotations.NonNull;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

public final class ModParticles {
	public static final ParticleType<@NonNull ColorParticleOption> SPRUCE_NEEDLES = register("spruce_needles");
	public static final ParticleType<@NonNull ColorParticleOption> BIRCH_LEAVES = register("birch_leaves");
	public static final ParticleType<@NonNull ColorParticleOption> MANGROVE_LEAVES = register("mangrove_leaves");
	public static final ParticleType<@NonNull ColorParticleOption> JUNGLE_LEAVES = register("jungle_leaves");
	public static final ParticleType<@NonNull ColorParticleOption> ACACIA_LEAVES = register("acacia_leaves");
	public static final ParticleType<@NonNull ColorParticleOption> DARK_OAK_LEAVES = register("dark_oak_leaves");
	public static final SimpleParticleType AZALEA_LEAVES = registerSimple("azalea_leaves");
	public static final SimpleParticleType FLOWERING_AZALEA_PARTICLES = registerSimple("flowering_azalea_particles");

	private static ParticleType<@NonNull ColorParticleOption> register(String name) {
		ParticleType<@NonNull ColorParticleOption> type = FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec);
		Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MoreLeafParticles.MOD_ID, name), type);
		return type;
	}

	private static SimpleParticleType registerSimple(String name) {
		SimpleParticleType type = FabricParticleTypes.simple(false);
		Registry.register(BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MoreLeafParticles.MOD_ID, name), type);
		return type;
	}

	public static void init() {
	}
}
