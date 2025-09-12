package net.fellter.moreLeafParticles.yacl;

import java.awt.*;
import java.util.Arrays;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.fellter.moreLeafParticles.ModParticles;
import org.jetbrains.annotations.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.TintedParticleEffect;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class YACLImpl {
	public static YetAnotherConfigLib create() {
		return YetAnotherConfigLib.createBuilder()
				.title(Text.translatable("more-leaf-particles.config.title"))
				.categories(Arrays.asList(
						createSpruceCategory(),
						createBirchCategory(),
						createMangroveCategory(),
						createJungleCategory(),
						createAcaciaCategory(),
						createAzaleaCategory(),
						createFloweringAzaleaCategory()
				))
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
						.name(Text.translatable("more-leaf-particles.spruce.group.size.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.spruce.group.size.desc")))

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
						.name(Text.translatable("more-leaf-particles.birch.group.size.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.birch.group.size.desc")))

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
						.name(Text.translatable("more-leaf-particles.mangrove.group.size.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.mangrove.group.size.desc")))

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
						.name(Text.translatable("more-leaf-particles.jungle.group.size.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.jungle.group.size.desc")))

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
						.name(Text.translatable("more-leaf-particles.acacia.group.size.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.acacia.group.size.desc")))

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
										2.0F,
										() -> ModConfig.acaciaSize,
										value -> {
											ModConfig.acaciaSize = value;
											ModConfig.HANDLER.save();
										}
								)
								.available(ModConfig.enableAcaciaSize)
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
						.name(Text.translatable("more-leaf-particles.azalea.group.size.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.azalea.group.size.desc")))

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
						.name(Text.translatable("more-leaf-particles.floweringAzalea.group.size.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.floweringAzalea.group.size.desc")))

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
						.build())
				.build();
	}

	private static Option.Builder<Boolean> boolOptWithTickBox(Text name, Text desc) {
		return Option.<Boolean>createBuilder()
				.name(name)
				.description(OptionDescription.of(desc))
				.controller(TickBoxControllerBuilder::create);
	}

	public static @Nullable TintedParticleEffect getTintedFromConfig(Block block, World world, BlockPos pos) {
		if (block == Blocks.SPRUCE_LEAVES && ModConfig.enableSpruce) {
			return TintedParticleEffect.create(ModParticles.SPRUCE_NEEDLES, world.getBlockColor(pos));
		} else if (block == Blocks.BIRCH_LEAVES && ModConfig.enableBirch) {
			return TintedParticleEffect.create(ModParticles.BIRCH_LEAVES, world.getBlockColor(pos));
		} else if (block == Blocks.MANGROVE_LEAVES && ModConfig.enableMangrove) {
			return TintedParticleEffect.create(ModParticles.MANGROVE_LEAVES, world.getBlockColor(pos));
		} else if (block == Blocks.JUNGLE_LEAVES && ModConfig.enableJungle) {
			return TintedParticleEffect.create(ModParticles.JUNGLE_LEAVES, world.getBlockColor(pos));
		} else if (block == Blocks.ACACIA_LEAVES && ModConfig.enableAcacia) {
			return TintedParticleEffect.create(ModParticles.ACACIA_LEAVES, world.getBlockColor(pos));
		}

		return null;
	}

	public static @Nullable ParticleEffect spawnParticleFromConfig(Block block) {
		if (block == Blocks.AZALEA_LEAVES && ModConfig.enableAzalea) {
			return ModParticles.AZALEA_LEAVES;
		} else if (block == Blocks.FLOWERING_AZALEA_LEAVES && ModConfig.enableFloweringAzalea) {
			return ModParticles.FLOWERING_AZALEA_PARTICLES;
		}

		return null;
	}
}
