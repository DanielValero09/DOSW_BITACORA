package src.main.dosw.semana_3.taller4;

public class Ejercicio07 {

    public static void main(String[] args) {
        DocumentHandler approvalChain = new AutorHandler();
        approvalChain
                .setNext(new LiderHandler())
                .setNext(new JuridicoHandler())
                .setNext(new FinancieroHandler());

        Document approved = new Document("Contrato proveedor", true, true, true, 15000);
        approved.startReview();
        approvalChain.handle(approved);
        System.out.println(approved.getTitle() + " queda en estado " + approved.getStateName());

        Document rejected = new Document("Compra sin presupuesto", true, true, true, 90000);
        rejected.startReview();
        approvalChain.handle(rejected);
        System.out.println(rejected.getTitle() + " queda en estado " + rejected.getStateName());
    }

    interface DocumentState {
        void startReview(Document document);

        void approve(Document document);

        void reject(Document document);

        String name();
    }

    static class DraftState implements DocumentState {
        public void startReview(Document document) {
            document.setState(new InReviewState());
        }

        public void approve(Document document) {
            System.out.println("Un borrador no puede aprobarse directamente");
        }

        public void reject(Document document) {
            document.setState(new RejectedState());
        }

        public String name() {
            return "BORRADOR";
        }
    }

    static class InReviewState implements DocumentState {
        public void startReview(Document document) {
            System.out.println("El documento ya esta en revision");
        }

        public void approve(Document document) {
            document.setState(new ApprovedState());
        }

        public void reject(Document document) {
            document.setState(new RejectedState());
        }

        public String name() {
            return "EN_REVISION";
        }
    }

    static class ApprovedState implements DocumentState {
        public void startReview(Document document) {
            System.out.println("El documento ya fue aprobado");
        }

        public void approve(Document document) {
            System.out.println("El documento permanece aprobado");
        }

        public void reject(Document document) {
            System.out.println("No se rechaza un documento aprobado");
        }

        public String name() {
            return "APROBADO";
        }
    }

    static class RejectedState implements DocumentState {
        public void startReview(Document document) {
            System.out.println("El documento rechazado debe corregirse antes de nueva revision");
        }

        public void approve(Document document) {
            System.out.println("No se aprueba un documento rechazado");
        }

        public void reject(Document document) {
            System.out.println("El documento permanece rechazado");
        }

        public String name() {
            return "RECHAZADO";
        }
    }

    static class Document {
        private final String title;
        private final boolean authorReady;
        private final boolean leaderApproved;
        private final boolean legalApproved;
        private final double amount;
        private DocumentState state = new DraftState();

        Document(String title, boolean authorReady, boolean leaderApproved, boolean legalApproved, double amount) {
            this.title = title;
            this.authorReady = authorReady;
            this.leaderApproved = leaderApproved;
            this.legalApproved = legalApproved;
            this.amount = amount;
        }

        String getTitle() {
            return title;
        }

        boolean isAuthorReady() {
            return authorReady;
        }

        boolean isLeaderApproved() {
            return leaderApproved;
        }

        boolean isLegalApproved() {
            return legalApproved;
        }

        double getAmount() {
            return amount;
        }

        void startReview() {
            state.startReview(this);
        }

        void approve() {
            state.approve(this);
        }

        void reject() {
            state.reject(this);
        }

        void setState(DocumentState state) {
            this.state = state;
        }

        String getStateName() {
            return state.name();
        }
    }

    abstract static class DocumentHandler {
        private DocumentHandler next;

        DocumentHandler setNext(DocumentHandler next) {
            this.next = next;
            return next;
        }

        void handle(Document document) {
            if (!canApprove(document)) {
                System.out.println(getClass().getSimpleName() + " rechaza " + document.getTitle());
                document.reject();
                return;
            }

            System.out.println(getClass().getSimpleName() + " aprueba " + document.getTitle());
            if (next == null) {
                document.approve();
            } else {
                next.handle(document);
            }
        }

        protected abstract boolean canApprove(Document document);
    }

    static class AutorHandler extends DocumentHandler {
        protected boolean canApprove(Document document) {
            return document.isAuthorReady();
        }
    }

    static class LiderHandler extends DocumentHandler {
        protected boolean canApprove(Document document) {
            return document.isLeaderApproved();
        }
    }

    static class JuridicoHandler extends DocumentHandler {
        protected boolean canApprove(Document document) {
            return document.isLegalApproved();
        }
    }

    static class FinancieroHandler extends DocumentHandler {
        protected boolean canApprove(Document document) {
            return document.getAmount() <= 50000;
        }
    }
}
