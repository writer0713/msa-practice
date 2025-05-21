package com.writer0713.order.entity

import com.writer0713.order.dto.OrderDto
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime

@Entity
@Table(name = "orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
    @Column(nullable = false, length = 120)
    val productId: String,
    @Column(nullable = false)
    val quantity: Int,
    @Column(nullable = false)
    val unitPrice: Int,
    @Column(nullable = false)
    val totalPrice: Int,
    @Column(nullable = false)
    val userId: String,
    @Column(nullable = false, unique = true)
    val orderId: String,
    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime = LocalDateTime.now(),
)

fun OrderEntity.toDto() =
    OrderDto(
        productId = this.productId,
        quantity = this.quantity,
        unitPrice = this.unitPrice,
        totalPrice = this.totalPrice,
        orderId = this.orderId,
        userId = this.userId,
        createdAt = this.createdAt,
    )
