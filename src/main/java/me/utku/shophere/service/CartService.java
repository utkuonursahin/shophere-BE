package me.utku.shophere.service;

import lombok.RequiredArgsConstructor;
import me.utku.shophere.model.Cart;
import me.utku.shophere.model.Product;
import me.utku.shophere.model.User;
import me.utku.shophere.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public Cart getCartByOwnerId(User owner) {
        return cartRepository.findByOwnerId(owner).orElse(null);
    }

    public Cart addProductToCart(User user, Product product) {
        Cart cart = getCartByOwnerId(user);
        if(cart == null){
            cart = new Cart();
            cart.setOwnerId(user);
            cart = cartRepository.save(cart);
        }
        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }

    public Cart removeProductFromCart(User user, UUID id) {
        Cart cart = getCartByOwnerId(user);
        if(cart == null) return null;
        Product product = productService.getProductById(id);
        cart.getProducts().remove(product);
        return cartRepository.save(cart);
    }
}
