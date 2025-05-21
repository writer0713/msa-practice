package com.writer0713.order.dto

import com.writer0713.order.entity.OrderEntity
import com.writer0713.order.vo.ResponseOrder
import java.time.LocalDateTime
import java.util.*

data class OrderDto(
    val productId: String,
    val quantity: Int,
    val unitPrice: Int,
    val totalPrice: Int = quantity * unitPrice,
    val orderId: String = UUID.randomUUID().toString(),
    val userId: String,
    val createdAt: LocalDateTime? = null,
)

fun OrderDto.toEntity() =
    OrderEntity(
        productId = this.productId,
        quantity = this.quantity,
        unitPrice = this.unitPrice,
        totalPrice = this.totalPrice,
        orderId = this.orderId,
        userId = this.userId,
    )

fun OrderDto.toResponseOrder() =
    ResponseOrder(
        productId = this.productId,
        quantity = this.quantity,
        unitPrice = this.unitPrice,
        totalPrice = this.totalPrice,
        createdAt = this.createdAt!!,
        orderId = this.orderId,
    )
