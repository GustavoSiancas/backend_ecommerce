package com.example.EcommerceBackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.EcommerceBackend.controllers.products.request.AddWishList;
import com.example.EcommerceBackend.controllers.products.response.WishlistResponse;
import com.example.EcommerceBackend.entities.Wishlist;
import com.example.EcommerceBackend.repositories.ProductsRepository;
import com.example.EcommerceBackend.repositories.WishlistRepository;
import com.example.EcommerceBackend.repositories.UserRepository;

import com.example.EcommerceBackend.entities.Products;
import com.example.EcommerceBackend.entities.User;
import com.example.EcommerceBackend.controllers.products.response.ProductsResponse;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListService {
    private final WishlistRepository wishlistRepository;
    private final ProductsRepository productsRepository;
    private final UserRepository userRepository;

    public WishlistResponse addWishList(AddWishList addWishList){
        if (wishlistRepository.findByUser_IdAndProduct_Id(addWishList.userId(), addWishList.productId()).isPresent()){
            Wishlist wishlist= wishlistRepository.findByUser_IdAndProduct_Id(addWishList.userId(), addWishList.productId()).get();
            wishlist.setIsActive(true);
            wishlistRepository.save(wishlist);
            return new WishlistResponse(
                wishlist.getId(),
                wishlist.getIsActive(),
                new ProductsResponse(
                    wishlist.getProduct().getId(),
                    wishlist.getProduct().getName(),
                    wishlist.getProduct().getPrice(),
                    wishlist.getProduct().getImageUrl(),
                    wishlist.getProduct().getRating(),
                    wishlist.getProduct().getDescription(),
                    wishlist.getProduct().getStock(),
                    wishlist.getProduct().getStatus(),
                    wishlist.getProduct().getCategory()
            ));
        }
        User user = userRepository.findById(addWishList.userId()).get();
        Products product = productsRepository.findById(addWishList.productId()).get();
        Wishlist wishlist = new Wishlist();
        wishlist.setUser(user);
        wishlist.setProduct(product);
        wishlistRepository.save(wishlist);
        return new WishlistResponse(
            wishlist.getId(),
            wishlist.getIsActive(),
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
            )
        );
    }

    public List<WishlistResponse> getWishListwithUserId(Long id){
        List<Wishlist> wishlist = wishlistRepository.findByUser_IdAndIsActive(id, true);
        return wishlist.stream().map(wish -> new WishlistResponse(
            wish.getId(),
            wish.getIsActive(),
            new ProductsResponse(
                wish.getProduct().getId(),
                wish.getProduct().getName(),
                wish.getProduct().getPrice(),
                wish.getProduct().getImageUrl(),
                wish.getProduct().getRating(),
                wish.getProduct().getDescription(),
                wish.getProduct().getStock(),
                wish.getProduct().getStatus(),
                wish.getProduct().getCategory()
            )
        )).toList();

    }

    public WishlistResponse deleteWishList(Long id){
        Wishlist wishlist = wishlistRepository.findById(id).get();
        wishlist.setIsActive(false);
        wishlistRepository.save(wishlist);
        return new WishlistResponse(
            wishlist.getId(),
            wishlist.getIsActive(),
            new ProductsResponse(
                wishlist.getProduct().getId(),
                wishlist.getProduct().getName(),
                wishlist.getProduct().getPrice(),
                wishlist.getProduct().getImageUrl(),
                wishlist.getProduct().getRating(),
                wishlist.getProduct().getDescription(),
                wishlist.getProduct().getStock(),
                wishlist.getProduct().getStatus(),
                wishlist.getProduct().getCategory()
            )
        );
    }
    
}
