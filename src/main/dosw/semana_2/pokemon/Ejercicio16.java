package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio16 {

    public static void main(String[] args) {
        List<Entrenador> entrenadores = crearEntrenadores();
        List<Entrenador> experimentados = obtenerEntrenadoresExperimentados(entrenadores);

        System.out.println(experimentados);
    }

    private static List<Entrenador> crearEntrenadores() {
        return List.of(
                new Entrenador(1L, "Ash", 8, List.of()),
                new Entrenador(2L, "Misty", 4, List.of()),
                new Entrenador(3L, "Brock", 6, List.of()),
                new Entrenador(4L, "Gary", 10, List.of()),
                new Entrenador(5L, "Dawn", 7, List.of())
        );
    }

    public static List<Entrenador> obtenerEntrenadoresExperimentados(List<Entrenador> entrenadores) {
        return entrenadores.stream()
                .filter(entrenador -> entrenador.getMedallas() > 5)
                .collect(Collectors.toList());
    }
}
