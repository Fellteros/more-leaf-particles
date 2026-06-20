package net.fellter.moreLeafParticles

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.core.Registry
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.Identifier

object ModParticles {
	@JvmField val SPRUCE_NEEDLES: ParticleType<ColorParticleOption> = register("spruce_needles")
	@JvmField val BIRCH_LEAVES: ParticleType<ColorParticleOption> = register("birch_leaves")
	@JvmField val MANGROVE_LEAVES: ParticleType<ColorParticleOption> = register("mangrove_leaves")
	@JvmField val JUNGLE_LEAVES: ParticleType<ColorParticleOption> = register("jungle_leaves")
	@JvmField val ACACIA_LEAVES: ParticleType<ColorParticleOption> = register("acacia_leaves")
	@JvmField val DARK_OAK_LEAVES: ParticleType<ColorParticleOption> = register("dark_oak_leaves")
	@JvmField val AZALEA_LEAVES: SimpleParticleType = registerSimple("azalea_leaves")
	@JvmField val FLOWERING_AZALEA_PARTICLES: SimpleParticleType = registerSimple("flowering_azalea_particles")

	private fun register(name: String): ParticleType<ColorParticleOption> {
		return Registry.register(
			BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MoreLeafParticles.MOD_ID, name),
			FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec)
		)
	}

	private fun registerSimple(name: String): SimpleParticleType {
		return Registry.register(
			BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MoreLeafParticles.MOD_ID, name),
			FabricParticleTypes.simple(false)
		)
	}

	@JvmStatic fun init() {}
}
