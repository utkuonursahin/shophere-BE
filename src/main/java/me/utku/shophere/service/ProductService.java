package me.utku.shophere.service;

import lombok.RequiredArgsConstructor;
import me.utku.shophere.dto.ProductFilter;
import me.utku.shophere.model.Product;
import me.utku.shophere.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getProductsByFilter(ProductFilter productFilter) {
        return productRepository.findAllByNameContainingIgnoreCaseOrCategoryIn(
                productFilter.name(),
                productFilter.categories()
        ).orElse(null);
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> createProducts(List<Product> products){
        return productRepository.saveAll(products);
    }
}
