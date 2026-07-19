plugins {
    id("dev.kikugie.stonecutter")
}

stonecutter parameters {
    val (version, loader) = current.project.split('-', limit = 2)

    properties {
        tags(version, loader)
    }

    constants {
        match(loader, "fabric", "neoforge")
    }

    replacements.string(current.parsed >= "1.21.11") {
        replace("org.jetbrains.annotations.NotNull", "org.jspecify.annotations.NonNull")
        replace("org.jetbrains.annotations.Nullable", "org.jspecify.annotations.Nullable")
        replace("NotNull", "NonNull")
        replace("ResourceLocation", "Identifier")
    }

    replacements.string(!constants["fabric"]!!) {
        replace("@Environment", "//@Environment")
    }
}

stonecutter active "26.2-fabric"