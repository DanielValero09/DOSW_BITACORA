package src.main.semana_1.streams.TALLER1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ejercicio12 {
    public static void main(String[] args) {
        List<Integer> salarios = List.of(
                1800000, 2500000, 3200000,
                2100000, 4000000);

        Integer salarioMaximo = salarios.stream()
                .max(Comparator.naturalOrder())
                .orElse(null);

        System.out.println("Salario maximo: " + salarioMaximo);
    }
}
