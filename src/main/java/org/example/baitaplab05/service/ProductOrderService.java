package org.example.baitaplab05.service;

import org.example.baitaplab05.entities.Product;
import org.example.baitaplab05.entities.ProductOrder;
import org.example.baitaplab05.repositories.ProductOrderRepositories;
import org.example.baitaplab05.repositories.ProductRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderService {
    @Autowired private ProductOrderRepositories productOrderRepositories;
    public List<ProductOrder> getAllProductOrder() {
        return (List<ProductOrder>) productOrderRepositories.findAll();
    }

    public void saveProductOrder(ProductOrder productOrder) {
        productOrderRepositories.save(productOrder);
    }
}
