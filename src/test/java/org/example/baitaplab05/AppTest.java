package org.example.baitaplab05;

import org.example.baitaplab05.entities.Product;
import org.example.baitaplab05.entities.ProductOrder;
import org.example.baitaplab05.repositories.ProductOrderRepositories;
import org.example.baitaplab05.repositories.ProductRepositories;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AppTest {
    @Autowired private ProductRepositories productRepositories;
    @Autowired private ProductOrderRepositories  productOrderRepositories;


    @Test
    public void testAddNew() {
         Product product = new Product();
         product.setName("Iphone 14 Pro Max");
         product.setPrice(3000);
         product.setQuantity(20);
         Product product1 = productRepositories.save(product);

         Assertions.assertThat(product1).isNotNull();
         Assertions.assertThat(product1.getId()).isGreaterThan(0);
    }
    // test add new product order
    @Test
    public void testAddNewProductOrder() {
        ProductOrder productOrder = new ProductOrder();

        long productId = 1;
        Optional<Product> optionalProduct = productRepositories.findById(productId);
        Product product = optionalProduct.get();

        productOrder.setProduct(product);
        productOrder.setCustomerName("Nguyễn Xuân Long");
        ProductOrder productOrder1 = productOrderRepositories.save(productOrder);

        Assertions.assertThat(productOrder1).isNotNull();
        Assertions.assertThat(productOrder1.getId()).isGreaterThan(0);
    }
    // test find all
    @Test
    public void findAll() {
        Iterable<Product> products = productRepositories.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);
        for (Product product : products) {
            System.out.println(product);
        }
    }
    @Test
    public void findAllProducOrer() {
        Iterable<ProductOrder> products = productOrderRepositories.findAll();
        Assertions.assertThat(products).hasSizeGreaterThan(0);
        for (ProductOrder product : products) {
            System.out.println(product);
        }
    }
    // test update by id
    @Test
    public void testUpdate() {
        long productId = 1;
        Optional<Product> optionalProduct = productRepositories.findById(productId);
        Product product = optionalProduct.get();
        product.setQuantity(14);
        productRepositories.save(product);
        Product product1 = productRepositories.findById(productId).get();
        Assertions.assertThat(product1.getQuantity()).isEqualTo(14);

    }
    // test find by id
    @Test
    public void testFindById() {
        long productId = 1;
        Optional<Product> optionalProduct = productRepositories.findById(productId);
        Assertions.assertThat(optionalProduct).isPresent();
        System.out.println(optionalProduct.get());
    }
    // test delete by id
    @Test
    public void testDelete() {
        long productId = 3;
        productRepositories.deleteById(productId);
        Optional<Product> optionalProduct = productRepositories.findById(productId);
        Assertions.assertThat(optionalProduct).isNotPresent();
    }
}
