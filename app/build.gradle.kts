plugins {
    alias(libs.plugins.android.application)
    kotlin("kapt")
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.utkarsh.news"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.utkarsh.news"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    dataBinding {
        enable=true
    }
}

dependencies {

    implementation(project(":search:search_presentation"))
    implementation(project(":news:news_presentation"))
    implementation(project(":common:common_utils"))
//    implementation(libs.androidx.core.ktx)
    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.androidMaterial)
    implementation(Deps.activity)
    implementation(Deps.constraintLayout)
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)

    implementation(LottieAnimations.lottieAnimations)
    implementation(DaggerHilt.hilt)
    kapt(DaggerHilt.hiltAndroidCompiler)
    kapt(DaggerHilt.hiltCompiler)
}