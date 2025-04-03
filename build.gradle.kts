import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile

plugins {
    kotlin("multiplatform") version "2.1.20"
    `maven-publish`
    id("signing")
    id("tech.yanand.maven-central-publish") version "1.3.0"
}

group = "com.araksis"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
}

kotlin {
    js(IR) {
        browser {}
        binaries.library()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(kotlin("stdlib-common"))
            }
        }
        val jsMain by getting {
            dependencies {
                implementation(kotlin("stdlib-js"))
                implementation(npm("@material/web", "2.3.0"))
            }
        }
    }

    jvmToolchain(21)
}

tasks.withType<KotlinJsCompile>().configureEach {
    compilerOptions {
        target.set("es2015")
    }
}