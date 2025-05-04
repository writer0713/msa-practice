import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("msa.kotlin-spring-convention")
}

group = "com.writer0713.msa"
version = "0.0.1-SNAPSHOT"

subprojects {

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks {
        withType<KotlinCompile> {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "21"
            }
        }

        withType<JavaCompile> {
            sourceCompatibility = "21"
            targetCompatibility = "21"
        }

        withType<Test> {
            useJUnitPlatform()
        }
    }
}
