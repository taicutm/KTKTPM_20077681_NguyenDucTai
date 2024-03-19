package org.example.baitaplab05.conTroller;

import org.example.baitaplab05.entities.Product;
import org.example.baitaplab05.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired private ProductService productService;


    @GetMapping("/product")
    public String showProductPage(Model model) {
        model.addAttribute("newProduct", new Product());
        List<Product> listproduct = productService.getAllProduct();
        model.addAttribute("listproduct", listproduct);

        return "product";
    }
    // thêm sản phẩm
    @PostMapping("/product/add")
    public String saveProduct(Product product) {
        productService.saveProduct(product);
        return "redirect:/product";
    }
}
