plugins {
	alias(libs.plugins.android.library)
	id("com.google.devtools.ksp")
}

android {
	namespace = "com.em.data"
	compileSdk {
		version = release(36) {
			minorApiLevel = 1
		}
	}
	
	defaultConfig {
		minSdk = 26
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
		consumerProguardFiles("consumer-rules.pro")
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}
	
	buildFeatures.viewBinding = true
}

dependencies {
	implementation(project(":domain"))
	
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.core.ktx)
	implementation(libs.material)
	
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.junit)
	
	val retrofitVersion = "3.0.0"
	implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
	implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")
	implementation("com.squareup.moshi:moshi-kotlin:1.15.2")
	implementation("com.squareup.okhttp3:okhttp:5.3.2")
	
	implementation("io.insert-koin:koin-core:4.2.1")
	
	val roomVersion = "2.8.4"
	implementation("androidx.room:room-runtime:$roomVersion")
	ksp("androidx.room:room-compiler:$roomVersion")
	implementation("androidx.room:room-ktx:${roomVersion}")
	
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.10.2")
}