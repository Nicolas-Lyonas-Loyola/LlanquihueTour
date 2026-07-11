# Llanquihue Tour App

Aplicación desarrollada en Java para gestionar información relacionada con la agencia turística Llanquihue Tour.

El proyecto integra una aplicación de consola y una interfaz gráfica básica. El sistema permite cargar colaboradores desde un archivo CSV, buscar y filtrar sus registros, además de registrar guías turísticos y vehículos mediante una GUI construida con `JOptionPane`.

La aplicación aplica Programación Orientada a Objetos mediante encapsulamiento, composición, herencia, polimorfismo, interfaces, colecciones genéricas, validación de tipos y manejo de excepciones.

## Objetivo

Desarrollar una aplicación modular que permita gestionar diferentes entidades de la agencia Llanquihue Tour, reutilizando las funcionalidades de las entregas anteriores e incorporando los contenidos de la Sumativa 3:

- Herencia.
- Polimorfismo.
- Interfaces.
- Colecciones genéricas.
- Validación de tipos mediante `instanceof`.
- Interfaz gráfica básica.
- Validación y manejo de datos incorrectos.

## Tecnologías utilizadas

- Java.
- JDK 17 o superior.
- IntelliJ IDEA.
- Java Swing.
- `JOptionPane`.
- Git.
- GitHub.
- Archivo CSV.

## Estructura del proyecto

```text
LlanquihueTour
├── resources
│   └── colaboradores.csv
│
├── src
│   ├── model
│   │   ├── Direccion.java
│   │   ├── Persona.java
│   │   ├── ColaboradorTuristico.java
│   │   ├── GuiaTuristico.java
│   │   ├── Vehiculo.java
│   │   ├── ColaboradorExterno.java
│   │   └── Registrable.java
│   │
│   ├── data
│   │   ├── GestorColaboradores.java
│   │   └── GestorEntidades.java
│   │
│   ├── ui
│   │   ├── Main.java
│   │   └── InterfazEntidades.java
│   │
│   └── util
│       └── ValidadorDatos.java
│
└── README.md
```

## Paquetes y responsabilidades

### `model`

Contiene las clases e interfaces que representan las entidades principales del sistema.

- `Direccion`: almacena calle, número, comuna y región.
- `Persona`: contiene los datos personales comunes, como nombre, RUT, correo, teléfono y dirección.
- `ColaboradorTuristico`: representa a las personas que colaboran con la agencia.
- `GuiaTuristico`: especialización de `ColaboradorTuristico`. Agrega el idioma que domina e implementa `Registrable`.
- `ColaboradorExterno`: especialización de `ColaboradorTuristico`. Agrega el servicio prestado e implementa `Registrable`.
- `Vehiculo`: representa un vehículo utilizado por la agencia e implementa `Registrable`.
- `Registrable`: interfaz que define el método común `mostrarResumen()`.

### `data`

Contiene las clases encargadas de gestionar los registros y colecciones.

- `GestorColaboradores`: lee el archivo CSV, crea objetos, almacena colaboradores en una colección y permite realizar búsquedas y filtros.
- `GestorEntidades`: administra una colección `ArrayList<Registrable>`, agrega entidades y recorre los objetos aplicando polimorfismo e `instanceof`.

### `ui`

Contiene la interacción con el usuario.

- `Main`: punto de entrada principal. Carga los datos del CSV, muestra el menú de consola y permite abrir la interfaz gráfica.
- `InterfazEntidades`: GUI construida con `JOptionPane`. Permite registrar guías turísticos, registrar vehículos y mostrar las entidades creadas.

### `util`

Contiene herramientas reutilizables de validación.

- `ValidadorDatos`: valida la cantidad de campos, campos vacíos, números de dirección y valores booleanos provenientes del archivo CSV.

## Jerarquía de clases

El proyecto utiliza herencia para reutilizar los datos y comportamientos comunes:

```text
Persona
└── ColaboradorTuristico
    ├── GuiaTuristico
    └── ColaboradorExterno
```

- `GuiaTuristico` es un tipo de `ColaboradorTuristico`.
- `ColaboradorExterno` es un tipo de `ColaboradorTuristico`.
- `Persona` contiene una `Direccion`, aplicando composición.

La clase `Vehiculo` no hereda de `Persona`, porque un vehículo no es una persona. Sin embargo, comparte un comportamiento común con las demás entidades mediante la interfaz `Registrable`.

## Interfaz `Registrable`

La interfaz establece el contrato común para las entidades gestionables:

```java
public interface Registrable {
    String mostrarResumen();
}
```

Las clases que implementan esta interfaz son:

- `GuiaTuristico`.
- `Vehiculo`.
- `ColaboradorExterno`.

Cada clase implementa su propia versión de `mostrarResumen()`, permitiendo aplicar polimorfismo.

## Colección polimórfica

`GestorEntidades` utiliza una colección:

```java
ArrayList<Registrable>
```

Esta colección permite almacenar objetos de distintas clases, siempre que implementen la interfaz `Registrable`.

El método `mostrarTodosLosResumenes()` recorre la colección mediante un ciclo `for-each`:

```java
for (Registrable entidad : entidades) {
    entidad.mostrarResumen();
}
```

Java ejecuta la versión correcta de `mostrarResumen()` según el tipo real de cada objeto.

También se utiliza `instanceof` para identificar si una entidad corresponde a:

- `GuiaTuristico`.
- `Vehiculo`.
- `ColaboradorExterno`.

## Archivo de datos

Los colaboradores iniciales se almacenan en:

```text
resources/colaboradores.csv
```

Cada línea utiliza el siguiente formato:

```text
nombre;rut;correo;telefono;calle;numero;comuna;region;tipo;especialidad;activo
```

Ejemplo:

```text
Camila Soto;11.111.111-1;camila.soto@llanquihuetour.cl;+56 9 1111 1111;Los Arrayanes;123;Puerto Varas;Los Lagos;Guía;Rutas culturales;true
```

## Funcionalidades de consola

El menú principal permite:

1. Mostrar todos los colaboradores.
2. Mostrar solamente los colaboradores activos.
3. Buscar colaboradores por tipo.
4. Filtrar colaboradores por comuna.
5. Mostrar la cantidad total de colaboradores.
6. Abrir la gestión gráfica de entidades.
0. Finalizar el programa.

Las búsquedas:

- Permiten coincidencias parciales mediante `contains()`.
- No distinguen entre mayúsculas y minúsculas.
- Permiten buscar palabras con o sin tilde.

Por ejemplo, las búsquedas `guía`, `guia` o `guí` pueden encontrar un colaborador cuyo tipo sea `Guía`.

## Funcionalidades de la interfaz gráfica

La GUI permite:

- Registrar un guía turístico.
- Registrar un vehículo.
- Ingresar los datos mediante cuadros de diálogo.
- Validar campos vacíos y valores numéricos.
- Mostrar un resumen de cada entidad registrada.
- Visualizar todas las entidades almacenadas.
- Identificar el tipo real de cada objeto mediante `instanceof`.

Los registros creados desde la GUI se mantienen mientras el programa continúa ejecutándose. Al cerrar completamente la aplicación, la colección vuelve a comenzar vacía, ya que la actividad no requiere persistencia permanente.

## Validaciones implementadas

El sistema incluye validaciones en los setters y constructores.

Entre ellas:

- Nombre no vacío.
- RUT no vacío.
- Correo con formato básico válido.
- Teléfono no vacío.
- Dirección no nula.
- Número de dirección mayor que cero.
- Tipo de colaborador no vacío.
- Especialidad no vacía.
- Idioma no vacío.
- Patente no vacía.
- Capacidad de pasajeros mayor que cero.
- Servicio prestado no vacío.

Los constructores reutilizan los setters para asegurar que los objetos sean válidos desde su creación.

Durante la lectura del CSV, cada registro se procesa dentro de un bloque `try-catch`. Si una línea contiene datos incorrectos, se informa:

- Número de la línea.
- Motivo del error.
- Contenido completo de la línea.

El programa omite solamente el registro incorrecto y continúa cargando los demás.

## Conceptos de Programación Orientada a Objetos

El proyecto aplica:

- Clases y objetos.
- Encapsulamiento con atributos `private`.
- Constructores.
- Getters y setters.
- Validaciones dentro de los setters.
- Composición.
- Herencia.
- Uso de `extends`.
- Uso de `super(...)`.
- Interfaces.
- Uso de `implements`.
- Sobrescritura con `@Override`.
- Polimorfismo.
- Colecciones genéricas.
- `List<ColaboradorTuristico>`.
- `ArrayList<Registrable>`.
- Recorrido mediante `for-each`.
- Validación de tipos con `instanceof`.
- Método `toString()`.
- Método `mostrarResumen()`.
- Manejo de excepciones.
- Organización modular mediante paquetes.
- Interfaz gráfica con Java Swing.

## Mejoras aplicadas a partir del feedback

Se incorporaron las siguientes mejoras:

- Uso de `List<ColaboradorTuristico>` como tipo de la colección.
- Validaciones reutilizadas desde constructores y setters.
- Visualización del contenido de las líneas inválidas.
- Búsquedas parciales mediante `contains()`.
- Normalización de mayúsculas, minúsculas y tildes.
- Manejo individual de errores durante la carga del CSV.
- Continuidad de la ejecución aunque exista un registro inválido.

## Instrucciones de ejecución

1. Descargar o clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Configurar el proyecto con JDK 17 o superior.
4. Confirmar que el archivo `colaboradores.csv` se encuentre dentro de la carpeta `resources`.
5. Ejecutar la clase principal:

```text
src/ui/Main.java
```

6. Utilizar el menú de consola.
7. Seleccionar la opción `6` para abrir la interfaz gráfica de gestión de entidades.

## Prueba recomendada

Para comprobar las funcionalidades principales:

1. Ejecutar `Main`.
2. Buscar colaboradores escribiendo `guia`.
3. Filtrar colaboradores por comuna.
4. Seleccionar la opción `6`.
5. Registrar un vehículo.
6. Registrar un guía turístico.
7. Mostrar todas las entidades registradas.
8. Verificar que aparezcan sus resúmenes y tipos correspondientes.
9. Regresar al menú principal.
10. Finalizar el programa con la opción `0`.

## Autor

**Nicolás Loyola**  
Analista Programador Computacional  
Duoc UC