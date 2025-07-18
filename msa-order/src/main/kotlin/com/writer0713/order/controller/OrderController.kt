package com.writer0713.order.controller

import com.writer0713.order.dto.toResponseOrder
import com.writer0713.order.kafka.KafkaProducer
import com.writer0713.order.service.OrderService
import com.writer0713.order.vo.RequestOrder
import com.writer0713.order.vo.ResponseOrder
import com.writer0713.order.vo.toDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(value = ["/order-service"])
@RestController
class OrderController(
    private val orderService: OrderService,
    private val kafkaProducer: KafkaProducer,
) {
    @PostMapping("/{userId}/orders")
    fun createOrder(
        @PathVariable("userId") userId: String,
        @RequestBody requestOrder: RequestOrder,
    ): ResponseEntity<ResponseOrder> {
        val orderDto = requestOrder.toDto(userId = userId)
        val createdOrderDto = orderService.createOrder(orderDto)
        val responseOrder = createdOrderDto.toResponseOrder()

        // TODO : Kafka Producer로 메시지 전송
        kafkaProducer.sendMessage("example-topic", orderDto)

        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder)
    }

    @GetMapping("/{userId}/orders")
    fun getOrders(
        @PathVariable("userId") userId: String,
    ): ResponseEntity<List<ResponseOrder>> {
        val orders = orderService.getOrdersByUserId(userId)
        val responseOrders = orders.map { it.toResponseOrder() }

        return ResponseEntity.ok(responseOrders)
    }

    @GetMapping("/orders/{orderId}")
    fun getOrder(
        @PathVariable("orderId") orderId: String,
    ): ResponseEntity<ResponseOrder> {
        val orderDto = orderService.getOrderByOrderId(orderId)
        val responseOrder = orderDto.toResponseOrder()

        return ResponseEntity.ok(responseOrder)
    }

    @GetMapping("/health-check")
    fun status(): String = "It's Working in Catalog Service"
}
