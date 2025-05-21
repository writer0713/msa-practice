package com.writer0713.order.service

import com.writer0713.order.dto.OrderDto

interface OrderService {
    fun createOrder(orderDto: OrderDto): OrderDto

    fun getOrderByOrderId(orderId: String): OrderDto

    fun getOrdersByUserId(userId: String): Iterable<OrderDto>
}
