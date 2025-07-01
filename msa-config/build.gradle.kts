plugins {
    id("msa.kotlin-spring-convention")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.cloud:spring-cloud-config-server")
    implementation("org.springframework.cloud:spring-cloud-starter-bus-amqp")

    // 개발환경에서만 사용
    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
}
