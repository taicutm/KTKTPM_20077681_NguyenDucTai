package org.example.baitaplab05.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.baitaplab05.entities.Product;
import org.example.baitaplab05.entities.ProductOrder;

import java.lang.reflect.Type;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;


public class ConvertObject2Json {

    // từ object -> json chp product
    public String convertObject2Json(Product product) {
        Gson gson = new Gson();
        return gson.toJson(product);
    }
    // từ object -> json chp productorder
    public String convertProductOrder2Json(ProductOrder productOrder) {
        Gson gson = new Gson();
        return gson.toJson(productOrder);
    }

    // từ json -> object
    public Product convertJson2Object(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Product.class);
    }
    // từ json productorder -> object
    public ProductOrder convertJson2ProductOrder(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ProductOrder.class);
    }

    //  từ 1 list object product -> json
    public List<Product> convertListObject2Json(String json) {
        Gson gson = new Gson();
        Type productListType = new TypeToken<List<Product>>(){}.getType();
        return gson.fromJson(json, productListType);
    }
    // từ 1 list json -> list object product
    public String convertListJson2Object(List<Product> products) {
        Gson gson = new Gson();

        return gson.toJson(products);
    }

    public static void main(String[] args) {
        // test bài
        ConvertObject2Json cv = new ConvertObject2Json();
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
            product.setId(i);
            product.setName("Product " + i);
            product.setPrice(i * 1000);
            product.setQuantity(i * 10);
            products.add(product);
        }
        // xuất ra danh sách sản phẩm
        System.out.println("Danh sách sản phẩm: ");
        products.forEach(product -> System.out.println(product));
        // sau khi chuyển đổi qua json
        String json = cv.convertListJson2Object(products);
        System.out.println("Danh sách sản phẩm sau khi chuyển đổi qua json: ");
        System.out.println(json);

        // mã hoá chuỗi json
        CodingService cd = new CodingService();
        String encodeJson = cd.encode(json);
        System.out.println("Chuỗi json sau khi mã hoá: ");
        System.out.println(encodeJson);
        // giãi mả ngược lai từ encodeJson qua json
        String decodeJson = cd.decode(encodeJson);
        System.out.println("Chuỗi json sau khi giải mã: ");
        System.out.println(decodeJson);

    }

}
