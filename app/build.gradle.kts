import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id ("org.jetbrains.kotlin.plugin.serialization")
}

android {
    namespace = "com.aredruss.warmaster"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.aredruss.warmaster"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    applicationVariants.all {
        outputs
            .map { it as com.android.build.gradle.internal.api.ApkVariantOutputImpl }
            .all { output ->
                output.outputFileName = "Warmaster_$versionName.apk"
                false
            }
    }
}

dependencies {

    implementation(files(org.gradle.internal.jvm.Jvm.current().toolsJar))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.0")
    implementation("com.jakewharton.timber:timber:5.0.1")

    implementation( "io.github.raamcosta.compose-destinations:animations-core:1.9.42-beta")
    ksp ("io.github.raamcosta.compose-destinations:ksp:1.9.42-beta")

    implementation("io.insert-koin:koin-androidx-compose:3.4.5")
    implementation("io.insert-koin:koin-android:3.4.2")

    implementation("io.coil-kt:coil-compose:2.4.0")

    ksp ("androidx.room:room-compiler:2.6.0")
    implementation("androidx.room:room-ktx:2.6.0")
}