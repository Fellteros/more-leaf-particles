package net.fellter.moreLeafParticles.client;

import net.fellter.moreLeafParticles.ModLeavesParticle;
import net.fellter.moreLeafParticles.ModParticles;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

@Environment(EnvType.CLIENT)
public class MoreLeafParticlesClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ParticleFactoryRegistry.getInstance().register(ModParticles.SPRUCE_NEEDLES, ModLeavesParticle.SpruceLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.BIRCH_LEAVES, ModLeavesParticle.BirchLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.MANGROVE_LEAVES, ModLeavesParticle.MangroveLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.JUNGLE_LEAVES, ModLeavesParticle.JungleLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.ACACIA_LEAVES, ModLeavesParticle.AcaciaLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.DARK_OAK_LEAVES, ModLeavesParticle.DarkOakLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.AZALEA_LEAVES, ModLeavesParticle.AzaleaLeavesFactory::new);
		ParticleFactoryRegistry.getInstance().register(ModParticles.FLOWERING_AZALEA_PARTICLES, ModLeavesParticle.FloweringAzaleaLeavesFactory::new);
	}
}
