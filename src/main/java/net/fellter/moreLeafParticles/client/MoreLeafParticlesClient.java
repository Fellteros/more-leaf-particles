package net.fellter.moreLeafParticles.client;

import net.fellter.moreLeafParticles.ModParticles;

import net.minecraft.client.particle.LeavesParticle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class MoreLeafParticlesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(ModParticles.SPRUCE_NEEDLES, LeavesParticle.TintedLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.BIRCH_LEAVES, LeavesParticle.TintedLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.MANGROVE_LEAVES, LeavesParticle.TintedLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.AZALEA_LEAVES, ModParticles.SimpleLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.FLOWERING_AZALEA_PARTICLES, ModParticles.SimpleLeavesFactory::new);
	}
}
