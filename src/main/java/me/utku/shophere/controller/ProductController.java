package me.utku.shophere.controller;

import lombok.RequiredArgsConstructor;
import me.utku.shophere.dto.GenericResponse;
import me.utku.shophere.dto.ProductFilter;
import me.utku.shophere.model.Product;
import me.utku.shophere.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<GenericResponse<List<Product>>> getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        return new GenericResponse<>(HttpStatus.OK.value(),"Products find successfully",productList).toResponseEntity();
    }

    @GetMapping("/id")
    public ResponseEntity<GenericResponse<Product>> getProductById(@RequestParam UUID id) {
        Product product = productService.getProductById(id);
        return new GenericResponse<>(HttpStatus.OK.value(),"Product find successfully",product).toResponseEntity();
    }

    @PostMapping("/filter")
    public ResponseEntity<GenericResponse<List<Product>>> getProductsByFilter(@RequestBody ProductFilter productFilter){
        List<Product> productList = productService.getProductsByFilter(productFilter);
        return new GenericResponse<>(HttpStatus.OK.value(),"Products filtered successfully",productList).toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<GenericResponse<Product>> createProduct(@RequestBody Product productData) {
        Product product = productService.createProduct(productData);
        return new GenericResponse<>(HttpStatus.CREATED.value(),"Product created successfully",product).toResponseEntity();
    }

    @PostMapping("/add-all")
    public ResponseEntity<GenericResponse<List<Product>>> createProducts(@RequestBody List<Product> productsData){
        List<Product> products = productService.createProducts(productsData);
        return new GenericResponse<>(HttpStatus.CREATED.value(),"Products created successfully",products).toResponseEntity();
    }
}