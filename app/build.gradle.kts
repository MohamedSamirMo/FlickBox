plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs")

}

android {
    namespace = "com.example.movieapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movieapp"
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

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation( "io.reactivex.rxjava3:rxjava:3.1.5")
    implementation ("io.reactivex.rxjava3:rxandroid:3.0.2")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.intuit.sdp:sdp-android:1.1.0")

    // lottie Animation
    implementation ("com.airbnb.android:lottie:5.2.0")
    //Glide Image
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    val room_version = "2.6.1"
    val rxjava_version = "3.1.5"

    implementation ("androidx.room:room-runtime:2.6.1")
    kapt ("androidx.room:room-compiler:2.6.1")
    implementation( "androidx.room:room-rxjava3:2.6.1") // Room RxJava integration

    implementation ("io.reactivex.rxjava3:rxjava:3.1.5")
    implementation ( "io.reactivex.rxjava3:rxandroid:3.0.2")

    implementation("androidx.room:room-runtime:2.6.1")
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.6.1")
    implementation ("androidx.cardview:cardview:1.0.0")

    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.6.1")


    //
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
    // optional - Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.1")

    // optional - RxJava2 support for Room
    implementation("androidx.room:room-rxjava2:2.6.1")

    // optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:2.6.1")
    val lifecycle_version = "2.8.5"
    val arch_version = "2.2.0"

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4'")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")


    // Room KTX (for Kotlin Coroutines support)
    implementation ("androidx.room:room-ktx:2.5.0")
    // ViewModel
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.5")

    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.8.5")
    //dagger Hilt
    implementation("com.google.dagger:hilt-android:2.52")
    kapt("com.google.dagger:hilt-android-compiler:2.52")
    // fragment Ktx
    implementation ("androidx.fragment:fragment-ktx:1.6.1")
    // navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.3")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.3")
}
kapt{
    correctErrorTypes = true
}