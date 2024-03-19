package org.example.baitaplab05.service;

import org.example.baitaplab05.entities.Product;
import org.example.baitaplab05.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired private ProductRepositories productRepository;
    public List<Product> getAllProduct() {
        return (List<Product>) productRepository.findAll();
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).get();
    }

}
