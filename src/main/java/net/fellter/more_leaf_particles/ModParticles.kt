package net.fellter.more_leaf_particles

//? if fabric {
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.core.Registry
import net.minecraft.resources.Identifier
//?} else if neoforge {
/*import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.neoforged.neoforge.registries.DeferredRegister
import java.util.function.Supplier
import com.mojang.serialization.MapCodec
*///?}
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.particles.SimpleParticleType
import net.minecraft.core.registries.BuiltInRegistries

object ModParticles {
	//? if neoforge
	//internal val PARTICLE_TYPES: DeferredRegister<ParticleType<*>> = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, MoreLeafParticles.MOD_ID)

	@JvmField val SPRUCE_NEEDLES: /*? if neoforge {*//*Supplier<ParticleType<ColorParticleOption>>*//*?} else if fabric {*/ParticleType<ColorParticleOption>/*?}*/ = register("spruce_needles")
	@JvmField val BIRCH_LEAVES: /*? if neoforge {*//*Supplier<ParticleType<ColorParticleOption>>*//*?} else if fabric {*/ParticleType<ColorParticleOption>/*?}*/ = register("birch_leaves")
	@JvmField val MANGROVE_LEAVES: /*? if neoforge {*//*Supplier<ParticleType<ColorParticleOption>>*//*?} else if fabric {*/ParticleType<ColorParticleOption>/*?}*/ = register("mangrove_leaves")
	@JvmField val JUNGLE_LEAVES: /*? if neoforge {*//*Supplier<ParticleType<ColorParticleOption>>*//*?} else if fabric {*/ParticleType<ColorParticleOption>/*?}*/ = register("jungle_leaves")
	@JvmField val ACACIA_LEAVES: /*? if neoforge {*//*Supplier<ParticleType<ColorParticleOption>>*//*?} else if fabric {*/ParticleType<ColorParticleOption>/*?}*/ = register("acacia_leaves")
	@JvmField val DARK_OAK_LEAVES: /*? if neoforge {*//*Supplier<ParticleType<ColorParticleOption>>*//*?} else if fabric {*/ParticleType<ColorParticleOption>/*?}*/ = register("dark_oak_leaves")
	@JvmField val AZALEA_LEAVES: /*? if neoforge {*//*Supplier<SimpleParticleType>*//*?} else if fabric {*/SimpleParticleType/*?}*/ = registerSimple("azalea_leaves")
	@JvmField val FLOWERING_AZALEA_PARTICLES: /*? if neoforge {*//*Supplier<SimpleParticleType>*//*?} else if fabric {*/SimpleParticleType/*?}*/ = registerSimple("flowering_azalea_particles")

	private fun register(name: String): /*? if neoforge {*//*Supplier<ParticleType<ColorParticleOption>>*//*?} else if fabric {*/ParticleType<ColorParticleOption>/*?}*/ {
		//? if neoforge {
		/*return PARTICLE_TYPES.register(name, Supplier {
			object : ParticleType<ColorParticleOption>(false) {
				override fun codec(): MapCodec<ColorParticleOption> = ColorParticleOption.codec(this)

				override fun streamCodec(): StreamCodec<in RegistryFriendlyByteBuf, ColorParticleOption> = ColorParticleOption.streamCodec(this)
			}
		})
		*///?} else if fabric {
		return Registry.register(
			BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MoreLeafParticles.MOD_ID, name),
			FabricParticleTypes.complex(ColorParticleOption::codec, ColorParticleOption::streamCodec)
		)
		//?}
	}

	private fun registerSimple(name: String): /*? if neoforge {*//*Supplier<SimpleParticleType>*//*?} else if fabric {*/SimpleParticleType/*?}*/ {
		//? if neoforge {
		/*return PARTICLE_TYPES.register(name, Supplier {
			SimpleParticleType(false)
		})
		*///?} else if fabric {
		return Registry.register(
			BuiltInRegistries.PARTICLE_TYPE, Identifier.fromNamespaceAndPath(MoreLeafParticles.MOD_ID, name),
			FabricParticleTypes.simple(false)
		)
		//?}
	}

	@JvmStatic fun init() {}
}
