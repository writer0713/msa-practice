package com.writer0713.order.kafka

import com.fasterxml.jackson.databind.ObjectMapper
import com.writer0713.order.dto.OrderDto
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val objectMapper: ObjectMapper,
) {
    companion object {
        private val log = KotlinLogging.logger { }
    }

    fun sendMessage(
        topic: String,
        orderDto: OrderDto,
    ): OrderDto {
        val message = objectMapper.writeValueAsString(orderDto)
        kafkaTemplate.send(topic, message)
        log.info { ">>> sent kafka message $message to topic : $topic" }
        return orderDto
    }
}
