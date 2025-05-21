package com.writer0713.order.repository

import com.writer0713.order.entity.OrderEntity
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<OrderEntity, Long> {
    fun findByOrderId(orderId: String): OrderEntity?

    fun findOrdersByUserId(userId: String): List<OrderEntity>
}
