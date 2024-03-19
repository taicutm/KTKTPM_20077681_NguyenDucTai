package org.example.baitaplab05.service.serviceImpl;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.example.baitaplab05.entities.Product;
import org.example.baitaplab05.entities.ProductOrder;
import org.example.baitaplab05.service.EmailService;
import org.example.baitaplab05.service.ProductOrderService;
import org.example.baitaplab05.service.ProductService;
import org.example.baitaplab05.utils.CodingService;
import org.example.baitaplab05.utils.ConvertObject2Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl {
    @Autowired
    private ProductOrderService productOrderService;
    @Autowired
    private ProductService productService;
    @Autowired
    private EmailService emailService;

    // vết 1 hàm gửi message
    public void sendMessage(ProductOrder productOrder) throws JMSException {
        try {
            // code here
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            Queue destinatiom = session.createQueue("dat_hang");

            // tu session tao ra producer
            MessageProducer producer = session.createProducer(destinatiom);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // lấy product được truyền vào và chuyển thành json
            ConvertObject2Json cv = new ConvertObject2Json();
            String jsonProducOrder = cv.convertProductOrder2Json(productOrder);
            CodingService cd = new CodingService();
            String encodeProductOrder = cd.encode(jsonProducOrder);
            // tạo ra message
            TextMessage message = session.createTextMessage(encodeProductOrder);
            System.out.println("Sent message: " + encodeProductOrder);
            // gửi message
            producer.send(message);
            // đóng kết nối
            session.close();
            connection.close();
        }
        catch (JMSException e) {
            e.printStackTrace();
        }

    }


        @JmsListener(destination = "dat_hang")
    public void receiveMessage(final Message jsonMessage) throws  JMSException {

        System.out.println("Tin nhắn nhận được " + jsonMessage);
        if (jsonMessage instanceof TextMessage){
            TextMessage textMessage = (TextMessage) jsonMessage;
            String jsonlistOfProduct = textMessage.getText();

            // n đang bị mã hoá cần giải mã lại ra json
            CodingService cd = new CodingService();
            jsonlistOfProduct = cd.decode(jsonlistOfProduct);

            // giải mã ra decode
            ConvertObject2Json cv = new ConvertObject2Json();
            ProductOrder  decodeProductOrder = cv.convertJson2ProductOrder(jsonlistOfProduct);
            System.out.println("Received : " + decodeProductOrder);
            // thêm vào database productOrder
            // Kiểm tra và thực hiện thêm vào cơ sở dữ liệu
            if (decodeProductOrder != null) {
                Product product = productService.getProductById(decodeProductOrder.getProduct().getId());
                if (product != null && product.getQuantity() > 0 && product.getQuantity() >= decodeProductOrder.getQuantity_order()) {
                    // Lưu đơn hàng
                    productOrderService.saveProductOrder(decodeProductOrder);
                    // Trừ đi số lượng sản phẩm
                    product.setQuantity(product.getQuantity() - decodeProductOrder.getQuantity_order());
                    productService.saveProduct(product);
                    System.out.println("Đã thêm vào cơ sở dữ liệu và trừ đi 1 đơn vị số lượng sản phẩm");
                    // Truy xuất thông tin của khách hàng từ đối tượng decodeProductOrder
                    String customerName = decodeProductOrder.getCustomerName();
                    String customerEmail = decodeProductOrder.getCustomerEmail();

                    // Tạo nội dung email với thông tin của khách hàng và sản phẩm
                    String subject = "Cảm ơn bạn đã mua hàng của chúng tôi!";
                    String body = "<html><body>"
                            + "<h2>Cảm ơn bạn đã mua hàng!</h2>"
                            + "<p>Xin chào " + customerName + ",</p>"
                            + "<p>Chúng tôi xin gửi lời cảm ơn sâu sắc đến bạn đã mua hàng tại cửa hàng của chúng tôi.</p>"
                            + "<p>Mong rằng bạn sẽ hài lòng với sản phẩm mà bạn đã mua. Nếu bạn có bất kỳ câu hỏi hoặc yêu cầu nào, đừng ngần ngại liên hệ với chúng tôi.</p>"
                            + "<p>Dưới đây là thông tin về đơn hàng của bạn:</p>"
                            + "<p><strong>Tên Khách Hàng:</strong> " + customerName + "</p>"
                            + "<p><strong>Email:</strong> " + customerEmail + "</p>"
                            + "<p><strong>Tên Sản Phẩm:</strong> " + product.getName() + "</p>"
                            + "<p><strong>Số Lượng Đặt:</strong> " + decodeProductOrder.getQuantity_order() + "</p>"
                            + "<p>Xin cảm ơn một lần nữa!</p>"
                            + "<p>Trân trọng,</p>"
                            + "<p>Đội ngũ cửa hàng của chúng tôi</p>"
                            + "</body></html>";
                    String emailKh = decodeProductOrder.getCustomerEmail();
                    emailService.sendEmail(emailKh,body,subject);
                } else {
                    System.out.println("Sản phẩm không có sẵn hoặc không đủ số lượng để đặt hàng.");

                }
            } else {
                System.out.println("Không thể giải mã tin nhắn.");
            }


        }
    }

}
