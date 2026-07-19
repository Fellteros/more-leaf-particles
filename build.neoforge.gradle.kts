plugins {
	id("neoforge-mutex")
	id("common")
	id("publish-mods")
	id("net.neoforged.moddev")
}

repositories {
	// For KLF
	maven("https://repo.nyon.dev/releases")
}

dependencies {
	implementation("dev.nyon:KotlinLangForge:${sc.property("klf.version")}-${sc.property("deps.klf")}+neoforge")

	implementation("maven.modrinth:yacl:${sc.property("deps.yacl")}")

	if (sc.current.parsed <= "1.21.8") {
		implementation("maven.modrinth:particle-rain:${sc.property("deps.particleRain")}")
	}
}

neoForge {
	version = sc.property("deps.neoLoader")

	mods {
		register("more_leaf_particles") {
			sourceSet(sourceSets.main.get())
		}
	}

	if (sc.current.parsed < "26.0") {
		parchment {
			minecraftVersion = sc.property("minecraft.version")
			mappingsVersion = sc.properties.getOrNull("parchment.version")
		}
	}

	interfaceInjectionData.from(rootProject.file("src/main/resources/neoforge-interfaces.json"))

	runs {
		register("client") {
			gameDirectory = File(rootProject.rootDir, "run")
			client()

			programArgument("-XX:+AllowEnhancedClassRedefinition")
		}
	}
}

tasks.processResources {
	fun computeVersionRange(): String {
		val allAvailableRanges: MutableList<String> = (sc.getArrayOrEmpty<String>("additional", "versions", conversion = { it.asPrimitive().toString() }) + sc.property("minecraft.version")).toMutableList()
		val versions = allAvailableRanges.map(sc.semantics::parse).sorted()

		return if (versions.isEmpty()) {
			throw NullPointerException("At least one version is needed to compute a version range!")
		} else if (versions.size == 1) {
			versions.first().toString()
		} else {
			"[${versions.first()}, ${versions.last()}]"
		}
	}

	filteringCharset = "UTF-8"

	val props = buildMap {
		put("id", sc.property("mod.id"))
		put("version", sc.property("mod.version"))
		put("version_range", computeVersionRange())
	}

	filesMatching("META-INF/neoforge.mods.toml") {
		expand(props)
	}

	exclude("fabric.mod.json", "*.ct", "*.classtweaker")
}