plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
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
    implementation(project(":news:news_data"))
    implementation(project(":news:news_domain"))
    implementation(project(":common:common_utils"))

    implementation(project(":search:search_domain"))
    implementation(project(":search:search_data"))
//    implementation(libs.androidx.core.ktx)
    implementation(Deps.core)
    implementation(Deps.appCompat)
    implementation(Deps.androidMaterial)
    implementation(Deps.activity)
    implementation(Deps.constraintLayout)
    testImplementation(TestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.junit)
    androidTestImplementation(AndroidTestImplementation.espresso)

    implementation(Coroutines.coroutineCore)
    implementation(Coroutines.coroutineAndroid)
    implementation(CoroutinesLifecycleScope.lifecycleRuntime)
    implementation(CoroutinesLifecycleScope.lifecycleViewModel)

    implementation(LottieAnimations.lottieAnimations)
    implementation(DaggerHilt.hilt)
    ksp(DaggerHilt.hiltAndroidCompiler)
    ksp(DaggerHilt.hiltCompiler)
    implementation(Room.room)
    ksp(Room.roomCompiler)
}