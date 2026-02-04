import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization") apply true
}

android {
    namespace = "com.kosmasfn.movie"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.kosmasfn.movie"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val properties = Properties()
    properties.load(project.rootProject.file("gradle.properties").inputStream())

    buildTypes {
        debug {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "API_BASE_URL",
                "\"${properties.getProperty("API_BASE_URL")}\""
            )
            buildConfigField(
                "String",
                "BEARER_TOKEN",
                "\"${properties.getProperty("BEARER_TOKEN")}\""
            )
            buildConfigField(
                "String",
                "POSTER_BASE_URL",
                "\"${properties.getProperty("POSTER_BASE_URL")}\""
            )
            buildConfigField(
                "String",
                "ACCOUNT_ID",
                "\"${properties.getProperty("ACCOUNT_ID")}\""
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "API_BASE_URL",
                "\"${properties.getProperty("API_BASE_URL")}\""
            )
            buildConfigField(
                "String",
                "BEARER_TOKEN",
                "\"${properties.getProperty("BEARER_TOKEN")}\""
            )
            buildConfigField(
                "String",
                "POSTER_BASE_URL",
                "\"${properties.getProperty("POSTER_BASE_URL")}\""
            )
            buildConfigField(
                "String",
                "ACCOUNT_ID",
                "\"${properties.getProperty("ACCOUNT_ID")}\""
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material)

    implementation(libs.adapter.rxjava2)
    implementation(libs.converter.gson)
    implementation(libs.retrofit)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.kotlinx.serialization.json)

    implementation(libs.lottie.compose)
    implementation(libs.coil.compose)
    implementation(libs.androidyoutubeplayer.core)

    testImplementation (libs.kotlinx.coroutines.test)
    testImplementation (libs.junit)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}