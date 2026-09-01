package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio8 {

    public static void main(String[] args) {
        List<PokemonEvolucion> pokemones = crearPokemones();
        List<String> nombresConEvolucion = obtenerPokemonesQueEvolucionan(pokemones);

        System.out.println(nombresConEvolucion);
    }

    private static List<PokemonEvolucion> crearPokemones() {
        return List.of(
                new PokemonEvolucion("Pikachu", true),
                new PokemonEvolucion("Charmander", true),
                new PokemonEvolucion("Squirtle", true),
                new PokemonEvolucion("Mewtwo", false),
                new PokemonEvolucion("Lapras", false)
        );
    }

    public static List<String> obtenerPokemonesQueEvolucionan(List<PokemonEvolucion> pokemones) {
        return pokemones.stream()
                .filter(PokemonEvolucion::puedeEvolucionar)
                .map(PokemonEvolucion::nombre)
                .collect(Collectors.toList());
    }

    private record PokemonEvolucion(String nombre, boolean puedeEvolucionar) {
    }
}
