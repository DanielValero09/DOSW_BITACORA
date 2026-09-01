package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ejercicio17 {

    public static void main(String[] args) {
        Optional<Entrenador> entrenadorMasPoderoso = encontrarEntrenadorMasPoderoso(crearEntrenadores());

        entrenadorMasPoderoso.ifPresent(entrenador -> {
            System.out.println("Entrenador más poderoso: " + entrenador.getNombre());
            System.out.println("Poder acumulado del equipo: " + (int) calcularPoderTotalEquipo(entrenador));
        });
    }

    private static List<Entrenador> crearEntrenadores() {
        return List.of(
                new Entrenador(1L, "Ash", 8, List.of(
                        new Pokemon(1L, "Pikachu", "Electrico", 70, 620, "Kanto", false),
                        new Pokemon(2L, "Charizard", "Fuego", 80, 780, "Kanto", false),
                        new Pokemon(3L, "Snorlax", "Normal", 75, 450, "Kanto", false)
                )),
                new Entrenador(2L, "Gary", 10, List.of(
                        new Pokemon(4L, "Blastoise", "Agua", 82, 840, "Kanto", false),
                        new Pokemon(5L, "Arcanine", "Fuego", 78, 700, "Kanto", false),
                        new Pokemon(6L, "Umbreon", "Siniestro", 76, 800, "Johto", false)
                )),
                new Entrenador(3L, "Brock", 6, List.of(
                        new Pokemon(7L, "Onix", "Roca", 65, 520, "Kanto", false),
                        new Pokemon(8L, "Geodude", "Roca", 55, 350, "Kanto", false),
                        new Pokemon(9L, "Steelix", "Acero", 78, 800, "Johto", false)
                ))
        );
    }

    public static double calcularPoderTotalEquipo(Entrenador entrenador) {
        return entrenador.getEquipo().stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .sum();
    }

    public static Optional<Entrenador> encontrarEntrenadorMasPoderoso(List<Entrenador> entrenadores) {
        return entrenadores.stream()
                .max(Comparator.comparingDouble(Ejercicio17::calcularPoderTotalEquipo));
    }
}
