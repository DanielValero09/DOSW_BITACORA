package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio5 {

    public static void main(String[] args) {
        List<PokemonNivel> pokemones = crearPokemones();
        long cantidadLegendarios = contarPokemonesConNivelMayorA80(pokemones);
        List<String> nombresLegendarios = obtenerNombresConNivelMayorA80(pokemones);

        System.out.println("Pokémon con nivel > 80: " + cantidadLegendarios);
        System.out.println(nombresLegendarios);
    }

    private static List<PokemonNivel> crearPokemones() {
        return List.of(
                new PokemonNivel("Pikachu", 45),
                new PokemonNivel("Mewtwo", 95),
                new PokemonNivel("Charizard", 76),
                new PokemonNivel("Dragonite", 88),
                new PokemonNivel("Mew", 92),
                new PokemonNivel("Bulbasaur", 35)
        );
    }

    public static long contarPokemonesConNivelMayorA80(List<PokemonNivel> pokemones) {
        return pokemones.stream()
                .filter(pokemon -> pokemon.nivel() > 80)
                .count();
    }

    public static List<String> obtenerNombresConNivelMayorA80(List<PokemonNivel> pokemones) {
        return pokemones.stream()
                .filter(pokemon -> pokemon.nivel() > 80)
                .map(PokemonNivel::nombre)
                .collect(Collectors.toList());
    }

    private record PokemonNivel(String nombre, int nivel) {
    }
}
