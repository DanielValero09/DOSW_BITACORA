package src.main.semana_1.streams.TALLER1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio9 {
    public static void main(String[] args) {
        List<Integer> puntaajes = List.of(
                980, 950, 920, 900, 890,
                870, 860, 850, 840, 830,
                820, 810, 800, 790, 780,
                770, 760, 750, 740, 730
        );

        List<Integer> top5 = puntaajes.stream()
                .limit(5)
                .collect(Collectors.toList());

        System.out.println("Top5: " + top5);
    }
}
