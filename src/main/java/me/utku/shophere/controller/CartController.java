package me.utku.shophere.controller;

import lombok.RequiredArgsConstructor;
import me.utku.shophere.dto.GenericResponse;
import me.utku.shophere.model.Cart;
import me.utku.shophere.model.Product;
import me.utku.shophere.model.User;
import me.utku.shophere.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping
    public ResponseEntity<GenericResponse<Cart>> getCartByOwnerId(@AuthenticationPrincipal User user) {
        Cart cart = cartService.getCartByOwnerId(user);
        return new GenericResponse<>(
                HttpStatus.OK.value(), "Cart retrieved successfully", cart
        ).toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<GenericResponse<Cart>> addProductToCart(
            @AuthenticationPrincipal User user,
            @RequestBody Product product
    ) {
        Cart cart = cartService.addProductToCart(user, product);
        return new GenericResponse<>(
                HttpStatus.OK.value(), "Product added to cart successfully", cart
        ).toResponseEntity();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Cart>> removeProductFromCart(
            @AuthenticationPrincipal User user,
            @PathVariable UUID id
    ){
        Cart cart = cartService.removeProductFromCart(user, id);
        return new GenericResponse<>(
                HttpStatus.OK.value(), "Product removed from cart successfully", cart
        ).toResponseEntity();
    }
}
