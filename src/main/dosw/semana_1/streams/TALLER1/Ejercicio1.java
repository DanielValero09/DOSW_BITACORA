package src.main.semana_1.streams.TALLER1;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio1 {
    public static void main(String[] args) {
        List<String> estudiantes = List.of(
                "Ana",
                "Carlos",
                "Andres",
                "Pedro",
                "Alejandra",
                "Juan",
                "Amanda");

        List<String> resultado = estudiantes.stream()
                .filter(nombre -> nombre.startsWith("A"))
                .collect(Collectors.toList());

        System.out.println(resultado);
    }
}
