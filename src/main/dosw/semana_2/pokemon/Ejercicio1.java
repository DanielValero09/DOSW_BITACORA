package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio1 {

    public static void main(String[] args) {
        List<PokemonTipo> pokemones = crearPokemones();
        List<String> pokemonesFuego = obtenerPokemonesTipoFuego(pokemones);

        System.out.println(pokemonesFuego);
    }

    private static List<PokemonTipo> crearPokemones() {
        return List.of(
                new PokemonTipo("Pikachu", "Electrico"),
                new PokemonTipo("Charmander", "Fuego"),
                new PokemonTipo("Squirtle", "Agua"),
                new PokemonTipo("Vulpix", "Fuego"),
                new PokemonTipo("Bulbasaur", "Planta"),
                new PokemonTipo("Flareon", "Fuego")
        );
    }

    public static List<String> obtenerPokemonesTipoFuego(List<PokemonTipo> pokemones) {
        return pokemones.stream()
                .filter(pokemon -> "Fuego".equals(pokemon.tipo()))
                .map(PokemonTipo::nombre)
                .collect(Collectors.toList());
    }

    private record PokemonTipo(String nombre, String tipo) {
    }
}
