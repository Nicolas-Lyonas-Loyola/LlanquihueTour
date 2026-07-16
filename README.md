# Llanquihue Tour App

Aplicación modular desarrollada en Java para representar y gestionar distintas operaciones de la agencia turística **Llanquihue Tour**, ubicada en la Región de Los Lagos.

El sistema integra una aplicación de consola y una interfaz gráfica básica. Permite cargar información desde archivos externos `.txt`, administrar colaboradores, clientes y tours, registrar reservas, realizar búsquedas y filtros, mostrar un historial de acciones y procesar objetos de distintas clases mediante polimorfismo.

El proyecto fue desarrollado aplicando los principales conceptos de la Programación Orientada a Objetos:

- Encapsulamiento.
- Composición.
- Herencia.
- Interfaces.
- Polimorfismo.
- Sobrescritura de métodos.
- Colecciones genéricas.
- Manejo de archivos externos.
- Validaciones.
- Excepciones personalizadas.
- Organización modular mediante paquetes.

## Objetivo

Desarrollar un prototipo reutilizable y extensible que permita iniciar la digitalización de las operaciones de Llanquihue Tour.

La aplicación busca organizar la información de la agencia, reducir la duplicación de datos y demostrar la integración de los contenidos estudiados durante la asignatura Desarrollo Orientado a Objetos I.

## Tecnologías utilizadas

- Java.
- JDK 17 o superior.
- IntelliJ IDEA.
- Java Swing.
- `JOptionPane`.
- Git.
- GitHub.
- Archivos de texto `.txt`.

## Estructura del proyecto

```text
LlanquihueTour
├── resources
│   ├── colaboradores.txt
│   ├── clientes.txt
│   └── tours.txt
│
├── src
│   ├── model
│   │   ├── Direccion.java
│   │   ├── Persona.java
│   │   ├── Cliente.java
│   │   ├── ColaboradorTuristico.java
│   │   ├── GuiaTuristico.java
│   │   ├── ColaboradorExterno.java
│   │   ├── Vehiculo.java
│   │   ├── Tour.java
│   │   ├── Reserva.java
│   │   └── Registrable.java
│   │
│   ├── data
│   │   ├── GestorColaboradores.java
│   │   ├── GestorEntidades.java
│   │   └── GestorReservas.java
│   │
│   ├── ui
│   │   ├── Main.java
│   │   └── InterfazEntidades.java
│   │
│   └── util
│       ├── ValidadorDatos.java
│       └── DatoInvalidoException.java
│
└── README.md
```

## Paquetes y responsabilidades

### `model`

Contiene las clases e interfaces que representan las entidades principales del sistema.

- `Direccion`: almacena calle, número, comuna y región.
- `Persona`: clase base con nombre, RUT, correo, teléfono y dirección.
- `Cliente`: especialización de `Persona`. Agrega tipo de cliente y preferencia turística.
- `ColaboradorTuristico`: representa a las personas que prestan servicios a la agencia.
- `GuiaTuristico`: especialización de `ColaboradorTuristico`. Agrega el idioma que domina.
- `ColaboradorExterno`: especialización de `ColaboradorTuristico`. Agrega el servicio prestado.
- `Vehiculo`: representa un vehículo utilizado por la agencia.
- `Tour`: representa una experiencia turística, incluyendo código, tipo, duración, precio y estado.
- `Reserva`: relaciona un cliente con un tour y calcula el valor total de la reserva.
- `Registrable`: interfaz que define el comportamiento común `mostrarResumen()`.

### `data`

Contiene las clases responsables de cargar, almacenar y gestionar los datos.

- `GestorColaboradores`: lee `colaboradores.txt`, crea objetos y permite listar, buscar y filtrar colaboradores.
- `GestorEntidades`: administra una colección polimórfica de objetos `Registrable` utilizados desde la interfaz gráfica.
- `GestorReservas`: carga clientes y tours desde archivos `.txt`, gestiona reservas y utiliza diferentes colecciones para búsquedas e historial.

### `ui`

Contiene la interacción con el usuario.

- `Main`: punto de entrada de la aplicación. Realiza la carga inicial, muestra el menú y coordina las funciones del sistema.
- `InterfazEntidades`: interfaz gráfica básica construida con `JOptionPane`. Permite registrar guías turísticos y vehículos, además de mostrar las entidades creadas.

### `util`

Contiene herramientas reutilizables para validación y manejo de errores.

- `ValidadorDatos`: valida campos, números y valores booleanos provenientes de archivos externos.
- `DatoInvalidoException`: excepción personalizada utilizada cuando se intenta crear o modificar un objeto con datos inválidos.

## Jerarquía de clases

El proyecto utiliza herencia para reutilizar atributos y comportamientos comunes.

```text
Persona
├── Cliente
└── ColaboradorTuristico
    ├── GuiaTuristico
    └── ColaboradorExterno
```

Ejemplos:

- Un `Cliente` es una `Persona`.
- Un `GuiaTuristico` es un `ColaboradorTuristico`.
- Un `ColaboradorExterno` es un `ColaboradorTuristico`.

La clase `Vehiculo` no hereda de `Persona`, porque un vehículo no es una persona. Sin embargo, comparte un comportamiento común con otras entidades mediante la interfaz `Registrable`.

## Composición

El proyecto aplica composición para representar relaciones entre objetos.

### Persona y Dirección


private Direccion direccion;
```

Una persona contiene una dirección.

### Reserva, Cliente y Tour

```text
private Cliente cliente;
private Tour tour;
```

Una reserva contiene un cliente y un tour.

La reserva no almacena únicamente nombres o códigos aislados, sino referencias a objetos completos. Esto permite acceder a sus atributos y comportamientos sin duplicar información.

## Interfaz `Registrable`

La interfaz establece un contrato común para las entidades que pueden mostrarse de manera resumida.


public interface Registrable {
    String mostrarResumen();
}
```

Entre las clases que implementan esta interfaz se encuentran:

- `Cliente`.
- `GuiaTuristico`.
- `ColaboradorExterno`.
- `Vehiculo`.
- `Tour`.
- `Reserva`.

Cada clase sobrescribe `mostrarResumen()` y entrega un resultado diferente según sus propios atributos.

## Polimorfismo

El sistema utiliza colecciones de tipo común para almacenar objetos de distintas clases.


List<Registrable> registros = new ArrayList<>();
```

En esta colección pueden almacenarse clientes, tours y reservas, siempre que implementen `Registrable`.

Al recorrer la colección:


for (Registrable registro : registros) {
    System.out.println(registro.mostrarResumen());
}
```

Java ejecuta automáticamente la implementación correspondiente al tipo real de cada objeto.

## Uso de `instanceof`

El operador `instanceof` permite identificar el tipo real de los objetos almacenados en una colección polimórfica.


if (registro instanceof Cliente) {
    System.out.println("Tipo detectado: Cliente");
} else if (registro instanceof Tour) {
    System.out.println("Tipo detectado: Tour");
} else if (registro instanceof Reserva) {
    System.out.println("Tipo detectado: Reserva");
}
```

## Colecciones utilizadas

El sistema utiliza distintas colecciones según la función requerida.

### `ArrayList`

Se utiliza para almacenar:

- Colaboradores.
- Clientes.
- Tours.
- Reservas.
- Entidades polimórficas.

### `HashMap`

Se utiliza para asociar claves con objetos:


Map<String, Cliente> clientesPorRut;
Map<String, Tour> toursPorCodigo;
```

Esto permite buscar clientes por RUT y tours por código de forma directa.

### `Stack`

Se utiliza para almacenar el historial de acciones del sistema.


Stack<String> historialAcciones;
```

El historial se muestra desde la acción más reciente hasta la más antigua, aplicando la lógica LIFO:

```text
Last In, First Out
```

## Archivos de datos

La aplicación utiliza archivos `.txt` ubicados dentro de la carpeta `resources`.

### `colaboradores.txt`

Formato:

```text
nombre;rut;correo;telefono;calle;numero;comuna;region;tipo;especialidad;activo
```

### `clientes.txt`

Formato:

```text
nombre;rut;correo;telefono;calle;numero;comuna;region;tipoCliente;preferenciaTuristica
```

### `tours.txt`

Formato:

```text
codigo;nombre;tipo;duracionHoras;precio;activo
```

Cada línea representa un registro. Los datos se separan mediante punto y coma.

El sistema:

1. Lee cada línea del archivo.
2. Separa los campos con `split(";")`.
3. Valida los datos.
4. Crea el objeto correspondiente.
5. Almacena el objeto en una colección.
6. Continúa procesando el archivo aunque un registro individual sea inválido.

## Funcionalidades del sistema

### Gestión de colaboradores

1. Mostrar todos los colaboradores.
2. Mostrar colaboradores activos.
3. Buscar colaboradores por tipo.
4. Filtrar colaboradores por comuna.
5. Mostrar la cantidad de colaboradores.
6. Abrir la gestión gráfica de entidades.

### Gestión turística

7. Mostrar clientes.
8. Mostrar tours.
9. Buscar clientes por RUT.
10. Filtrar tours por tipo.
11. Registrar una reserva.
12. Mostrar reservas.
13. Mostrar registros polimórficos.
14. Mostrar historial de acciones.

**Opción 0:** Finalizar el programa.

## Registro de reservas

Para registrar una reserva, el sistema solicita:

- Código de reserva.
- RUT del cliente.
- Código del tour.
- Cantidad de personas.

El sistema verifica que:

- El cliente exista.
- El tour exista.
- El tour se encuentre activo.
- El código de reserva no esté repetido.
- La cantidad de personas sea mayor que cero.

El total se calcula mediante:


tour.getPrecio() * cantidadPersonas
```

Las reservas se mantienen en memoria durante la ejecución del programa.

## Búsquedas y filtros

Las búsquedas implementadas:

- Permiten coincidencias parciales.
- No distinguen entre mayúsculas y minúsculas.
- Ignoran tildes.
- Normalizan códigos y RUT antes de utilizarlos como claves.

Por ejemplo, las siguientes búsquedas pueden encontrar el mismo tipo de tour:

```text
gastronomico
Gastronómico
GASTRONOMICO
```

## Validaciones implementadas

Entre las validaciones del sistema se encuentran:

- Nombre no vacío.
- RUT no vacío.
- Correo con formato básico válido.
- Teléfono no vacío.
- Dirección no nula.
- Número de dirección mayor que cero.
- Tipo de cliente no vacío.
- Preferencia turística no vacía.
- Tipo de colaborador no vacío.
- Especialidad no vacía.
- Código de tour no vacío.
- Duración del tour mayor que cero.
- Precio del tour mayor que cero.
- Código de reserva no vacío.
- Cliente de una reserva no nulo.
- Tour de una reserva no nulo.
- Cantidad de personas mayor que cero.
- Estado de reserva no vacío.
- Valores booleanos limitados a `true` o `false`.

Los constructores reutilizan los setters para impedir que los objetos sean creados con datos inválidos.

## Manejo de excepciones

El proyecto utiliza:

- `try-catch`.
- `IllegalStateException`.
- `NumberFormatException`.
- `DatoInvalidoException`.

`DatoInvalidoException` es una excepción personalizada que hereda de `IllegalArgumentException`.


public class DatoInvalidoException
        extends IllegalArgumentException {

    public DatoInvalidoException(String mensaje) {
        super(mensaje);
    }
}
```

Durante la lectura de archivos, si una línea contiene datos incorrectos, el sistema informa:

- Número de línea.
- Contenido de la línea.
- Motivo del error.

El registro inválido se omite y la carga continúa con las demás líneas.

## Interfaz gráfica

La aplicación incluye una GUI básica mediante `JOptionPane`.

Permite:

- Registrar un guía turístico.
- Registrar un vehículo.
- Validar los datos ingresados.
- Mostrar el resumen de cada entidad.
- Visualizar las entidades almacenadas.
- Aplicar polimorfismo.
- Identificar objetos mediante `instanceof`.

Los datos creados desde la GUI permanecen disponibles mientras la aplicación sigue ejecutándose.

## Conceptos de Programación Orientada a Objetos

El proyecto evidencia:

- Clases y objetos.
- Encapsulamiento.
- Atributos `private`.
- Métodos `public`.
- Constructores.
- Getters y setters.
- Validaciones desde setters.
- Composición.
- Herencia.
- Uso de `extends`.
- Uso de `super(...)`.
- Interfaces.
- Uso de `implements`.
- Sobrescritura con `@Override`.
- Métodos `toString()`.
- Polimorfismo.
- Colecciones genéricas.
- `ArrayList`.
- `HashMap`.
- `Stack`.
- Recorridos mediante `for-each`.
- Búsquedas y filtros.
- Uso de `instanceof`.
- Lectura de archivos externos.
- Excepciones personalizadas.
- Separación de responsabilidades.
- Organización mediante paquetes.
- Interfaz gráfica con Java Swing.

## Instrucciones para clonar y ejecutar

### Clonar el repositorio

```bash
git clone https://github.com/Nicolas-Lyonas-Loyola/LlanquihueTour.git
```

### Abrir el proyecto

1. Abrir IntelliJ IDEA.
2. Seleccionar **Open**.
3. Elegir la carpeta clonada del proyecto.
4. Configurar JDK 17 o superior.
5. Confirmar que la carpeta `resources` contenga:

```text
colaboradores.txt
clientes.txt
tours.txt
```

6. Ejecutar la clase:

```text
src/ui/Main.java
```

7. Utilizar el menú de consola para probar las funcionalidades.

## Prueba recomendada

Para verificar el funcionamiento del sistema:

1. Ejecutar `Main`.
2. Confirmar la carga de 5 clientes y 6 tours.
3. Seleccionar la opción `7` para listar clientes.
4. Seleccionar la opción `8` para listar tours.
5. Buscar el cliente con RUT `11111111-1`.
6. Filtrar los tours escribiendo `cultural`.
7. Registrar una reserva con los siguientes datos:

```text
Código: R001
RUT: 11111111-1
Tour: T001
Personas: 2
```

8. Mostrar las reservas.
9. Mostrar los registros polimórficos.
10. Revisar los tipos detectados mediante `instanceof`.
11. Mostrar el historial de acciones.
12. Abrir la interfaz gráfica mediante la opción `6`.
13. Finalizar el programa con la opción `0`.

## Pruebas de validación realizadas

Se comprobó el comportamiento del sistema ante:

- Código de reserva repetido.
- Cliente inexistente.
- Tour inexistente.
- Tour inactivo.
- Cantidad de personas igual o menor que cero.
- Opciones de menú inválidas.
- Datos numéricos incorrectos.

El programa controla estos errores y continúa ejecutándose sin finalizar de manera inesperada.

## Autor

**Nicolás Alberto Loyola Guevara**  
Analista Programador Computacional  
Duoc UC