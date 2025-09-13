package net.fellter.moreLeafParticles.yacl;

import java.awt.*;
import java.util.Arrays;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;

import net.minecraft.text.Text;

public class YACLImpl {
	public static YetAnotherConfigLib create() {
		return YetAnotherConfigLib.createBuilder()
				.title(Text.translatable("more-leaf-particles.config.title"))
				.categories(Arrays.asList(
						createOakCategory(),
						createSpruceCategory(),
						createBirchCategory(),
						createJungleCategory(),
						createAcaciaCategory(),
						createDarkOakCategory(),
						createMangroveCategory(),
						createCherryCategory(),
						createPaleOakCategory(),
						createAzaleaCategory(),
						createFloweringAzaleaCategory()
				))
				.build();
	}

	private static ConfigCategory createOakCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.oak.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.oak.option.enable.name"),
						Text.translatable("more-leaf-particles.oak.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableOak,
								value -> {
									ModConfig.enableOak = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableOak, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.oak.group.color.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.oak.group.color.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Text.translatable("more-leaf-particles.option.enableCustomColor.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableOakCustomColor,
										value -> {
											ModConfig.enableOakCustomColor = value;
											ModConfig.HANDLER.save();
										})
								.available(ModConfig.enableOak)
								.build())

						.optionIf(ModConfig.enableOak, boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.useTint.name"),
								Text.translatable("more-leaf-particles.option.useTint.desc")
						)
								.binding(
										true,
										() -> ModConfig.useOakTint,
										value -> {
											ModConfig.useOakTint = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableOakCustomColor)
								.build())

						.optionIf(ModConfig.enableOak, Option.<Color>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.color.desc")))
								.binding(
										new Color(0, 0, 0, 0),
										() -> ModConfig.oakColor,
										value -> {
											ModConfig.oakColor = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableOakCustomColor)
								.controller(ColorControllerBuilder::create)
								.build())
						.build())

				.groupIf(ModConfig.enableOak, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.oak.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.oak.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableOakSize,
										value -> {
											ModConfig.enableOakSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.oakSize,
										value -> {
											ModConfig.oakSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableOakSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableOakGravity,
										value -> {
											ModConfig.enableOakGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.oakGravity,
										value -> {
											ModConfig.oakGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableOakGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableOakInitialVelocity,
										value -> {
											ModConfig.enableOakInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.oakInitialVelocity,
										value -> {
											ModConfig.oakInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableOakInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createDarkOakCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.darkOak.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.darkOak.option.enable.name"),
						Text.translatable("more-leaf-particles.darkOak.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableDarkOak,
								value -> {
									ModConfig.enableDarkOak = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableDarkOak, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.darkOak.group.color.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.darkOak.group.color.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Text.translatable("more-leaf-particles.option.enableCustomColor.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableDarkOakCustomColor,
										value -> {
											ModConfig.enableDarkOakCustomColor = value;
											ModConfig.HANDLER.save();
										})
								.available(ModConfig.enableDarkOak)
								.build())

						.optionIf(ModConfig.enableDarkOak, boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.useTint.name"),
								Text.translatable("more-leaf-particles.option.useTint.desc")
						)
								.binding(
										true,
										() -> ModConfig.useDarkOakTint,
										value -> {
											ModConfig.useDarkOakTint = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableDarkOakCustomColor)
								.build())

						.optionIf(ModConfig.enableDarkOak, Option.<Color>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.color.desc")))
								.binding(
										new Color(0, 0, 0, 0),
										() -> ModConfig.darkOakColor,
										value -> {
											ModConfig.darkOakColor = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableDarkOakCustomColor)
								.controller(ColorControllerBuilder::create)
								.build())
						.build())

				.groupIf(ModConfig.enableDarkOak, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.darkOak.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.darkOak.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableDarkOakSize,
										value -> {
											ModConfig.enableDarkOakSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.darkOakSize,
										value -> {
											ModConfig.darkOakSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableDarkOakSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableDarkOakGravity,
										value -> {
											ModConfig.enableDarkOakGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.darkOakGravity,
										value -> {
											ModConfig.darkOakGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableDarkOakGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableDarkOakInitialVelocity,
										value -> {
											ModConfig.enableDarkOakInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.darkOakInitialVelocity,
										value -> {
											ModConfig.darkOakInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableDarkOakInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createSpruceCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.spruce.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.spruce.option.enable.name"),
						Text.translatable("more-leaf-particles.spruce.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableSpruce,
								value -> {
									ModConfig.enableSpruce = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableSpruce, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.spruce.group.color.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.spruce.group.color.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Text.translatable("more-leaf-particles.option.enableCustomColor.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableSpruceCustomColor,
										value -> {
											ModConfig.enableSpruceCustomColor = value;
											ModConfig.HANDLER.save();
										})
								.available(ModConfig.enableSpruce)
								.build())

						.optionIf(ModConfig.enableSpruce, boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.useTint.name"),
								Text.translatable("more-leaf-particles.option.useTint.desc")
						)
								.binding(
										true,
										() -> ModConfig.useSpruceTint,
										value -> {
											ModConfig.useSpruceTint = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableSpruceCustomColor)
								.build())

						.optionIf(ModConfig.enableSpruce, Option.<Color>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.color.desc")))
								.binding(
										new Color(0, 0, 0, 0),
										() -> ModConfig.spruceColor,
										value -> {
											ModConfig.spruceColor = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableSpruceCustomColor)
								.controller(ColorControllerBuilder::create)
								.build())
						.build())

				.groupIf(ModConfig.enableSpruce, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.spruce.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.spruce.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableSpruceSize,
										value -> {
											ModConfig.enableSpruceSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.spruceSize,
										value -> {
											ModConfig.spruceSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableSpruceSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableSpruceGravity,
										value -> {
											ModConfig.enableSpruceGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.spruceGravity,
										value -> {
											ModConfig.spruceGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableSpruceGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableSpruceInitialVelocity,
										value -> {
											ModConfig.enableSpruceInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.spruceInitialVelocity,
										value -> {
											ModConfig.spruceInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableSpruceInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.build())
				.build();
	}

	private static ConfigCategory createBirchCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.birch.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.birch.option.enable.name"),
						Text.translatable("more-leaf-particles.birch.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableBirch,
								value -> {
									ModConfig.enableBirch = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableBirch, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.birch.group.color.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.birch.group.color.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Text.translatable("more-leaf-particles.option.enableCustomColor.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableBirchCustomColor,
										value -> {
											ModConfig.enableBirchCustomColor = value;
											ModConfig.HANDLER.save();
										})
								.available(ModConfig.enableBirch)
								.build())

						.optionIf(ModConfig.enableBirch, boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.useTint.name"),
								Text.translatable("more-leaf-particles.option.useTint.desc")
						)
								.binding(
										true,
										() -> ModConfig.useBirchTint,
										value -> {
											ModConfig.useBirchTint = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableBirchCustomColor)
								.build())

						.optionIf(ModConfig.enableBirch, Option.<Color>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.color.desc")))
								.binding(
										new Color(0, 0, 0, 0),
										() -> ModConfig.birchColor,
										value -> {
											ModConfig.birchColor = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableBirchCustomColor)
								.controller(ColorControllerBuilder::create)
								.build())
						.build())

				.groupIf(ModConfig.enableBirch, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.birch.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.birch.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableBirchSize,
										value -> {
											ModConfig.enableBirchSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.birchSize,
										value -> {
											ModConfig.birchSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableBirchSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableBirchGravity,
										value -> {
											ModConfig.enableBirchGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.birchGravity,
										value -> {
											ModConfig.birchGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableBirchGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableBirchInitialVelocity,
										value -> {
											ModConfig.enableBirchInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.birchInitialVelocity,
										value -> {
											ModConfig.birchInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableBirchInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createMangroveCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.mangrove.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.mangrove.option.enable.name"),
						Text.translatable("more-leaf-particles.mangrove.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableMangrove,
								value -> {
									ModConfig.enableMangrove = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableMangrove, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.mangrove.group.color.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.mangrove.group.color.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Text.translatable("more-leaf-particles.option.enableCustomColor.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableMangroveCustomColor,
										value -> {
											ModConfig.enableMangroveCustomColor = value;
											ModConfig.HANDLER.save();
										})
								.available(ModConfig.enableMangrove)
								.build())

						.optionIf(ModConfig.enableMangrove, boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.useTint.name"),
								Text.translatable("more-leaf-particles.option.useTint.desc")
						)
								.binding(
										true,
										() -> ModConfig.useMangroveTint,
										value -> {
											ModConfig.useMangroveTint = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableMangroveCustomColor)
								.build())

						.optionIf(ModConfig.enableMangrove, Option.<Color>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.color.desc")))
								.binding(
										new Color(0, 0, 0, 0),
										() -> ModConfig.mangroveColor,
										value -> {
											ModConfig.mangroveColor = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableMangroveCustomColor)
								.controller(ColorControllerBuilder::create)
								.build())
						.build())

				.groupIf(ModConfig.enableMangrove, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.mangrove.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.mangrove.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableMangroveSize,
										value -> {
											ModConfig.enableMangroveSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.mangroveSize,
										value -> {
											ModConfig.mangroveSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableMangroveSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableMangroveGravity,
										value -> {
											ModConfig.enableMangroveGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.mangroveGravity,
										value -> {
											ModConfig.mangroveGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableMangroveGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableMangroveInitialVelocity,
										value -> {
											ModConfig.enableMangroveInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.mangroveInitialVelocity,
										value -> {
											ModConfig.mangroveInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableMangroveInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createJungleCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.jungle.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.jungle.option.enable.name"),
						Text.translatable("more-leaf-particles.jungle.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableJungle,
								value -> {
									ModConfig.enableJungle = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableJungle, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.jungle.group.color.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.jungle.group.color.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Text.translatable("more-leaf-particles.option.enableCustomColor.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableJungleCustomColor,
										value -> {
											ModConfig.enableJungleCustomColor = value;
											ModConfig.HANDLER.save();
										})
								.available(ModConfig.enableJungle)
								.build())

						.optionIf(ModConfig.enableJungle, boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.useTint.name"),
								Text.translatable("more-leaf-particles.option.useTint.desc")
						)
								.binding(
										true,
										() -> ModConfig.useJungleTint,
										value -> {
											ModConfig.useJungleTint = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableJungleCustomColor)
								.build())

						.optionIf(ModConfig.enableJungle, Option.<Color>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.color.desc")))
								.binding(
										new Color(0, 0, 0, 0),
										() -> ModConfig.jungleColor,
										value -> {
											ModConfig.jungleColor = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableJungleCustomColor)
								.controller(ColorControllerBuilder::create)
								.build())
						.build())

				.groupIf(ModConfig.enableJungle, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.jungle.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.jungle.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableJungleSize,
										value -> {
											ModConfig.enableJungleSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.jungleSize,
										value -> {
											ModConfig.jungleSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableJungleSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableJungleGravity,
										value -> {
											ModConfig.enableJungleGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.jungleGravity,
										value -> {
											ModConfig.jungleGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableJungleGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableJungleInitialVelocity,
										value -> {
											ModConfig.enableJungleInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.jungleInitialVelocity,
										value -> {
											ModConfig.jungleInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableJungleInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createAcaciaCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.acacia.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.acacia.option.enable.name"),
						Text.translatable("more-leaf-particles.acacia.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableAcacia,
								value -> {
									ModConfig.enableAcacia = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableAcacia, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.acacia.group.color.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.acacia.group.color.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Text.translatable("more-leaf-particles.option.enableCustomColor.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableAcaciaCustomColor,
										value -> {
											ModConfig.enableAcaciaCustomColor = value;
											ModConfig.HANDLER.save();
										})
								.available(ModConfig.enableAcacia)
								.build())

						.optionIf(ModConfig.enableAcacia, boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.useTint.name"),
								Text.translatable("more-leaf-particles.option.useTint.desc")
						)
								.binding(
										true,
										() -> ModConfig.useAcaciaTint,
										value -> {
											ModConfig.useAcaciaTint = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableAcaciaCustomColor)
								.build())

						.optionIf(ModConfig.enableAcacia, Option.<Color>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.color.desc")))
								.binding(
										new Color(0, 0, 0, 0),
										() -> ModConfig.acaciaColor,
										value -> {
											ModConfig.acaciaColor = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableAcaciaCustomColor)
								.controller(ColorControllerBuilder::create)
								.build())
						.build())

				.groupIf(ModConfig.enableAcacia, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.acacia.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.acacia.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableAcaciaSize,
										value -> {
											ModConfig.enableAcaciaSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										1.66F,
										() -> ModConfig.acaciaSize,
										value -> {
											ModConfig.acaciaSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableAcaciaSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableAcaciaGravity,
										value -> {
											ModConfig.enableAcaciaGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.acaciaGravity,
										value -> {
											ModConfig.acaciaGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableAcaciaGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableAcaciaInitialVelocity,
										value -> {
											ModConfig.enableAcaciaInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.acaciaInitialVelocity,
										value -> {
											ModConfig.acaciaInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableAcaciaInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createPaleOakCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.paleOak.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.paleOak.option.enable.name"),
						Text.translatable("more-leaf-particles.paleOak.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enablePaleOak,
								value -> {
									ModConfig.enablePaleOak = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enablePaleOak, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.paleOak.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.paleOak.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enablePaleOakSize,
										value -> {
											ModConfig.enablePaleOakSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.paleOakSize,
										value -> {
											ModConfig.paleOakSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enablePaleOakSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enablePaleOakGravity,
										value -> {
											ModConfig.enablePaleOakGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.paleOakGravity,
										value -> {
											ModConfig.paleOakGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enablePaleOakGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enablePaleOakInitialVelocity,
										value -> {
											ModConfig.enablePaleOakInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.paleOakInitialVelocity,
										value -> {
											ModConfig.paleOakInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enablePaleOakInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createCherryCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.cherry.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.cherry.option.enable.name"),
						Text.translatable("more-leaf-particles.cherry.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableCherry,
								value -> {
									ModConfig.enableCherry = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableCherry, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.cherry.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.cherry.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableCherrySize,
										value -> {
											ModConfig.enableCherrySize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.cherrySize,
										value -> {
											ModConfig.cherrySize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableCherrySize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableCherryGravity,
										value -> {
											ModConfig.enableCherryGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.cherryGravity,
										value -> {
											ModConfig.cherryGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableCherryGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableCherryInitialVelocity,
										value -> {
											ModConfig.enableCherryInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.cherryInitialVelocity,
										value -> {
											ModConfig.cherryInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableCherryInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createAzaleaCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.azalea.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.azalea.option.enable.name"),
						Text.translatable("more-leaf-particles.azalea.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableAzalea,
								value -> {
									ModConfig.enableAzalea = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableAzalea, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.azalea.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.azalea.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableAzaleaSize,
										value -> {
											ModConfig.enableAzaleaSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.azaleaSize,
										value -> {
											ModConfig.azaleaSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableAzaleaSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableAzaleaGravity,
										value -> {
											ModConfig.enableAzaleaGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.azaleaGravity,
										value -> {
											ModConfig.azaleaGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableAzaleaGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableAzaleaInitialVelocity,
										value -> {
											ModConfig.enableAzaleaInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.azaleaInitialVelocity,
										value -> {
											ModConfig.azaleaInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableAzaleaInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static ConfigCategory createFloweringAzaleaCategory() {
		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.floweringAzalea.name"))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.floweringAzalea.option.enable.name"),
						Text.translatable("more-leaf-particles.floweringAzalea.option.enable.desc")
				)
						.binding(
								true,
								() -> ModConfig.enableFloweringAzalea,
								value -> {
									ModConfig.enableFloweringAzalea = value;
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(ModConfig.enableFloweringAzalea, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.floweringAzalea.group.physicalProps.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.floweringAzalea.group.physicalProps.desc")))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableSize.name"),
								Text.translatable("more-leaf-particles.option.enableSize.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableFloweringAzaleaSize,
										value -> {
											ModConfig.enableFloweringAzaleaSize = value;
											ModConfig.HANDLER.save();
										})
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.size.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
								.binding(
										2.0F,
										() -> ModConfig.floweringAzaleaSize,
										value -> {
											ModConfig.floweringAzaleaSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableFloweringAzaleaSize)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableGravity.name"),
								Text.translatable("more-leaf-particles.option.enableGravity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableFloweringAzaleaGravity,
										value -> {
											ModConfig.enableFloweringAzaleaGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.gravity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.floweringAzaleaGravity,
										value -> {
											ModConfig.floweringAzaleaGravity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableFloweringAzaleaGravity)
								.controller(FloatFieldControllerBuilder::create)
								.build())

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
								Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
						)
								.binding(
										false,
										() -> ModConfig.enableFloweringAzaleaInitialVelocity,
										value -> {
											ModConfig.enableFloweringAzaleaInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.build())

						.option(Option.<Float>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
								.binding(
										0.07F,
										() -> ModConfig.floweringAzaleaInitialVelocity,
										value -> {
											ModConfig.floweringAzaleaInitialVelocity = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableFloweringAzaleaInitialVelocity)
								.controller(FloatFieldControllerBuilder::create)
								.build())
						.build())
				.build();
	}

	private static Option.Builder<Boolean> boolOptWithTickBox(Text name, Text desc) {
		return Option.<Boolean>createBuilder()
				.name(name)
				.description(OptionDescription.of(desc))
				.controller(TickBoxControllerBuilder::create);
	}
}
