plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.weatherapp"
    compileSdk {
        version = release(36)
    }

    buildFeatures{
        viewBinding = true
    }
    defaultConfig {
        applicationId = "com.example.weatherapp"
        minSdk = 25
        targetSdk = 36
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    // 🌐 Networking (Retrofit & OkHttp)
    implementation("com.squareup.retrofit2:retrofit:3.0.0")
    implementation("com.squareup.retrofit2:converter-gson:3.0.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0") // 👈 API Log দেখার জন্য

    // 🖼️ Image Loading (Glide)
    implementation("com.github.bumptech.glide:glide:5.0.7")
    kapt("com.github.bumptech.glide:compiler:5.0.7") // 👈 annotationProcessor থেকে kapt করা হয়েছে

    // 🧵 Coroutines (Asynchronous Tasks)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")

    // 🔄 Lifecycle & MVVM (ViewModel & LiveData)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.9.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.2") // 👈 ViewModel-এর জন্য যোগ করা হয়েছে
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.9.2")  // 👈 LiveData-এর জন্য যোগ করা হয়েছে
}