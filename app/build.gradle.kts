plugins {
    alias(libs.plugins.agp.app)
}

android {
    namespace = "icu.nullptr.polyglot"

    defaultConfig {
        applicationId = "icu.nullptr.polyglot"
        versionCode = 1
        versionName = "0.1.0"
        minSdk = 28
        targetSdk = 37
        compileSdk = 37
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    compileOnly(libs.libxposed.api)
    implementation(libs.dexkit)
    implementation(libs.gson)
    implementation(libs.mmkv)
}
