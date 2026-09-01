package src.main.semana_1.streams.TALLER1;

import java.util.List;

public class Ejercicio14 {
    public static void main(String[] args) {
        List<Double> notas = List.of(
                4.0, 3.5, 4.2, 5.0, 3.8);

        boolean todasAprueban = notas.stream()
                .allMatch(nota -> nota >= 3.0);

        System.out.println("¿Todas >= 3.0? " + todasAprueban);

    }
}
