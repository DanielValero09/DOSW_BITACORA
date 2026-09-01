# Taller 4 - Patrones de Diseño Combinados

### Ejercicio 01 — Plataforma de Pagos Inteligentes

Enunciado del Ejercicio

Desacoplar el `Checkout` de los mecanismos concretos de pago y usar una fábrica para crear la estrategia adecuada según el país o tipo de pago.

**Código implementado:**

`Ejercicio01.java`

Contiene `PaymentStrategy`, `TarjetaStrategy`, `PseStrategy`, `NequiStrategy`, `PaypalStrategy`, `Checkout`, `PaymentFactory`, `ColombiaPaymentFactory` y `UsaPaymentFactory`.

**Captura de ejecución:**

```text
Checkout inicia el pago.
Pago Nequi colombiano procesado por $85000.0
Checkout inicia el pago.
Pago PayPal estadounidense procesado por $120.0
```

**Explicación:**

`Strategy` define cómo se procesa cada pago. `Factory Method` decide qué estrategia concreta crear. El `Checkout` depende de la abstracción `PaymentStrategy`, no de pagos concretos.

### Ejercicio 02 — Sistema de Notificaciones Multicanal

Enunciado del Ejercicio

Notificar automáticamente por varios canales cuando un pedido cambia de estado, usando una fábrica para construir el mensaje de cada canal.

**Código implementado:**

`Ejercicio02.java`

Contiene `OrderEvent`, `Message`, `NotificationObserver`, notificaciones por Email/SMS/Push, `MessageFactory` y fábricas concretas por canal.

**Captura de ejecución:**

```text
Pedido ORD-1001 cambia a ENVIADO
Email enviado: <h1>Pedido ORD-1001: ENVIADO</h1>
SMS enviado: Pedido ORD-1001 ahora esta ENVIADO
Push enviado: {order:'ORD-1001', status:'ENVIADO'}
Pedido ORD-1001 cambia a ENTREGADO
Email enviado: <h1>Pedido ORD-1001: ENTREGADO</h1>
SMS enviado: Pedido ORD-1001 ahora esta ENTREGADO
Push enviado: {order:'ORD-1001', status:'ENTREGADO'}
```

**Explicación:**

`Observer` permite que `Order` notifique a los canales registrados sin conocerlos en detalle. `Factory Method` crea el mensaje adecuado para cada canal antes de simular el envío.

### Ejercicio 03 — Sistema de Reportes Empresariales

Enunciado del Ejercicio

Generar reportes con un flujo fijo de cuatro pasos y permitir que una fábrica seleccione el formato concreto.

**Código implementado:**

`Ejercicio03.java`

Contiene `ReportGenerator`, `PdfReport`, `ExcelReport`, `CsvReport` y `ReportFactory`.

**Captura de ejecución:**

```text
1. Obtener datos comunes
2. Procesar informacion comun
3. Aplicar formato PDF
4. Exportar archivo PDF
1. Obtener datos comunes
2. Procesar informacion comun
3. Aplicar formato CSV
4. Exportar archivo CSV
```

**Explicación:**

`Template Method` fija el orden de generación en `generate()`. `Factory Method` crea el tipo de reporte solicitado y el cliente solo ejecuta el algoritmo común.

### Ejercicio 04 — Plataforma de Videojuegos: Personajes

Enunciado del Ejercicio

Construir un personaje base configurable y agregar poderes temporales de forma dinámica.

**Código implementado:**

`Ejercicio04.java`

Contiene `GameCharacter`, `BasicGameCharacter`, `GameCharacterBuilder`, `CharacterDecorator`, `ShieldDecorator`, `SpeedDecorator` e `InvisibilityDecorator`.

**Captura de ejecución:**

```text
Guerrero con Armadura de acero, Espada larga y habilidad Furia
Guerrero con Armadura de acero, Espada larga y habilidad Furia + escudo de hielo + velocidad extra + invisibilidad
Invisibilidad activa antes del ataque.
Velocidad extra activa.
Escudo de hielo activo.
Guerrero ataca con Espada larga usando Furia
```

**Explicación:**

`Builder` crea el personaje inicial paso a paso. `Decorator` envuelve ese personaje para agregar poderes sin crear combinaciones por herencia.

### Ejercicio 05 — Integración con Sistema Bancario Antiguo

Enunciado del Ejercicio

Integrar una interfaz moderna de pagos con un servicio bancario antiguo que trabaja con métodos y unidades incompatibles.

**Código implementado:**

`Ejercicio05.java`

Contiene `PaymentProcessor`, `LegacyBankService`, `LegacyBankAdapter` y `BankFacade`.

**Captura de ejecución:**

```text
Facade inicia configuracion bancaria
Facade prepara conexion, credenciales y contexto legacy
Facade abre sesion para cuenta ACC-001
Adapter convierte $249.99 a 24999 centavos
Legacy verifica saldo de ACC-001 por 24999 centavos
Legacy ejecuta transaccion de 24999 centavos en ACC-001
Pago moderno confirmado
```

**Explicación:**

`Facade` oculta la preparación del sistema bancario. `Adapter` traduce la interfaz moderna `pay(double)` a las operaciones legacy en centavos.

### Ejercicio 06 — Motor de Recomendaciones

Enunciado del Ejercicio

Cambiar el algoritmo de recomendación en tiempo de ejecución y avisar a varios componentes cuando cambian las preferencias.

**Código implementado:**

`Ejercicio06.java`

Contiene `RecommendationAlgorithm`, estrategias por género, historial, popularidad y similitud, `RecommendationEngine`, `PreferenceObserver` y componentes observadores.

**Captura de ejecución:**

```text
Preferencia cambiada a fantasia
Home actualiza: [Serie de fantasia, Aventura epica, Mundo magico]
Notificacion enviada a Laura
Sugeridos actualizados: [Serie de fantasia, Aventura epica, Mundo magico]
Preferencia cambiada a tendencias
Home actualiza: [Top global, Estreno popular, Mas visto hoy]
Notificacion enviada a Laura
Sugeridos actualizados: [Top global, Estreno popular, Mas visto hoy]
```

**Explicación:**

`Strategy` permite intercambiar el algoritmo de recomendación. `Observer` hace que los componentes reaccionen automáticamente al cambio de preferencias.

### Ejercicio 07 — Flujo de Aprobación de Documentos

Enunciado del Ejercicio

Procesar documentos por una cadena de revisores y controlar sus transiciones entre borrador, revisión, aprobado y rechazado.

**Código implementado:**

`Ejercicio07.java`

Contiene `DocumentState`, estados concretos, `Document`, `DocumentHandler` y handlers de autor, líder, jurídico y financiero.

**Captura de ejecución:**

```text
AutorHandler aprueba Contrato proveedor
LiderHandler aprueba Contrato proveedor
JuridicoHandler aprueba Contrato proveedor
FinancieroHandler aprueba Contrato proveedor
Contrato proveedor queda en estado APROBADO
AutorHandler aprueba Compra sin presupuesto
LiderHandler aprueba Compra sin presupuesto
JuridicoHandler aprueba Compra sin presupuesto
FinancieroHandler rechaza Compra sin presupuesto
Compra sin presupuesto queda en estado RECHAZADO
```

**Explicación:**

`Chain of Responsibility` organiza las etapas de revisión. `State` controla cómo cambia el documento cuando la cadena aprueba o rechaza.

### Ejercicio 08 — Sistema de Pedidos en Restaurante

Enunciado del Ejercicio

Construir un pedido personalizado y notificar a cocina, facturación y domicilio cuando se confirme.

**Código implementado:**

`Ejercicio08.java`

Contiene `Order`, `OrderBuilder`, `OrderObserver`, `KitchenService`, `BillingService` y `DeliveryService`.

**Captura de ejecución:**

```text
Pedido confirmado
Cocina prepara: Hamburguesa Grande con Doble carne, toppings [Queso, Lechuga] y acompanamientos [Papas, Gaseosa]
Facturacion genera cuenta del pedido
Domicilio prepara ruta de entrega
```

**Explicación:**

`Builder` valida y construye el pedido paso a paso. `Observer` desacopla las reacciones posteriores a la confirmación del pedido.

### Ejercicio 09 — Sistema de Autenticación Empresarial

Enunciado del Ejercicio

Autenticar usuarios con estrategias intercambiables y luego aplicar una cadena de validaciones de autorización.

**Código implementado:**

`Ejercicio09.java`

Contiene `AuthStrategy`, estrategias de password, Google, Microsoft, token y biometría, `AuthService`, `Validator` y validadores concretos.

**Captura de ejecución:**

```text
Autenticacion por password: true
CredentialValidator aprobado
PermissionValidator aprobado
LocationValidator aprobado
TimeValidator aprobado
Acceso concedido a ana
Autenticacion por token: true
CredentialValidator aprobado
PermissionValidator aprobado
LocationValidator detuvo el acceso
```

**Explicación:**

`Strategy` decide el mecanismo de autenticación. `Chain of Responsibility` valida credenciales, permisos, ubicación y horario después de autenticar.

### Ejercicio 10 — Aplicación de Edición de Imágenes

Enunciado del Ejercicio

Aplicar filtros acumulativos a una imagen y permitir deshacer una acción de filtro individual.

**Código implementado:**

`Ejercicio10.java`

Contiene `Image`, `BaseImage`, decoradores de filtros, `ImageCommand`, `ApplyFilterCommand`, `ImageEditor` y `CommandHistory`.

**Captura de ejecución:**

```text
Imagen base foto-producto.png
Filtro aplicado: Blanco y negro
Filtro aplicado: Brillo
Filtro aplicado: Sepia
Resultado acumulado: Imagen base foto-producto.png + filtro blanco y negro + ajuste de brillo + filtro sepia
Undo filtro: Brillo
Despues de undo individual: Imagen base foto-producto.png + filtro blanco y negro + filtro sepia
```

**Explicación:**

`Decorator` representa filtros acumulativos sobre la imagen base. `Command` encapsula aplicar y deshacer un filtro; el historial permite revertir una acción específica.
