package org.example.baitaplab05.TestQueue.PublicsherConSumer;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ReceiveQueue {
    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // create destination
            Destination destination = session.createQueue("ductai");
            // taoj ra consumer tren queue do
            MessageConsumer consumer = session.createConsumer(destination);


            // cách 1 : cách này là cách có hàng đợi là 1S , nó sẽ lấy ra hết tất cả các message trong hàng đợi cho tới khi null
            // mỗi lâ ợi l 1 s
//            do {
//                Message message = consumer.receive(1000);
//                if (message instanceof TextMessage) {
//                    TextMessage textMessage = (TextMessage) message;
//                    String text = textMessage.getText();
//                    System.out.println("Received: " + text);
//                } else {
//                    System.out.println("Received: " + message);
//                }
//                // Kiểm tra nếu không còn tin nhắn nào
//                if (message == null) {
//                    break;
//                }
//                Thread.sleep(50);
//            } while (true);
//
//            consumer.close();
//            session.close();
//            connection.close();

            // cách 2 : cách này là cách không có hàng đợi , nó sẽ lấy ra hết tất cả các message trong hàng đợi cho tới khi null
            // này chương trình không dừng lại khi có message được gửi tới thì nó sẽ tự đn gọi hàm onMessage

            // khi chạy 3 receiverque ở mô hình publicsherConSumer thì 3 receiverque sẽ nhận được message thay phiên nhau ví dụ như
            // là khi gửi  1 2 3 4 5 6 7 8 9 10 thì 3 receiverque sẽ nhận được message như sau
            // receiverque1 : 1 4 7 10
            // receiverque2 : 2 5 8
            // receiverque3 : 3 6 9
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        if (message instanceof TextMessage) {
                            TextMessage textMessage = (TextMessage) message;
                            String text = textMessage.getText();
                            System.out.println("Received: " + text);
                        } else {
                            System.out.println("Received: " + message);
                        }
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
