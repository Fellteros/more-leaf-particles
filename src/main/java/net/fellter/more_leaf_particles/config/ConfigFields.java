package net.fellter.more_leaf_particles.config;

import dev.isxander.yacl3.config.v2.api.SerialEntry;
import org.jspecify.annotations.NonNull;

import java.awt.Color;

public class ConfigFields {
	@SerialEntry("enable_particle_rain_wind")
	public static boolean enableParticleRainWind = true;

	@SerialEntry("particle_rain_wind_multiplier")
	public static double particleRainWindMultiplier = 1.0F;

	@SerialEntry("ticks_to_blend")
	public static int ticksToBlend = 20;

	@SerialEntry("enable_oak")
	public static boolean enableOak = true;

	@SerialEntry("oak_multiplier")
	public static int oakMultiplier = 1;

	@SerialEntry("oak_size")
	public static float oakSize = 2.0F;

	@SerialEntry("oak_rotate")
	public static boolean oakRotate = true;

	@SerialEntry("enable_oak_custom_color")
	public static boolean enableOakCustomColor;

	@SerialEntry("oak_color")
	public static Color oakColor = new Color(0, 0, 0, 0);

	@SerialEntry("enable_oak_tint")
	public static boolean useOakTint = true;

	@SerialEntry("oak_gravity")
	public static float oakGravity = 0.07F;

	@SerialEntry("oak_wind")
	public static float oakWind = 10.0F;

	@SerialEntry("oak_flow_away")
	public static boolean oakFlowAway;

	@SerialEntry("oak_init_velocity")
	public static float oakInitialVelocity = 0.021F;

	@SerialEntry("enable_dark_oak")
	public static boolean enableDarkOak = true;

	@SerialEntry("dark_oak_multiplier")
	public static int darkOakMultiplier = 1;

	@SerialEntry("dark_oak_size")
	public static float darkOakSize = 2.0F;

	@SerialEntry("dark_oak_rotate")
	public static boolean darkOakRotate = true;

	@SerialEntry("enable_dark_oak_custom_color")
	public static boolean enableDarkOakCustomColor;

	@SerialEntry("dark_oak_color")
	@NonNull
	public static Color darkOakColor = new Color(0, 0, 0, 0);

	@SerialEntry("enable_dark_oak_tint")
	public static boolean useDarkOakTint = true;

	@SerialEntry("dark_oak_gravity")
	public static float darkOakGravity = 0.07F;

	@SerialEntry("dark_oak_wind")
	public static float darkOakWind = 10.0F;

	@SerialEntry("dark_oak_flow_away")
	public static boolean darkOakFlowAway;

	@SerialEntry("dark_oak_init_velocity")
	public static float darkOakInitialVelocity = 0.021F;

	@SerialEntry("enable_spruce")
	public static boolean enableSpruce = true;

	@SerialEntry("spruce_multiplier")
	public static int spruceMultiplier = 1;

	@SerialEntry("spruce_size")
	public static float spruceSize = 2.0F;

	@SerialEntry("spruce_rotate")
	public static boolean spruceRotate = true;

	@SerialEntry("enable_spruce_custom_color")
	public static boolean enableSpruceCustomColor;

	@SerialEntry("spruce_color")
	@NonNull
	public static Color spruceColor = new Color(0, 0, 0, 0);

	@SerialEntry("enable_spruce_tint")
	public static boolean useSpruceTint = true;

	@SerialEntry("spruce_gravity")
	public static float spruceGravity = 0.07F;

	@SerialEntry("spruce_wind")
	public static float spruceWind = 10.0F;

	@SerialEntry("spruce_flow_away")
	public static boolean spruceFlowAway;

	@SerialEntry("spruce_init_velocity")
	public static float spruceInitialVelocity = 0.021F;

	@SerialEntry("enable_birch")
	public static boolean enableBirch = true;

	@SerialEntry("birch_multiplier")
	public static int birchMultiplier = 1;

	@SerialEntry("birch_size")
	public static float birchSize = 2.0F;

	@SerialEntry("birch_rotate")
	public static boolean birchRotate = true;

	@SerialEntry("enable_birch_custom_color")
	public static boolean enableBirchCustomColor;

	@SerialEntry("birch_color")
	@NonNull
	public static Color birchColor = new Color(0, 0, 0, 0);

	@SerialEntry("enable_birch_tint")
	public static boolean useBirchTint = true;

	@SerialEntry("birch_gravity")
	public static float birchGravity = 0.07F;

	@SerialEntry("birch_wind")
	public static float birchWind = 10.0F;

	@SerialEntry("birch_flow_away")
	public static boolean birchFlowAway;

	@SerialEntry("birch_init_velocity")
	public static float birchInitialVelocity = 0.021F;

	@SerialEntry("enable_mangrove")
	public static boolean enableMangrove = true;

	@SerialEntry("mangrove_multiplier")
	public static int mangroveMultiplier = 1;

	@SerialEntry("mangrove_size")
	public static float mangroveSize = 2.0F;

	@SerialEntry("mangrove_rotate")
	public static boolean mangroveRotate = true;

	@SerialEntry("enable_mangrove_custom_color")
	public static boolean enableMangroveCustomColor;

	@SerialEntry("mangrove_color")
	@NonNull
	public static Color mangroveColor = new Color(0, 0, 0, 0);

	@SerialEntry("enable_mangrove_tint")
	public static boolean useMangroveTint = true;

	@SerialEntry("mangrove_gravity")
	public static float mangroveGravity = 0.07F;

	@SerialEntry("mangrove_wind")
	public static float mangroveWind = 10.0F;

	@SerialEntry("mangrove_flow_away")
	public static boolean mangroveFlowAway;

	@SerialEntry("mangrove_init_velocity")
	public static float mangroveInitialVelocity = 0.021F;

	@SerialEntry("enable_jungle")
	public static boolean enableJungle = true;

	@SerialEntry("jungle_multiplier")
	public static int jungleMultiplier = 1;

	@SerialEntry("jungle_size")
	public static float jungleSize = 2.0F;

	@SerialEntry("jungle_rotate")
	public static boolean jungleRotate = true;

	@SerialEntry("enable_jungle_custom_color")
	public static boolean enableJungleCustomColor;

	@SerialEntry("jungle_color")
	@NonNull
	public static Color jungleColor = new Color(0, 0, 0, 0);

	@SerialEntry("enable_jungle_tint")
	public static boolean useJungleTint = true;

	@SerialEntry("jungle_gravity")
	public static float jungleGravity = 0.07F;

	@SerialEntry("jungle_wind")
	public static float jungleWind = 10.0F;

	@SerialEntry("jungle_flow_away")
	public static boolean jungleFlowAway;

	@SerialEntry("jungle_init_velocity")
	public static float jungleInitialVelocity = 0.021F;

	@SerialEntry("enable_acacia")
	public static boolean enableAcacia = true;

	@SerialEntry("acacia_multiplier")
	public static int acaciaMultiplier = 1;

	@SerialEntry("acacia_size")
	public static float acaciaSize = 1.66F;

	@SerialEntry("acacia_rotate")
	public static boolean acaciaRotate = true;

	@SerialEntry("enable_acacia_custom_color")
	public static boolean enableAcaciaCustomColor;

	@SerialEntry("acacia_color")
	@NonNull
	public static Color acaciaColor = new Color(0, 0, 0, 0);

	@SerialEntry("enable_acacia_tint")
	public static boolean useAcaciaTint = true;

	@SerialEntry("acacia_gravity")
	public static float acaciaGravity = 0.07F;

	@SerialEntry("acacia_wind")
	public static float acaciaWind = 10.0F;

	@SerialEntry("acacia_flow_away")
	public static boolean acaciaFlowAway;

	@SerialEntry("acacia_init_velocity")
	public static float acaciaInitialVelocity = 0.021F;

	@SerialEntry("enable_azalea")
	public static boolean enableAzalea = true;

	@SerialEntry("azalea_multiplier")
	public static int azaleaMultiplier = 1;

	@SerialEntry("azalea_size")
	public static float azaleaSize = 2.0F;

	@SerialEntry("azalea_rotate")
	public static boolean azaleaRotate = true;

	@SerialEntry("azalea_gravity")
	public static float azaleaGravity = 0.07F;

	@SerialEntry("azalea_wind")
	public static float azaleaWind = 10.0F;

	@SerialEntry("azalea_flow_away")
	public static boolean azaleaFlowAway;

	@SerialEntry("azalea_init_velocity")
	public static float azaleaInitialVelocity = 0.021F;

	@SerialEntry("enable_pale_oak")
	public static boolean enablePaleOak = true;

	@SerialEntry("pale_oak_multiplier")
	public static int paleOakMultiplier = 1;

	@SerialEntry("pale_oak_size")
	public static float paleOakSize = 2.0F;

	@SerialEntry("pale_oak_rotate")
	public static boolean paleOakRotate = true;

	@SerialEntry("pale_oak_gravity")
	public static float paleOakGravity = 0.07F;

	@SerialEntry("pale_oak_wind")
	public static float paleOakWind = 10.0F;

	@SerialEntry("pale_oak_flow_away")
	public static boolean paleOakFlowAway;

	@SerialEntry("pale_oak_init_velocity")
	public static float paleOakInitialVelocity = 0.021F;

	@SerialEntry("enable_cherry")
	public static boolean enableCherry = true;

	@SerialEntry("cherry_multiplier")
	public static int cherryMultiplier = 1;

	@SerialEntry("cherry_size")
	public static float cherrySize = 2.0F;

	@SerialEntry("cherry_rotate")
	public static boolean cherryRotate = true;

	@SerialEntry("cherry_gravity")
	public static float cherryGravity = 0.07F;

	@SerialEntry("cherry_wind")
	public static float cherryWind = 10.0F;

	@SerialEntry("cherry_flow_away")
	public static boolean cherryFlowAway;

	@SerialEntry("cherry_init_velocity")
	public static float cherryInitialVelocity = 0.021F;

	@SerialEntry("enable_flowering_azalea")
	public static boolean enableFloweringAzalea = true;

	@SerialEntry("flowering_azalea_multiplier")
	public static int floweringAzaleaMultiplier = 1;

	@SerialEntry("flowering_azalea_size")
	public static float floweringAzaleaSize = 2.0F;

	@SerialEntry("flowering_azalea_rotate")
	public static boolean floweringAzaleaRotate = true;

	@SerialEntry("flowering_azalea_gravity")
	public static float floweringAzaleaGravity = 0.07F;

	@SerialEntry("flowering_azalea_wind")
	public static float floweringAzaleaWind = 10.0F;

	@SerialEntry("flowering_azalea_flow_away")
	public static boolean floweringAzaleaFlowAway;

	@SerialEntry("flowering_azalea_init_velocity")
	public static float floweringAzaleaInitialVelocity = 0.021F;

}