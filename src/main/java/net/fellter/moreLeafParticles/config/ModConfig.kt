package net.fellter.moreLeafParticles.config

import com.google.gson.GsonBuilder
import dev.isxander.yacl3.api.*
import dev.isxander.yacl3.api.controller.*
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder
import dev.isxander.yacl3.config.v2.impl.ConfigClassHandlerImpl
import dev.isxander.yacl3.gui.YACLScreen
import dev.isxander.yacl3.gui.controllers.slider.IntegerSliderController
import dev.isxander.yacl3.impl.controller.FloatFieldControllerBuilderImpl
import dev.isxander.yacl3.impl.controller.IntegerSliderControllerBuilderImpl
import net.fabricmc.loader.api.FabricLoader
import net.fellter.moreLeafParticles.MoreLeafParticles
import net.fellter.moreLeafParticles.config.ConfigExtensions.binding
import net.fellter.moreLeafParticles.config.ConfigExtensions.category
import net.fellter.moreLeafParticles.config.ConfigExtensions.color
import net.fellter.moreLeafParticles.config.ConfigExtensions.colorOptions
import net.fellter.moreLeafParticles.config.ConfigExtensions.description
import net.fellter.moreLeafParticles.config.ConfigExtensions.enable
import net.fellter.moreLeafParticles.config.ConfigExtensions.enableColor
import net.fellter.moreLeafParticles.config.ConfigExtensions.flowAway
import net.fellter.moreLeafParticles.config.ConfigExtensions.gravity
import net.fellter.moreLeafParticles.config.ConfigExtensions.initialVelocity
import net.fellter.moreLeafParticles.config.ConfigExtensions.physicalProperties
import net.fellter.moreLeafParticles.config.ConfigExtensions.probability
import net.fellter.moreLeafParticles.config.ConfigExtensions.rotate
import net.fellter.moreLeafParticles.config.ConfigExtensions.size
import net.fellter.moreLeafParticles.config.ConfigExtensions.useTint
import net.fellter.moreLeafParticles.config.ConfigExtensions.wind
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.components.tabs.Tab
import net.minecraft.network.chat.Component
import net.minecraft.resources.Identifier
import java.awt.Color
import kotlin.reflect.KMutableProperty0
import kotlin.reflect.KProperty0

object ModConfig {
	@JvmField var HANDLER: ConfigClassHandler<ConfigFields> = object : ConfigClassHandlerImpl<ConfigFields>(
		ConfigFields::class.java,
		Identifier.fromNamespaceAndPath(MoreLeafParticles.MOD_ID, "config"),
		{ config ->
			GsonConfigSerializerBuilder.create(config)
				.setPath(FabricLoader.getInstance().configDir.resolve("more-leaf-particles.json5"))
				.appendGsonBuilder(GsonBuilder::setPrettyPrinting)
				.setJson5(true)
				.build()
		}
	) {
		//~ if >=26.2 'client.setScreen' -> 'client.gui.setScreen', 'client.screen' -> 'client.gui.screen()' {
		override fun save() {
			super.save()
			val client = Minecraft.getInstance()

			if (client.gui.screen() == null) {
				return
			}

			if (client.gui.screen() is YACLScreen) {
				val screen = client.gui.screen() as YACLScreen
				val currentTab: Tab? = screen.tabManager.currentTab

				val index = if (currentTab != null) screen.tabNavigationBar.tabs.indexOf(currentTab) else 0

				val scrollOffset: Int = screen.tabNavigationBar.scrollOffset
				client.gui.screen()!!.onClose()

				val newScreen = create().generateScreen(client.gui.screen()) as YACLScreen
				newScreen.init( /*? if <=1.21.10 {*/ /*client,  *//*?}*/client.window.guiScaledWidth, client.window.guiScaledHeight)

				newScreen.tabNavigationBar.selectTab(index, false)
				newScreen.tabNavigationBar.arrangeElements(/*? if >=26.2 {*/client.gui.screen()!!.width/*?}*/)
				newScreen.tabNavigationBar.setScrollOffset(scrollOffset)

				client.gui.setScreen(newScreen)
			} else {
				client.gui.screen()!!.onClose()
				client.gui.setScreen(create().generateScreen(client.gui.screen()))
			}
		}
	}
	//~}

	fun create(): YetAnotherConfigLib {
		return YetAnotherConfigLib.createBuilder()
			.title(Component.translatable("more-leaf-particles.config.title"))
			.category(
				ConfigCategory.createBuilder()
					.name(Component.translatable("more-leaf-particles.category.common.name"))
					.group(
						OptionGroup.createBuilder()
							.name(Component.translatable("more-leaf-particles.group.particleRain.name"))
							.description(Component.translatable("more-leaf-particles.group.particleRain.desc"))
							.option(
								tickBoxOption(
									Component.translatable("more-leaf-particles.option.enablePartRainWind.name"),
									Component.translatable("more-leaf-particles.option.enablePartRainWind.desc"),
								)
									.binding(true, ConfigFields::enableParticleRainWind)
									.available(MoreLeafParticles.isParticleRainPresent)
									.build()
							)
							.option(
								Option.createBuilder<Double>()
									.name(Component.translatable("more-leaf-particles.option.windMultiplier.name"))
									.description(Component.translatable("more-leaf-particles.option.windMultiplier.desc"))
									.binding(1.0, ConfigFields::particleRainWindMultiplier)
									.available(MoreLeafParticles.isParticleRainPresent && ConfigFields::enableParticleRainWind.get())
									.controller(DoubleFieldControllerBuilder::create)
									.build()
							)
							.option(
								Option.createBuilder<Int>()
									.name(Component.translatable("more-leaf-particles.option.ticksToBlend.name"))
									.description(Component.translatable("more-leaf-particles.option.ticksToBlend.desc"))
									.binding(20, ConfigFields::ticksToBlend)
									.available(MoreLeafParticles.isParticleRainPresent && ConfigFields::enableParticleRainWind.get())
									.controller(IntegerFieldControllerBuilder::create)
									.build()
							)
							.build()
					)
					.build()
			)
			.category(Component.translatable("more-leaf-particles.category.translation.oak")) {
				enable(ConfigFields::enableOak)
				probability(ConfigFields::oakMultiplier)

				colorOptions(ConfigFields::enableOak) {
					enableColor(ConfigFields::enableOakCustomColor)
					color(ConfigFields::oakColor, ConfigFields::enableOakCustomColor)
					useTint(ConfigFields::useOakTint)
				}

				physicalProperties {
					size(ConfigFields::oakSize)
					rotate(ConfigFields::oakRotate)
					gravity(ConfigFields::oakGravity)
					initialVelocity(ConfigFields::oakInitialVelocity)
					wind(ConfigFields::oakWind)
					flowAway(ConfigFields::oakFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.spruce")) {
				enable(ConfigFields::enableSpruce)
				probability(ConfigFields::spruceMultiplier)

				colorOptions(ConfigFields::enableSpruce) {
					enableColor(ConfigFields::enableSpruceCustomColor)
					color(ConfigFields::spruceColor, ConfigFields::enableSpruceCustomColor)
					useTint(ConfigFields::useSpruceTint)
				}

				physicalProperties {
					size(ConfigFields::spruceSize)
					rotate(ConfigFields::spruceRotate)
					gravity(ConfigFields::spruceGravity)
					initialVelocity(ConfigFields::spruceInitialVelocity)
					wind(ConfigFields::spruceWind)
					flowAway(ConfigFields::spruceFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.birch")) {
				enable(ConfigFields::enableBirch)
				probability(ConfigFields::birchMultiplier)

				colorOptions(ConfigFields::enableBirch) {
					enableColor(ConfigFields::enableBirchCustomColor)
					color(ConfigFields::birchColor, ConfigFields::enableBirchCustomColor)
					useTint(ConfigFields::useBirchTint)
				}

				physicalProperties {
					size(ConfigFields::birchSize)
					rotate(ConfigFields::birchRotate)
					gravity(ConfigFields::birchGravity)
					initialVelocity(ConfigFields::birchInitialVelocity)
					wind(ConfigFields::birchWind)
					flowAway(ConfigFields::birchFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.jungle")) {
				enable(ConfigFields::enableJungle)
				probability(ConfigFields::jungleMultiplier)

				colorOptions(ConfigFields::enableJungle) {
					enableColor(ConfigFields::enableJungleCustomColor)
					color(ConfigFields::jungleColor, ConfigFields::enableJungleCustomColor)
					useTint(ConfigFields::useJungleTint)
				}

				physicalProperties {
					size(ConfigFields::jungleSize)
					rotate(ConfigFields::jungleRotate)
					gravity(ConfigFields::jungleGravity)
					initialVelocity(ConfigFields::jungleInitialVelocity)
					wind(ConfigFields::jungleWind)
					flowAway(ConfigFields::jungleFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.acacia")) {
				enable(ConfigFields::enableAcacia)
				probability(ConfigFields::acaciaMultiplier)

				colorOptions(ConfigFields::enableAcacia) {
					enableColor(ConfigFields::enableAcaciaCustomColor)
					color(ConfigFields::acaciaColor, ConfigFields::enableAcaciaCustomColor)
					useTint(ConfigFields::useAcaciaTint)
				}

				physicalProperties {
					size(ConfigFields::acaciaSize, 1.66F)
					rotate(ConfigFields::acaciaRotate)
					gravity(ConfigFields::acaciaGravity)
					initialVelocity(ConfigFields::acaciaInitialVelocity)
					wind(ConfigFields::acaciaWind)
					flowAway(ConfigFields::acaciaFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.darkOak")) {
				enable(ConfigFields::enableDarkOak)
				probability(ConfigFields::darkOakMultiplier)

				colorOptions(ConfigFields::enableDarkOak) {
					enableColor(ConfigFields::enableDarkOakCustomColor)
					color(ConfigFields::darkOakColor, ConfigFields::enableDarkOakCustomColor)
					useTint(ConfigFields::useDarkOakTint)
				}

				physicalProperties {
					size(ConfigFields::darkOakSize)
					rotate(ConfigFields::darkOakRotate)
					gravity(ConfigFields::darkOakGravity)
					initialVelocity(ConfigFields::darkOakInitialVelocity)
					wind(ConfigFields::darkOakWind)
					flowAway(ConfigFields::darkOakFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.mangrove")) {
				enable(ConfigFields::enableMangrove)
				probability(ConfigFields::mangroveMultiplier)

				colorOptions(ConfigFields::enableMangrove) {
					enableColor(ConfigFields::enableMangroveCustomColor)
					color(ConfigFields::mangroveColor, ConfigFields::enableMangroveCustomColor)
					useTint(ConfigFields::useMangroveTint)
				}

				physicalProperties {
					size(ConfigFields::mangroveSize)
					rotate(ConfigFields::mangroveRotate)
					gravity(ConfigFields::mangroveGravity)
					initialVelocity(ConfigFields::mangroveInitialVelocity)
					wind(ConfigFields::mangroveWind)
					flowAway(ConfigFields::mangroveFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.cherry")) {
				enable(ConfigFields::enableCherry)
				probability(ConfigFields::cherryMultiplier)

				physicalProperties {
					size(ConfigFields::cherrySize)
					rotate(ConfigFields::cherryRotate)
					gravity(ConfigFields::cherryGravity)
					initialVelocity(ConfigFields::cherryInitialVelocity)
					wind(ConfigFields::cherryWind)
					flowAway(ConfigFields::cherryFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.paleOak")) {
				enable(ConfigFields::enablePaleOak)
				probability(ConfigFields::paleOakMultiplier)

				physicalProperties {
					size(ConfigFields::paleOakSize)
					rotate(ConfigFields::paleOakRotate)
					gravity(ConfigFields::paleOakGravity)
					initialVelocity(ConfigFields::paleOakInitialVelocity)
					wind(ConfigFields::paleOakWind)
					flowAway(ConfigFields::paleOakFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.azalea")) {
				enable(ConfigFields::enableAzalea)
				probability(ConfigFields::azaleaMultiplier)

				physicalProperties {
					size(ConfigFields::azaleaSize)
					rotate(ConfigFields::azaleaRotate)
					gravity(ConfigFields::azaleaGravity)
					initialVelocity(ConfigFields::azaleaInitialVelocity)
					wind(ConfigFields::azaleaWind)
					flowAway(ConfigFields::azaleaFlowAway)
				}
			}
			.category(Component.translatable("more-leaf-particles.category.translation.floweringAzalea")) {
				enable(ConfigFields::enableFloweringAzalea)
				probability(ConfigFields::floweringAzaleaMultiplier)

				physicalProperties {
					size(ConfigFields::floweringAzaleaSize)
					rotate(ConfigFields::floweringAzaleaRotate)
					gravity(ConfigFields::floweringAzaleaGravity)
					initialVelocity(ConfigFields::floweringAzaleaInitialVelocity)
					wind(ConfigFields::floweringAzaleaWind)
					flowAway(ConfigFields::floweringAzaleaFlowAway)
				}
			}
			.build()
	}

	fun initConfig() {
		HANDLER.load()
		create()
	}
}

@DslMarker
private annotation class ConfigDsl

@ConfigDsl
private object ConfigExtensions {
	inline fun YetAnotherConfigLib.Builder.category(name: Component, init: context(/* name = */ Component) ConfigCategory.Builder.() -> Unit): YetAnotherConfigLib.Builder {
		return this.category(
			context<Component, ConfigCategory>(name) {
				ConfigCategory.createBuilder()
					.name(name)
					.apply { this.init() }.build()
			}
		)
	}

	context(name: Component)
	fun ConfigCategory.Builder.enable(prop: KMutableProperty0<Boolean>) {
		this.option(
			tickBoxOption(
				Component.translatable("more-leaf-particles.option.enable.name", name),
				Component.translatable("more-leaf-particles.option.enable.desc")
			)
				.binding(true, prop)
				.build()
		)
	}

	context(name: Component)
	fun ConfigCategory.Builder.probability(prop: KMutableProperty0<Int>) {
		this.option(
			Option.createBuilder<Int>()
				.name(Component.translatable("more-leaf-particles.option.probability.name", name))
				.description(Component.translatable("more-leaf-particles.option.probability.desc"))
				.binding(1, prop)
				.controller { option ->
					object : IntegerSliderControllerBuilderImpl(option) {
						override fun build(): Controller<Int> {
							return IntegerSliderController.createInternal(option, 0, 100, 1) { Component.literal("$it%") }
						}
					}
				}
				.build()
		)
	}

	context(name: Component)
	inline fun ConfigCategory.Builder.physicalProperties(init: context(/* name = */ Component) OptionGroup.Builder.() -> Unit) {
		context<Component, Unit>(name) {
			this.group(
				OptionGroup.createBuilder()
					.name(Component.translatable("more-leaf-particles.group.physicalProps.name", name))
					.description(Component.translatable("more-leaf-particles.group.physicalProps.desc", name))
					.apply { this.init() }
					.build()
			)
		}
	}

	fun OptionGroup.Builder.size(prop: KMutableProperty0<Float>, defValue: Float = 2.0F) {
		this.option(
			Option.createBuilder<Float>()
				.name(Component.translatable("more-leaf-particles.option.size.name"))
				.description(Component.translatable("more-leaf-particles.option.size.desc"))
				.binding(defValue, prop)
				.controller(FloatFieldControllerBuilder::create)
				.build()
		)
	}

	fun OptionGroup.Builder.rotate(prop: KMutableProperty0<Boolean>) {
		this.option(
			tickBoxOption(
				Component.translatable("more-leaf-particles.option.rotate.name"),
				Component.translatable("more-leaf-particles.option.rotate.desc")
			)
				.binding(true, prop)
				.build()
		)
	}

	fun OptionGroup.Builder.gravity(prop: KMutableProperty0<Float>) {
		this.option(
			Option.createBuilder<Float>()
				.name(Component.translatable("more-leaf-particles.option.gravity.name"))
				.description(Component.translatable("more-leaf-particles.option.gravity.desc"))
				.binding(0.07F, prop)
				.controller{ option ->
					FloatFieldControllerBuilderImpl(option).formatValue {
						Component.literal(String.format("%,.2f", it).replace("[\u00a0\u202F]", " "))
					}
				}
				.build()
		)
	}

	fun OptionGroup.Builder.initialVelocity(prop: KMutableProperty0<Float>) {
		this.option(
			Option.createBuilder<Float>()
				.name(Component.translatable("more-leaf-particles.option.initVelocity.name"))
				.description(Component.translatable("more-leaf-particles.option.initVelocity.desc"))
				.binding(0.021F, prop)
				.controller { option ->
					FloatFieldControllerBuilderImpl(option).formatValue {
						Component.literal(String.format("%,.3f", it).replace("[\u00a0\u202F]", " "))
					}
				}
				.build()
		)
	}

	fun OptionGroup.Builder.wind(prop: KMutableProperty0<Float>) {
		this.option(
			Option.createBuilder<Float>()
				.available(!MoreLeafParticles.isParticleRainPresent)
				.name(Component.translatable("more-leaf-particles.option.wind.name"))
				.description(Component.translatable("more-leaf-particles.option.wind.desc"))
				.binding(10F, prop)
				.controller(FloatFieldControllerBuilder::create)
				.build()
		)
	}

	fun OptionGroup.Builder.flowAway(prop: KMutableProperty0<Boolean>) {
		this.option(
			tickBoxOption(
				Component.translatable("more-leaf-particles.option.flowAway.name"),
				Component.translatable("more-leaf-particles.option.flowAway.desc")
			)
				.binding(false, prop)
				.build()
		)
	}

	context(name: Component)
	inline fun ConfigCategory.Builder.colorOptions(condition: Boolean = true, init: context(/* name = */ Component) OptionGroup.Builder.() -> Unit) {
		context<Component, Unit>(name) {
			this.groupIf(
				condition,
				OptionGroup.createBuilder()
					.name(Component.translatable("more-leaf-particles.group.color.name", name))
					.description(Component.translatable("more-leaf-particles.group.color.desc", name))
					.apply { this.init() }
					.build()
			)
		}
	}

	context(name: Component)
	inline fun ConfigCategory.Builder.colorOptions(condition: KProperty0<Boolean>, init: context(Component) OptionGroup.Builder.() -> Unit) =
		colorOptions(condition.get(), init)

	context(_: Component)
	fun OptionGroup.Builder.enableColor(prop: KMutableProperty0<Boolean>) {
		this.option(
			tickBoxOption(
				Component.translatable("more-leaf-particles.option.enableCustomColor.name"),
				Component.translatable("more-leaf-particles.option.enableCustomColor.desc")
			)
				.binding(false, prop)
				.build()
		)
	}

	context(_: Component)
	fun OptionGroup.Builder.useTint(prop: KMutableProperty0<Boolean>) {
		this.option(
			tickBoxOption(
				Component.translatable("more-leaf-particles.option.useTint.name"),
				Component.translatable("more-leaf-particles.option.useTint.desc")
			)
				.binding(false, prop)
				.build()
		)
	}

	context(_: Component)
	fun OptionGroup.Builder.color(prop: KMutableProperty0<Color>, available: KMutableProperty0<Boolean>) {
		this.option(
			Option.createBuilder<Color>()
				.name(Component.translatable("more-leaf-particles.option.color.name"))
				.description(Component.translatable("more-leaf-particles.option.color.desc"))
				.available(available.get())
				.binding(Color(0, 0, 0), prop)
				.controller(ColorControllerBuilder::create)
				.build()
		)
	}

	fun <T : Any> Option.Builder<T>.binding(defaultValue: T, property: KMutableProperty0<T>): Option.Builder<T> =
		this.binding(defaultValue, property::get) {
			property.set(it)
			ModConfig.HANDLER.save()
		}

	fun <T> Option.Builder<T>.description(vararg components: Component): Option.Builder<T> = this.description(OptionDescription.of(*components))

	fun OptionGroup.Builder.description(vararg components: Component): OptionGroup.Builder = this.description(OptionDescription.of(*components))
}

private fun tickBoxOption(name: Component, desc: Component): Option.Builder<Boolean> {
	return Option.createBuilder<Boolean>()
		.name(name)
		.description(desc)
		.controller(TickBoxControllerBuilder::create)
}