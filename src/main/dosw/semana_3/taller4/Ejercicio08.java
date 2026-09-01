package src.main.dosw.semana_3.taller4;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio08 {

    public static void main(String[] args) {
        Order order = new OrderBuilder()
                .setSize("Grande")
                .setMeat("Doble carne")
                .addTopping("Queso")
                .addTopping("Lechuga")
                .addSide("Papas")
                .addSide("Gaseosa")
                .build();

        order.addObserver(new KitchenService());
        order.addObserver(new BillingService());
        order.addObserver(new DeliveryService());

        order.confirm();
    }

    interface OrderObserver {
        void onConfirmed(Order order);
    }

    static class KitchenService implements OrderObserver {
        public void onConfirmed(Order order) {
            System.out.println("Cocina prepara: " + order.description());
        }
    }

    static class BillingService implements OrderObserver {
        public void onConfirmed(Order order) {
            System.out.println("Facturacion genera cuenta del pedido");
        }
    }

    static class DeliveryService implements OrderObserver {
        public void onConfirmed(Order order) {
            System.out.println("Domicilio prepara ruta de entrega");
        }
    }

    static class Order {
        private final String size;
        private final String meat;
        private final List<String> toppings;
        private final List<String> sides;
        private final List<OrderObserver> observers = new ArrayList<>();

        Order(String size, String meat, List<String> toppings, List<String> sides) {
            this.size = size;
            this.meat = meat;
            this.toppings = List.copyOf(toppings);
            this.sides = List.copyOf(sides);
        }

        void addObserver(OrderObserver observer) {
            observers.add(observer);
        }

        void confirm() {
            System.out.println("Pedido confirmado");
            observers.forEach(observer -> observer.onConfirmed(this));
        }

        String description() {
            return "Hamburguesa " + size + " con " + meat + ", toppings " + toppings + " y acompanamientos " + sides;
        }
    }

    static class OrderBuilder {
        private String size;
        private String meat;
        private final List<String> toppings = new ArrayList<>();
        private final List<String> sides = new ArrayList<>();

        OrderBuilder setSize(String size) {
            this.size = size;
            return this;
        }

        OrderBuilder setMeat(String meat) {
            this.meat = meat;
            return this;
        }

        OrderBuilder addTopping(String topping) {
            toppings.add(topping);
            return this;
        }

        OrderBuilder addSide(String side) {
            sides.add(side);
            return this;
        }

        Order build() {
            if (size == null || meat == null) {
                throw new IllegalStateException("El pedido requiere tamano y carne");
            }
            return new Order(size, meat, toppings, sides);
        }
    }
}
