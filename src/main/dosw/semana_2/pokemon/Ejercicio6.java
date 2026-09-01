package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio6 {

    public static void main(String[] args) {
        List<String> nombres = crearNombresPokemon();
        List<String> nombresSinDuplicados = eliminarDuplicados(nombres);

        System.out.println(nombresSinDuplicados);
    }

    private static List<String> crearNombresPokemon() {
        return List.of("Pikachu", "Charmander", "Pikachu", "Squirtle", "Charmander", "Mewtwo");
    }

    public static List<String> eliminarDuplicados(List<String> nombres) {
        return nombres.stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
