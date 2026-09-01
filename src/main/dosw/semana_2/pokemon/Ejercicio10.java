package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio10 {

    public static void main(String[] args) {
        List<Pokemon> pokemones = crearPokemones();
        List<String> nombres = obtenerNombres(pokemones);

        System.out.println(nombres);
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(4L, "Bulbasaur", "Planta", 35, 210, "Kanto", false)
        );
    }

    public static List<String> obtenerNombres(List<Pokemon> pokemones) {
        return pokemones.stream()
                .map(Pokemon::getNombre)
                .collect(Collectors.toList());
    }
}
