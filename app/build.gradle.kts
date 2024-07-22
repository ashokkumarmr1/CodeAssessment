plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.codeassessmentexample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.codeassessmentexample"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    sourceSets {
        getByName("main") {
            java {
                srcDirs(
                    "src\\main\\java",
                    "src\\main\\java\\com.codeassessmentexample\\local\\entity",
                    "src\\main\\java",
                    "src\\main\\java\\com.codeassessmentexample.data.local.entity",
                    "src\\main\\java",
                    "src\\main\\java\\com\\codeassessmentexample\\data\\local\\entity",
                    "src\\main\\java",
                    "src\\main\\java\\com\\codeassementexample\\data\\respository",
                    "src\\main\\java",
                    "src\\main\\java\\com.codeassessmentexample\\data\\repository",
                    "src\\main\\java",
                    "src\\main\\java\\com.codeassessmentexample\\source", "src\\main\\java", "src\\main\\java\\com.codeassessmentexample\\presentation",
                    "src\\main\\java",
                    "src\\main\\java\\com.codeassessmentexample\\presentation\\viewmodel"
                )
            }
        }
    }
}

dependencies {

    implementation("androidx.test.ext:junit-ktx:1.2.1")
    val room_version = "2.6.1"

    implementation("androidx.core:core-ktx:1.8.10")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.activity:activity-compose:1.7.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("com.google.android.gms:play-services-mlkit-text-recognition-common:19.0.0")
    testImplementation("junit:junit:4.13.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(project(mapOf("path" to ":app")))
    androidTestImplementation(project(mapOf("path" to ":app")))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Networking
    implementation ("com.squareup.retrofit2:converter-moshi:2.6.2")
    implementation ("com.squareup.retrofit2:converter-gson:2.0.0-beta4")
    implementation ("com.squareup.retrofit2:retrofit:2.8.1")
    implementation ("com.squareup.okhttp3:okhttp:4.7.2")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.7.2")

    implementation("com.google.dagger:hilt-android:2.48")
    ksp("com.google.dagger:hilt-android-compiler:2.48")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("io.coil-kt:coil-compose:2.0.0-rc01")

    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.mockito:mockito-core:3.4.6")
    androidTestImplementation("org.mockito:mockito-android:2.24.5")
    testImplementation ("app.cash.turbine:turbine:0.11.0")

    // For Robolectric tests.
    testImplementation("com.google.dagger:hilt-android-testing:2.44")
    // ...with Kotlin.
    kspTest("com.google.dagger:hilt-android-compiler:2.44")
    // ...with Java.
    testAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.44")

    // For instrumented tests.
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.44")
    // ...with Kotlin.
    kspAndroidTest("com.google.dagger:hilt-android-compiler:2.44")
    // ...with Java.
    androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.44")

    androidTestImplementation("junit:junit:4.12")
    androidTestImplementation("com.android.support:support-annotations:23.3.0")
    androidTestImplementation("com.android.support.test:runner:0.4.1")
    androidTestImplementation("com.android.support.test:rules:0.4.1")
    androidTestImplementation("com.android.support.test.espresso:espresso-core:2.2.1")
    androidTestImplementation("com.android.support.test.espresso:espresso-contrib:2.2.1")
    androidTestImplementation("org.hamcrest:hamcrest-library:1.3")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")

    //MockWebServer
    implementation ("com.squareup.okhttp3:mockwebserver:4.11.0")
    testImplementation ("io.mockk:mockk:1.13.4")

    implementation("androidx.room:androidx.room.gradle.plugin:$room_version")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // To use Kotlin Symbol Processing (KSP)
    ksp("androidx.room:room-compiler:$room_version")

    //optional - Kotlin Extension and Coroutines support for Room
    implementation("androidx.room:room-ktx:$room_version")

    //optional - RxJava3 support for Room
    implementation("androidx.room:room-rxjava3:$room_version")


    //optional - Paging 3 Integration
    implementation("androidx.room:room-paging:$room_version")

    // Gson
    implementation ("com.google.code.gson:gson:2.9.0")

}
