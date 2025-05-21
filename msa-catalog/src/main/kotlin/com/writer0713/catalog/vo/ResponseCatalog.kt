package com.writer0713.catalog.vo

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseCatalog(
    val productId: String,
    val productName: String,
    val unitPrice: Int,
    val stock: Int,
    val createdAt: LocalDateTime,
)
