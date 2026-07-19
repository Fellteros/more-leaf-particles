package net.fellter.more_leaf_particles.client

//? if fabric {
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
//?} else if neoforge {
/*import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.common.Mod
import net.neoforged.bus.api.IEventBus
import net.neoforged.fml.ModContainer
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent
import net.neoforged.neoforge.client.gui.IConfigScreenFactory
import net.fellter.more_leaf_particles.config.ModConfig
*///?}
import net.fellter.more_leaf_particles.*

@Environment(EnvType.CLIENT)
/*? if neoforge {*//*@Mod(value = MoreLeafParticles.MOD_ID, dist = [Dist.CLIENT])*//*?}*/
class MoreLeafParticlesClient /*? if neoforge {*//*(eventBus: IEventBus, container: ModContainer)*//*?} else if fabric {*/ : ClientModInitializer/*?}*/ {
	//? if neoforge {
	/*init {
		container.registerExtensionPoint(IConfigScreenFactory::class.java, IConfigScreenFactory { _, screen -> ModConfig.create().generateScreen(screen) })
		ModParticles.PARTICLE_TYPES.register(eventBus)
		eventBus.addListener(::registerParticleProviders)
	}

	fun registerParticleProviders(event: RegisterParticleProvidersEvent)*//*?} else if fabric {*/override fun onInitializeClient()/*?}*/ {
		//? if fabric {
		ParticleRegistry.getInstance().register(ModParticles.SPRUCE_NEEDLES, ::SpruceLeavesFactory)
		ParticleRegistry.getInstance().register(ModParticles.BIRCH_LEAVES, ::BirchLeavesFactory)
		ParticleRegistry.getInstance().register(ModParticles.MANGROVE_LEAVES, ::MangroveLeavesFactory)
		ParticleRegistry.getInstance().register(ModParticles.JUNGLE_LEAVES, ::JungleLeavesFactory)
		ParticleRegistry.getInstance().register(ModParticles.ACACIA_LEAVES, ::AcaciaLeavesFactory)
		ParticleRegistry.getInstance().register(ModParticles.DARK_OAK_LEAVES, ::DarkOakLeavesFactory)
		ParticleRegistry.getInstance().register(ModParticles.AZALEA_LEAVES, ::AzaleaLeavesFactory)
		ParticleRegistry.getInstance().register(ModParticles.FLOWERING_AZALEA_PARTICLES, ::FloweringAzaleaLeavesFactory)
		//?} else if neoforge {
		/*event.registerSpriteSet(ModParticles.SPRUCE_NEEDLES.get(), ::SpruceLeavesFactory)
		event.registerSpriteSet(ModParticles.BIRCH_LEAVES.get(), ::BirchLeavesFactory)
		event.registerSpriteSet(ModParticles.MANGROVE_LEAVES.get(), ::MangroveLeavesFactory)
		event.registerSpriteSet(ModParticles.JUNGLE_LEAVES.get(), ::JungleLeavesFactory)
		event.registerSpriteSet(ModParticles.ACACIA_LEAVES.get(), ::AcaciaLeavesFactory)
		event.registerSpriteSet(ModParticles.DARK_OAK_LEAVES.get(), ::DarkOakLeavesFactory)
		event.registerSpriteSet(ModParticles.AZALEA_LEAVES.get(), ::AzaleaLeavesFactory)
		event.registerSpriteSet(ModParticles.FLOWERING_AZALEA_PARTICLES.get(), ::FloweringAzaleaLeavesFactory)
		*///?}
	}
}
