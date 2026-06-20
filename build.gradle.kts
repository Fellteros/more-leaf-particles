@file:Suppress("AvoidDuplicateDependencies")

import dev.kikugie.stonecutter.build.StonecutterBuildExtension
import dev.kikugie.stonecutter.data.deserialization.SCElement


plugins {
	id("dev.kikugie.loom-back-compat")
	alias(libs.plugins.mod.publish.plugin)
	kotlin("jvm") version libs.versions.kotlin
}

version = "${sc.property("mod.version")}+${sc.property("minecraft.version")}"
group = sc.property("maven_group")

val accesswidener = when {
	sc.current.parsed >= "26.1" -> "26.1.ct"
	sc.current.parsed >= "1.21.9" -> "1.21.9-1.21.11.ct"
	else -> "1.21.5-1.21.8.ct"
}

base {
	archivesName = sc.property("archives_base_name")
}

repositories {
	maven("https://maven.isxander.dev/releases") { name = "Xander Maven" }
	maven("https://maven.terraformersmc.com/") { name = "TerraformersMC" }

	exclusiveContent {
		forRepository {
			maven("https://api.modrinth.com/maven") { name = "Modrinth" }
		}

		filter {
			includeGroup("maven.modrinth")
		}
	}
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
	modLocalRuntime("maven.modrinth:particle-rain:${sc.property("deps.particleRain")}")
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

tasks.test {
	useJUnitPlatform()
}

stonecutter {
	replacements.string(current.parsed >= "1.21.11") {
		replace("org.jetbrains.annotations.NotNull", "org.jspecify.annotations.NonNull")
		replace("org.jetbrains.annotations.Nullable", "org.jspecify.annotations.Nullable")
		replace("NotNull", "NonNull")
		replace("ResourceLocation", "Identifier")
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

val targetJavaVersion = 25

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

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll(
			"-Xreturn-value-checker=check",
			"-Xcontext-parameters",
			"-Xexplicit-context-arguments"
		)
	}
}

tasks.jar {
	from("LICENSE") {
		rename { "${it}_${property("archives_base_name")}" }
	}
}

publishMods {
	dryRun = false
	val fullModVersion = project.version.toString()

	file = project.file("build/libs/more_leaf_particles-${fullModVersion}.jar")
	additionalFiles.from("build/libs/more_leaf_particles-${fullModVersion}-sources.jar")
	modLoaders.add("fabric")
	type = STABLE
	changelog = expandProperties(
		rootProject.file("src/main/resources/changelogs/${sc.properties.get<String>("mod.version")}.md"), mapOf(
			"mcVersion" to sc.current.version
		)
	)

	modrinth {
		projectId = "HwWDzPBa"
		accessToken = providers.environmentVariable("MODRINTH_PAT")
		displayName = "More Leaf Particles $fullModVersion"
		version = fullModVersion

		val versions = sc.getArrayOrEmpty<String>("additional", "versions", conversion = { it.asPrimitive().toString() }).toList()
		val compatibleVersions = (versions + sc.property("minecraft.version")).joinToString()

		minecraftVersionList(compatibleVersions)

		requires("fabric-api")
		optional("modmenu", "yacl", "particle-rain")
	}

	github {
		repository = "Fellteros/more-leaf-particles"
		accessToken = providers.environmentVariable("GITHUB_PAT")
		commitish = "out"
		tagName = fullModVersion
		displayName = fullModVersion
	}
}

fun expandProperties(file: File?, properties: Map<String, *>): String {
	if (file == null) return ""

	var body: String = try {
		file.readText(Charsets.UTF_8)
	} catch (_: Exception) {
		return ""
	}

	for ((string, sth) in properties) {
		body = body.replace($$"${$$string}", sth.toString())
	}

	return body
}

inline fun <reified T : Any> StonecutterBuildExtension.getArrayOrEmpty(vararg name: String, noinline conversion: ((SCElement) -> T)? = null): Array<T> {
	return properties.rawOrNull(*name)?.asList()?.map { conversion?.invoke(it) ?: it.to<T>() }?.toTypedArray() ?: arrayOf()
}

fun StonecutterBuildExtension.property(name: String): String {
	return this.properties[name]
}