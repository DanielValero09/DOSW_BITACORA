package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.Locale;

public class Ejercicio11 {

    public static void main(String[] args) {
        List<Pokemon> pokemones = crearPokemones();
        double promedio = calcularPromedioPoderCombate(pokemones);

        System.out.println(String.format(Locale.US, "%.2f", promedio));
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(4L, "Bulbasaur", "Planta", 35, 210, "Kanto", false),
                new Pokemon(5L, "Lapras", "Agua", 60, 495, "Kanto", false),
                new Pokemon(6L, "Charizard", "Fuego", 76, 610, "Kanto", false)
        );
    }

    public static double calcularPromedioPoderCombate(List<Pokemon> pokemones) {
        return pokemones.stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .average()
                .orElse(0);
    }
}
