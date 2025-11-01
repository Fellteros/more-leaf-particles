package net.fellter.moreLeafParticles.yacl;

import java.awt.*;
import java.util.Arrays;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.ColorControllerBuilder;
import dev.isxander.yacl3.api.controller.FloatFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;

import net.minecraft.text.Text;

import net.fabricmc.loader.impl.util.StringUtil;

public class YACLImpl {
	public static YetAnotherConfigLib create() {
		return YetAnotherConfigLib.createBuilder()
				.title(Text.translatable("more-leaf-particles.config.title"))
				.categories(Arrays.asList(
						createCategory("oak"),
						createCategory("spruce"),
						createCategory("birch"),
						createCategory("jungle"),
						createCategory("acacia"),
						createCategory("darkOak"),
						createCategory("mangrove"),
						createUntintable("cherry"),
						createUntintable("paleOak"),
						createUntintable("azalea"),
						createUntintable("floweringAzalea")
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

	private static ConfigCategory createCategory(String name) {
		name = StringUtil.capitalize(name);
		final String finalName = name;
		final String decapitalized = decapitalize(finalName);
		boolean enabled = (boolean) getConfigField("enable%s".formatted(name));

		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.%s.name".formatted(decapitalized)))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.%s.option.enable.name".formatted(decapitalized)),
						Text.translatable("more-leaf-particles.%s.option.enable.desc".formatted(decapitalized))
				)
						.binding(
								true,
								() -> (Boolean) getConfigField("enable%s".formatted(finalName)),
								value -> {
									setConfigField("enable%s".formatted(finalName), value);
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(enabled, OptionGroup.createBuilder()
						.name(Text.translatable("more-leaf-particles.%s.group.color.name".formatted(decapitalized)))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.%s.group.color.desc".formatted(decapitalized))))

						.option(boolOptWithTickBox(
								Text.translatable("more-leaf-particles.option.enableCustomColor.name"),
								Text.translatable("more-leaf-particles.option.enableCustomColor.desc")
						)
								.binding(
										false,
										() -> (Boolean) getConfigField("enable%sCustomColor".formatted(finalName)),
										value -> {
											setConfigField("enable%sCustomColor".formatted(finalName), value);
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
										() -> (Boolean) getConfigField("use%sTint".formatted(finalName)),
										value -> {
											setConfigField("use%sTint".formatted(finalName), value);
											ModConfig.HANDLER.save();
										}
								)
								.available((Boolean) getConfigField("enable%sCustomColor".formatted(name)))
								.build())

						.optionIf(enabled, Option.<Color>createBuilder()
								.name(Text.translatable("more-leaf-particles.option.color.name"))
								.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.color.desc")))
								.binding(
										new Color(0, 0, 0),
										() -> (Color) getConfigField("%sColor".formatted(decapitalized)),
										value -> {
											setConfigField("%sColor".formatted(decapitalized), value);
											ModConfig.HANDLER.save();
										}
								)
								.available((Boolean) getConfigField("enable%sCustomColor".formatted(name)))
								.controller(ColorControllerBuilder::create)
								.build())
						.build())

				.groupIf(enabled, generalPhysicalProperties(name, decapitalized))
				.build();
	}

	private static OptionGroup generalPhysicalProperties(String name, String lowerCase) {
		return OptionGroup.createBuilder()
				.name(Text.translatable("more-leaf-particles.%s.group.physicalProps.name".formatted(lowerCase)))
				.description(OptionDescription.of(Text.translatable("more-leaf-particles.%s.group.physicalProps.desc".formatted(lowerCase))))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.option.enableSize.name"),
						Text.translatable("more-leaf-particles.option.enableSize.desc")
				)
						.binding(
								false,
								() -> (Boolean) getConfigField("enable%sSize".formatted(name)),
								value -> {
									setConfigField("enable%sSize".formatted(name), value);
									ModConfig.HANDLER.save();
								})
						.build())

				.option(Option.<Float>createBuilder()
						.name(Text.translatable("more-leaf-particles.option.size.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.size.desc")))
						.binding(
								2.0F,
								() -> (Float) getConfigField("%sSize".formatted(lowerCase)),
								value -> {
									setConfigField("%sSize".formatted(lowerCase), value);
									ModConfig.HANDLER.save();
								}
						)
						.available((Boolean) getConfigField("enable%sSize".formatted(name)))
						.controller(FloatFieldControllerBuilder::create)
						.build())

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.option.enableGravity.name"),
						Text.translatable("more-leaf-particles.option.enableGravity.desc")
				)
						.binding(
								false,
								() -> (Boolean) getConfigField("enable%sGravity".formatted(name)),
								value -> {
									setConfigField("enable%sGravity".formatted(name), value);
									ModConfig.HANDLER.save();
								}
						)
						.build())

				.option(Option.<Float>createBuilder()
						.name(Text.translatable("more-leaf-particles.option.gravity.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.gravity.desc")))
						.binding(
								0.07F,
								() -> (Float) getConfigField("%sGravity".formatted(lowerCase)),
								value -> {
									setConfigField("%sGravity".formatted(lowerCase), value);
									ModConfig.HANDLER.save();
								}
						)
						.available((Boolean) getConfigField("enable%sGravity".formatted(name)))
						.controller(FloatFieldControllerBuilder::create)
						.build())

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.option.enableInitVelocity.name"),
						Text.translatable("more-leaf-particles.option.enableInitVelocity.desc")
				)
						.binding(
								false,
								() -> (Boolean) getConfigField("enable%sInitialVelocity".formatted(name)),
								value -> {
									setConfigField("enable%sInitialVelocity".formatted(name), value);
									ModConfig.HANDLER.save();
								}
						)
						.build())

				.option(Option.<Float>createBuilder()
						.name(Text.translatable("more-leaf-particles.option.initVelocity.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.initVelocity.desc")))
						.binding(
								0.07F,
								() -> (Float) getConfigField("%sInitialVelocity".formatted(lowerCase)),
								value -> {
									setConfigField("%sInitialVelocity".formatted(lowerCase), value);
									ModConfig.HANDLER.save();
								}
						)
						.available((Boolean) getConfigField("enable%sInitialVelocity".formatted(name)))
						.controller(FloatFieldControllerBuilder::create)
						.build())

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.option.enableWind.name"),
						Text.translatable("more-leaf-particles.option.enableWind.desc")
				)
						.binding(
								false,
								() -> (Boolean) getConfigField("enable%sWind".formatted(name)),
								value -> {
									setConfigField("enable%sWind".formatted(name), value);
									ModConfig.HANDLER.save();
								}
						)
						.build())

				.option(Option.<Float>createBuilder()
						.name(Text.translatable("more-leaf-particles.option.wind.name"))
						.description(OptionDescription.of(Text.translatable("more-leaf-particles.option.wind.desc")))
						.binding(
								10.0F,
								() -> (Float) getConfigField("%sWind".formatted(lowerCase)),
								value -> {
									setConfigField("%sWind".formatted(lowerCase), value);
									ModConfig.HANDLER.save();
								}
						)
						.available((Boolean) getConfigField("enable%sWind".formatted(name)))
						.controller(FloatFieldControllerBuilder::create)
						.build())

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.option.flowAway.name"),
						Text.translatable("more-leaf-particles.option.flowAway.desc")
				)

						.binding(
								false,
								() -> (Boolean) getConfigField("%sFlowAway".formatted(lowerCase)),
								value -> {
									setConfigField("%sFlowAway".formatted(lowerCase), value);
									ModConfig.HANDLER.save();
								}
						)
						.build())
				.build();
	}

	private static ConfigCategory createUntintable(String name) {
		name = StringUtil.capitalize(name);
		final String finalName = name;
		final String decapitalized = decapitalize(finalName);
		boolean enabled = (boolean) getConfigField("enable%s".formatted(name));

		return ConfigCategory.createBuilder()
				.name(Text.translatable("more-leaf-particles.category.%s.name".formatted(decapitalized)))

				.option(boolOptWithTickBox(
						Text.translatable("more-leaf-particles.%s.option.enable.name".formatted(decapitalized)),
						Text.translatable("more-leaf-particles.%s.option.enable.desc".formatted(decapitalized))
				)
						.binding(
								true,
								() -> (Boolean) getConfigField("enable%s".formatted(finalName)),
								value -> {
									setConfigField("enable%s".formatted(finalName), value);
									ModConfig.HANDLER.save();
								}
						).build())

				.groupIf(enabled, generalPhysicalProperties(name, decapitalized))
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