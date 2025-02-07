package com.ecommerceapp.app.productservice.service;

import com.ecommerceapp.app.productservice.model.Product;
import com.ecommerceapp.app.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public Product createProduct(Product product){
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails){
        return productRepository.findById(id).map(
                product -> {
                    product.setName(productDetails.getName());
                    product.setDescription(productDetails.getDescription());
                    product.setPrice(productDetails.getPrice());
                    product.setStockQuantity(productDetails.getStockQuantity());
                    return productRepository.save(product);
                }
        ).orElseThrow(()->new IllegalArgumentException("Invalid product details"));
    }

    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }
}
