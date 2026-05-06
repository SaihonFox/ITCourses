plugins {
	alias(libs.plugins.android.library)
}

android {
	namespace = "com.em.presentation"
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
	
	implementation("io.insert-koin:koin-android:4.2.1")
	implementation("io.insert-koin:koin-core-viewmodel:4.2.1")
	
	implementation("androidx.fragment:fragment-ktx:1.8.9")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.10.0")
	
	implementation(libs.androidx.activity.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.constraintlayout)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.recyclerview)
	implementation(libs.material)
	
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.junit)
}