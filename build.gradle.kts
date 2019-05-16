plugins {
    java
}

group = "arven"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        setUrl("https://repo.spongepowered.org/maven")
    }
}

dependencies {
    compileOnly("org.spongepowered:spongeapi:7.1.0")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}