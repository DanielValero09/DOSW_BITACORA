package src.main.semana_1.streams.TALLER1;

import java.util.List;
import java.util.Scanner;

public class Ejercicio2 {

    public static void main(String[] args) {
        List<String> productos = List.of(
                "Laptop",
                "Mouse",
                "Teclado",
                "Monitor",
                "Impresora"
        );

        productos.forEach(producto -> System.out.println("Producto disponible:" + producto));
    }
}
