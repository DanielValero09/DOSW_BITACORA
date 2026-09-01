package src.main.dosw.semana_2.pokemon;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio14 {

    public static void main(String[] args) {
        Map<String, List<String>> pokemonesPorRegion = agruparPorRegion(crearPokemones());

        pokemonesPorRegion.forEach((region, nombres) -> System.out.println(region + ": " + nombres));
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Charmander", "Fuego", 32, 310, "Kanto", false),
                new Pokemon(3L, "Chikorita", "Planta", 30, 280, "Johto", false),
                new Pokemon(4L, "Totodile", "Agua", 33, 300, "Johto", false),
                new Pokemon(5L, "Torchic", "Fuego", 29, 275, "Hoenn", false),
                new Pokemon(6L, "Piplup", "Agua", 28, 270, "Sinnoh", false)
        );
    }

    public static Map<String, List<String>> agruparPorRegion(List<Pokemon> pokemones) {
        return pokemones.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getRegion,
                        LinkedHashMap::new,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));
    }
}
