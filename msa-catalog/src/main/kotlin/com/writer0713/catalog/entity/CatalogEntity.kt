package com.writer0713.catalog.entity

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.time.LocalDateTime

@Table(name = "catalog")
@Entity
class CatalogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false, unique = true, length = 120)
    val productId: String,
    @Column(nullable = false)
    val productName: String,
    @Column(nullable = false)
    val stock: Int,
    @Column(nullable = false)
    val unitPrice: Int,
    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    val createdAt: LocalDateTime,
)
