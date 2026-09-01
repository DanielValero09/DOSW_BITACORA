package src.main.semana_1.streams.TALLER1;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio7 {
    public static void main(String[] args) {
        List<Integer> edades = List.of(25, 18, 32, 21, 19, 28);

        List<Integer> ordenAscendente = edades.stream()
                .sorted()
                .collect(Collectors.toList());

        List<Integer> ordenDescendente = edades.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Ascendente: " + ordenAscendente);

        System.out.println("Descendiente: " + ordenDescendente);

    }
}
