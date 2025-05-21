package com.writer0713.catalog.controller

import com.writer0713.catalog.service.CatalogService
import com.writer0713.catalog.vo.ResponseCatalog
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping(value = ["/catalog-service"])
@RestController
class CatalogController(
    private val catalogService: CatalogService,
) {
    @GetMapping("/catalogs")
    fun getUsers(): ResponseEntity<List<ResponseCatalog>> {
        val catalogs = catalogService.getAllCatalogs()
        val responseCatalogs =
            catalogs.map { catalogEntity ->
                ResponseCatalog(
                    productId = catalogEntity.productId,
                    productName = catalogEntity.productName,
                    unitPrice = catalogEntity.unitPrice,
                    stock = catalogEntity.stock,
                    createdAt = catalogEntity.createdAt,
                )
            }
        return ResponseEntity.ok(responseCatalogs)
    }

    @GetMapping("/health-check")
    fun status(): String = "It's Working in Catalog Service"
}
