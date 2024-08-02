import java.io.FileInputStream
import java.io.InputStream
import java.util.Properties

plugins {
  kotlin("kapt")
  alias(libs.plugins.androidApplication)
  alias(libs.plugins.jetbrainsKotlinAndroid)
  id("com.google.dagger.hilt.android")
  id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}

android {
  namespace = "com.example.gastion"
  compileSdk = 34

  defaultConfig {
    applicationId = "com.example.gastion"
    minSdk = 24
    targetSdk = 34
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    vectorDrawables {
      useSupportLibrary = true
    }
    buildConfigField("Boolean", "IS_WS", "true")
  }

  secrets {
    // Optionally specify a different file name containing your secrets.
    // The plugin defaults to "local.properties"
    propertiesFileName = "secret.properties"

    // A properties file containing default secret values. This file can be
    // checked in version control.
    defaultPropertiesFileName = "local.properties"

    // Configure which keys should be ignored by the plugin by providing regular expressions.
    // "sdk.dir" is ignored by default.
    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
  }


  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }

  productFlavors {
    create("customer") {
      applicationIdSuffix = ".customer"
    }

    create("driver") {
      applicationIdSuffix = ".driver"
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
    buildConfig = true
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.5.1"
  }
  packaging {
    resources {
      excludes += "/META-INF/{AL2.0,LGPL2.1}"
    }
  }
}

dependencies {
  // Maps SDK for Android
  implementation("com.google.android.gms:play-services-maps:18.2.0")
  implementation("com.google.maps.android:maps-compose:4.4.0")
  implementation("com.google.android.gms:play-services-location:21.0.1")
  implementation("com.github.MKergall:osmbonuspack:6.9.0")
  //implementation("org.osmdroid:osmdroid-android:6.1.14")

  implementation("com.google.dagger:hilt-android:2.48")
  kapt("com.google.dagger:hilt-android-compiler:2.48")

  // Http Networking
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("com.squareup.okhttp3:okhttp:4.12.0")
  implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

  // MQTT
  implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")
  implementation("org.eclipse.paho:org.eclipse.paho.android.service:1.1.1")

  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.activity.compose)
  implementation(platform(libs.androidx.compose.bom))
  implementation(libs.androidx.ui)
  implementation(libs.androidx.ui.graphics)
  implementation(libs.androidx.ui.tooling.preview)
  implementation(libs.androidx.material3)
  testImplementation(libs.junit)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.ui.test.junit4)
  debugImplementation(libs.androidx.ui.tooling)
  debugImplementation(libs.androidx.ui.test.manifest)
}