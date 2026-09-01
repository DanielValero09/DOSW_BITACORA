package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ejercicio12 {

    public static void main(String[] args) {
        List<Pokemon> pokemones = crearPokemones();
        Optional<Pokemon> campeon = obtenerCampeonRegional(pokemones);

        campeon.ifPresent(pokemon -> System.out.println(
                "Campeón: " + pokemon.getNombre() + " con PC: " + (int) pokemon.getPoderCombate()
        ));
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(4L, "Charizard", "Fuego", 76, 610, "Kanto", false)
        );
    }

    public static Optional<Pokemon> obtenerCampeonRegional(List<Pokemon> pokemones) {
        return pokemones.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate));
    }
}
