package src.main.dosw.semana_3.taller4;

public class Ejercicio01 {

    public static void main(String[] args) {
        PaymentFactory colombiaFactory = new ColombiaPaymentFactory();
        PaymentStrategy pagoColombia = colombiaFactory.create("nequi");
        Checkout checkoutColombia = new Checkout(pagoColombia);
        checkoutColombia.pay(85000);

        PaymentFactory usaFactory = new UsaPaymentFactory();
        PaymentStrategy pagoUsa = usaFactory.create("paypal");
        Checkout checkoutUsa = new Checkout(pagoUsa);
        checkoutUsa.pay(120);
    }

    interface PaymentStrategy {
        void process(double amount);
    }

    static class TarjetaStrategy implements PaymentStrategy {
        public void process(double amount) {
            System.out.println("Pago con tarjeta procesado por $" + amount);
        }
    }

    static class PseStrategy implements PaymentStrategy {
        public void process(double amount) {
            System.out.println("Pago PSE colombiano procesado por $" + amount);
        }
    }

    static class NequiStrategy implements PaymentStrategy {
        public void process(double amount) {
            System.out.println("Pago Nequi colombiano procesado por $" + amount);
        }
    }

    static class PaypalStrategy implements PaymentStrategy {
        public void process(double amount) {
            System.out.println("Pago PayPal estadounidense procesado por $" + amount);
        }
    }

    static class Checkout {
        private final PaymentStrategy paymentStrategy;

        Checkout(PaymentStrategy paymentStrategy) {
            this.paymentStrategy = paymentStrategy;
        }

        void pay(double amount) {
            System.out.println("Checkout inicia el pago.");
            paymentStrategy.process(amount);
        }
    }

    interface PaymentFactory {
        PaymentStrategy create(String type);
    }

    static class ColombiaPaymentFactory implements PaymentFactory {
        public PaymentStrategy create(String type) {
            return switch (type.toLowerCase()) {
                case "pse" -> new PseStrategy();
                case "nequi" -> new NequiStrategy();
                case "tarjeta" -> new TarjetaStrategy();
                default -> throw new IllegalArgumentException("Medio de pago no soportado en Colombia: " + type);
            };
        }
    }

    static class UsaPaymentFactory implements PaymentFactory {
        public PaymentStrategy create(String type) {
            return switch (type.toLowerCase()) {
                case "paypal" -> new PaypalStrategy();
                case "card", "tarjeta" -> new TarjetaStrategy();
                default -> throw new IllegalArgumentException("Medio de pago no soportado en USA: " + type);
            };
        }
    }
}
