plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-entitymanager:5.6.15.Final")
    runtimeOnly("com.h2database:h2:1.4.200")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}