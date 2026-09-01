package src.main.dosw.semana_3.taller4;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio02 {

    public static void main(String[] args) {
        Order order = new Order("ORD-1001");
        order.addObserver(new EmailNotifier(new EmailMessageFactory()));
        order.addObserver(new SmsNotifier(new SmsMessageFactory()));
        order.addObserver(new PushNotifier(new PushMessageFactory()));

        order.changeStatus("ENVIADO");
        order.changeStatus("ENTREGADO");
    }

    record OrderEvent(String orderId, String status) {
    }

    record Message(String channel, String content) {
    }

    interface NotificationObserver {
        void notify(OrderEvent event);
    }

    interface MessageFactory {
        Message build(OrderEvent event);
    }

    static class EmailMessageFactory implements MessageFactory {
        public Message build(OrderEvent event) {
            return new Message("Email", "<h1>Pedido " + event.orderId() + ": " + event.status() + "</h1>");
        }
    }

    static class SmsMessageFactory implements MessageFactory {
        public Message build(OrderEvent event) {
            return new Message("SMS", "Pedido " + event.orderId() + " ahora esta " + event.status());
        }
    }

    static class PushMessageFactory implements MessageFactory {
        public Message build(OrderEvent event) {
            return new Message("Push", "{order:'" + event.orderId() + "', status:'" + event.status() + "'}");
        }
    }

    static class EmailNotifier implements NotificationObserver {
        private final MessageFactory factory;

        EmailNotifier(MessageFactory factory) {
            this.factory = factory;
        }

        public void notify(OrderEvent event) {
            Message message = factory.build(event);
            System.out.println(message.channel() + " enviado: " + message.content());
        }
    }

    static class SmsNotifier implements NotificationObserver {
        private final MessageFactory factory;

        SmsNotifier(MessageFactory factory) {
            this.factory = factory;
        }

        public void notify(OrderEvent event) {
            Message message = factory.build(event);
            System.out.println(message.channel() + " enviado: " + message.content());
        }
    }

    static class PushNotifier implements NotificationObserver {
        private final MessageFactory factory;

        PushNotifier(MessageFactory factory) {
            this.factory = factory;
        }

        public void notify(OrderEvent event) {
            Message message = factory.build(event);
            System.out.println(message.channel() + " enviado: " + message.content());
        }
    }

    static class Order {
        private final String id;
        private final List<NotificationObserver> observers = new ArrayList<>();

        Order(String id) {
            this.id = id;
        }

        void addObserver(NotificationObserver observer) {
            observers.add(observer);
        }

        void removeObserver(NotificationObserver observer) {
            observers.remove(observer);
        }

        void changeStatus(String status) {
            System.out.println("Pedido " + id + " cambia a " + status);
            OrderEvent event = new OrderEvent(id, status);
            observers.forEach(observer -> observer.notify(event));
        }
    }
}
