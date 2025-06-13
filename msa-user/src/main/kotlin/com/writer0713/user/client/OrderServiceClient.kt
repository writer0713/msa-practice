package com.writer0713.user.client

import com.writer0713.user.vo.ResponseOrder
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "msa-order")
interface OrderServiceClient {
    @GetMapping("/order-service/{userId}/orders")
    fun getOrders(
        @PathVariable("userId") userId: String,
    ): List<ResponseOrder>
}
