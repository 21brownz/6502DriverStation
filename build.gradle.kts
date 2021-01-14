import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "FRC6502"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    implementation("uk.co.electronstudio.sdl2gdx:sdl2gdx:1.0.+")
    implementation("com.fazecast:jSerialComm:[2.0.0,3.0.0)")
    implementation("com.github.WilliamAHartman:Jamepad:1.4.0")
    implementation("net.razorvine:pickle:1.0")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}