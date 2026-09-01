package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EjercicioAdicionalMewtwo {

    public static void main(String[] args) {
        List<PokemonCompetitivo> competitivos = obtenerPokemonCompetitivos(crearPokedex(), 70);
        Map<String, List<String>> competitivosPorTipo = agruparPorTipo(competitivos);
        double poderTotal = calcularPoderTotal(competitivos);

        System.out.println("Pokemon competitivos por tipo: " + competitivosPorTipo);
        System.out.println("Poder total competitivo: " + (int) poderTotal);
    }

    private static List<Pokemon> crearPokedex() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Charizard", "Fuego", 76, 610, "Kanto", false),
                new Pokemon(4L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(5L, "Mew", "Psiquico", 92, 650, "Kanto", true),
                new Pokemon(6L, "Gengar", "Fantasma", 74, 520, "Kanto", false),
                new Pokemon(7L, "Bulbasaur", "Planta", 35, 210, "Kanto", false)
        );
    }

    public static List<PokemonCompetitivo> obtenerPokemonCompetitivos(List<Pokemon> pokedex, int nivelMinimo) {
        return pokedex.stream()
                .filter(pokemon -> pokemon.getNivel() >= nivelMinimo)
                .map(pokemon -> new PokemonCompetitivo(
                        pokemon.getNombre(),
                        pokemon.getTipo(),
                        pokemon.getPoderCombate()
                ))
                .sorted(Comparator.comparingDouble(PokemonCompetitivo::poderCombate).reversed())
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> agruparPorTipo(List<PokemonCompetitivo> competitivos) {
        return competitivos.stream()
                .collect(Collectors.groupingBy(
                        PokemonCompetitivo::tipo,
                        LinkedHashMap::new,
                        Collectors.mapping(PokemonCompetitivo::nombre, Collectors.toList())
                ));
    }

    public static double calcularPoderTotal(List<PokemonCompetitivo> competitivos) {
        return competitivos.stream()
                .map(PokemonCompetitivo::poderCombate)
                .reduce(0.0, Double::sum);
    }

    public record PokemonCompetitivo(String nombre, String tipo, double poderCombate) {
    }
}
