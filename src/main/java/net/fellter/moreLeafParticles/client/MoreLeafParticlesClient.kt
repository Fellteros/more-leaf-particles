package net.fellter.moreLeafParticles.client

import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
//? if <26.1 {
/*import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
*///?} else
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry
import net.fellter.moreLeafParticles.*

@Environment(EnvType.CLIENT)
class MoreLeafParticlesClient : ClientModInitializer {
	override fun onInitializeClient() {
		/*? if <26.1 {*//*ParticleFactoryRegistry*//*?} else {*/ParticleProviderRegistry/*?}*/.getInstance().register(ModParticles.SPRUCE_NEEDLES, ::SpruceLeavesFactory)
		/*? if <26.1 {*//*ParticleFactoryRegistry*//*?} else {*/ParticleProviderRegistry/*?}*/.getInstance().register(ModParticles.BIRCH_LEAVES, ::BirchLeavesFactory)
		/*? if <26.1 {*//*ParticleFactoryRegistry*//*?} else {*/ParticleProviderRegistry/*?}*/.getInstance().register(ModParticles.MANGROVE_LEAVES, ::MangroveLeavesFactory)
		/*? if <26.1 {*//*ParticleFactoryRegistry*//*?} else {*/ParticleProviderRegistry/*?}*/.getInstance().register(ModParticles.JUNGLE_LEAVES, ::JungleLeavesFactory)
		/*? if <26.1 {*//*ParticleFactoryRegistry*//*?} else {*/ParticleProviderRegistry/*?}*/.getInstance().register(ModParticles.ACACIA_LEAVES, ::AcaciaLeavesFactory)
		/*? if <26.1 {*//*ParticleFactoryRegistry*//*?} else {*/ParticleProviderRegistry/*?}*/.getInstance().register(ModParticles.DARK_OAK_LEAVES, ::DarkOakLeavesFactory)
		/*? if <26.1 {*//*ParticleFactoryRegistry*//*?} else {*/ParticleProviderRegistry/*?}*/.getInstance().register(ModParticles.AZALEA_LEAVES, ::AzaleaLeavesFactory)
		/*? if <26.1 {*//*ParticleFactoryRegistry*//*?} else {*/ParticleProviderRegistry/*?}*/.getInstance().register(ModParticles.FLOWERING_AZALEA_PARTICLES, ::FloweringAzaleaLeavesFactory)
	}
}
