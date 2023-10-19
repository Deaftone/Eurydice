plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "net.deaftone.album.ui"
    compileSdk = 34


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }

}

dependencies {
    implementation(project(mapOf("path" to ":feature:album:data")))
    implementation(project(mapOf("path" to ":core")))
    retrofit()
    room()
    hilt()
    compose()
    moshi()
    activity()
    navigation()
    glide()
    lifecycle()
}