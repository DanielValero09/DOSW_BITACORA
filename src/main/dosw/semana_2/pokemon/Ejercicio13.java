package src.main.dosw.semana_2.pokemon;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio13 {

    public static void main(String[] args) {
        Map<String, List<String>> pokemonesPorTipo = agruparPorTipo(crearPokemones());

        pokemonesPorTipo.forEach((tipo, nombres) -> System.out.println(tipo + ": " + nombres));
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Squirtle", "Agua", 30, 290, "Kanto", false),
                new Pokemon(2L, "Psyduck", "Agua", 28, 260, "Kanto", false),
                new Pokemon(3L, "Charmander", "Fuego", 32, 310, "Kanto", false),
                new Pokemon(4L, "Vulpix", "Fuego", 34, 330, "Kanto", false),
                new Pokemon(5L, "Bulbasaur", "Planta", 31, 300, "Kanto", false)
        );
    }

    public static Map<String, List<String>> agruparPorTipo(List<Pokemon> pokemones) {
        return pokemones.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getTipo,
                        LinkedHashMap::new,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));
    }
}
