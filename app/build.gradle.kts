import java.util.Properties

plugins {
    alias(libs.plugins.agp.app)
}

val localProperties = Properties().apply {
    rootProject.file("local.properties")
        .inputStream()
        .use { load(it) }
}

fun localProperty(name: String): String? =
    localProperties.getProperty(name)?.takeIf { it.isNotBlank() }

val hasSigningConfig = localProperty("androidStoreFile") != null

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

    signingConfigs {
        if (hasSigningConfig) {
            create("release") {
                storeFile = file(localProperty("androidStoreFile")!!)
                storePassword = localProperty("androidStorePassword")
                keyAlias = localProperty("androidKeyAlias")
                keyPassword = localProperty("androidKeyPassword")
            }
        }
    }

    buildTypes {
        if (hasSigningConfig) {
            all {
                signingConfig = signingConfigs.getByName("release")
            }
        }
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
