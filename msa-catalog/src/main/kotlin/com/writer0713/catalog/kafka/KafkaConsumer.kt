package com.writer0713.catalog.kafka

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.writer0713.catalog.repository.CatalogRepository
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaConsumer(
    private val catalogRepository: CatalogRepository,
    private val objectMapper: ObjectMapper,
) {
    companion object {
        private val log = KotlinLogging.logger {}
    }

    @KafkaListener(topics = ["example-topic"])
    fun consumeMessage(message: String) {
        log.info { "Consuming message: $message" }

        val map =
            try {
                objectMapper.readValue(message, object : TypeReference<Map<String, String>>() {})
            } catch (ex: JsonProcessingException) {
                ex.printStackTrace()
                throw RuntimeException("Could not parse message: $message", ex)
            }

        val quantity = map["quantity"]!!
        val productId = map["productId"]!!
        val catalog = catalogRepository.findByProductId(productId) ?: return

        catalog.stock -= quantity.toInt()
        catalogRepository.save(catalog)
    }
}
