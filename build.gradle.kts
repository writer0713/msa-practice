plugins {
    id("msa.kotlin-spring-convention")
}

group = "com.writer0713.msa"
version = "0.0.1-SNAPSHOT"

allprojects {
    apply {
        plugin("kotlin")
        plugin("kotlin-kapt")
        plugin("io.spring.dependency-management")
        plugin("kotlin-spring")
    }

    extra["springCloudVersion"] = "2024.0.1"

    dependencyManagement {
        imports {
            mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
        }
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp")
        implementation("org.jetbrains.kotlin:kotlin-reflect")

        // logging
        implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")

        // test
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }
}
