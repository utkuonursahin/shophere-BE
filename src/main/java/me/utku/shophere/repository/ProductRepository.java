package me.utku.shophere.repository;

import me.utku.shophere.enums.ProductCategory;
import me.utku.shophere.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<List<Product>> findAllByNameContainingIgnoreCaseOrCategoryIn(String name, List<String> category);
}
