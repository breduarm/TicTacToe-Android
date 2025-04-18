plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // DI
    alias(libs.plugins.android.hilt)

    // Kotlin
    kotlin("kapt")
}

android {
    namespace = "com.beam.tictactoe"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.beam.tictactoe"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.beam.tictactoe.di.HiltTestRunner"
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

    buildFeatures {
        viewBinding = true
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.9"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packaging {
        resources {
            excludes += "/META-INF/{LICENSE-notice.md,LICENSE.md}"
        }
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {

    // Kotlin
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.kotlinx.coroutines.android)

    // DI
    implementation(libs.android.hilt)
    kapt(libs.android.hilt.compiler)

    // Glide
    implementation(libs.glide)

    // Coil
    implementation(libs.coil)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // OkHttp
    implementation(libs.okhttp3.loggin.interceptor)

    // Room
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    // Compose
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    implementation(libs.compose.material)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)
    implementation(libs.compose.activity)
    implementation(libs.compose.hilt.navigation)
    implementation(libs.compose.navigation)

    // Icons
    implementation(libs.material.icons.extended)

    // Unit Test
    testImplementation(project(":appTestShared"))
    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)

    // Instrumentation Test
    androidTestImplementation(project(":appTestShared"))
    androidTestImplementation(libs.androidx.test.rules)
    androidTestImplementation(libs.androidx.junit.ktx)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.mockwebserver)
    androidTestImplementation(libs.turbine)
    androidTestImplementation(libs.fragment.testing)
    androidTestImplementation(composeBom)
    androidTestImplementation(libs.compose.ui.test.junit4)
    androidTestImplementation(libs.mockk.android)
    debugImplementation(libs.fragment.testing.manifest)
    debugImplementation(libs.compose.ui.test.manifest)
    kaptAndroidTest(libs.android.hilt.compiler)

    // Testing JUnit 5
    testImplementation(libs.junit.api5)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.junit.params)
    testRuntimeOnly(libs.junit.vintage.engine)
}

kapt {
    correctErrorTypes = true
}