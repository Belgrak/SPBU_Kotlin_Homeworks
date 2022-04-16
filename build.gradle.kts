plugins {
    kotlin("jvm") version "1.5.0"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.8"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

javafx {
    version = "11.0.2"
    modules = mutableListOf("javafx.controls", "javafx.graphics")
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.2")
    implementation("no.tornado:tornadofx:1.7.20")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.19.0")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = "11"
}

tasks.compileTestKotlin {
    kotlinOptions.jvmTarget = "11"
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
