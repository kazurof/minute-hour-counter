import io.gitlab.arturbosch.detekt.Detekt

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.6/userguide/building_java_projects.html
 */

plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.8.20"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`

    id("org.jetbrains.kotlinx.kover") version "0.7.1"
    id("io.gitlab.arturbosch.detekt") version "1.22.0"
    id("com.github.ben-manes.versions") version "0.46.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use the Kotlin JUnit 5 integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    // Use the JUnit 5 integration.
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")

    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
    testImplementation("io.mockk:mockk:1.13.4")
}

tasks.named<Test>("test") {
    // Use JUnit Platform for unit tests.
    useJUnitPlatform()
}

kover {
    // true to disable instrumentation and all Kover tasks in this project
    isDisabled.set(false)

    // to change engine, use kotlinx.kover.api.IntellijEngine("xxx") or kotlinx.kover.api.JacocoEngine("xxx")
    engine.set(kotlinx.kover.api.DefaultIntellijEngine)
}

tasks.withType<Detekt>().configureEach {
    reports {
        xml.required.set(true)
        html.required.set(true)
        txt.required.set(false)
        sarif.required.set(false)
        md.required.set(false)
    }
}
