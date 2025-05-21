package com.writer0713.catalog.dto

data class CatalogDto(
    val productId: String,
    val productName: String,
    val unitPrice: Int,
    val stock: Int,
    val quantity: Int,
    val totalPrice: Int,
    val orderId: String,
    val userId: String,
)
