package org.example.baitaplab05.conTroller;

import org.example.baitaplab05.entities.Product;
import org.example.baitaplab05.entities.ProductOrder;
import org.example.baitaplab05.service.EmailService;
import org.example.baitaplab05.service.ProductOrderService;
import org.example.baitaplab05.service.ProductService;
import org.example.baitaplab05.service.serviceImpl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductOrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    private OrderServiceImpl orderServiceimpl;
    @Autowired
    private EmailService emailService;
    @GetMapping("/productorder")
    public String ShowProductOrderPage(Model model) {
        // thêm sản phẩm order
        model.addAttribute("newProductOrder", new ProductOrder());

        // lấy ra danh sách sản phẩm và sản phẩm order
        List<Product> listproduct = productService.getAllProduct();
        model.addAttribute("listproduct", listproduct);

        List<ProductOrder> listproductorder = productOrderService.getAllProductOrder();
        model.addAttribute("listproductorder", listproductorder);
        return "productorder";
    }
    // thêm sản phẩm order khi thêm sản phẩm vào giỏ hàng thì bên bảng sản phẩm , sản phẩm đó sẽ giảm đi 1
    @PostMapping("/productorder/add")
    public String saveProduct(@ModelAttribute("newProductOrder") ProductOrder productOrder, @RequestParam("productId") Long productId) {
        Product product = productService.getProductById(productId); // Lấy sản phẩm từ ID được chọn
        productOrder.setProduct(product); // Set sản phẩm cho đơn hàng

        // kiểm tra số lượng ở frontend trước
        if (product.getQuantity() > 0 && product.getQuantity() >= productOrder.getQuantity_order()) {
            try {
                orderServiceimpl.sendMessage(productOrder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // Gưi email thông báo cho KH thông qua email của productOrder
            // Tạo nội dung email từ chối đơn hàng
            String customerName = productOrder.getCustomerName();
            String productName = productOrder.getProduct().getName();
            long quantityOrdered = productOrder.getQuantity_order();
            String customerEmail = productOrder.getCustomerEmail();
            String emailNpp = "handsomeboy7622@gmail.com";
            String subject = "Thông báo về đơn hàng của bạn";
            String body = "<html><body>"
                    + "<h2>Thông báo về đơn hàng của bạn</h2>"
                    + "<p>Xin chào " +  customerName + ",</p>"
                    + "<p>Chúng tôi rất tiếc phải thông báo rằng sản phẩm " + productName + " bạn đặt hàng không có sẵn hoặc không đủ số lượng (" + quantityOrdered + " sản phẩm) để đáp ứng yêu cầu của bạn tại thời điểm này.</p>"
                    + "<p>Xin lưu ý rằng chúng tôi sẽ cố gắng hết sức để cung cấp sản phẩm cho bạn trong thời gian sớm nhất có thể. Trong trường hợp bạn cần hỗ trợ hoặc muốn biết thông tin cụ thể hơn, vui lòng liên hệ với chúng tôi qua email: " + emailNpp + ".</p>"
                    + "<p>Xin chân thành xin lỗi về sự bất tiện này!</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p>Đội ngũ cửa hàng của chúng tôi</p>"
                    + "</body></html>";

            // Gửi email từ chối đơn hàng
            emailService.sendEmail(customerEmail, body, subject);
            return "redirect:/error";
        }


        return "redirect:/productorder";
    }

    @GetMapping("/")
    public String Homepage(Model model) {
        return "redirect:/productorder";
    }
    @GetMapping("/error")
    public String Errorpage(Model model) {
        return "redirect:/error";
    }
}
