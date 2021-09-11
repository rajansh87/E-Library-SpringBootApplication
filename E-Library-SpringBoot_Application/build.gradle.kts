import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.4"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
	kotlin("plugin.jpa") version "1.5.21"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
	mavenCentral()
	jcenter()


}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.projectlombok:lombok:1.18.20")
    implementation("junit:junit:4.13.1")
	implementation("org.mockito:mockito-all:1.10.19")
	implementation("org.mockito:mockito-core:3.12.1")
	implementation("org.easymock:easymock:4.3")
	implementation("com.ymock.mock:mock-jdbc:1.0.5")
	implementation("com.ymock.mock:mock-socket:1.0.5")
	implementation("com.github.paweladamski:HttpClientMock:2.2.1")
	implementation("com.github.lzj960515:easy-mock:0.0.1")
	runtimeOnly("mysql:mysql-connector-java")
	testImplementation("io.mockk:mockk:1.12.0")
	testImplementation("com.h2database:h2:1.4.200")
	testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
	testImplementation("org.mockito:mockito-core:1.+")
	implementation("org.springframework.boot:spring-boot-starter-cache")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	implementation("redis.clients:jedis:3.6.3")
	// swagger dependencies
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("org.springframework.boot:spring-boot-starter-actuator:2.5.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "16"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
