package src.main.semana_1.streams.TALLER1;

import java.util.List;

public class Ejercicio13 {
    public static void main(String[] args) {
        List<Integer> lista = List.of(
                7, 11, 13, 20, 25);

        boolean par = lista.stream()
                .anyMatch(numero -> numero % 2 == 0);

        System.out.println("¿Hay algún par?" + par);
    }
}
