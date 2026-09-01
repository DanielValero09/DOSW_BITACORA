package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ejercicio4 {

    public static void main(String[] args) {
        List<PokemonNivel> pokemones = crearPokemones();
        Optional<PokemonNivel> pokemonAlfa = encontrarPokemonAlfa(pokemones);

        pokemonAlfa.ifPresent(pokemon -> System.out.println(
                "Pokémon Alfa: " + pokemon.nombre() + " (nivel " + pokemon.nivel() + ")"
        ));
    }

    private static List<PokemonNivel> crearPokemones() {
        return List.of(
                new PokemonNivel("Pikachu", 45),
                new PokemonNivel("Charizard", 76),
                new PokemonNivel("Blastoise", 82),
                new PokemonNivel("Snorlax", 90),
                new PokemonNivel("Dragonite", 88)
        );
    }

    public static Optional<PokemonNivel> encontrarPokemonAlfa(List<PokemonNivel> pokemones) {
        return pokemones.stream()
                .max(Comparator.comparingInt(PokemonNivel::nivel));
    }

    private record PokemonNivel(String nombre, int nivel) {
    }
}
