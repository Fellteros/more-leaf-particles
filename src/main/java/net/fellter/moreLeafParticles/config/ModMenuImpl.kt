package net.fellter.moreLeafParticles.config

import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import com.terraformersmc.modmenu.api.UpdateChecker
import net.fellter.moreLeafParticles.MoreLeafParticles.Companion.isYACLPresent
import net.minecraft.client.gui.screens.Screen

class ModMenuImpl : ModMenuApi {
	override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
		return if (isYACLPresent)
			ConfigScreenFactory { parentScreen: Screen -> YACLImpl.create().generateScreen(parentScreen) }
		else
			super.getModConfigScreenFactory()
	}

	override fun getUpdateChecker(): UpdateChecker = MoreLeafParticlesUpdateChecker()
}
