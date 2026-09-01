//public class PS02 {
    // ============================================================
// EJERCICIO 1
// PROPÓSITO:
// Obtener de una lista de números enteros únicamente aquellos
// que sean pares y además mayores a 10.
// ============================================================

    List<Integer> numbers = List.of(3, 8, 10, 12, 15, 18, 20);

    List<Integer> evenNumbersGreaterThanTen = numbers.stream()
            .filter(number -> number % 2 == 0)
            .filter(number -> number > 10)
            .toList();


// ============================================================
// EJERCICIO 2
// PROPÓSITO:
// Procesar una lista de palabras filtrando las que tengan más
// de 4 caracteres, convertirlas a mayúsculas, ordenarlas
// alfabéticamente y obtener la cantidad total resultante.
// ============================================================

    List<String> words = List.of(
            "java",
            "stream",
            "api",
            "functional",
            "code",
            "git"
    );

    List<String> processedWords = words.stream()
            .filter(word -> word.length() > 4)
            .map(String::toUpperCase)
            .sorted()
            .toList();

    long totalWords = processedWords.size();


// ============================================================
// EJERCICIO 3
// PROPÓSITO:
// Filtrar únicamente los usuarios activos, obtener sus nombres
// en mayúsculas y ordenar el resultado alfabéticamente.
// ============================================================

    List<String> activeUserNames = users.stream()
            .filter(User::isActive)
            .map(User::getName)
            .map(String::toUpperCase)
            .sorted()
            .toList();


// ============================================================
// EJERCICIO 4
// PROPÓSITO:
// Filtrar los usuarios mayores de edad y obtener únicamente
// sus nombres.
// ============================================================

    List<String> adultUserNames = users.stream()
            .filter(user -> user.getAge() >= 18)
            .map(User::getName)
            .toList();


// ============================================================
// EJERCICIO 5
// PROPÓSITO:
// Procesar un lote de transacciones bancarias mostrando cada
// transacción con peek y determinar si el lote es válido.
// El lote es válido únicamente cuando NO existe ninguna
// transacción que esté sin aprobar.
// ============================================================

    //class Transaction {
        //String id;
        //double amount;
        //boolean approved;//


    //boolean validBatch = transactions.stream()
            .peek(transaction -> System.out.println(transaction))
            .noneMatch(transaction -> !transaction.approved);

    void main() {
    }


//