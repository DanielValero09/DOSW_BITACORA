package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio9 {

    public static void main(String[] args) {
        List<Pokemon> pokemones = crearPokemones();
        List<String> equipoElite = obtenerEquipoElite(pokemones);

        System.out.println(equipoElite);
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Charizard", "Fuego", 76, 610, "Kanto", false),
                new Pokemon(4L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(5L, "Bulbasaur", "Planta", 35, 210, "Kanto", false)
        );
    }

    public static List<String> obtenerEquipoElite(List<Pokemon> pokemones) {
        return pokemones.stream()
                .filter(pokemon -> pokemon.getPoderCombate() > 500)
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .map(Ejercicio9::formatearPokemon)
                .collect(Collectors.toList());
    }

    private static String formatearPokemon(Pokemon pokemon) {
        return pokemon.getNombre() + "(" + (int) pokemon.getPoderCombate() + ")";
    }
}
