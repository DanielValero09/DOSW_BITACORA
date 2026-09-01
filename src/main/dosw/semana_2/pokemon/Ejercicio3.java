package src.main.dosw.semana_2.pokemon;

import java.util.List;

public class Ejercicio3 {

    public static void main(String[] args) {
        List<Integer> niveles = crearNiveles();
        int poderTotal = calcularPoderTotal(niveles);

        System.out.println(poderTotal);
    }

    private static List<Integer> crearNiveles() {
        return List.of(45, 62, 38, 71, 55, 29);
    }

    public static int calcularPoderTotal(List<Integer> niveles) {
        return niveles.stream()
                .reduce(0, Integer::sum);
    }
}
