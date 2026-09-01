package src.main.dosw.semana_3.taller4;

public class Ejercicio05 {

    public static void main(String[] args) {
        BankFacade facade = new BankFacade();
        facade.procesarPago(249.99);
    }

    interface PaymentProcessor {
        void pay(double amount);
    }

    static class LegacyBankService {
        boolean verifyBalance(String account, int cents) {
            System.out.println("Legacy verifica saldo de " + account + " por " + cents + " centavos");
            return cents <= 50000;
        }

        void executeTransaction(String account, int cents) {
            System.out.println("Legacy ejecuta transaccion de " + cents + " centavos en " + account);
        }
    }

    static class LegacyBankAdapter implements PaymentProcessor {
        private final LegacyBankService legacyBankService;
        private final String account;

        LegacyBankAdapter(LegacyBankService legacyBankService, String account) {
            this.legacyBankService = legacyBankService;
            this.account = account;
        }

        public void pay(double amount) {
            int cents = (int) Math.round(amount * 100);
            System.out.println("Adapter convierte $" + amount + " a " + cents + " centavos");
            if (legacyBankService.verifyBalance(account, cents)) {
                legacyBankService.executeTransaction(account, cents);
                System.out.println("Pago moderno confirmado");
            } else {
                System.out.println("Pago rechazado por saldo insuficiente");
            }
        }
    }

    static class BankFacade {
        void procesarPago(double monto) {
            System.out.println("Facade inicia configuracion bancaria");
            LegacyBankService legacyBankService = inicializarServicioLegacy();
            String account = abrirSesionBancaria();
            PaymentProcessor processor = new LegacyBankAdapter(legacyBankService, account);
            processor.pay(monto);
        }

        private LegacyBankService inicializarServicioLegacy() {
            System.out.println("Facade prepara conexion, credenciales y contexto legacy");
            return new LegacyBankService();
        }

        private String abrirSesionBancaria() {
            System.out.println("Facade abre sesion para cuenta ACC-001");
            return "ACC-001";
        }
    }
}
