package net.fellter.moreLeafParticles.yacl;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import net.fabricmc.loader.api.FabricLoader;

public class ModMenuImpl implements ModMenuApi {
	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		return FabricLoader.getInstance().isModLoaded("yet_another_config_lib_v3")
				? parentScreen -> YACLImpl.create().generateScreen(parentScreen)
				: ModMenuApi.super.getModConfigScreenFactory();
	}
}
