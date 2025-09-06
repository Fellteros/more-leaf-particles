package net.fellter.moreLeafParticles;

import net.fabricmc.api.ModInitializer;

public class MoreLeafParticles implements ModInitializer {
	public static final String MOD_ID = "more-leaf-particles";

	@Override
	public void onInitialize() {
		ModParticles.init();
	}
}
