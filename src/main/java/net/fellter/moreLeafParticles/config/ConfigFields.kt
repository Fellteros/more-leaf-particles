package net.fellter.moreLeafParticles.config

import dev.isxander.yacl3.config.v2.api.SerialEntry
import java.awt.Color

/*
 * Has to be a class instead of an object because YACL relies on
 * retrieving a no-arg primary constructor, and objects do not have one.
 */
class ConfigFields {
	companion object {
		/* COMMON */
		@JvmField @SerialEntry("enable_particle_rain_wind")
		var enableParticleRainWind: Boolean = true

		@JvmField @SerialEntry("particle_rain_wind_multiplier")
		var particleRainWindMultiplier: Double = 1.0

		@JvmField @SerialEntry("ticks_to_blend")
		var ticksToBlend: Int = 20

		/* OAK */
		@JvmField @SerialEntry(value = "enable_oak")
		var enableOak: Boolean = true

		@JvmField @SerialEntry(value = "oak_multiplier")
		var oakMultiplier: Int = 1

		@JvmField @SerialEntry(value = "oak_size")
		var oakSize: Float = 2.0f

		@JvmField @SerialEntry(value = "oak_rotate")
		var oakRotate: Boolean = true

		@JvmField @SerialEntry(value = "enable_oak_custom_color")
		var enableOakCustomColor: Boolean = false

		@JvmField @SerialEntry(value = "oak_color")
		var oakColor: Color = Color(0, 0, 0, 0)

		@JvmField @SerialEntry(value = "enable_oak_tint")
		var useOakTint: Boolean = true

		@JvmField @SerialEntry(value = "oak_gravity")
		var oakGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "oak_wind")
		var oakWind: Float = 10.0f

		@JvmField @SerialEntry(value = "oak_flow_away")
		var oakFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "oak_init_velocity")
		var oakInitialVelocity: Float = 0.021f

		/* DARK OAK */
		@JvmField @SerialEntry(value = "enable_dark_oak")
		var enableDarkOak: Boolean = true

		@JvmField @SerialEntry(value = "dark_oak_multiplier")
		var darkOakMultiplier: Int = 1

		@JvmField @SerialEntry(value = "dark_oak_size")
		var darkOakSize: Float = 2.0f

		@JvmField @SerialEntry(value = "dark_oak_rotate")
		var darkOakRotate: Boolean = true

		@JvmField @SerialEntry(value = "enable_dark_oak_custom_color")
		var enableDarkOakCustomColor: Boolean = false

		@JvmField @SerialEntry(value = "dark_oak_color")
		var darkOakColor: Color = Color(0, 0, 0, 0)

		@JvmField @SerialEntry(value = "enable_dark_oak_tint")
		var useDarkOakTint: Boolean = true

		@JvmField @SerialEntry(value = "dark_oak_gravity")
		var darkOakGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "dark_oak_wind")
		var darkOakWind: Float = 10f

		@JvmField @SerialEntry(value = "dark_oak_flow_away")
		var darkOakFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "dark_oak_init_velocity")
		var darkOakInitialVelocity: Float = 0.021f

		/* SPRUCE */
		@JvmField @SerialEntry(value = "enable_spruce")
		var enableSpruce: Boolean = true

		@JvmField @SerialEntry(value = "spruce_multiplier")
		var spruceMultiplier: Int = 1

		@JvmField @SerialEntry(value = "spruce_size")
		var spruceSize: Float = 2.0f

		@JvmField @SerialEntry(value = "spruce_rotate")
		var spruceRotate: Boolean = true

		@JvmField @SerialEntry(value = "enable_spruce_custom_color")
		var enableSpruceCustomColor: Boolean = false

		@JvmField @SerialEntry(value = "spruce_color")
		var spruceColor: Color = Color(0, 0, 0, 0)

		@JvmField @SerialEntry(value = "enable_spruce_tint")
		var useSpruceTint: Boolean = true

		@JvmField @SerialEntry(value = "spruce_gravity")
		var spruceGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "spruce_wind")
		var spruceWind: Float = 10f

		@JvmField @SerialEntry(value = "spruce_flow_away")
		var spruceFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "spruce_init_velocity")
		var spruceInitialVelocity: Float = 0.021f

		/* BIRCH */
		@JvmField @SerialEntry(value = "enable_birch")
		var enableBirch: Boolean = true

		@JvmField @SerialEntry(value = "birch_multiplier")
		var birchMultiplier: Int = 1

		@JvmField @SerialEntry(value = "birch_size")
		var birchSize: Float = 2.0f

		@JvmField @SerialEntry(value = "birch_rotate")
		var birchRotate: Boolean = true

		@JvmField @SerialEntry(value = "enable_birch_custom_color")
		var enableBirchCustomColor: Boolean = false

		@JvmField @SerialEntry(value = "birch_color")
		var birchColor: Color = Color(0, 0, 0, 0)

		@JvmField @SerialEntry(value = "enable_birch_tint")
		var useBirchTint: Boolean = true

		@JvmField @SerialEntry(value = "birch_gravity")
		var birchGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "birch_wind")
		var birchWind: Float = 10f

		@JvmField @SerialEntry(value = "birch_flow_away")
		var birchFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "birch_init_velocity")
		var birchInitialVelocity: Float = 0.021f

		/* MANGROVE */
		@JvmField @SerialEntry(value = "enable_mangrove")
		var enableMangrove: Boolean = true

		@JvmField @SerialEntry(value = "mangrove_multiplier")
		var mangroveMultiplier: Int = 1

		@JvmField @SerialEntry(value = "mangrove_size")
		var mangroveSize: Float = 2.0f

		@JvmField @SerialEntry(value = "mangrove_rotate")
		var mangroveRotate: Boolean = true

		@JvmField @SerialEntry(value = "enable_mangrove_custom_color")
		var enableMangroveCustomColor: Boolean = false

		@JvmField @SerialEntry(value = "mangrove_color")
		var mangroveColor: Color = Color(0, 0, 0, 0)

		@JvmField @SerialEntry(value = "enable_mangrove_tint")
		var useMangroveTint: Boolean = true

		@JvmField @SerialEntry(value = "mangrove_gravity")
		var mangroveGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "mangrove_wind")
		var mangroveWind: Float = 10f

		@JvmField @SerialEntry(value = "mangrove_flow_away")
		var mangroveFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "mangrove_init_velocity")
		var mangroveInitialVelocity: Float = 0.021f

		/* JUNGLE */
		@JvmField @SerialEntry(value = "enable_jungle")
		var enableJungle: Boolean = true

		@JvmField @SerialEntry(value = "jungle_multiplier")
		var jungleMultiplier: Int = 1

		@JvmField @SerialEntry(value = "jungle_size")
		var jungleSize: Float = 2.0f

		@JvmField @SerialEntry(value = "jungle_rotate")
		var jungleRotate: Boolean = true

		@JvmField @SerialEntry(value = "enable_jungle_custom_color")
		var enableJungleCustomColor: Boolean = false

		@JvmField @SerialEntry(value = "jungle_color")
		var jungleColor: Color = Color(0, 0, 0, 0)

		@JvmField @SerialEntry(value = "enable_jungle_tint")
		var useJungleTint: Boolean = true

		@JvmField @SerialEntry(value = "jungle_gravity")
		var jungleGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "jungle_wind")
		var jungleWind: Float = 10f

		@JvmField @SerialEntry(value = "jungle_flow_away")
		var jungleFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "jungle_init_velocity")
		var jungleInitialVelocity: Float = 0.021f

		/* ACACIA */
		@JvmField @SerialEntry(value = "enable_acacia")
		var enableAcacia: Boolean = true

		@JvmField @SerialEntry(value = "acacia_multiplier")
		var acaciaMultiplier: Int = 1

		@JvmField @SerialEntry(value = "acacia_size")
		var acaciaSize: Float = 1.66f

		@JvmField @SerialEntry(value = "acacia_rotate")
		var acaciaRotate: Boolean = true

		@JvmField @SerialEntry(value = "enable_acacia_custom_color")
		var enableAcaciaCustomColor: Boolean = false

		@JvmField @SerialEntry(value = "acacia_color")
		var acaciaColor: Color = Color(0, 0, 0, 0)

		@JvmField @SerialEntry(value = "enable_acacia_tint")
		var useAcaciaTint: Boolean = true

		@JvmField @SerialEntry(value = "acacia_gravity")
		var acaciaGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "acacia_wind")
		var acaciaWind: Float = 10f

		@JvmField @SerialEntry(value = "acacia_flow_away")
		var acaciaFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "acacia_init_velocity")
		var acaciaInitialVelocity: Float = 0.021f

		/* AZALEA */
		@JvmField @SerialEntry(value = "enable_azalea")
		var enableAzalea: Boolean = true

		@JvmField @SerialEntry(value = "azalea_multiplier")
		var azaleaMultiplier: Int = 1

		@JvmField @SerialEntry(value = "azalea_size")
		var azaleaSize: Float = 2.0f

		@JvmField @SerialEntry(value = "azalea_rotate")
		var azaleaRotate: Boolean = true

		@JvmField @SerialEntry(value = "azalea_gravity")
		var azaleaGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "azalea_wind")
		var azaleaWind: Float = 10f

		@JvmField @SerialEntry(value = "azalea_flow_away")
		var azaleaFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "azalea_init_velocity")
		var azaleaInitialVelocity: Float = 0.021f

		/* PALE OAK */
		@JvmField @SerialEntry(value = "enable_pale_oak")
		var enablePaleOak: Boolean = true

		@JvmField @SerialEntry(value = "pale_oak_multiplier")
		var paleOakMultiplier: Int = 1

		@JvmField @SerialEntry(value = "pale_oak_size")
		var paleOakSize: Float = 2.0f

		@JvmField @SerialEntry(value = "pale_oak_rotate")
		var paleOakRotate: Boolean = true

		@JvmField @SerialEntry(value = "pale_oak_gravity")
		var paleOakGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "pale_oak_wind")
		var paleOakWind: Float = 10f

		@JvmField @SerialEntry(value = "pale_oak_flow_away")
		var paleOakFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "pale_oak_init_velocity")
		var paleOakInitialVelocity: Float = 0.021f

		/* CHERRY */
		@JvmField @SerialEntry(value = "enable_cherry")
		var enableCherry: Boolean = true

		@JvmField @SerialEntry(value = "cherry_multiplier")
		var cherryMultiplier: Int = 1

		@JvmField @SerialEntry(value = "cherry_size")
		var cherrySize: Float = 2.0f

		@JvmField @SerialEntry(value = "cherry_rotate")
		var cherryRotate: Boolean = true

		@JvmField @SerialEntry(value = "cherry_gravity")
		var cherryGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "cherry_wind")
		var cherryWind: Float = 10f

		@JvmField @SerialEntry(value = "cherry_flow_away")
		var cherryFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "cherry_init_velocity")
		var cherryInitialVelocity: Float = 0.021f

		/* FLOWERING AZALEA */
		@JvmField @SerialEntry(value = "enable_flowering_azalea")
		var enableFloweringAzalea: Boolean = true

		@JvmField @SerialEntry(value = "flowering_azalea_multiplier")
		var floweringAzaleaMultiplier: Int = 1

		@JvmField @SerialEntry(value = "flowering_azalea_size")
		var floweringAzaleaSize: Float = 2.0f

		@JvmField @SerialEntry(value = "flowering_azalea_rotate")
		var floweringAzaleaRotate: Boolean = true

		@JvmField @SerialEntry(value = "flowering_azalea_gravity")
		var floweringAzaleaGravity: Float = 0.07f

		@JvmField @SerialEntry(value = "flowering_azalea_wind")
		var floweringAzaleaWind: Float = 10f

		@JvmField @SerialEntry(value = "flowering_azalea_flow_away")
		var floweringAzaleaFlowAway: Boolean = false

		@JvmField @SerialEntry(value = "flowering_azalea_init_velocity")
		var floweringAzaleaInitialVelocity: Float = 0.021f
	}
}