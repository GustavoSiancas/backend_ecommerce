package com.example.EcommerceBackend.controllers.orders;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.EcommerceBackend.controllers.orders.request.AddCart;
import com.example.EcommerceBackend.controllers.orders.request.OrderPaymentDirectRequest;
import com.example.EcommerceBackend.controllers.orders.response.OrdersResponse;
import com.example.EcommerceBackend.services.OrdersService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.EcommerceBackend.entities.Orders;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping("/direct-payment")
    public OrdersResponse directPayment(@RequestBody OrderPaymentDirectRequest ordersResponse){
        return ordersService.directPayment(ordersResponse);
    }

    @PostMapping("/addCart")
    public Orders createOrder(@RequestBody AddCart addCart){
        return ordersService.addtoCart(addCart);
    }
    
}
