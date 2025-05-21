package com.writer0713.catalog.service

import com.writer0713.catalog.entity.CatalogEntity
import com.writer0713.catalog.repository.CatalogRepository
import org.springframework.stereotype.Service

@Service
class CatalogServiceImpl(
    private val catalogRepository: CatalogRepository,
) : CatalogService {
    override fun getAllCatalogs(): Iterable<CatalogEntity> = catalogRepository.findAll()
}
