package src.main.semana_1.streams.TALLER1;

import java.util.Comparator;
import java.util.List;

public class Ejercicio11 {
    public static void main(String[] args) {
        List<Integer> precios = List.of(
                12000, 5000, 18000, 7500, 3000);

        Integer precioMinimo = precios.stream()
                .min(Comparator.naturalOrder())
                .orElse(null);

        System.out.println("Precio mínimo: " + precioMinimo);
    }
}
