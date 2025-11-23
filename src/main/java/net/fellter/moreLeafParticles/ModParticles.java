//~ not_null
//~ non_null_import

package net.fellter.moreLeafParticles;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.Registry;
import net.minecraft.core.particles.ColorParticleOption;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

public final class ModParticles {
	public static final ParticleType<@NotNull ColorParticleOption> SPRUCE_NEEDLES = register("spruce_needles");
	public static final ParticleType<@NotNull ColorParticleOption> BIRCH_LEAVES = register("birch_leaves");
	public static final ParticleType<@NotNull ColorParticleOption> MANGROVE_LEAVES = register("mangrove_leaves");
	public static final ParticleType<@NotNull ColorParticleOption> JUNGLE_LEAVES = register("jungle_leaves");
	public static final ParticleType<@NotNull ColorParticleOption> ACACIA_LEAVES = register("acacia_leaves");
	public static final ParticleType<@NotNull ColorParticleOption> DARK_OAK_LEAVES = register("dark_oak_leaves");
	public static final SimpleParticleType AZALEA_LEAVES = registerSimple("azalea_leaves");
	public static final SimpleParticleType FLOWERING_AZALEA_PARTICLES = registerSimple("flowering_azalea_particles");

	private static ParticleType<@NotNull ColorParticleOption> register(String name) {
		ParticleType<@NotNull ColorParticleOption> type = FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec);
		Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(MoreLeafParticles.MOD_ID, name), type);
		return type;
	}

	private static SimpleParticleType registerSimple(String name) {
		SimpleParticleType type = FabricParticleTypes.simple(false);
		Registry.register(BuiltInRegistries.PARTICLE_TYPE, ResourceLocation.fromNamespaceAndPath(MoreLeafParticles.MOD_ID, name), type);
		return type;
	}

	public static void init() {
	}
}
