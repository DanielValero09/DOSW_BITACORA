package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio2 {

    public static void main(String[] args) {
        List<String> nombres = crearNombresPokemon();
        List<String> nombresEnMayuscula = convertirNombresAMayuscula(nombres);

        System.out.println(String.join(", ", nombresEnMayuscula));
    }

    private static List<String> crearNombresPokemon() {
        return List.of("Pikachu", "Charmander", "Squirtle", "Bulbasaur");
    }

    public static List<String> convertirNombresAMayuscula(List<String> nombres) {
        return nombres.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
