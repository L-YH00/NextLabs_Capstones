import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

plugins {
    id("java")
    id("org.springframework.boot") version "2.7.13"
    id("io.spring.dependency-management") version "1.1.0"
    id("jacoco")
}

group = "com.company.nextlabs"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-configuration-processor")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.apache.poi:poi:5.2.3")
    implementation("org.apache.poi:poi-ooxml:5.2.3")

    // HTTP Client
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    // JSON Processing

    // Logging
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.20.0")
    implementation("org.apache.logging.log4j:log4j-core:2.20.0")
    implementation("org.apache.logging.log4j:log4j-api:2.20.0")
    implementation("com.lmax:disruptor:3.4.4")

    // Validation
    implementation("org.hibernate.validator:hibernate-validator:6.2.5.Final")

    // Command Line Interface (CLI)
    implementation("info.picocli:picocli:4.7.4")

    // Testing dependencies
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.3")
    testImplementation("org.mockito:mockito-core:4.11.0")
    testImplementation("org.assertj:assertj-core:3.24.2")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy("jacocoTestReport")
}

configure<JacocoPluginExtension> {
    toolVersion = "0.8.8"
}

tasks.named<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.test)

    reports {
        xml.required.set(true)
        html.required.set(true)
        csv.required.set(false)
    }

    classDirectories.setFrom(
        files(layout.buildDirectory.dir("classes/java/main"))
    )
    sourceDirectories.setFrom(files("src/main/java"))
    executionData.setFrom(fileTree(layout.buildDirectory.dir("jacoco")).include("test.exec"))
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
    mainClass.set("com.company.nextlabs.policygen.PolicyGeneratorApplication")
}

tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    mainClass.set("com.company.nextlabs.policygen.PolicyGeneratorApplication")
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}