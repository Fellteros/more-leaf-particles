package net.fellter.moreLeafParticles.yacl;

import java.awt.*;
import java.util.Arrays;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;

import net.minecraft.text.Text;

import static net.fabricmc.loader.impl.util.StringUtil.capitalize;

public class YACLImpl {
	public static YetAnotherConfigLib create() {
		return YetAnotherConfigLib.createBuilder()
				.title(Text.translatable("more-leaf-particles.config.title"))
				.categories(Arrays.asList(
						createCategory("oak", Text.translatable("more-leaf-particles.category.translation.oak"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("spruce", Text.translatable("more-leaf-particles.category.translation.spruce"), Text.translatable("more-leaf-particles.leafType.needle.singular"), Text.translatable("more-leaf-particles.leafType.needle.plural")),
						createCategory("birch", Text.translatable("more-leaf-particles.category.translation.birch"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("jungle", Text.translatable("more-leaf-particles.category.translation.jungle"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("acacia", Text.translatable("more-leaf-particles.category.translation.acacia"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("darkOak", Text.translatable("more-leaf-particles.category.translation.darkOak"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaf.plural")),
						createCategory("mangrove", Text.translatable("more-leaf-particles.category.translation.mangrove"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaf.plural")),
						createUntintable("cherry", Text.translatable("more-leaf-particles.category.translation.cherry"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaf.plural")),
						createUntintable("paleOak", Text.translatable("more-leaf-particles.category.translation.paleOak"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaf.plural")),
						createUntintable("azalea", Text.translatable("more-leaf-particles.category.translation.azalea"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaf.plural")),
						createUntintable("floweringAzalea", Text.translatable("more-leaf-particles.category.translation.floweringAzalea"), Text.translatable("more-leaf-particles.leafType.leaf.singular"), Text.translatable("more-leaf-particles.leafType.leaves_and_petals"))
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

	private static ConfigCategory createCategory(String id, final Text translation, final Text singleLeafType, final Text pluralLeafType) {
		boolean enabled = (boolean) getConfigField("enable%s".formatted(capitalize(id)));

		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.name", translation))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.option.enable.name", translation),
						Text.translatable(
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
						.name(Text.translatable("more-leaf-particles.group.color.name", translation))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.group.color.desc", translation.getString().toLowerCase(), singleLeafType)))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Text.translatable("more-leaf-particles.option.enableCustomColor.desc")
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
								Text.translatable("more-leaf-particles.option.useTint.name"),
								Text.translatable("more-leaf-particles.option.useTint.desc")
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
								.name(Text.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.color.desc")))
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

	private static OptionGroup generalPhysicalProperties(String id, Text translation, final Text singleLeafType) {
		return OptionGroup.createBuilder()
				.name(Text.translatable("more-leaf-particles.group.physicalProps.name", translation))
				.description(OptionDescription.of(Text.translatable("more-leaf-particles.group.physicalProps.desc", translation.getString().toLowerCase(), singleLeafType)))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.option.enableSize.name"),
						Text.translatable("more-leaf-particles.option.enableSize.desc")
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
						.name(Text.translatable("more-leaf-particles.option.size.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
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
						Text.translatable("more-leaf-particles.option.enableGravity.name"),
						Text.translatable("more-leaf-particles.option.enableGravity.desc")
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
						.name(Text.translatable("more-leaf-particles.option.gravity.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
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
						Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
						Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
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
						.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
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
						Text.translatable("more-leaf-particles.option.enableWind.name"),
						Text.translatable("more-leaf-particles.option.enableWind.desc")
				)
						.binding(
								false,
								() -> (Boolean) getConfigField("enable%sWind".formatted(capitalize(id))),
								value -> {
									setConfigField("enable%sWind".formatted(capitalize(id)), value);
									ModConfig.HANDLER.save();
								}
						)
						.build())

				.option(Option.<Float>createBuilder()
						.name(Text.translatable("more-leaf-particles.option.wind.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.wind.desc")))
						.binding(
								10.0F,
								() -> (Float) getConfigField("%sWind".formatted(id)),
								value -> {
									setConfigField("%sWind".formatted(id), value);
									ModConfig.HANDLER.save();
								}
						)
						.available((Boolean) getConfigField("enable%sWind".formatted(capitalize(id))))
						.controller(FloatFieldControllerBuilder::create)
						.build())

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.option.flowAway.name"),
						Text.translatable("more-leaf-particles.option.flowAway.desc")
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

	private static ConfigCategory createUntintable(String id, final Text translation, final Text singleLeafType, final Text pluralLeafType) {
		boolean enabled = (boolean) getConfigField("enable%s".formatted(capitalize(id)));

		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.name", translation))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.option.enable.name", translation),
						Text.translatable(
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

	private static Option.Builder<Boolean> boolOptWithTickBox(Text name, Text desc) {
		return Option.<Boolean>createBuilder()
				.name(name)
				.description(OptionDescription.of(desc))
				.controller(TickBoxControllerBuilder::create);
	}

	public static String decapitalize(String s) {
		if (s.isEmpty()) return s;

		int pos;

		for (pos = 0; pos < s.length(); pos++) {
			if (Character.isLetterOrDigit(s.codePointAt(pos))) {
				break;
			}
		}

		if (pos == s.length()) return s;

		int cp = s.codePointAt(pos);
		int cpUpper = Character.toLowerCase(cp);
		if (cpUpper == cp) return s;

		StringBuilder ret = new StringBuilder(s.length());
		ret.append(s, 0, pos);
		ret.appendCodePoint(cpUpper);
		ret.append(s, pos + Character.charCount(cp), s.length());

		return ret.toString();
	}
}
