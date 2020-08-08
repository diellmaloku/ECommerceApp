package com.example.demo.Controllers;

import com.example.demo.Models.Product;
import com.example.demo.Services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/product")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        productService.updateProduct(product);
        return ResponseEntity.ok().body(product);
    }

    @GetMapping("/products")
    public ResponseEntity<Iterable<Product>> getAllProducts() {
        Iterable<Product> productIterable = productService.getAllProducts();
        return ResponseEntity.ok().body(productIterable);
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok().body(product);
    }
}
