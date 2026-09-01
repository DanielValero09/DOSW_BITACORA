package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio18 {

    public static void main(String[] args) {
        List<Pokemon> top5 = obtenerTop5MasFuertes(crearPokemones());

        IntStream.range(0, top5.size())
                .mapToObj(indice -> "#" + (indice + 1) + " " + formatearPokemon(top5.get(indice)))
                .forEach(System.out::println);
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(2L, "Charizard", "Fuego", 76, 610, "Kanto", false),
                new Pokemon(3L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(4L, "Lapras", "Agua", 60, 495, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 74, 520, "Kanto", false),
                new Pokemon(6L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(7L, "Mew", "Psiquico", 92, 650, "Kanto", true)
        );
    }

    public static List<Pokemon> obtenerTop5MasFuertes(List<Pokemon> pokemones) {
        return pokemones.stream()
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    private static String formatearPokemon(Pokemon pokemon) {
        return pokemon.getNombre() + " - PC: " + (int) pokemon.getPoderCombate();
    }
}
