# Llanquihue Tour App

Aplicación de consola desarrollada en Java para gestionar información de las personas que colaboran con la agencia turística Llanquihue Tour.

El sistema lee los datos desde un archivo externo, los transforma en objetos Java y los almacena en una colección dinámica. Posteriormente permite recorrer, buscar y filtrar los registros mediante un menú de consola.

## Objetivo

Desarrollar una aplicación modular que aplique Programación Orientada a Objetos, lectura de archivos, colecciones y separación de responsabilidades.

## Tecnologías utilizadas

* Java
* JDK 17
* IntelliJ IDEA
* Git
* GitHub
* Archivo CSV

## Estructura del proyecto

```text
LlanquihueTour
├── resources
│   └── colaboradores.csv
│
├── src
│   ├── app
│   │   └── Main.java
│   │
│   ├── model
│   │   ├── Direccion.java
│   │   ├── Persona.java
│   │   └── ColaboradorTuristico.java
│   │
│   ├── service
│   │   └── GestorColaboradores.java
│   │
│   └── util
│       └── ValidadorDatos.java
│
└── README.md
```

## Paquetes y responsabilidades

### `model`

Contiene las clases que representan los datos principales del sistema.

* `Direccion`: almacena calle, número, comuna y región.
* `Persona`: contiene los datos personales comunes de una persona.
* `ColaboradorTuristico`: representa a un guía, operador o proveedor de la agencia.

### `service`

Contiene la lógica principal de gestión.

* `GestorColaboradores`: lee el archivo CSV, crea los objetos, los guarda en un `ArrayList` y permite realizar búsquedas y filtros.

### `util`

Contiene herramientas reutilizables.

* `ValidadorDatos`: valida la cantidad de campos, campos vacíos, números de dirección y estados booleanos.

### `app`

Contiene la clase principal.

* `Main`: inicia el programa, muestra el menú y coordina las operaciones del sistema.

## Archivo de datos

Los colaboradores se almacenan en:

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

## Funcionalidades

El programa permite:

1. Cargar colaboradores desde un archivo CSV.
2. Mostrar todos los colaboradores.
3. Mostrar solamente los colaboradores activos.
4. Buscar colaboradores según su tipo.
5. Filtrar colaboradores según su comuna.
6. Mostrar la cantidad total de registros cargados.
7. Validar datos incorrectos mediante `try-catch`.
8. Mostrar mensajes claros por consola.

## Conceptos aplicados

* Encapsulamiento mediante atributos `private`.
* Constructores.
* Getters y setters.
* Método `toString()`.
* Herencia entre `Persona` y `ColaboradorTuristico`.
* Composición entre `Persona` y `Direccion`.
* Organización modular mediante paquetes.
* Lectura de archivos con `BufferedReader`.
* Separación de datos con `split(";")`.
* Colección `ArrayList`.
* Recorrido con `for-each`.
* Búsqueda y filtrado.
* Validaciones y manejo de excepciones.

## Instrucciones de ejecución

1. Descargar o clonar el repositorio.
2. Abrir el proyecto en IntelliJ IDEA.
3. Verificar que el proyecto utilice JDK 17.
4. Confirmar que el archivo `colaboradores.csv` esté dentro de la carpeta `resources`.
5. Ejecutar la clase:

```text
src/app/Main.java
```

6. Seleccionar las opciones disponibles en el menú de consola.

## Autor

Nicolás Loyola
Analista Programador Computacional
Duoc UC
