package org.example.baitaplab05.repositories;

import org.example.baitaplab05.entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepositories extends CrudRepository<Product,Long> {
}
