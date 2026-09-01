package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ejercicio20 {

    public static void main(String[] args) {
        List<Pokemon> pokedex = crearPokedex();

        System.out.println("Cantidad por tipo: " + contarPorTipo(pokedex));
        System.out.println("Cantidad por region: " + contarPorRegion(pokedex));
        System.out.println("Legendarios: " + contarLegendarios(pokedex));
        System.out.println("Promedio de nivel: " + calcularPromedioNivel(pokedex));
        obtenerPokemonMasFuerte(pokedex).ifPresent(pokemon -> System.out.println(
                "Pokemon mas fuerte: " + pokemon.getNombre() + " con PC: " + (int) pokemon.getPoderCombate()
        ));
    }

    private static List<Pokemon> crearPokedex() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Charizard", "Fuego", 76, 610, "Kanto", false),
                new Pokemon(4L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(5L, "Chikorita", "Planta", 30, 280, "Johto", false),
                new Pokemon(6L, "Lugia", "Psiquico", 92, 670, "Johto", true),
                new Pokemon(7L, "Torchic", "Fuego", 29, 275, "Hoenn", false),
                new Pokemon(8L, "Kyogre", "Agua", 91, 660, "Hoenn", true)
        );
    }

    public static Map<String, Long> contarPorTipo(List<Pokemon> pokedex) {
        return pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getTipo, LinkedHashMap::new, Collectors.counting()));
    }

    public static Map<String, Long> contarPorRegion(List<Pokemon> pokedex) {
        return pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getRegion, LinkedHashMap::new, Collectors.counting()));
    }

    public static long contarLegendarios(List<Pokemon> pokedex) {
        return pokedex.stream()
                .filter(Pokemon::isLegendario)
                .count();
    }

    public static double calcularPromedioNivel(List<Pokemon> pokedex) {
        return pokedex.stream()
                .mapToInt(Pokemon::getNivel)
                .average()
                .orElse(0);
    }

    public static Optional<Pokemon> obtenerPokemonMasFuerte(List<Pokemon> pokedex) {
        return pokedex.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate));
    }
}
