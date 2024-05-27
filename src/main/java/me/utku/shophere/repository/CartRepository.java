package me.utku.shophere.repository;

import me.utku.shophere.model.Cart;
import me.utku.shophere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    Optional<Cart> findByOwnerId(User owner);
}
