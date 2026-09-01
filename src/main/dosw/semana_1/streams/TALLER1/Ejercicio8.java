package src.main.semana_1.streams.TALLER1;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ejercicio8 {
    public static void main(String[] args) {
        List<String> codigos = List.of(
                "P01",
                "P02",
                "P01",
                "P03",
                "P02",
                "P04"
        );
        List<String> codigoSinRepetir = codigos.stream()
                .distinct()
                .collect(Collectors.toList());

        System.out.println(codigoSinRepetir);

    }
}
