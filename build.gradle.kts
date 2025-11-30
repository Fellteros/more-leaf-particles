plugins {
	id("fabric-loom") version "1.13-SNAPSHOT"
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
	stonecutter.eval(minecraft, ">=1.21.10") -> "1.21.10.aw"
	else -> "pre-1.21.8.aw"
}

loom {
	accessWidenerPath = rootProject.file("src/main/resources/accesswideners/$accesswidener")
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
		"compatible_with" to project.property("compatible_with")
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

// configure the maven publication
//publishing {
//    publications {
//        create("mavenJava", MavenPublication) {
//            artifactId = project.archives_base_name
//            from components.java
//        }
//    }
//
//    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
//    repositories {
//        // Add repositories to publish to here.
//        // Notice: This block does NOT have the same function as the block in the top level.
//        // The repositories here will be used for publishing your artifact, not for
//        // retrieving dependencies.
//    }
//}
