package net.fellter.moreLeafParticles.client;

import net.fellter.moreLeafParticles.ModLeavesParticle;
import net.fellter.moreLeafParticles.ModParticles;

import net.minecraft.client.particle.LeavesParticle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

@Environment(EnvType.CLIENT)
public class MoreLeafParticlesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(ModParticles.SPRUCE_NEEDLES, LeavesParticle.TintedLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.BIRCH_LEAVES, LeavesParticle.TintedLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.MANGROVE_LEAVES, LeavesParticle.TintedLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.JUNGLE_LEAVES, LeavesParticle.TintedLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.ACACIA_LEAVES, ModLeavesParticle.AcaciaLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.AZALEA_LEAVES, ModLeavesParticle.SimpleLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.FLOWERING_AZALEA_PARTICLES, ModLeavesParticle.SimpleLeavesFactory::new);
	}
}
