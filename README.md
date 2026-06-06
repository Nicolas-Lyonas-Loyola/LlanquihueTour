# Llanquihue Tour

## Descripción del proyecto

Este proyecto corresponde a una aplicación básica desarrollada en Java para la agencia de turismo **Llanquihue Tour**, ubicada en la Región de Los Lagos.

El objetivo del sistema es representar de forma estructurada a distintas personas vinculadas a la agencia, tales como clientes, guías turísticos y empleados. Para ello se aplican principios de Programación Orientada a Objetos, incluyendo encapsulamiento, composición, herencia, reutilización de clases y organización modular mediante paquetes.

Este proyecto corresponde a la **Actividad Sumativa de la Semana 3** de la asignatura **Desarrollo Orientado a Objetos I**.

## Objetivo

Diseñar e implementar un modelo básico de clases que permita representar personas relacionadas con la operación de una agencia de turismo, evitando duplicación de datos y separando responsabilidades entre las clases del sistema.

## Tecnologías utilizadas

- Java
- IntelliJ IDEA
- JDK 17
- Proyecto probado con JDK 17
- GitHub
  
## Estructura del proyecto

```text
LlanquihueTour
└── src
    ├── app
    │   └── Main.java
    │
    └── model
        ├── Direccion.java
        ├── Persona.java
        ├── Cliente.java
        ├── GuiaTuristico.java
        └── Empleado.java
```

## Paquetes del proyecto

### Paquete `model`

Contiene las clases que representan el dominio del problema.

* `Direccion`: representa los datos de ubicación de una persona.
* `Persona`: clase base que contiene los datos comunes de una persona vinculada a la agencia.
* `Cliente`: representa a un cliente de Llanquihue Tour.
* `GuiaTuristico`: representa a un guía turístico que trabaja con la agencia.
* `Empleado`: representa a un empleado administrativo u operativo de la agencia.

### Paquete `app`

Contiene la clase principal del sistema.

* `Main`: clase encargada de crear objetos, relacionarlos y mostrar los resultados por consola.

## Principios de Programación Orientada a Objetos aplicados

### Encapsulamiento

Todas las clases utilizan atributos privados (`private`) y métodos públicos `get` y `set` para acceder o modificar sus valores.

### Composición

La clase `Persona` contiene un objeto de tipo `Direccion`, lo que representa la relación:

```text
Una Persona tiene una Dirección.
```

En Java, esta relación se implementa mediante el atributo:

```java
private Direccion direccion;
```

### Herencia

Las clases `Cliente`, `GuiaTuristico` y `Empleado` heredan de la clase base `Persona`, reutilizando sus atributos comunes.

Relaciones aplicadas:

```text
Cliente es una Persona.
GuiaTuristico es una Persona.
Empleado es una Persona.
```

En Java, esto se implementa mediante `extends Persona`.

## Clases implementadas

### `Direccion`

Representa una dirección física mediante los atributos:

* calle
* número
* comuna
* región

### `Persona`

Representa los datos generales de una persona:

* nombre
* RUT
* correo
* teléfono
* dirección

### `Cliente`

Representa a un cliente de la agencia e incluye:

* preferencia de tour
* cantidad de reservas

### `GuiaTuristico`

Representa a un guía turístico e incluye:

* especialidad
* años de experiencia

### `Empleado`

Representa a un empleado de la agencia e incluye:

* cargo
* área de trabajo

## Ejecución del programa

Para ejecutar el proyecto:

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar que el JDK esté configurado correctamente.
3. Abrir la clase `Main.java`, ubicada en el paquete `app`.
4. Ejecutar el método:

```java
public static void main(String[] args)
```

5. Revisar la salida por consola.

## Salida esperada

El programa muestra por consola los datos de:

* un cliente
* un guía turístico
* un empleado

Cada objeto se imprime utilizando el método `toString()` correspondiente, mostrando la información de forma ordenada.

## Autor

Nicolás Loyola
