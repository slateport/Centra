import com.moowork.gradle.node.npm.NpmTask;

plugins {
    java
    `maven-publish`
    idea
    application
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    id("com.github.node-gradle.node") version "2.2.2"
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator:2.3.5.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-security:2.3.5.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb:2.3.5.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-web:2.3.5.RELEASE")
    implementation("com.github.cloudyrock.mongock:mongock-bom:4.1.17")
    implementation("org.mongodb:mongodb-driver-sync:4.1.1")
    implementation("org.springframework.data:spring-data-mongodb:3.0.5.RELEASE")
    implementation("dev.galasa:com.auth0.jwt:3.8.1")
    implementation("org.springframework.hateoas:spring-hateoas:1.1.2.RELEASE")
    implementation("org.antlr:antlr4-runtime:4.8")
    implementation("commons-codec:commons-codec:1.15")
    implementation("javax.xml.bind:jaxb-api:2.3.0")
    implementation("com.github.cloudyrock.mongock:mongock-spring-v5:4.1.17")
    implementation("com.github.cloudyrock.mongock:mongodb-springdata-v3-driver:4.1.17")
    implementation("io.springfox:springfox-boot-starter:3.0.0")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation("org.javers:javers-spring-boot-starter-mongo:5.13.2")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.3.5.RELEASE")
}

springBoot {
    mainClassName = "dev.conductor.centra.CentraApplication"
}

group = "dev.conductor"
version = "0.0.1-SNAPSHOT"
description = "centra"
java.sourceCompatibility = JavaVersion.VERSION_15
java.targetCompatibility = JavaVersion.VERSION_15

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
    dependsOn("copyWebApp")
}

tasks.register<NpmTask>("appNpmInstall") {
    this.onlyIf { !file("${project.projectDir}/src/main/webapp/node_modules").exists() }
    description = "Installs all dependencies from package.json"
    workingDir = file("${project.projectDir}/src/main/webapp")
    args = listOf("install")
}

tasks.register<NpmTask>("appNpmBuild") {
    dependsOn("appNpmInstall")
    description = "Builds project"
    workingDir = file("${project.projectDir}/src/main/webapp")
    args = listOf("run", "build")
}

tasks.register<Copy>("copyWebApp") {
    dependsOn("appNpmBuild")
    description = "Copies built project to where it will be served"
    from("src/main/webapp/dist")
    into("build/resources/main/static/.")
}

node {
    download = false
}