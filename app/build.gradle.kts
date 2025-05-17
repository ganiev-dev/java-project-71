plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.52.0"
    id("org.sonarqube") version "6.2.0.5505"
    checkstyle
    application
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

application {
    mainClass = "hexlet.code.App"
}

repositories {
    mavenCentral()
}

sonar {
    properties {
        property("sonar.projectKey", "ganiev-dev_java-project-71")
        property("sonar.organization", "ganiev-dev")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    implementation ("org.junit.jupiter:junit-jupiter-api:5.5.1")
    implementation ("info.picocli:picocli:4.7.7")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.7")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
}

tasks.test {
    useJUnitPlatform()
}