plugins {
	id("common")
	id("publish-mods")
	id("dev.kikugie.loom-back-compat")
}

val accesswidener = when {
	sc.current.parsed <= "1.21.8" -> "1.21.8.ct"
	sc.current.parsed <= "1.21.11" -> "1.21.11.ct"
	else -> "26.x.ct"
}

dependencies {
	fun fapiModule(vararg modules: String) {
		for (it in modules) modImplementation(fabricApi.module(it, sc.properties["deps.fabricApi"]))
	}

	minecraft("com.mojang:minecraft:${sc.property("minecraft.version")}")
	loomx.applyMojangMappings()

	modImplementation(libs.fabric.loader)
	testImplementation(libs.fabric.loader.junit)
	modImplementation(libs.fabric.language.kotlin)

	fapiModule("fabric-particles-v1")

	modCompileOnly("dev.isxander:yet-another-config-lib:${sc.property("deps.yacl")}")
	modLocalRuntime("dev.isxander:yet-another-config-lib:${sc.property("deps.yacl")}")

	modCompileOnly("com.terraformersmc:modmenu:${sc.property("deps.modmenu")}")
	modLocalRuntime("com.terraformersmc:modmenu:${sc.property("deps.modmenu")}")

	modCompileOnly("maven.modrinth:particle-rain:${sc.property("deps.particleRain")}")

	if (sc.current.parsed < "26.2") {
		modLocalRuntime("maven.modrinth:particle-rain:${sc.property("deps.particleRain")}")
	}
}

loom {
	accessWidenerPath = rootProject.file("src/main/resources/classtweakers/$accesswidener")

	runConfigs.configureEach {
		generateRunConfig = true
		runDirectory.set(File(rootProject.rootDir, "run"))

		jvmArguments.addAll(
			"-javaagent:${gradle.gradleUserHomeDir}/caches/modules-2/files-2.1/net.fabricmc/sponge-mixin/0.17.3+mixin.0.8.7/41c4a3984a80f4679e759fb9f495587acc5cdac7/sponge-mixin-0.17.3+mixin.0.8.7.jar"
		)

		programArguments.add(
			"-XX:+AllowEnhancedClassRedefinition"
		)
	}
}

tasks.processResources {
	fun computeCompatibleVersions(): String {
		val allAvailableRanges: MutableList<String> = (sc.getArrayOrEmpty<String>("additional", "versions", conversion = { it.asPrimitive().toString() }) + sc.property("minecraft.version")).toMutableList()
		val versions = allAvailableRanges.map(sc.semantics::parse).sorted()

		return if (versions.isEmpty()) {
			throw NullPointerException("At least one version is needed to compute a version range!")
		} else if (versions.size == 1) {
			versions.first().toString()
		} else {
			">=${versions.first()} <=${versions.last()}"
		}
	}

	filteringCharset = "UTF-8"

	inputs.properties(
		"mod_version" to version,
		"minecraft_version" to sc.property("minecraft.version"),
		"loader_version" to libs.versions.fabric.loader.get(),
		"aw_path" to accesswidener,
		"compatible_with" to computeCompatibleVersions(),
		"deps_modmenu" to sc.property("deps.modmenu"),
		"deps_yacl" to sc.property("deps.yacl"),
		"deps_particleRain" to sc.property("deps.particleRain")
	)

	filesMatching("fabric.mod.json") {
		expand(inputs.properties)
	}
}