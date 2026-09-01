package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio7 {

    public static void main(String[] args) {
        List<String> nombres = crearNombresPokemon();
        List<String> nombresOrdenados = ordenarAlfabeticamente(nombres);

        System.out.println(nombresOrdenados);
    }

    private static List<String> crearNombresPokemon() {
        return List.of("Pikachu", "Charmander", "Squirtle", "Bulbasaur", "Mewtwo", "Abra");
    }

    public static List<String> ordenarAlfabeticamente(List<String> nombres) {
        return nombres.stream()
                .sorted()
                .collect(Collectors.toList());
    }
}
