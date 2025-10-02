package com.example.EcommerceBackend.controllers.products;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.EcommerceBackend.services.ProductsService;
import com.example.EcommerceBackend.services.WishListService;

import lombok.*;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

import com.example.EcommerceBackend.controllers.products.request.ProductsRequest;
import com.example.EcommerceBackend.controllers.products.request.UpdateProduct;
import com.example.EcommerceBackend.controllers.products.response.ProductsResponse;
import com.example.EcommerceBackend.controllers.products.response.WishlistResponse;
import com.example.EcommerceBackend.entities.enums.Status;

import com.example.EcommerceBackend.controllers.products.request.AddWishList;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductsService productsService;
    private final WishListService wishListService;

    @GetMapping("/seller/{id}")
    public ResponseEntity<List<ProductsResponse>> getAllProductsbyUserId(@PathVariable Long id){
        return ResponseEntity.ok(productsService.getAllProductsbyUserId(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductsResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productsService.getProductById(id));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ProductsResponse>> getAllActiveProducts(@PathVariable Status status){
        return ResponseEntity.ok(productsService.getallActiveProducts(status));
    }
    
    @PostMapping
    public ResponseEntity<ProductsResponse> createProduct(@RequestBody ProductsRequest productsRequest){
        return ResponseEntity.ok(productsService.createProduct(productsRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductsResponse> updateProduct(@PathVariable Long id, @RequestBody UpdateProduct updateProduct){
        return ResponseEntity.ok(productsService.updaProductsResponse(id, updateProduct));
    }
    
    @PostMapping("/wishlist")
    public ResponseEntity<WishlistResponse> addWishlist(@RequestBody AddWishList addWishList){
        return ResponseEntity.ok(wishListService.addWishList(addWishList));

    }
    
    @GetMapping("/wishlist/{id}")
    public ResponseEntity<List<WishlistResponse>> getWishListwithUserId(@PathVariable Long id){
        return ResponseEntity.ok(wishListService.getWishListwithUserId(id));
    }

    @DeleteMapping("/wishlist/{id}")
    public ResponseEntity<WishlistResponse> deleteWishList(@PathVariable Long id){
        return ResponseEntity.ok(wishListService.deleteWishList(id));
    }
    
    

}
