# Semana 2 - Bitácora Pokémon

### Ejercicio 01 — Pokémon Tipo Fuego

Enunciado del Ejercicio

Dada una colección de Pokémon con nombre y tipo, obtener solamente los Pokémon de tipo `Fuego`.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio1 {

    public static void main(String[] args) {
        List<PokemonTipo> pokemones = crearPokemones();
        List<String> pokemonesFuego = obtenerPokemonesTipoFuego(pokemones);

        System.out.println(pokemonesFuego);
    }

    private static List<PokemonTipo> crearPokemones() {
        return List.of(
                new PokemonTipo("Pikachu", "Electrico"),
                new PokemonTipo("Charmander", "Fuego"),
                new PokemonTipo("Squirtle", "Agua"),
                new PokemonTipo("Vulpix", "Fuego"),
                new PokemonTipo("Bulbasaur", "Planta"),
                new PokemonTipo("Flareon", "Fuego")
        );
    }

    public static List<String> obtenerPokemonesTipoFuego(List<PokemonTipo> pokemones) {
        return pokemones.stream()
                .filter(pokemon -> "Fuego".equals(pokemon.tipo()))
                .map(PokemonTipo::nombre)
                .collect(Collectors.toList());
    }

    private record PokemonTipo(String nombre, String tipo) {
    }
}
```

**Captura de ejecución:**

```text
[Charmander, Vulpix, Flareon]
```

**Explicación:**

Se usa `filter()` para conservar solo los Pokémon cuyo tipo es `Fuego`. Luego `map()` obtiene sus nombres y el resultado se recoge en una lista.

### Ejercicio 02 — Pokédex Gritona

Enunciado del Ejercicio

Transformar todos los nombres de Pokémon a mayúsculas.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio2 {

    public static void main(String[] args) {
        List<String> nombres = crearNombresPokemon();
        List<String> nombresEnMayuscula = convertirNombresAMayuscula(nombres);

        System.out.println(String.join(", ", nombresEnMayuscula));
    }

    private static List<String> crearNombresPokemon() {
        return List.of("Pikachu", "Charmander", "Squirtle", "Bulbasaur");
    }

    public static List<String> convertirNombresAMayuscula(List<String> nombres) {
        return nombres.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }
}
```

**Captura de ejecución:**

```text
PIKACHU, CHARMANDER, SQUIRTLE, BULBASAUR
```

**Explicación:**

Se usa `map()` para transformar cada nombre a mayúsculas. La transformación se hace con la referencia de método `String::toUpperCase`.

### Ejercicio 03 — Poder Total del Equipo

Enunciado del Ejercicio

Calcular la suma de los niveles `[45, 62, 38, 71, 55, 29]`.

**Código implementado:**

```java
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
```

**Captura de ejecución:**

```text
300
```

**Explicación:**

Se usa `reduce()` con identidad `0` para acumular todos los niveles. La referencia de método `Integer::sum` suma cada valor hasta obtener el total `300`.

### Ejercicio 04 — Pokémon Alfa

Enunciado del Ejercicio

Encontrar el Pokémon con mayor nivel dentro de una colección.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ejercicio4 {

    public static void main(String[] args) {
        List<PokemonNivel> pokemones = crearPokemones();
        Optional<PokemonNivel> pokemonAlfa = encontrarPokemonAlfa(pokemones);

        pokemonAlfa.ifPresent(pokemon -> System.out.println(
                "Pokémon Alfa: " + pokemon.nombre() + " (nivel " + pokemon.nivel() + ")"
        ));
    }

    private static List<PokemonNivel> crearPokemones() {
        return List.of(
                new PokemonNivel("Pikachu", 45),
                new PokemonNivel("Charizard", 76),
                new PokemonNivel("Blastoise", 82),
                new PokemonNivel("Snorlax", 90),
                new PokemonNivel("Dragonite", 88)
        );
    }

    public static Optional<PokemonNivel> encontrarPokemonAlfa(List<PokemonNivel> pokemones) {
        return pokemones.stream()
                .max(Comparator.comparingInt(PokemonNivel::nivel));
    }

    private record PokemonNivel(String nombre, int nivel) {
    }
}
```

**Captura de ejecución:**

```text
Pokémon Alfa: Snorlax (nivel 90)
```

**Explicación:**

Se usa `max()` con un `Comparator` basado en el nivel de cada Pokémon. El resultado se maneja como `Optional` y se imprime el Pokémon con el nivel más alto.

### Ejercicio 05 — Pokémon Legendarios

Enunciado del Ejercicio

Contar los Pokémon con nivel superior a `80` y mostrar los nombres correspondientes.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio5 {

    public static void main(String[] args) {
        List<PokemonNivel> pokemones = crearPokemones();
        long cantidadLegendarios = contarPokemonesConNivelMayorA80(pokemones);
        List<String> nombresLegendarios = obtenerNombresConNivelMayorA80(pokemones);

        System.out.println("Pokémon con nivel > 80: " + cantidadLegendarios);
        System.out.println(nombresLegendarios);
    }

    private static List<PokemonNivel> crearPokemones() {
        return List.of(
                new PokemonNivel("Pikachu", 45),
                new PokemonNivel("Mewtwo", 95),
                new PokemonNivel("Charizard", 76),
                new PokemonNivel("Dragonite", 88),
                new PokemonNivel("Mew", 92),
                new PokemonNivel("Bulbasaur", 35)
        );
    }

    public static long contarPokemonesConNivelMayorA80(List<PokemonNivel> pokemones) {
        return pokemones.stream()
                .filter(pokemon -> pokemon.nivel() > 80)
                .count();
    }

    public static List<String> obtenerNombresConNivelMayorA80(List<PokemonNivel> pokemones) {
        return pokemones.stream()
                .filter(pokemon -> pokemon.nivel() > 80)
                .map(PokemonNivel::nombre)
                .collect(Collectors.toList());
    }

    private record PokemonNivel(String nombre, int nivel) {
    }
}
```

**Captura de ejecución:**

```text
Pokémon con nivel > 80: 3
[Mewtwo, Dragonite, Mew]
```

**Explicación:**

Se usa `filter()` para seleccionar únicamente los Pokémon con nivel mayor a `80`. Luego `count()` calcula cuántos cumplen la condición y otro flujo obtiene sus nombres con `map()`.

### Ejercicio 06 — Pokédex Sin Duplicados

Enunciado del Ejercicio

Eliminar nombres repetidos de una lista de Pokémon.

**Código implementado:**

```java
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
```

**Captura de ejecución:**

```text
[Pikachu, Charmander, Squirtle, Mewtwo]
```

**Explicación:**

Se usa `distinct()` para eliminar nombres repetidos dentro del flujo. El resultado conserva el primer orden de aparición y se recoge en una nueva lista.

### Ejercicio 07 — Orden del Profesor Oak

Enunciado del Ejercicio

Ordenar alfabéticamente los nombres de Pokémon.

**Código implementado:**

```java
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
```

**Captura de ejecución:**

```text
[Abra, Bulbasaur, Charmander, Mewtwo, Pikachu, Squirtle]
```

**Explicación:**

Se usa `sorted()` para ordenar los nombres alfabéticamente sin modificar la lista original. Después se recoge el resultado en una lista nueva.

### Ejercicio 08 — Evoluciones Preparadas

Enunciado del Ejercicio

Obtener únicamente los Pokémon cuyo atributo `puedeEvolucionar` sea verdadero.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio8 {

    public static void main(String[] args) {
        List<PokemonEvolucion> pokemones = crearPokemones();
        List<String> nombresConEvolucion = obtenerPokemonesQueEvolucionan(pokemones);

        System.out.println(nombresConEvolucion);
    }

    private static List<PokemonEvolucion> crearPokemones() {
        return List.of(
                new PokemonEvolucion("Pikachu", true),
                new PokemonEvolucion("Charmander", true),
                new PokemonEvolucion("Squirtle", true),
                new PokemonEvolucion("Mewtwo", false),
                new PokemonEvolucion("Lapras", false)
        );
    }

    public static List<String> obtenerPokemonesQueEvolucionan(List<PokemonEvolucion> pokemones) {
        return pokemones.stream()
                .filter(PokemonEvolucion::puedeEvolucionar)
                .map(PokemonEvolucion::nombre)
                .collect(Collectors.toList());
    }

    private record PokemonEvolucion(String nombre, boolean puedeEvolucionar) {
    }
}
```

**Captura de ejecución:**

```text
[Pikachu, Charmander, Squirtle]
```

**Explicación:**

Se usa `filter()` para conservar solo los Pokémon que pueden evolucionar. Luego `map()` obtiene sus nombres y se recogen en una lista.

### Ejercicio 09 — Equipo Élite

Enunciado del Ejercicio

Mostrar Pokémon cuyo `poderCombate` sea mayor a `500`.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio9 {

    public static void main(String[] args) {
        List<Pokemon> pokemones = crearPokemones();
        List<String> equipoElite = obtenerEquipoElite(pokemones);

        System.out.println(equipoElite);
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Charizard", "Fuego", 76, 610, "Kanto", false),
                new Pokemon(4L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(5L, "Bulbasaur", "Planta", 35, 210, "Kanto", false)
        );
    }

    public static List<String> obtenerEquipoElite(List<Pokemon> pokemones) {
        return pokemones.stream()
                .filter(pokemon -> pokemon.getPoderCombate() > 500)
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .map(Ejercicio9::formatearPokemon)
                .collect(Collectors.toList());
    }

    private static String formatearPokemon(Pokemon pokemon) {
        return pokemon.getNombre() + "(" + (int) pokemon.getPoderCombate() + ")";
    }
}
```

**Captura de ejecución:**

```text
[Mewtwo(680), Charizard(610), Dragonite(530)]
```

**Explicación:**

Se usa `filter()` para seleccionar Pokémon con poder de combate mayor a `500`. Luego `sorted()` ordena el resultado de mayor a menor poder usando `Comparator`.

### Ejercicio 10 — Pokédex Compacta

Enunciado del Ejercicio

Transformar una lista de objetos `Pokemon` en una lista que contenga únicamente sus nombres.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.List;
import java.util.stream.Collectors;

public class Ejercicio10 {

    public static void main(String[] args) {
        List<Pokemon> pokemones = crearPokemones();
        List<String> nombres = obtenerNombres(pokemones);

        System.out.println(nombres);
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(4L, "Bulbasaur", "Planta", 35, 210, "Kanto", false)
        );
    }

    public static List<String> obtenerNombres(List<Pokemon> pokemones) {
        return pokemones.stream()
                .map(Pokemon::getNombre)
                .collect(Collectors.toList());
    }
}
```

**Captura de ejecución:**

```text
[Pikachu, Mewtwo, Dragonite, Bulbasaur]
```

**Explicación:**

Se usa `map()` para transformar cada objeto `Pokemon` en su nombre. La referencia de método `Pokemon::getNombre` hace la transformación de forma directa.

### Ejercicio 11 — Poder Promedio

Enunciado del Ejercicio

Calcular el promedio del `poderCombate` de una colección de Pokémon.

**Código implementado:**

```java
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
```

**Captura de ejecución:**

```text
474.17
```

**Explicación:**

Se usa `mapToDouble()` para convertir los poderes a un flujo numérico. Luego `average()` calcula el promedio y `orElse(0)` maneja una lista vacía de forma segura.

### Ejercicio 12 — Campeón Regional

Enunciado del Ejercicio

Encontrar el Pokémon con mayor `poderCombate`.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Ejercicio12 {

    public static void main(String[] args) {
        List<Pokemon> pokemones = crearPokemones();
        Optional<Pokemon> campeon = obtenerCampeonRegional(pokemones);

        campeon.ifPresent(pokemon -> System.out.println(
                "Campeón: " + pokemon.getNombre() + " con PC: " + (int) pokemon.getPoderCombate()
        ));
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(4L, "Charizard", "Fuego", 76, 610, "Kanto", false)
        );
    }

    public static Optional<Pokemon> obtenerCampeonRegional(List<Pokemon> pokemones) {
        return pokemones.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate));
    }
}
```

**Captura de ejecución:**

```text
Campeón: Mewtwo con PC: 680
```

**Explicación:**

Se usa `max()` con un `Comparator` que compara por poder de combate. El resultado se maneja con `Optional` antes de imprimirlo.

### Ejercicio 13 — Organizar por Tipo

Enunciado del Ejercicio

Agrupar Pokémon según su tipo.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio13 {

    public static void main(String[] args) {
        Map<String, List<String>> pokemonesPorTipo = agruparPorTipo(crearPokemones());

        pokemonesPorTipo.forEach((tipo, nombres) -> System.out.println(tipo + ": " + nombres));
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Squirtle", "Agua", 30, 290, "Kanto", false),
                new Pokemon(2L, "Psyduck", "Agua", 28, 260, "Kanto", false),
                new Pokemon(3L, "Charmander", "Fuego", 32, 310, "Kanto", false),
                new Pokemon(4L, "Vulpix", "Fuego", 34, 330, "Kanto", false),
                new Pokemon(5L, "Bulbasaur", "Planta", 31, 300, "Kanto", false)
        );
    }

    public static Map<String, List<String>> agruparPorTipo(List<Pokemon> pokemones) {
        return pokemones.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getTipo,
                        LinkedHashMap::new,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));
    }
}
```

**Captura de ejecución:**

```text
Agua: [Squirtle, Psyduck]
Fuego: [Charmander, Vulpix]
Planta: [Bulbasaur]
```

**Explicación:**

Se usa `Collectors.groupingBy()` para agrupar por tipo. Además, `Collectors.mapping()` guarda solo los nombres de los Pokémon dentro de cada grupo.

### Ejercicio 14 — Organizar por Región

Enunciado del Ejercicio

Agrupar Pokémon según su región.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ejercicio14 {

    public static void main(String[] args) {
        Map<String, List<String>> pokemonesPorRegion = agruparPorRegion(crearPokemones());

        pokemonesPorRegion.forEach((region, nombres) -> System.out.println(region + ": " + nombres));
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Charmander", "Fuego", 32, 310, "Kanto", false),
                new Pokemon(3L, "Chikorita", "Planta", 30, 280, "Johto", false),
                new Pokemon(4L, "Totodile", "Agua", 33, 300, "Johto", false),
                new Pokemon(5L, "Torchic", "Fuego", 29, 275, "Hoenn", false),
                new Pokemon(6L, "Piplup", "Agua", 28, 270, "Sinnoh", false)
        );
    }

    public static Map<String, List<String>> agruparPorRegion(List<Pokemon> pokemones) {
        return pokemones.stream()
                .collect(Collectors.groupingBy(
                        Pokemon::getRegion,
                        LinkedHashMap::new,
                        Collectors.mapping(Pokemon::getNombre, Collectors.toList())
                ));
    }
}
```

**Captura de ejecución:**

```text
Kanto: [Pikachu, Charmander]
Johto: [Chikorita, Totodile]
Hoenn: [Torchic]
Sinnoh: [Piplup]
```

**Explicación:**

Se usa `Collectors.groupingBy()` con `Pokemon::getRegion` para agrupar por región. El `mapping()` permite guardar únicamente los nombres en cada lista.

### Ejercicio 15 — Maestro de Gimnasios

Enunciado del Ejercicio

Encontrar el entrenador con más medallas.

**Código implementado:**

```java
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
```

**Captura de ejecución:**

```text
Campeón de gimnasios: Gary
Medallas obtenidas: 10
```

**Explicación:**

Se usa `max()` con un `Comparator` que compara la cantidad de medallas. El resultado se maneja con `Optional` y se imprime el entrenador ganador.

### Ejercicio 16 — Entrenadores Experimentados

Enunciado del Ejercicio

Obtener entrenadores con más de `5` medallas.

**Código implementado:**

```java
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
```

**Captura de ejecución:**

```text
[Ash(8), Brock(6), Gary(10), Dawn(7)]
```

**Explicación:**

Se usa `filter()` para seleccionar únicamente entrenadores con más de `5` medallas. Luego se recoge el resultado en una lista.

### Ejercicio 17 — Equipo Más Poderoso

Enunciado del Ejercicio

Encontrar el entrenador cuyo equipo tenga la mayor suma de `poderCombate`.

**Código implementado:**

```java
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
```

**Captura de ejecución:**

```text
Entrenador más poderoso: Gary
Poder acumulado del equipo: 2340
```

**Explicación:**

Se usa `mapToDouble()` y `sum()` para calcular el poder total de cada equipo. Luego `max()` selecciona el entrenador con mayor poder acumulado.

### Ejercicio 18 — Top 5 Pokémon Más Fuertes

Enunciado del Ejercicio

Generar un ranking descendente de los cinco Pokémon con mayor poder de combate.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ejercicio18 {

    public static void main(String[] args) {
        List<Pokemon> top5 = obtenerTop5MasFuertes(crearPokemones());

        IntStream.range(0, top5.size())
                .mapToObj(indice -> "#" + (indice + 1) + " " + formatearPokemon(top5.get(indice)))
                .forEach(System.out::println);
    }

    private static List<Pokemon> crearPokemones() {
        return List.of(
                new Pokemon(1L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(2L, "Charizard", "Fuego", 76, 610, "Kanto", false),
                new Pokemon(3L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(4L, "Lapras", "Agua", 60, 495, "Kanto", false),
                new Pokemon(5L, "Gengar", "Fantasma", 74, 520, "Kanto", false),
                new Pokemon(6L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(7L, "Mew", "Psiquico", 92, 650, "Kanto", true)
        );
    }

    public static List<Pokemon> obtenerTop5MasFuertes(List<Pokemon> pokemones) {
        return pokemones.stream()
                .sorted(Comparator.comparingDouble(Pokemon::getPoderCombate).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    private static String formatearPokemon(Pokemon pokemon) {
        return pokemon.getNombre() + " - PC: " + (int) pokemon.getPoderCombate();
    }
}
```

**Captura de ejecución:**

```text
#1 Mewtwo - PC: 680
#2 Mew - PC: 650
#3 Charizard - PC: 610
#4 Dragonite - PC: 530
#5 Gengar - PC: 520
```

**Explicación:**

Se usa `sorted()` con un `Comparator` descendente por poder de combate. Luego `limit(5)` toma únicamente los cinco primeros del ranking.

### Ejercicio 19 — Top 3 Entrenadores

Enunciado del Ejercicio

Crear un ranking de entrenadores usando medallas, poder acumulado y nombre como criterios de orden.

**Código implementado:**

```java
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
```

**Captura de ejecución:**

```text
#1 Gary - Medallas: 10 - Poder: 1540
#2 Ash - Medallas: 8 - Poder: 1400
#3 Dawn - Medallas: 8 - Poder: 1400
```

**Explicación:**

Se usa `sorted()` con un `Comparator` compuesto: primero medallas descendentes, luego poder acumulado descendente y finalmente nombre ascendente. Después `limit(3)` deja solo los tres mejores.

### Ejercicio 20 — Pokédex Analítica

Enunciado del Ejercicio

Obtener estadísticas de una lista de Pokémon: cantidad por tipo, cantidad por región, cantidad de legendarios, promedio de nivel y Pokémon más fuerte.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class Ejercicio20 {

    public static void main(String[] args) {
        List<Pokemon> pokedex = crearPokedex();

        System.out.println("Cantidad por tipo: " + contarPorTipo(pokedex));
        System.out.println("Cantidad por region: " + contarPorRegion(pokedex));
        System.out.println("Legendarios: " + contarLegendarios(pokedex));
        System.out.println("Promedio de nivel: " + calcularPromedioNivel(pokedex));
        obtenerPokemonMasFuerte(pokedex).ifPresent(pokemon -> System.out.println(
                "Pokemon mas fuerte: " + pokemon.getNombre() + " con PC: " + (int) pokemon.getPoderCombate()
        ));
    }

    private static List<Pokemon> crearPokedex() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Charizard", "Fuego", 76, 610, "Kanto", false),
                new Pokemon(4L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(5L, "Chikorita", "Planta", 30, 280, "Johto", false),
                new Pokemon(6L, "Lugia", "Psiquico", 92, 670, "Johto", true),
                new Pokemon(7L, "Torchic", "Fuego", 29, 275, "Hoenn", false),
                new Pokemon(8L, "Kyogre", "Agua", 91, 660, "Hoenn", true)
        );
    }

    public static Map<String, Long> contarPorTipo(List<Pokemon> pokedex) {
        return pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getTipo, LinkedHashMap::new, Collectors.counting()));
    }

    public static Map<String, Long> contarPorRegion(List<Pokemon> pokedex) {
        return pokedex.stream()
                .collect(Collectors.groupingBy(Pokemon::getRegion, LinkedHashMap::new, Collectors.counting()));
    }

    public static long contarLegendarios(List<Pokemon> pokedex) {
        return pokedex.stream()
                .filter(Pokemon::isLegendario)
                .count();
    }

    public static double calcularPromedioNivel(List<Pokemon> pokedex) {
        return pokedex.stream()
                .mapToInt(Pokemon::getNivel)
                .average()
                .orElse(0);
    }

    public static Optional<Pokemon> obtenerPokemonMasFuerte(List<Pokemon> pokedex) {
        return pokedex.stream()
                .max(Comparator.comparingDouble(Pokemon::getPoderCombate));
    }
}
```

**Captura de ejecución:**

```text
Cantidad por tipo: {Electrico=1, Psiquico=2, Fuego=2, Dragon=1, Planta=1, Agua=1}
Cantidad por region: {Kanto=4, Johto=2, Hoenn=2}
Legendarios: 3
Promedio de nivel: 68.25
Pokemon mas fuerte: Mewtwo con PC: 680
```

**Explicación:**

Se separa cada análisis en un método. Se usan `groupingBy()` y `counting()` para conteos, `filter()` y `count()` para legendarios, `mapToInt()` y `average()` para promedio, y `max()` con `Comparator` para encontrar el Pokémon más fuerte.

## Modelos compartidos

Desde el ejercicio 9 se reutiliza `pokemon/Pokemon.java`. Desde el ejercicio 15 se reutiliza `pokemon/Entrenador.java`.

### Reto Mewtwo — Análisis Competitivo

Enunciado del Ejercicio

Crear un análisis adicional que use en una misma solución `filter()`, `map()`, `sorted()`, `groupingBy()` y `reduce()`.

**Código implementado:**

```java
package src.main.dosw.semana_2.pokemon;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EjercicioAdicionalMewtwo {

    public static void main(String[] args) {
        List<PokemonCompetitivo> competitivos = obtenerPokemonCompetitivos(crearPokedex(), 70);
        Map<String, List<String>> competitivosPorTipo = agruparPorTipo(competitivos);
        double poderTotal = calcularPoderTotal(competitivos);

        System.out.println("Pokemon competitivos por tipo: " + competitivosPorTipo);
        System.out.println("Poder total competitivo: " + (int) poderTotal);
    }

    private static List<Pokemon> crearPokedex() {
        return List.of(
                new Pokemon(1L, "Pikachu", "Electrico", 45, 320, "Kanto", false),
                new Pokemon(2L, "Mewtwo", "Psiquico", 95, 680, "Kanto", true),
                new Pokemon(3L, "Charizard", "Fuego", 76, 610, "Kanto", false),
                new Pokemon(4L, "Dragonite", "Dragon", 88, 530, "Kanto", false),
                new Pokemon(5L, "Mew", "Psiquico", 92, 650, "Kanto", true),
                new Pokemon(6L, "Gengar", "Fantasma", 74, 520, "Kanto", false),
                new Pokemon(7L, "Bulbasaur", "Planta", 35, 210, "Kanto", false)
        );
    }

    public static List<PokemonCompetitivo> obtenerPokemonCompetitivos(List<Pokemon> pokedex, int nivelMinimo) {
        return pokedex.stream()
                .filter(pokemon -> pokemon.getNivel() >= nivelMinimo)
                .map(pokemon -> new PokemonCompetitivo(
                        pokemon.getNombre(),
                        pokemon.getTipo(),
                        pokemon.getPoderCombate()
                ))
                .sorted(Comparator.comparingDouble(PokemonCompetitivo::poderCombate).reversed())
                .collect(Collectors.toList());
    }

    public static Map<String, List<String>> agruparPorTipo(List<PokemonCompetitivo> competitivos) {
        return competitivos.stream()
                .collect(Collectors.groupingBy(
                        PokemonCompetitivo::tipo,
                        LinkedHashMap::new,
                        Collectors.mapping(PokemonCompetitivo::nombre, Collectors.toList())
                ));
    }

    public static double calcularPoderTotal(List<PokemonCompetitivo> competitivos) {
        return competitivos.stream()
                .map(PokemonCompetitivo::poderCombate)
                .reduce(0.0, Double::sum);
    }

    public record PokemonCompetitivo(String nombre, String tipo, double poderCombate) {
    }
}
```

**Captura de ejecución:**

```text
Pokemon competitivos por tipo: {Psiquico=[Mewtwo, Mew], Fuego=[Charizard], Dragon=[Dragonite], Fantasma=[Gengar]}
Poder total competitivo: 2990
```

**Explicación:**

Se usa `filter()` para elegir Pokémon con nivel mínimo, `map()` para crear una representación competitiva, `sorted()` para ordenar por poder, `groupingBy()` para agrupar por tipo y `reduce()` para sumar el poder total.
