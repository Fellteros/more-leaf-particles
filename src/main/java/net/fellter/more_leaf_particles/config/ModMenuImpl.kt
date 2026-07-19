package net.fellter.more_leaf_particles.config

//? if fabric {
import com.terraformersmc.modmenu.api.ConfigScreenFactory
import com.terraformersmc.modmenu.api.ModMenuApi
import com.terraformersmc.modmenu.api.UpdateChecker
import net.fellter.more_leaf_particles.MoreLeafParticles.Companion.isYACLPresent

class ModMenuImpl : ModMenuApi {
	override fun getModConfigScreenFactory(): ConfigScreenFactory<*> {
		return if (isYACLPresent)
			ConfigScreenFactory { parentScreen -> ModConfig.create().generateScreen(parentScreen) }
		else
			super.getModConfigScreenFactory()
	}

	override fun getUpdateChecker(): UpdateChecker = MoreLeafParticlesUpdateChecker()
}
//?}
