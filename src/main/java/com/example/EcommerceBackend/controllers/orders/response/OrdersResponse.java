package com.example.EcommerceBackend.controllers.orders.response;


import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

import com.example.EcommerceBackend.entities.enums.OrderType;

public record OrdersResponse(
    Long id,
    OrderType orderType,
    List<OrdersItemResponse> orderItems,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    BigDecimal total
) {
}