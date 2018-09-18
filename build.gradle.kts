// Top-level build file where you can add configuration options common to all sub-projects/modules.

println("root project start")
buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:3.0.1")
//        classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:1.2.21")
//
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}

//task clean(type: Delete) {
//    delete rootProject.buildDir
//}
//ext {
//    compileSdkVersion = 26
//    buildToolsVersion = '27.0.0'
//    minSdkVersion = 17
//    targetSdkVersion = 26
//    versionCode = 2
//    versionName = "2.0"
//
//    // App dependencies
//    supportLibraryVersion = '27.0.2'
//    rxjavaVersion = '2.1.10'
////    rxjavaVersion = '1.1.7'
//    rxandroidVersion = '1.2.1'
//    junitVersion = '4.12'
//    glideVersion = '3.7.0'
//    retrofitVersion = '2.3.0'
//    espressoVersion = '3.0.1'
//    runnerVersion = '1.0.0'
//    mockitoVersion = '1.10.19'
//    hamcrestVersion = '1.3'
//
//}
println("root project end")