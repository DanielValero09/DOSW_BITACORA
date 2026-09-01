package src.main.dosw.semana_3.taller4;

public class Ejercicio03 {

    public static void main(String[] args) {
        ReportFactory factory = new ReportFactory();

        ReportGenerator pdfReport = factory.create("pdf");
        pdfReport.generate();

        ReportGenerator csvReport = factory.create("csv");
        csvReport.generate();
    }

    abstract static class ReportGenerator {
        public final void generate() {
            fetchData();
            processData();
            applyFormat();
            exportFile();
        }

        protected void fetchData() {
            System.out.println("1. Obtener datos comunes");
        }

        protected void processData() {
            System.out.println("2. Procesar informacion comun");
        }

        protected abstract void applyFormat();

        protected abstract void exportFile();
    }

    static class PdfReport extends ReportGenerator {
        protected void applyFormat() {
            System.out.println("3. Aplicar formato PDF");
        }

        protected void exportFile() {
            System.out.println("4. Exportar archivo PDF");
        }
    }

    static class ExcelReport extends ReportGenerator {
        protected void applyFormat() {
            System.out.println("3. Aplicar formato Excel");
        }

        protected void exportFile() {
            System.out.println("4. Exportar archivo Excel");
        }
    }

    static class CsvReport extends ReportGenerator {
        protected void applyFormat() {
            System.out.println("3. Aplicar formato CSV");
        }

        protected void exportFile() {
            System.out.println("4. Exportar archivo CSV");
        }
    }

    static class ReportFactory {
        ReportGenerator create(String type) {
            return switch (type.toLowerCase()) {
                case "pdf" -> new PdfReport();
                case "excel" -> new ExcelReport();
                case "csv" -> new CsvReport();
                default -> throw new IllegalArgumentException("Tipo de reporte no soportado: " + type);
            };
        }
    }
}
