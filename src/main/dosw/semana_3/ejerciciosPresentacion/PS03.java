package src.main.dosw.semana_3.ejerciciosPresentacion;

//public class PS03 {
// ============================================================================
// EJERCICIO 1 - FACTORY METHOD
// PROPÓSITO:
// Permitir crear diferentes métodos de pago sin que el código cliente
// dependa directamente de clases concretas como CreditCardPayment,
// PayPalPayment o BankTransferPayment.
//
// PATRÓN: Factory Method
// ============================================================================


// Producto
interface Payment {
    void pay(double amount);
}


// Productos concretos
class CreditCardPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Pago con TARJETA DE CREDITO por $" + amount);
    }
}


class PayPalPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Pago con PAYPAL por $" + amount);
    }
}


class BankTransferPayment implements Payment {

    @Override
    public void pay(double amount) {
        System.out.println("Pago con TRANSFERENCIA BANCARIA por $" + amount);
    }
}


// Creador
abstract class PaymentFactory {

    public abstract Payment createPayment();

    public void processPayment(double amount) {
        Payment payment = createPayment();
        payment.pay(amount);
    }
}


// Creadores concretos
class CreditCardPaymentFactory extends PaymentFactory {

    @Override
    public Payment createPayment() {
        return new CreditCardPayment();
    }
}


class PayPalPaymentFactory extends PaymentFactory {

    @Override
    public Payment createPayment() {
        return new PayPalPayment();
    }
}


class BankTransferPaymentFactory extends PaymentFactory {

    @Override
    public Payment createPayment() {
        return new BankTransferPayment();
    }
}


// Ejemplo de uso
PaymentFactory factory = new CreditCardPaymentFactory();
factory.processPayment(150000.50);

factory = new PayPalPaymentFactory();
factory.processPayment(80000.75);

factory = new BankTransferPaymentFactory();
factory.processPayment(200000.00);





// ============================================================================
// EJERCICIO 2 - ABSTRACT FACTORY
// PROPÓSITO:
// Crear familias completas de componentes compatibles para cada consola.
//
// Una fábrica PlayStation crea:
// - Control PlayStation
// - Juego PlayStation
// - Interfaz gráfica PlayStation
//
// Una fábrica Xbox crea:
// - Control Xbox
// - Juego Xbox
// - Interfaz gráfica Xbox
//
// El motor del juego trabaja únicamente con abstracciones.
//
// PATRÓN: Abstract Factory
// ============================================================================


// Productos abstractos
interface Controller {
    void connect();
}


interface Game {
    void start();
}


interface GraphicInterface {
    void render();
}


// Productos concretos PlayStation
class PlayStationController implements Controller {

    @Override
    public void connect() {
        System.out.println("Control de PlayStation conectado");
    }
}


class PlayStationGame implements Game {

    @Override
    public void start() {
        System.out.println("Juego de PlayStation iniciado");
    }
}


class PlayStationGraphicInterface implements GraphicInterface {

    @Override
    public void render() {
        System.out.println("Interfaz gráfica de PlayStation renderizada");
    }
}


// Productos concretos Xbox
class XboxController implements Controller {

    @Override
    public void connect() {
        System.out.println("Control de Xbox conectado");
    }
}


class XboxGame implements Game {

    @Override
    public void start() {
        System.out.println("Juego de Xbox iniciado");
    }
}


class XboxGraphicInterface implements GraphicInterface {

    @Override
    public void render() {
        System.out.println("Interfaz gráfica de Xbox renderizada");
    }
}


// Fábrica abstracta
interface ConsoleFactory {

    Controller createController();

    Game createGame();

    GraphicInterface createGraphicInterface();
}


// Fábricas concretas
class PlayStationFactory implements ConsoleFactory {

    @Override
    public Controller createController() {
        return new PlayStationController();
    }

    @Override
    public Game createGame() {
        return new PlayStationGame();
    }

    @Override
    public GraphicInterface createGraphicInterface() {
        return new PlayStationGraphicInterface();
    }
}


class XboxFactory implements ConsoleFactory {

    @Override
    public Controller createController() {
        return new XboxController();
    }

    @Override
    public Game createGame() {
        return new XboxGame();
    }

    @Override
    public GraphicInterface createGraphicInterface() {
        return new XboxGraphicInterface();
    }
}


// Cliente
class GameEngine {

    private final Controller controller;
    private final Game game;
    private final GraphicInterface graphicInterface;

    public GameEngine(ConsoleFactory factory) {
        this.controller = factory.createController();
        this.game = factory.createGame();
        this.graphicInterface = factory.createGraphicInterface();
    }

    public void run() {
        controller.connect();
        game.start();
        graphicInterface.render();
    }
}


// Ejemplo de uso
ConsoleFactory consoleFactory = new PlayStationFactory();
GameEngine gameEngine = new GameEngine(consoleFactory);
gameEngine.run();





// ============================================================================
// EJERCICIO 3 - BUILDER
// PROPÓSITO:
// Construir diferentes tipos de muñecos paso a paso sin utilizar
// constructores enormes.
//
// Cada muñeco puede tener:
// - Cabeza
// - Cuerpo
// - Brazos
// - Piernas
// - Accesorios opcionales
//
// PATRÓN: Builder
// ============================================================================


// Producto
class Doll {

    private String head;
    private String body;
    private String arms;
    private String legs;
    private String accessories;

    public void setHead(String head) {
        this.head = head;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setArms(String arms) {
        this.arms = arms;
    }

    public void setLegs(String legs) {
        this.legs = legs;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    @Override
    public String toString() {
        return "Doll{" +
                "head='" + head + '\'' +
                ", body='" + body + '\'' +
                ", arms='" + arms + '\'' +
                ", legs='" + legs + '\'' +
                ", accessories='" + accessories + '\'' +
                '}';
    }
}


// Builder
interface DollBuilder {

    void buildHead();

    void buildBody();

    void buildArms();

    void buildLegs();

    void buildAccessories();

    Doll getResult();
}


// Builder concreto: muñeco de acción
class ActionDollBuilder implements DollBuilder {

    private final Doll doll = new Doll();

    @Override
    public void buildHead() {
        doll.setHead("Cabeza de muñeco de acción");
    }

    @Override
    public void buildBody() {
        doll.setBody("Cuerpo musculoso");
    }

    @Override
    public void buildArms() {
        doll.setArms("Brazos articulados");
    }

    @Override
    public void buildLegs() {
        doll.setLegs("Piernas articuladas");
    }

    @Override
    public void buildAccessories() {
        doll.setAccessories("Espada");
    }

    @Override
    public Doll getResult() {
        return doll;
    }
}


// Builder concreto: muñeca clásica
class ClassicDollBuilder implements DollBuilder {

    private final Doll doll = new Doll();

    @Override
    public void buildHead() {
        doll.setHead("Cabeza de muñeca clásica");
    }

    @Override
    public void buildBody() {
        doll.setBody("Cuerpo clásico");
    }

    @Override
    public void buildArms() {
        doll.setArms("Brazos clásicos");
    }

    @Override
    public void buildLegs() {
        doll.setLegs("Piernas clásicas");
    }

    @Override
    public void buildAccessories() {
        doll.setAccessories("Vestido");
    }

    @Override
    public Doll getResult() {
        return doll;
    }
}


// Director
class DollDirector {

    public void build(DollBuilder builder) {
        builder.buildHead();
        builder.buildBody();
        builder.buildArms();
        builder.buildLegs();
        builder.buildAccessories();
    }
}


// Ejemplo de uso
DollDirector director = new DollDirector();

DollBuilder builder = new ActionDollBuilder();

director.build(builder);

Doll actionDoll = builder.getResult();

System.out.println(actionDoll);





// ============================================================================
// EJERCICIO 4 - ADAPTER
// PROPÓSITO:
// Permitir que el sistema de una gasolinería utilice la misma interfaz
// para abastecer vehículos de combustión, eléctricos e híbridos.
//
// Los cargadores eléctricos tienen interfaces incompatibles y no pueden
// modificarse, por lo que se crean adaptadores.
//
// Conversión:
// - Cargador rápido: litros * 8.0
// - Cargador lento: litros * 7.0
//
// PATRÓN: Adapter
// ============================================================================


// Interfaz conocida por el sistema
interface FuelSupply {

    void supply(double liters);
}


// Sistema tradicional
class FuelPump implements FuelSupply {

    @Override
    public void supply(double liters) {
        System.out.println("Abasteciendo " + liters + " litros de combustible");
    }
}


// Servicio externo incompatible
class FastElectricCharger {

    public void fastCharge(double kWh) {
        System.out.println("Carga rápida de " + kWh + " kWh");
    }
}


// Otro servicio externo incompatible
class SlowElectricCharger {

    public void slowCharge(double kWh) {
        System.out.println("Carga lenta de " + kWh + " kWh");
    }
}


// Adapter para cargador rápido
class FastChargerAdapter implements FuelSupply {

    private final FastElectricCharger charger;

    public FastChargerAdapter(FastElectricCharger charger) {
        this.charger = charger;
    }

    @Override
    public void supply(double liters) {

        double kWh = liters * 8.0;

        charger.fastCharge(kWh);
    }
}


// Adapter para cargador lento
class SlowChargerAdapter implements FuelSupply {

    private final SlowElectricCharger charger;

    public SlowChargerAdapter(SlowElectricCharger charger) {
        this.charger = charger;
    }

    @Override
    public void supply(double liters) {

        double kWh = liters * 7.0;

        charger.slowCharge(kWh);
    }
}


// Ejemplo de uso
FuelSupply fuelVehicle = new FuelPump();
fuelVehicle.supply(10);

FuelSupply electricVehicle =
        new FastChargerAdapter(new FastElectricCharger());

electricVehicle.supply(10);





// ============================================================================
// EJERCICIO 5 - BRIDGE
// PROPÓSITO:
// Separar dos dimensiones que pueden variar independientemente:
//
// 1. Forma:
//    - Círculo
//    - Cuadrado
//
// 2. Color:
//    - Rojo
//    - Azul
//
// De esta manera evitamos crear clases como:
// CirculoRojo
// CirculoAzul
// CuadradoRojo
// CuadradoAzul
//
// PATRÓN: Bridge
// ============================================================================


// Implementación
interface Color {

    String applyColor();
}


// Implementaciones concretas
class Red implements Color {

    @Override
    public String applyColor() {
        return "Rojo";
    }
}


class Blue implements Color {

    @Override
    public String applyColor() {
        return "Azul";
    }
}


// Abstracción
abstract class Shape {

    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    public abstract void draw();
}


// Abstracciones refinadas
class Circle extends Shape {

    public Circle(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println(
                "Dibujando círculo de color " + color.applyColor()
        );
    }
}


class Square extends Shape {

    public Square(Color color) {
        super(color);
    }

    @Override
    public void draw() {
        System.out.println(
                "Dibujando cuadrado de color " + color.applyColor()
        );
    }
}


// Ejemplo de uso
Shape redCircle = new Circle(new Red());

Shape blueSquare = new Square(new Blue());

redCircle.draw();
blueSquare.draw();





// ============================================================================
// EJERCICIO 6 - COMPOSITE
// PROPÓSITO:
// Representar productos individuales y cajas utilizando la misma interfaz.
//
// Una caja puede contener:
// - Productos
// - Otras cajas
//
// El precio total se calcula recorriendo recursivamente todos sus elementos.
//
// PATRÓN: Composite
// ============================================================================


// Componente
interface WarehouseItem {

    double getPrice();
}


// Hoja
class Product implements WarehouseItem {

    private final String name;
    private final double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }
}


// Composite
class Box implements WarehouseItem {

    private final List<WarehouseItem> items = new ArrayList<>();

    public void add(WarehouseItem item) {
        items.add(item);
    }

    public void remove(WarehouseItem item) {
        items.remove(item);
    }

    @Override
    public double getPrice() {

        double total = 0;

        for (WarehouseItem item : items) {
            total += item.getPrice();
        }

        return total;
    }
}


// Ejemplo de uso
Product keyboard = new Product("Teclado", 100);
Product mouse = new Product("Mouse", 50);
Product monitor = new Product("Monitor", 500);

Box smallBox = new Box();

smallBox.add(keyboard);
smallBox.add(mouse);

Box largeBox = new Box();

largeBox.add(smallBox);
largeBox.add(monitor);

System.out.println(
        "Precio total: $" + largeBox.getPrice()
);





// ============================================================================
// EJERCICIO 7 - DECORATOR
// PROPÓSITO:
// Agregar mejoras dinámicamente a un barco sin modificar su clase
// original ni crear una subclase para cada combinación posible.
//
// Mejoras:
// - Blindaje reforzado: +30 defensa
// - Radar avanzado: +10 ataque
// - Misiles: +40 ataque
// - Sistema antitorpedos: +20 ataque
//
// PATRÓN: Decorator
// ============================================================================


// Componente
interface Ship {

    int getAttack();

    int getDefense();

    String getDescription();
}


// Componente concreto
class BasicShip implements Ship {

    @Override
    public int getAttack() {
        return 50;
    }

    @Override
    public int getDefense() {
        return 50;
    }

    @Override
    public String getDescription() {
        return "Barco básico";
    }
}


// Decorador base
abstract class ShipDecorator implements Ship {

    protected Ship ship;

    public ShipDecorator(Ship ship) {
        this.ship = ship;
    }

    @Override
    public int getAttack() {
        return ship.getAttack();
    }

    @Override
    public int getDefense() {
        return ship.getDefense();
    }

    @Override
    public String getDescription() {
        return ship.getDescription();
    }
}


// Decoradores concretos
class ReinforcedArmor extends ShipDecorator {

    public ReinforcedArmor(Ship ship) {
        super(ship);
    }

    @Override
    public int getDefense() {
        return ship.getDefense() + 30;
    }

    @Override
    public String getDescription() {
        return ship.getDescription() + " + Blindaje reforzado";
    }
}


class AdvancedRadar extends ShipDecorator {

    public AdvancedRadar(Ship ship) {
        super(ship);
    }

    @Override
    public int getAttack() {
        return ship.getAttack() + 10;
    }

    @Override
    public String getDescription() {
        return ship.getDescription() + " + Radar avanzado";
    }
}


class Missiles extends ShipDecorator {

    public Missiles(Ship ship) {
        super(ship);
    }

    @Override
    public int getAttack() {
        return ship.getAttack() + 40;
    }

    @Override
    public String getDescription() {
        return ship.getDescription() + " + Misiles";
    }
}


class AntiTorpedoSystem extends ShipDecorator {

    public AntiTorpedoSystem(Ship ship) {
        super(ship);
    }

    @Override
    public int getAttack() {
        return ship.getAttack() + 20;
    }

    @Override
    public String getDescription() {
        return ship.getDescription() + " + Sistema antitorpedos";
    }
}


// Ejemplo de uso
Ship ship = new BasicShip();

ship = new ReinforcedArmor(ship);
ship = new AdvancedRadar(ship);
ship = new Missiles(ship);

System.out.println(ship.getDescription());
        System.out.println("Ataque: " + ship.getAttack());
        System.out.println("Defensa: " + ship.getDefense());





// ============================================================================
// EJERCICIO 8 - CHAIN OF RESPONSIBILITY
// PROPÓSITO:
// Procesar el ingreso de una persona a Estados Unidos mediante una cadena
// de controles.
//
// Cada control puede:
// - Aprobar y enviar la solicitud al siguiente.
// - Rechazar y detener completamente el proceso.
//
// Controles:
// 1. Pasaporte y visa
// 2. Antecedentes
// 3. Motivo del viaje
// 4. Aprobación migratoria
//
// PATRÓN: Chain of Responsibility
// ============================================================================


// Solicitud
class Traveler {

    boolean validPassportAndVisa;
    boolean cleanBackground;
    boolean validTravelReason;

    public Traveler(
            boolean validPassportAndVisa,
            boolean cleanBackground,
            boolean validTravelReason
    ) {
        this.validPassportAndVisa = validPassportAndVisa;
        this.cleanBackground = cleanBackground;
        this.validTravelReason = validTravelReason;
    }
}


// Manejador
abstract class ImmigrationControl {

    protected ImmigrationControl next;

    public ImmigrationControl setNext(ImmigrationControl next) {
        this.next = next;
        return next;
    }

    public abstract boolean check(Traveler traveler);

    protected boolean checkNext(Traveler traveler) {

        if (next == null) {
            return true;
        }

        return next.check(traveler);
    }
}


// Manejadores concretos
class PassportVisaControl extends ImmigrationControl {

    @Override
    public boolean check(Traveler traveler) {

        if (!traveler.validPassportAndVisa) {
            System.out.println("Ingreso rechazado: pasaporte o visa");
            return false;
        }

        System.out.println("Pasaporte y visa aprobados");

        return checkNext(traveler);
    }
}


class BackgroundControl extends ImmigrationControl {

    @Override
    public boolean check(Traveler traveler) {

        if (!traveler.cleanBackground) {
            System.out.println("Ingreso rechazado: antecedentes");
            return false;
        }

        System.out.println("Antecedentes aprobados");

        return checkNext(traveler);
    }
}


class TravelReasonControl extends ImmigrationControl {

    @Override
    public boolean check(Traveler traveler) {

        if (!traveler.validTravelReason) {
            System.out.println("Ingreso rechazado: motivo del viaje");
            return false;
        }

        System.out.println("Motivo del viaje aprobado");

        return checkNext(traveler);
    }
}


class FinalImmigrationControl extends ImmigrationControl {

    @Override
    public boolean check(Traveler traveler) {

        System.out.println("Ingreso aprobado por migración");

        return true;
    }
}


// Ejemplo de uso
ImmigrationControl passport = new PassportVisaControl();
ImmigrationControl background = new BackgroundControl();
ImmigrationControl reason = new TravelReasonControl();
ImmigrationControl finalControl = new FinalImmigrationControl();

passport
        .setNext(background)
        .setNext(reason)
        .setNext(finalControl);

Traveler traveler =
        new Traveler(true, true, true);

passport.check(traveler);





// ============================================================================
// EJERCICIO 9 - COMMAND
// PROPÓSITO:
// Encapsular las acciones de un personaje de videojuego dentro de objetos
// independientes.
//
// El control del juego puede ejecutar cualquier comando sin conocer
// cómo está implementada realmente la acción.
//
// Acciones:
// - Caminar
// - Saltar
// - Atacar
// - Defender
//
// PATRÓN: Command
// ============================================================================


// Receptor
class Character {

    public void walk() {
        System.out.println("El personaje camina");
    }

    public void jump() {
        System.out.println("El personaje salta");
    }

    public void attack() {
        System.out.println("El personaje ataca");
    }

    public void defend() {
        System.out.println("El personaje se defiende");
    }
}


// Command
interface Command {

    void execute();
}


// Comandos concretos
class WalkCommand implements Command {

    private final Character character;

    public WalkCommand(Character character) {
        this.character = character;
    }

    @Override
    public void execute() {
        character.walk();
    }
}


class JumpCommand implements Command {

    private final Character character;

    public JumpCommand(Character character) {
        this.character = character;
    }

    @Override
    public void execute() {
        character.jump();
    }
}


class AttackCommand implements Command {

    private final Character character;

    public AttackCommand(Character character) {
        this.character = character;
    }

    @Override
    public void execute() {
        character.attack();
    }
}


class DefendCommand implements Command {

    private final Character character;

    public DefendCommand(Character character) {
        this.character = character;
    }

    @Override
    public void execute() {
        character.defend();
    }
}


// Invocador
class GameController {

    public void execute(Command command) {
        command.execute();
    }
}


// Ejemplo de uso
Character character = new Character();

GameController controller = new GameController();

controller.execute(new WalkCommand(character));
        controller.execute(new JumpCommand(character));
        controller.execute(new AttackCommand(character));
        controller.execute(new DefendCommand(character));





// ============================================================================
// EJERCICIO 10 - ITERATOR
// PROPÓSITO:
// Recorrer lugares turísticos de Roma sin revelar cómo están almacenados
// internamente dentro de la colección.
//
// Lugares:
// - Coliseo
// - Foro Romano
// - Fontana di Trevi
// - Panteón
// - Plaza de España
//
// PATRÓN: Iterator
// ============================================================================


// Iterator
interface PlaceIterator {

    boolean hasNext();

    String next();
}


// Colección
interface TouristCollection {

    PlaceIterator createIterator();
}


// Colección concreta
class RomeTour implements TouristCollection {

    private final List<String> places = List.of(
            "Coliseo",
            "Foro Romano",
            "Fontana di Trevi",
            "Panteón",
            "Plaza de España"
    );

    @Override
    public PlaceIterator createIterator() {
        return new RomeIterator(places);
    }
}


// Iterator concreto
class RomeIterator implements PlaceIterator {

    private final List<String> places;

    private int position = 0;

    public RomeIterator(List<String> places) {
        this.places = places;
    }

    @Override
    public boolean hasNext() {
        return position < places.size();
    }

    @Override
    public String next() {
        return places.get(position++);
    }
}


// Ejemplo de uso
TouristCollection romeTour = new RomeTour();

PlaceIterator iterator =
        romeTour.createIterator();

while (iterator.hasNext()) {

        System.out.println(iterator.next());
        }





// ============================================================================
// EJERCICIO 11 - STRATEGY
// PROPÓSITO:
// Permitir que una aplicación de navegación pueda cambiar dinámicamente
// el algoritmo utilizado para calcular una ruta.
//
// La aplicación no conoce los detalles internos del algoritmo.
// Simplemente utiliza una estrategia.
//
// PATRÓN: Strategy
// ============================================================================


// Strategy
interface RouteStrategy {

    void calculateRoute(String origin, String destination);
}


// Estrategias concretas
class FastestRouteStrategy implements RouteStrategy {

    @Override
    public void calculateRoute(
            String origin,
            String destination
    ) {

        System.out.println(
                "Calculando ruta más rápida desde "
                        + origin
                        + " hasta "
                        + destination
        );
    }
}


class ShortestRouteStrategy implements RouteStrategy {

    @Override
    public void calculateRoute(
            String origin,
            String destination
    ) {

        System.out.println(
                "Calculando ruta más corta desde "
                        + origin
                        + " hasta "
                        + destination
        );
    }
}


class ScenicRouteStrategy implements RouteStrategy {

    @Override
    public void calculateRoute(
            String origin,
            String destination
    ) {

        System.out.println(
                "Calculando ruta turística desde "
                        + origin
                        + " hasta "
                        + destination
        );
    }
}


// Contexto
class NavigationApp {

    private RouteStrategy strategy;

    public NavigationApp(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void calculateRoute(
            String origin,
            String destination
    ) {

        strategy.calculateRoute(
                origin,
                destination
        );
    }
}


// Ejemplo de uso
NavigationApp navigation =
        new NavigationApp(
                new FastestRouteStrategy()
        );

navigation.calculateRoute(
        "Bogotá",
                "Medellín"
);


// Cambio de estrategia en tiempo de ejecución
navigation.setStrategy(
        new ScenicRouteStrategy()
);

        navigation.calculateRoute(
        "Bogotá",
                "Medellín"
);
