plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation ("com.google.android.material:material:1.0.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Jetpack navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")


    // ViewBinding delegate 
    implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.3")

    //LiveData + ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    //Retrofit + OkHttp
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.5.0") // TODO: что будет если удалить

    // KOIN
    implementation ("io.insert-koin:koin-core:2.0.1")
    implementation ("io.insert-koin:koin-android:2.0.1")
    implementation ("io.insert-koin:koin-androidx-scope:2.0.1")
    // or Koin for Android Architecture ViewModel
    implementation ("io.insert-koin:koin-androidx-viewmodel:2.0.1")


    // testKoin
    testImplementation ("com.nhaarman:mockito-kotlin:1.5.0")
    testImplementation ("androidx.arch.core:core-testing:2.1.0") // Test helpers for LiveData
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.1")
    testImplementation ("io.insert-koin:koin-test:2.0.1")

    //Gson converter
    implementation ("com.google.code.gson:gson:2.10.1")
}