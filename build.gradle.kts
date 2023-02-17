import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.netflix.graphql.dgs.codegen.gradle.GenerateJavaTask

plugins {
	id("org.springframework.boot") version "3.0.2"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	id("com.netflix.dgs.codegen") version "5.1.17"
	kotlin("plugin.allopen") version "1.5.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

allOpen {
	annotation("jakarta.persistence.Entity")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation(platform("com.netflix.graphql.dgs:graphql-dgs-platform-dependencies:latest.release"))
	implementation("com.netflix.graphql.dgs:graphql-dgs-spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("com.h2database:h2")
	implementation("com.graphql-java:graphql-java-extended-scalars:17.0")
	implementation("org.jetbrains.kotlin:kotlin-noarg")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<GenerateJavaTask> {
	schemaPaths =
		mutableListOf(
			"${projectDir}/src/main/resources/schema"
		)
	packageName =
		"com.example.demo.generated"
	typeMapping = mutableMapOf("DateTime" to "java.time.OffsetDateTime")
	generateClient = false
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
