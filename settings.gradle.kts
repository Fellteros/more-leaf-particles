pluginManagement {
	repositories {
		gradlePluginPortal()
		maven("https://maven.fabricmc.net/") { name = "Fabric" }
		maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
		maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
	}
}

plugins {
	id("dev.kikugie.loom-back-compat") version "0.3"
	id("dev.kikugie.stonecutter") version "0.10-alpha.2"
}

stonecutter {
	create(rootProject) {
		versions(
			"1.21.5",
			"1.21.8",
			"1.21.10",
			"1.21.11",
			"26.1",
			"26.2"
		)

		vcsVersion = "26.2"
	}
}

rootProject.name = "More Leaf Particles"
