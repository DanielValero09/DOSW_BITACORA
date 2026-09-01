package src.main.semana_1.streams.TALLER1;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio6 {
    public static void main(String[] args) {
        List<String> empleados = List.of(
                "Laura",
                "Pedro",
                "Carlos",
                "Ana"
        );

        List<String> empleadosMayusculas = empleados.stream()
                .map(nombre -> nombre.toUpperCase())
                .peek(nombre -> System.out.println("Transformado: " + nombre))
                .collect(Collectors.toList());

        System.out.println("Lista: " + empleadosMayusculas);
    }
}
