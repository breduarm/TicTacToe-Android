plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    // DI
    alias(libs.plugins.android.hilt)

    // Kotlin
    kotlin("kapt")
}

android {
    namespace = "com.beam.tictactoexml"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.beam.tictactoexml"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "com.beam.tictactoexml.di.HiltTestRunner"
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
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
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

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.gson)

    // OkHttp
    implementation(libs.okhttp3.loggin.interceptor)

    // Room
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

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