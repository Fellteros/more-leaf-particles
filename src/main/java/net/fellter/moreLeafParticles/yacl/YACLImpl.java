package net.fellter.moreLeafParticles.yacl;

import java.awt.*;
import java.util.Arrays;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import net.fellter.moreLeafParticles.MoreLeafParticles;

import net.minecraft.network.chat.Component;

import static net.fabricmc.loader.impl.util.StringUtil.capitalize;

public class YACLImpl {
	public static YetAnotherConfigLib create() {
		return YetAnotherConfigLib.createBuilder()
				.title(Component.translatable("more-leaf-particles.config.title"))
				.categories(Arrays.asList(
						createCommonsCategory(),
						createCategory("oak", Component.translatable("more-leaf-particles.category.translation.oak"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("spruce", Component.translatable("more-leaf-particles.category.translation.spruce"), Component.translatable("more-leaf-particles.leafType.needle.singular"), Component.translatable("more-leaf-particles.leafType.needle.plural")),
						createCategory("birch", Component.translatable("more-leaf-particles.category.translation.birch"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("jungle", Component.translatable("more-leaf-particles.category.translation.jungle"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("acacia", Component.translatable("more-leaf-particles.category.translation.acacia"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("darkOak", Component.translatable("more-leaf-particles.category.translation.darkOak"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("mangrove", Component.translatable("more-leaf-particles.category.translation.mangrove"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaf.plural")),
						createUntintable("cherry", Component.translatable("more-leaf-particles.category.translation.cherry"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaf.plural")),
						createUntintable("paleOak", Component.translatable("more-leaf-particles.category.translation.paleOak"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaf.plural")),
						createUntintable("azalea", Component.translatable("more-leaf-particles.category.translation.azalea"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaf.plural")),
						createUntintable("floweringAzalea", Component.translatable("more-leaf-particles.category.translation.floweringAzalea"), Component.translatable("more-leaf-particles.leafType.leaf.singular"), Component.translatable("more-leaf-particles.leafType.leaves_and_petals"))
				))
				.build();
	}

	private static Object getConfigField(String name) {
		try {
			return ModConfig.class.getField(name).get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static void setConfigField(String name, Object value) {
		try {
			ModConfig.class.getField(name).set(null, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static ConfigCategory createCommonsCategory() {
		return ConfigCategory.createBuilder()
				.name(Component.translatable("more-leaf-particles.category.common.name"))
				.group(OptionGroup.createBuilder()
						.name(Component.translatable("more-leaf-particles.group.particleRain.name"))
						.description(OptionDescription.of(Component.translatable("more-leaf-particles.group.particleRain.desc")))
						.option(boolOptWithTickBox(
								Component.translatable("more-leaf-particles.option.enablePartRainWind.name"),
								Component.translatable("more-leaf-particles.option.enablePartRainWind.desc"))
								.binding(
										true,
										() -> ModConfig.enableParticleRainWind,
										value -> {
											ModConfig.enableParticleRainWind = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(MoreLeafParticles.isParticleRainPresent())
								.build())
						.option(Option.<Double>createBuilder()
								.name(Component.translatable("more-leaf-particles.option.windMultiplier.name"))
								.description(OptionDescription.of(Component.translatable("more-leaf-particles.option.windMultiplier.desc")))
								.binding(
										1.0,
										() -> ModConfig.particleRainWindMultiplier,
										value -> {
											ModConfig.particleRainWindMultiplier = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(MoreLeafParticles.isParticleRainPresent() && ModConfig.enableParticleRainWind)
								.controller(DoubleFieldControllerBuilder::create)
								.build())
						.option(Option.<Integer>createBuilder()
								.name(Component.translatable("more-leaf-particles.option.ticksToBlend.name"))
								.description(OptionDescription.of(Component.translatable("more-leaf-particles.option.ticksToBlend.desc")))
								.binding(
										20,
										() -> ModConfig.ticksToBlend,
										value -> {
											ModConfig.ticksToBlend = value;
											ModConfig.HANDLER.save();
										}
								)
								.controller(IntegerFieldControllerBuilder::create)
								.available(MoreLeafParticles.isParticleRainPresent() && ModConfig.enableParticleRainWind)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createCategory(String id, final Component translation, final Component singleLeafType, final Component pluralLeafType) {
		boolean enabled = (boolean) getConfigField("enable%s".formatted(capitalize(id)));

		return ConfigCategory.createBuilder()
				.name(Component.translatable("more-leaf-particles.category.name", translation))

				.option(boolOptWithTickBox(
						Component.translatable("more-leaf-particles.option.enable.name", translation),
						Component.translatable(
								"more-leaf-particles.option.enable.desc",
								translation.getString().toLowerCase(),
								singleLeafType,
								translation.getString().toLowerCase(),
								pluralLeafType
						)
				)
						.binding(
								true,
								() -> (Boolean) getConfigField("enable%s".formatted(capitalize(id))),
								value -> {
									setConfigField("enable%s".formatted(capitalize(id)), value);
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(enabled, OptionGroup.createBuilder()
						.name(Component.translatable("more-leaf-particles.group.color.name", translation))
						.description(OptionDescription.of(Component.translatable("more-leaf-particles.group.color.desc", translation.getString().toLowerCase(), singleLeafType)))

						.option(boolOptWithTickBox(
								Component.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Component.translatable("more-leaf-particles.option.enableCustomColor.desc")
						)
								.binding(
										false,
										() -> (Boolean) getConfigField("enable%sCustomColor".formatted(capitalize(id))),
										value -> {
											setConfigField("enable%sCustomColor".formatted(capitalize(id)), value);
											ModConfig.HANDLER.save();
										})
								.available(enabled)
								.build())

						.optionIf(enabled, boolOptWithTickBox(
								Component.translatable("more-leaf-particles.option.useTint.name"),
								Component.translatable("more-leaf-particles.option.useTint.desc")
						)
								.binding(
										true,
										() -> (Boolean) getConfigField("use%sTint".formatted(capitalize(id))),
										value -> {
											setConfigField("use%sTint".formatted(capitalize(id)), value);
											ModConfig.HANDLER.save();
										}
								)
								.available((Boolean) getConfigField("enable%sCustomColor".formatted(capitalize(id))))
								.build())

						.optionIf(enabled, Option.<Color>createBuilder()
								.name(Component.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Component.translatable("more-leaf-particles.option.color.desc")))
								.binding(
										new Color(0, 0, 0),
										() -> (Color) getConfigField("%sColor".formatted(id)),
										value -> {
											setConfigField("%sColor".formatted(id), value);
											ModConfig.HANDLER.save();
										}
								)
								.available((Boolean) getConfigField("enable%sCustomColor".formatted(capitalize(id))))
								.controller(ColorControllerBuilder::create)
								.build())
						.build())

				.groupIf(enabled, generalPhysicalProperties(id, translation, singleLeafType))
				.build();
	}

	private static OptionGroup generalPhysicalProperties(String id, Component translation, final Component singleLeafType) {
		return OptionGroup.createBuilder()
				.name(Component.translatable("more-leaf-particles.group.physicalProps.name", translation))
				.description(OptionDescription.of(Component.translatable("more-leaf-particles.group.physicalProps.desc", translation.getString().toLowerCase(), singleLeafType)))

				.option(boolOptWithTickBox(
						Component.translatable("more-leaf-particles.option.enableSize.name"),
						Component.translatable("more-leaf-particles.option.enableSize.desc")
				)
						.binding(
								false,
								() -> (Boolean) getConfigField("enable%sSize".formatted(capitalize(id))),
								value -> {
									setConfigField("enable%sSize".formatted(capitalize(id)), value);
									ModConfig.HANDLER.save();
								})
						.build())

				.option(Option.<Float>createBuilder()
						.name(Component.translatable("more-leaf-particles.option.size.name"))
						.description(OptionDescription.of(Component.translatable("more-leaf-particles.option.size.desc")))
						.binding(
								2.0F,
								() -> (Float) getConfigField("%sSize".formatted(id)),
								value -> {
									setConfigField("%sSize".formatted(id), value);
									ModConfig.HANDLER.save();
								}
						)
						.available((Boolean) getConfigField("enable%sSize".formatted(capitalize(id))))
						.controller(FloatFieldControllerBuilder::create)
						.build())

				.option(boolOptWithTickBox(
						Component.translatable("more-leaf-particles.option.enableGravity.name"),
						Component.translatable("more-leaf-particles.option.enableGravity.desc")
				)
						.binding(
								false,
								() -> (Boolean) getConfigField("enable%sGravity".formatted(capitalize(id))),
								value -> {
									setConfigField("enable%sGravity".formatted(capitalize(id)), value);
									ModConfig.HANDLER.save();
								}
						)
						.build())

				.option(Option.<Float>createBuilder()
						.name(Component.translatable("more-leaf-particles.option.gravity.name"))
						.description(OptionDescription.of(Component.translatable("more-leaf-particles.option.gravity.desc")))
						.binding(
								0.07F,
								() -> (Float) getConfigField("%sGravity".formatted(id)),
								value -> {
									setConfigField("%sGravity".formatted(id), value);
									ModConfig.HANDLER.save();
								}
						)
						.available((Boolean) getConfigField("enable%sGravity".formatted(capitalize(id))))
						.controller(FloatFieldControllerBuilder::create)
						.build())

				.option(boolOptWithTickBox(
						Component.translatable("more-leaf-particles.option.enableInitVelocity.name"),
						Component.translatable("more-leaf-particles.option.enableInitVelocity.desc")
				)
						.binding(
								false,
								() -> (Boolean) getConfigField("enable%sInitialVelocity".formatted(capitalize(id))),
								value -> {
									setConfigField("enable%sInitialVelocity".formatted(capitalize(id)), value);
									ModConfig.HANDLER.save();
								}
						)
						.build())

				.option(Option.<Float>createBuilder()
						.name(Component.translatable("more-leaf-particles.option.initVelocity.name"))
						.description(OptionDescription.of(Component.translatable("more-leaf-particles.option.initVelocity.desc")))
						.binding(
								0.021F,
								() -> (Float) getConfigField("%sInitialVelocity".formatted(id)),
								value -> {
									setConfigField("%sInitialVelocity".formatted(id), value);
									ModConfig.HANDLER.save();
								}
						)
						.available((Boolean) getConfigField("enable%sInitialVelocity".formatted(capitalize(id))))
						.controller(FloatFieldControllerBuilder::create)
						.build())

				.option(boolOptWithTickBox(
						Component.translatable("more-leaf-particles.option.enableWind.name"),
						Component.translatable("more-leaf-particles.option.enableWind.desc")
				)
						.binding(
								false,
								() -> (Boolean) getConfigField("enable%sWind".formatted(capitalize(id))),
								value -> {
									setConfigField("enable%sWind".formatted(capitalize(id)), value);
									ModConfig.HANDLER.save();
								}
						)
						.available(!MoreLeafParticles.isParticleRainPresent())
						.build())

				.option(Option.<Float>createBuilder()
						.name(Component.translatable("more-leaf-particles.option.wind.name"))
						.description(OptionDescription.of(Component.translatable("more-leaf-particles.option.wind.desc")))
						.binding(
								10.0F,
								() -> (Float) getConfigField("%sWind".formatted(id)),
								value -> {
									setConfigField("%sWind".formatted(id), value);
									ModConfig.HANDLER.save();
								}
						)
						.available((Boolean) getConfigField("enable%sWind".formatted(capitalize(id))) && !MoreLeafParticles.isParticleRainPresent())
						.controller(FloatFieldControllerBuilder::create)
						.build())

				.option(boolOptWithTickBox(
						Component.translatable("more-leaf-particles.option.flowAway.name"),
						Component.translatable("more-leaf-particles.option.flowAway.desc")
				)

						.binding(
								false,
								() -> (Boolean) getConfigField("%sFlowAway".formatted(id)),
								value -> {
									setConfigField("%sFlowAway".formatted(id), value);
									ModConfig.HANDLER.save();
								}
						)
						.build())
				.build();
	}

	private static ConfigCategory createUntintable(String id, final Component translation, final Component singleLeafType, final Component pluralLeafType) {
		boolean enabled = (boolean) getConfigField("enable%s".formatted(capitalize(id)));

		return ConfigCategory.createBuilder()
				.name(Component.translatable("more-leaf-particles.category.name", translation))

				.option(boolOptWithTickBox(
						Component.translatable("more-leaf-particles.option.enable.name", translation),
						Component.translatable(
								"more-leaf-particles.option.enable.desc",
								translation.getString().toLowerCase(),
								singleLeafType,
								translation.getString().toLowerCase(),
								pluralLeafType
						)
				)
						.binding(
								true,
								() -> (Boolean) getConfigField("enable%s".formatted(capitalize(id))),
								value -> {
									setConfigField("enable%s".formatted(capitalize(id)), value);
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(enabled, generalPhysicalProperties(id, translation, singleLeafType))
				.build();
	}

	private static Option.Builder<Boolean> boolOptWithTickBox(Component name, Component desc) {
		return Option.<Boolean>createBuilder()
				.name(name)
				.description(OptionDescription.of(desc))
				.controller(TickBoxControllerBuilder::create);
	}
}
