import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    application
}

group = "io.github.mwttg-games"
version = "1.0.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val lwjglNatives = System.getProperty("os.name")!!.let { name ->
    when {
        arrayOf("Linux", "FreeBSD", "SunOS", "Unit").any { name.startsWith(it) } -> "natives-linux"
        arrayOf("Mac OS X", "Darwin").any { name.startsWith(it) } -> "natives-macos"
        arrayOf("Windows").any { name.startsWith(it) } -> "natives-windows"
        else -> throw Error("Unrecognized or unsupported platform. Please set \"lwjglNatives\" manually")
    }
}

dependencies {
    implementation(platform("org.lwjgl:lwjgl-bom:3.3.1"))

    implementation("ch.qos.logback:logback-classic:1.4.4")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.4")
    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-opengl")
    implementation("org.lwjgl:lwjgl-stb")
    runtimeOnly("org.lwjgl", "lwjgl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-glfw", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-opengl", classifier = lwjglNatives)
    runtimeOnly("org.lwjgl", "lwjgl-stb", classifier = lwjglNatives)
    implementation("org.joml:joml:1.10.5")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}

distributions {

}

application {
    mainClass.set("io.github.mwttg.games.platformer.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-XstartOnFirstThread")
    applicationDistribution.from("./data") {
        into("bin/data")
    }
}