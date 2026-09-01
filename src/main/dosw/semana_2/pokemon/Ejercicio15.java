package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ejercicio15 {

    public static void main(String[] args) {
        Optional<Entrenador> campeon = encontrarEntrenadorConMasMedallas(crearEntrenadores());

        campeon.ifPresent(entrenador -> {
            System.out.println("Campeón de gimnasios: " + entrenador.getNombre());
            System.out.println("Medallas obtenidas: " + entrenador.getMedallas());
        });
    }

    private static List<Entrenador> crearEntrenadores() {
        return List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 4, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of())
        );
    }

    public static Optional<Entrenador> encontrarEntrenadorConMasMedallas(List<Entrenador> entrenadores) {
        return entrenadores.stream()
                .max(Comparator.comparingInt(Entrenador::getMedallas));
    }
}
