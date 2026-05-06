plugins {
	alias(libs.plugins.android.application)
}

android {
	namespace = "com.em.myapplication"
	compileSdk {
		version = release(36) {
			minorApiLevel = 1
		}
	}
	
	defaultConfig {
		applicationId = "com.em.myapplication"
		minSdk = 26
		targetSdk = 36
		versionCode = 1
		versionName = "1.0"
		
		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}
	
	buildTypes {
		release {
			isMinifyEnabled = true
			isShrinkResources = true
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_21
		targetCompatibility = JavaVersion.VERSION_21
	}
	
	buildFeatures.viewBinding = true
}

dependencies {
	implementation(project(":data"))
	implementation(project(":presentation"))
	
	implementation("io.insert-koin:koin-android:4.2.1")
	implementation("androidx.navigation:navigation-fragment-ktx:2.9.8")
	
	implementation(libs.androidx.activity.ktx)
	implementation(libs.androidx.appcompat)
	implementation(libs.androidx.constraintlayout)
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.fragment)
	implementation(libs.androidx.recyclerview)
	implementation(libs.material)
	
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(libs.androidx.junit)
}
