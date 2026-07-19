pluginManagement {
	repositories {
		mavenLocal()
		mavenCentral()
		gradlePluginPortal()
		maven("https://maven.fabricmc.net/") { name = "Fabric" }
		maven("https://maven.neoforged.net/releases/") { name = "NeoForged" }
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
		maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
	}
}

includeBuild("/build-logic/common")
includeBuild("/build-logic/publish-mods")
includeBuild("/build-logic/neoforge-mutex")

plugins {
	id("dev.kikugie.loom-back-compat") version "0.3" apply true
	id("net.neoforged.moddev") version "2.0.140" apply false
	id("dev.kikugie.stonecutter") version "0.10-alpha.2"
	id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

stonecutter {
	create(rootProject) {
		fun loaderVersion(project: String, vararg loaders: String) {
			for (loader in loaders) version(project = "$project-$loader", version = project).buildscript("build.$loader.gradle.kts")
		}

		loaderVersion("1.21.5", "fabric", "neoforge")
		loaderVersion("1.21.8", "fabric", "neoforge")
		loaderVersion("1.21.10", "fabric", "neoforge")
		loaderVersion("1.21.11", "fabric", "neoforge")
		loaderVersion("26.1", "fabric", "neoforge")
		loaderVersion("26.2", "fabric", "neoforge")

		vcsVersion = "26.2-fabric"
	}
}

rootProject.name = "More Leaf Particles"
