buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.6")
    }
}
plugins {
    id ("com.google.devtools.ksp") version "1.9.20-Beta-1.0.13" apply false
    id("com.android.application") version "8.2.2" apply false
    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
}