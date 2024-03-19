package org.example.baitaplab05.TestQueue.PublicSherSubcribe;

import jakarta.jms.*;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SendQueuePS {
    public static void main(String[] args) {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Create a Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

//            Queue destinatiom = session.createQueue("ductai");
            Topic destinatiom = session.createTopic("ductai");

            // tu session tao ra producer
            MessageProducer producer = session.createProducer(destinatiom);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // tao ra message
            for (int i = 1; i <= 1000; i++) {
                String text = "Hello world! From: " + Thread.currentThread().getName() + " : " + i;
                TextMessage message = session.createTextMessage(text);
                System.out.println("Sent message: " + text);
                // gui message
                producer.send(message);
                Thread.sleep(10);
            }
            // dong ket noi
            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
