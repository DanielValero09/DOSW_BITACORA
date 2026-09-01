package src.main.semana_1.streams.TALLER1;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ejercicio5 {
    public static void main(String[] args) {
        List<String> correos = List.of(
                "a@correo.com",
                "b@correo.com",
                "a@correo.com",
                "c@correo.com",
                "b@correo.com"
        );

        Set<String> correosUnicos = correos.stream()
                .collect(Collectors.toSet());

        System.out.println("Set con 3 elementos unicos " + correosUnicos);
    }
}
