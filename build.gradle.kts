plugins {
	id("fabric-loom") version "1.14.+"
	id("me.modmuss50.mod-publish-plugin") version "1.1.0"
}

version = property("mod_version").toString()
group = property("maven_group").toString()

base {
	archivesName = property("archives_base_name").toString()
}

repositories {
	// Add repositories to retrieve artifacts from in here.
	// You should only use this when depending on other mods because
	// Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
	// See https://docs.gradle.org/current/userguide/declaring_repositories.html
	// for more information about repositories.
	maven("https://maven.isxander.dev/releases") {
		name = "Xander Maven"
	}

	maven("https://maven.terraformersmc.com/") {
		name = "Terraformers"
	}

	exclusiveContent {
		forRepository {
			maven("https://api.modrinth.com/maven") {
				name = "Modrinth"
			}
		}
		filter {
			includeGroup("maven.modrinth")
		}
	}
}

val minecraft = stonecutter.current.version
val accesswidener = when {
	stonecutter.eval(minecraft, ">=1.21.10") -> "1.21.10.ct"
	else -> "pre-1.21.8.ct"
}

loom {
	accessWidenerPath = rootProject.file("src/main/resources/classtweakers/$accesswidener")
}

tasks.test {
	useJUnitPlatform()
}

stonecutter {
	replacements.string {
		direction = eval(minecraft, ">=1.21.11")
		replace("ResourceLocation", "Identifier")
	}
	replacements.string("non_null_import") {
		direction = eval(minecraft, ">=1.21.11")
		replace("org.jetbrains.annotations.NotNull", "org.jspecify.annotations.NonNull")
	}
	replacements.string("not_null") {
		direction = eval(minecraft, ">=1.21.11")
		replace("NotNull", "NonNull")
	}
}

dependencies {
	// To change the versions see the gradle.properties file
	minecraft("com.mojang:minecraft:${property("minecraft_version").toString()}")
	mappings(loom.officialMojangMappings())
	modImplementation("net.fabricmc:fabric-loader:${property("loader_version").toString()}")
	testImplementation("net.fabricmc:fabric-loader-junit:${property("loader_version")}")

	modImplementation("net.fabricmc.fabric-api:fabric-api:${property("fabric_version").toString()}")

	modCompileOnly("dev.isxander:yet-another-config-lib:${property("yacl_version").toString()}")
	modLocalRuntime("dev.isxander:yet-another-config-lib:${property("yacl_version").toString()}")

	modCompileOnly("com.terraformersmc:modmenu:${property("modmenu_version").toString()}")
	modLocalRuntime("com.terraformersmc:modmenu:${property("modmenu_version").toString()}")

	modCompileOnly("maven.modrinth:particle-rain:${property("particle_rain_version").toString()}")
	modLocalRuntime("maven.modrinth:particle-rain:${property("particle_rain_version").toString()}")
}

tasks.processResources {
	filteringCharset = "UTF-8"

	inputs.property("version", project.property("version"))
	inputs.property("minecraft_version", project.property("minecraft_version"))
	inputs.property("loader_version", project.property("loader_version"))

	val props = mapOf(
		"version" to project.property("version"),
		"minecraft_version" to project.property("minecraft_version"),
		"loader_version" to project.property("loader_version"),
		"aw_file" to accesswidener,
		"compatible_with" to project.property("compatible_with"),
		"modmenu_version" to project.property("modmenu_version"),
		"yacl_version" to project.property("yacl_version"),
		"particle_rain_version" to project.property("particle_rain_version")
	)

	filesMatching("fabric.mod.json") { expand(props) }
}

val targetJavaVersion = 21

java {
	val javaVersion = JavaVersion.toVersion(targetJavaVersion)
	if (JavaVersion.current() < javaVersion) {
		toolchain.languageVersion = JavaLanguageVersion.of(targetJavaVersion)
	}
	// Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
	// if it is present.
	// If you remove this line, sources will not be generated.
	withSourcesJar()
}

tasks.jar {
	from("LICENSE") {
		rename { "${it}_${property("archives_base_name")}" }
	}
}

publishMods {
	dryRun = false
	val modVersion = project.property("mod_version").toString()

	file = project.file("build/libs/more_leaf_particles-${modVersion}.jar")
	additionalFiles.from("build/libs/more_leaf_particles-${modVersion}-sources.jar")
	modLoaders.add("fabric")
	type = STABLE
	changelog = expand(rootProject.file("src/main/resources/changelogs/${modVersion.split("+")[0]}.md"), mapOf(
		"mcVersion" to minecraft
	))

	modrinth {
		projectId = "HwWDzPBa"
		accessToken = providers.environmentVariable("modrinth")
		displayName = "More Leaf Particles $modVersion"
		version = modVersion

		minecraftVersionRange {
			val versions = property("version_range").toString().trim().split(",")

			start = versions.first().trim()
			end = versions.last().trim()
		}

		requires("fabric-api")
		optional("modmenu", "yacl", "particle-rain")
	}

	github {
		repository = "Fellteros/more-leaf-particles"
		accessToken = providers.environmentVariable("github.pat")
		commitish = "out"
		tagName = modVersion
		displayName = modVersion
	}
}

fun expand(file: File, properties: Map<String, *>): String {
	var body: String = file.readText(Charsets.UTF_8)

	for ((string, sth) in properties) {
		body = body.replace($$"${$$string}", sth.toString())
	}

	return body
}
