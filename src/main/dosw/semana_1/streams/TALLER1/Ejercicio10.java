package src.main.semana_1.streams.TALLER1;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio10 {
    public static void main(String[] args) {
        List<String> peliculas = List.of(
                "Avatar",
                "Titanic",
                "Interestelar",
                "Matrix",
                "Gladiator"
        );

        List<String> sinDosPrimeras = peliculas.stream()
                .skip(2).collect(Collectors.toList());

        System.out.println(sinDosPrimeras);
    }
}
