package com.writer0713.order.service

import com.writer0713.order.dto.OrderDto
import com.writer0713.order.dto.toEntity
import com.writer0713.order.entity.toDto
import com.writer0713.order.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderServiceImpl(
    private val orderRepository: OrderRepository,
) : OrderService {
    override fun createOrder(orderDto: OrderDto): OrderDto {
        val orderEntity = orderDto.toEntity()
        val savedOrderEntity = orderRepository.save(orderEntity)

        return savedOrderEntity.toDto()
    }

    override fun getOrdersByUserId(userId: String): Iterable<OrderDto> {
        val orderEntities = orderRepository.findOrdersByUserId(userId)
        return orderEntities.map { orderEntity ->
            orderEntity.toDto()
        }
    }

    override fun getOrderByOrderId(orderId: String): OrderDto {
        val orderEntity =
            orderRepository.findByOrderId(orderId) ?: throw RuntimeException("Order not found for orderId=$orderId")

        return orderEntity.toDto()
    }
}
