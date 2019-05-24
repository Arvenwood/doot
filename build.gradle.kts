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
    maven {
        setUrl("https://dl.bintray.com/pie-flavor/maven")
    }
    maven {
        setUrl("https://jitpack.io")
    }
    maven {
        setUrl("https://repo.codemc.org/repository/maven-public/")
    }
}

dependencies {
    compileOnly("org.spongepowered:spongeapi:7.1.0")
    compileOnly("flavor.pie:mcmoji:1.2.0:all")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}