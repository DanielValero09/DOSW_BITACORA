package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio19 {

    public static void main(String[] args) {
        List<Entrenador> top3 = obtenerTop3Entrenadores(crearEntrenadores());

        IntStream.range(0, top3.size())
                .mapToObj(indice -> "#" + (indice + 1) + " " + formatearEntrenador(top3.get(indice)))
                .forEach(System.out::println);
    }

    private static List<Entrenador> crearEntrenadores() {
        return List.of(
                new Entrenador(1L, "Ash", 8, List.of(
                        new Pokemon(1L, "Pikachu", "Electrico", 70, 620, "Kanto", false),
                        new Pokemon(2L, "Charizard", "Fuego", 80, 780, "Kanto", false)
                )),
                new Entrenador(2L, "Gary", 10, List.of(
                        new Pokemon(3L, "Blastoise", "Agua", 82, 840, "Kanto", false),
                        new Pokemon(4L, "Arcanine", "Fuego", 78, 700, "Kanto", false)
                )),
                new Entrenador(3L, "Dawn", 8, List.of(
                        new Pokemon(5L, "Piplup", "Agua", 70, 640, "Sinnoh", false),
                        new Pokemon(6L, "Togekiss", "Hada", 74, 760, "Sinnoh", false)
                )),
                new Entrenador(4L, "Brock", 6, List.of(
                        new Pokemon(7L, "Onix", "Roca", 65, 520, "Kanto", false),
                        new Pokemon(8L, "Steelix", "Acero", 78, 800, "Johto", false)
                ))
        );
    }

    public static double calcularPoderTotalEquipo(Entrenador entrenador) {
        return entrenador.getEquipo().stream()
                .mapToDouble(Pokemon::getPoderCombate)
                .sum();
    }

    public static List<Entrenador> obtenerTop3Entrenadores(List<Entrenador> entrenadores) {
        Comparator<Entrenador> ranking = Comparator
                .comparingInt(Entrenador::getMedallas).reversed()
                .thenComparing(Comparator.comparingDouble(Ejercicio19::calcularPoderTotalEquipo).reversed())
                .thenComparing(Entrenador::getNombre);

        return entrenadores.stream()
                .sorted(ranking)
                .limit(3)
                .collect(Collectors.toList());
    }

    private static String formatearEntrenador(Entrenador entrenador) {
        return entrenador.getNombre()
                + " - Medallas: " + entrenador.getMedallas()
                + " - Poder: " + (int) calcularPoderTotalEquipo(entrenador);
    }
}
