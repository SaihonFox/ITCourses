plugins {
	id("java-library")
	alias(libs.plugins.jetbrains.kotlin.jvm)
}
java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}
kotlin {
	jvmToolchain(21)
	compilerOptions {
		jvmTarget = org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_21
	}
}

dependencies {
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}
