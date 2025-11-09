package net.fellter.moreLeafParticles.yacl;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.terraformersmc.modmenu.api.UpdateChecker;
import net.fellter.moreLeafParticles.MoreLeafParticles;

public class ModMenuImpl implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return MoreLeafParticles.isYACLPresent()
				? parentScreen -> YACLImpl.create().generateScreen(parentScreen)
				: ModMenuApi.super.getModConfigScreenFactory();
	}

	@Override
	public UpdateChecker getUpdateChecker() {
		return new MoreLeafParticlesUpdateChecker();
	}
}
