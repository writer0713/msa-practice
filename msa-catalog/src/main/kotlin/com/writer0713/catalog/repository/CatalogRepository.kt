package com.writer0713.catalog.repository

import com.writer0713.catalog.entity.CatalogEntity
import org.springframework.data.repository.CrudRepository

interface CatalogRepository : CrudRepository<CatalogEntity, Long> {
    fun findByProductId(productId: String): CatalogEntity?
}
