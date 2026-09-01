package src.main.semana_1.streams.TALLER1;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio3 {
    public static void main(String[] args) {
        List<String> ciudades = List.of(
                "Bogotá",
                "Medellín",
                "Cali",
                "Barranquilla"
        );

        List<String> ciudadesMayusculas = ciudades.stream().map(
                ciudad -> ciudad.toUpperCase())
                .collect(Collectors.toList());

        System.out.println(ciudadesMayusculas);
    }
}
