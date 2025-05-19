plugins {
    id("java")
    id("com.github.ben-manes.versions") version "0.52.0"
    id("org.sonarqube") version "6.2.0.5505"
    id("jacoco")
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
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.3")  // API для тестов
    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.9.3") // Движок для запуска
    implementation ("info.picocli:picocli:4.7.7")
    annotationProcessor ("info.picocli:picocli-codegen:4.7.7")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.4.2")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport) // report is always generated after tests run
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
}
jacoco {
    toolVersion = "0.8.13"
    reportsDirectory = layout.buildDirectory.dir("customJacocoReportDir")
}
tasks.jacocoTestReport {
    reports {
        xml.required = false
        csv.required = false
        html.outputLocation = layout.buildDirectory.dir("jacocoHtml")
    }
}