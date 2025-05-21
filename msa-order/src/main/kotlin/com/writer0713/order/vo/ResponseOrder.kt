package com.writer0713.order.vo

import java.time.LocalDateTime

data class ResponseOrder(
    val productId: String,
    val quantity: Int,
    val unitPrice: Int,
    val totalPrice: Int,
    val createdAt: LocalDateTime,
    val orderId: String,
)
