package com.writer0713.catalog.service

import com.writer0713.catalog.entity.CatalogEntity

interface CatalogService {
    fun getAllCatalogs(): Iterable<CatalogEntity>
}
