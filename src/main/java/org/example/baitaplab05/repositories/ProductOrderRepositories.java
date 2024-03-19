package org.example.baitaplab05.repositories;

import org.example.baitaplab05.entities.Product;
import org.example.baitaplab05.entities.ProductOrder;
import org.springframework.data.repository.CrudRepository;

public interface ProductOrderRepositories extends CrudRepository<ProductOrder,Long> {
}
