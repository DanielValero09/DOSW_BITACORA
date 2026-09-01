package src.main.dosw.semana_3.taller4;

public class Ejercicio09 {

    public static void main(String[] args) {
        Validator chain = new CredentialValidator();
        chain
                .setNext(new PermissionValidator())
                .setNext(new LocationValidator())
                .setNext(new TimeValidator());

        AuthService passwordAuth = new AuthService(new PasswordStrategy(), chain);
        AccessRequest successful = new AccessRequest("ana", "password123", "ADMIN", "OFICINA", 10);
        passwordAuth.login(successful);

        AuthService tokenAuth = new AuthService(new TokenStrategy(), chain);
        AccessRequest blocked = new AccessRequest("luis", "TOKEN-OK", "ADMIN", "EXTERNA", 11);
        tokenAuth.login(blocked);
    }

    record AccessRequest(String user, String credential, String role, String location, int hour) {
    }

    record AuthResult(boolean success, AccessRequest request) {
    }

    interface AuthStrategy {
        AuthResult authenticate(AccessRequest request);
    }

    static class PasswordStrategy implements AuthStrategy {
        public AuthResult authenticate(AccessRequest request) {
            boolean success = "password123".equals(request.credential());
            System.out.println("Autenticacion por password: " + success);
            return new AuthResult(success, request);
        }
    }

    static class GoogleStrategy implements AuthStrategy {
        public AuthResult authenticate(AccessRequest request) {
            boolean success = request.credential().startsWith("GOOGLE-");
            System.out.println("Autenticacion por Google: " + success);
            return new AuthResult(success, request);
        }
    }

    static class MicrosoftStrategy implements AuthStrategy {
        public AuthResult authenticate(AccessRequest request) {
            boolean success = request.credential().startsWith("MS-");
            System.out.println("Autenticacion por Microsoft: " + success);
            return new AuthResult(success, request);
        }
    }

    static class TokenStrategy implements AuthStrategy {
        public AuthResult authenticate(AccessRequest request) {
            boolean success = "TOKEN-OK".equals(request.credential());
            System.out.println("Autenticacion por token: " + success);
            return new AuthResult(success, request);
        }
    }

    static class BiometricStrategy implements AuthStrategy {
        public AuthResult authenticate(AccessRequest request) {
            boolean success = "HUELLA-OK".equals(request.credential());
            System.out.println("Autenticacion biometrica: " + success);
            return new AuthResult(success, request);
        }
    }

    static class AuthService {
        private final AuthStrategy strategy;
        private final Validator validator;

        AuthService(AuthStrategy strategy, Validator validator) {
            this.strategy = strategy;
            this.validator = validator;
        }

        void login(AccessRequest request) {
            AuthResult result = strategy.authenticate(request);
            if (!result.success()) {
                System.out.println("Acceso denegado por autenticacion");
                return;
            }
            if (validator.validate(result.request())) {
                System.out.println("Acceso concedido a " + request.user());
            }
        }
    }

    abstract static class Validator {
        private Validator next;

        Validator setNext(Validator next) {
            this.next = next;
            return next;
        }

        boolean validate(AccessRequest request) {
            if (!check(request)) {
                System.out.println(getClass().getSimpleName() + " detuvo el acceso");
                return false;
            }
            System.out.println(getClass().getSimpleName() + " aprobado");
            return next == null || next.validate(request);
        }

        protected abstract boolean check(AccessRequest request);
    }

    static class CredentialValidator extends Validator {
        protected boolean check(AccessRequest request) {
            return request.credential() != null && !request.credential().isBlank();
        }
    }

    static class PermissionValidator extends Validator {
        protected boolean check(AccessRequest request) {
            return "ADMIN".equals(request.role()) || "USER".equals(request.role());
        }
    }

    static class LocationValidator extends Validator {
        protected boolean check(AccessRequest request) {
            return "OFICINA".equals(request.location());
        }
    }

    static class TimeValidator extends Validator {
        protected boolean check(AccessRequest request) {
            return request.hour() >= 8 && request.hour() <= 18;
        }
    }
}
