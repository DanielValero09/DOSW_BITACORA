package src.main.semana_1.streams.TALLER1;

import java.util.ArrayList;
import java.util.List;

public class Ejercicio4 {
    public static void main(String[] args) {
        List<Integer> elementosSuma = List.of(12, 8, 5, 10, 15);

        int suma = elementosSuma.stream()
                .reduce(0, (acumulador, numero) -> acumulador + numero);

        System.out.println("Suma = " + suma);
    }
}
