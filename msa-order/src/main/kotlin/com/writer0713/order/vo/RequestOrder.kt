package com.writer0713.order.vo

import com.writer0713.order.dto.OrderDto

data class RequestOrder(
    val productId: String,
    val quantity: Int,
    val unitPrice: Int,
)

fun RequestOrder.toDto(userId: String) =
    OrderDto(
        productId = this.productId,
        quantity = this.quantity,
        unitPrice = this.unitPrice,
        userId = userId,
    )
