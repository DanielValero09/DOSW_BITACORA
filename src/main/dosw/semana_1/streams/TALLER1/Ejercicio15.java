package src.main.semana_1.streams.TALLER1;

import java.util.List;

public class Ejercicio15 {
    public static void main(String[] args) {
        List<String> usuarios = List.of(
                "juan",
                "maria",
                "admin",
                "pedro",
                "soporte"
        );

        boolean ningunoEsRoot = usuarios.stream()
                .noneMatch(usuario -> usuario.equals("root"));

        System.out.println("¿Ninguno es root?" + ningunoEsRoot);

    }


}
