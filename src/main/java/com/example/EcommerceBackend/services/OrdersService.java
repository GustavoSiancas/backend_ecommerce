package com.example.EcommerceBackend.services;

import com.example.EcommerceBackend.entities.User;
import com.example.EcommerceBackend.entities.enums.OrderType;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.EcommerceBackend.controllers.orders.request.AddCart;
import com.example.EcommerceBackend.controllers.orders.request.OrderPaymentDirectRequest;
import com.example.EcommerceBackend.controllers.orders.response.OrdersItemResponse;
import com.example.EcommerceBackend.controllers.orders.response.OrdersResponse;
import com.example.EcommerceBackend.controllers.products.response.ProductsResponse;
import com.example.EcommerceBackend.entities.Orders;
import com.example.EcommerceBackend.entities.OrdersItem;
import com.example.EcommerceBackend.entities.Products;
import com.example.EcommerceBackend.repositories.OrdersItemRepository;
import com.example.EcommerceBackend.repositories.OrdersRepository;
import com.example.EcommerceBackend.repositories.ProductsRepository;
import com.example.EcommerceBackend.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrdersItemRepository ordersItemRepository;
    private final UserRepository userRepository;
    private final ProductsRepository productsRepository;


    public OrdersResponse directPayment(OrderPaymentDirectRequest order){
        User user = userRepository.findById(order.userId()).get();
        Products product = productsRepository.findById(order.orderItemRequest().productId()).get();
        Orders newOrder = new Orders();
        BigDecimal totalPrice= product.getPrice().multiply(new BigDecimal(order.orderItemRequest().quantity()));
        newOrder.setUser(user);
        ordersRepository.save(newOrder);
        OrdersItem ordersItem = new OrdersItem();
        ordersItem.setOrder(newOrder);
        ordersItem.setProduct(product);
        ordersItem.setQuantity(order.orderItemRequest().quantity());
        ordersItem.setPrice(totalPrice);
        ordersItemRepository.save(ordersItem);
        product.setStock(product.getStock() - order.orderItemRequest().quantity());
        newOrder.setOrderType(OrderType.PAYED);
        newOrder.setTotal(totalPrice);
        ordersRepository.save(newOrder);
        productsRepository.save(product);
        List<OrdersItemResponse> items= new ArrayList<>();
        items.add(new OrdersItemResponse(
            new ProductsResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImageUrl(),
                product.getRating(),
                product.getDescription(),
                product.getStock(),
                product.getStatus(),
                product.getCategory()
            ),
            ordersItem.getQuantity(),
            ordersItem.getPrice()
        ));
        return new OrdersResponse(
            newOrder.getId(),
            newOrder.getOrderType(),
            items,
            newOrder.getCreatedAt(),
            newOrder.getUpdatedAt(),
            newOrder.getTotal()
        );
        
    }

    public Orders createOrder(Long id){
        User user = userRepository.findById(id).get();
        Orders order = new Orders();
        order.setUser(user);
        ordersRepository.save(order);
        return order;
    }

    public Orders addtoCart(AddCart addCart){
        Products product = productsRepository.findById(addCart.productId())
                .orElseThrow(() -> new RuntimeException("Product not found"));
        User user = userRepository.findById(addCart.userId())
                        .orElseThrow(() -> new RuntimeException("User not found"));
        Orders cart = ordersRepository.findByUser_IdAndOrderType(addCart.userId(), OrderType.CART)
            .orElseGet(() -> {
                // create new cart if not found
                Orders newCart = new Orders();
                newCart.setUser(user);
                newCart.setOrderType(OrderType.CART);
                newCart.setTotal(BigDecimal.ZERO);
                ordersRepository.save(newCart);
                OrdersItem cartItem = new OrdersItem();
                cartItem.setOrder(newCart);
                cartItem.setProduct(product);
                cartItem.setQuantity(addCart.quantity());
                cartItem.setPrice(product.getPrice().multiply(new BigDecimal(addCart.quantity())));
                ordersItemRepository.save(cartItem);
                return newCart;

            });

        OrdersItem cartItem = new OrdersItem();
        cartItem.setOrder(cart);
        cartItem.setProduct(product);
        cartItem.setQuantity(addCart.quantity());
        cartItem.setPrice(product.getPrice().multiply(new BigDecimal(addCart.quantity())));
        ordersItemRepository.save(cartItem);
        return cart;
    }
}
