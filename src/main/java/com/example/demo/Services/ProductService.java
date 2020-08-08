package com.example.demo.Services;

import com.example.demo.Models.Product;
import com.example.demo.Repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    public Product updateProduct(Product product) {
        Optional<Product> optionalProduct = productRepository.findById(product.getProductId());

        if (optionalProduct.isPresent()) {
            Product newProduct = optionalProduct.get();
            newProduct.setProductName(product.getProductName());
            newProduct.setProductDescription(product.getProductDescription());
            newProduct.setProductQuantity(product.getProductQuantity());
            productRepository.save(newProduct);
            return newProduct;
        } else {
            throw new RuntimeException("Product with id " + product.getProductId() + " does not exist!");
        }
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new RuntimeException("Product with id " + productId + " does not exist!");
        }
    }
}
