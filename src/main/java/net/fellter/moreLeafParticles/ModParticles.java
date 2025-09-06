package net.fellter.moreLeafParticles;

import net.minecraft.particle.ParticleType;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;

public final class ModParticles {
	public static final ParticleType<TintedParticleEffect> SPRUCE_NEEDLES = register("spruce_needles");
	public static final ParticleType<TintedParticleEffect> BIRCH_LEAVES = register("birch_leaves");
	public static final ParticleType<TintedParticleEffect> MANGROVE_LEAVES = register("mangrove_leaves");
	public static final SimpleParticleType AZALEA_LEAVES = registerSimple("azalea_leaves");
	public static final SimpleParticleType FLOWERING_AZALEA_PARTICLES = registerSimple("flowering_azalea_particles");

	private static ParticleType<TintedParticleEffect> register(String name) {
		ParticleType<TintedParticleEffect> type = FabricParticleTypes.complex(TintedParticleEffect::createCodec, TintedParticleEffect::createPacketCodec);
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MoreLeafParticles.MOD_ID, name), type);
		return type;
	}

	private static SimpleParticleType registerSimple(String name) {
		SimpleParticleType type = FabricParticleTypes.simple(false);
		Registry.register(Registries.PARTICLE_TYPE, Identifier.of(MoreLeafParticles.MOD_ID, name), type);
		return type;
	}

	public static void init() {
	}
}
