# Sistema de Control de Acceso al Grupo POO

## Descripción del proyecto

Aplicación desarrollada en Java que permite gestionar el ingreso de estudiantes al grupo de WhatsApp del curso de Programación Orientada a Objetos. El sistema utiliza los archivos `Alumnos.txt` y `Solicitudes.txt` para almacenar la lista oficial de alumnos y las solicitudes de ingreso al grupo.

El programa permite cargar los archivos, procesar automáticamente las solicitudes, realizar inscripciones manuales por nombre o RUT y administrar los alumnos del curso. La administración permite cambiar el paralelo de un alumno, eliminar alumnos e inscribir nuevos estudiantes, actualizando los cambios realizados en el archivo `Alumnos.txt`.

Además, el sistema permite generar reportes separados para los integrantes de los paralelos C1 y C2, junto con un reporte de solicitudes rechazadas. Los reportes utilizan un sistema de versiones para evitar sobrescribir archivos generados anteriormente.

Finalmente, el programa incluye un análisis estadístico que muestra el total de intentos de ingreso, la cantidad y porcentaje de solicitudes rechazadas y la cantidad de miembros pertenecientes a cada paralelo.

El sistema incorpora validación de datos de entrada para evitar errores durante la ejecución, controlando opciones incorrectas en los menús, campos vacíos, alumnos o RUT inexistentes, paralelos inválidos y registros repetidos.

---

## Integrantes

Andy Alfaro Perez
RUT: 21.918.973-7
GitHub: AndyAlfaroPerez

---

## Estructura del proyecto

`Taller/Main.java`
Clase principal que contiene la lógica completa del sistema, incluyendo:

- Menú principal
- Carga de alumnos y solicitudes
- Procesamiento automático de solicitudes
- Inscripción manual por nombre o RUT
- Administración de alumnos
- Cambio de paralelo
- Eliminación e inscripción de alumnos
- Generación de reportes por paralelo
- Registro de solicitudes rechazadas
- Análisis estadístico
- Lectura de archivos mediante Scanner
- Escritura y actualización de archivos mediante FileWriter y BufferedWriter

`Alumnos.txt`
Archivo que contiene la lista oficial de alumnos en formato:
nombre;apellido;rut;paralelo

`Solicitudes.txt`
Archivo que contiene las solicitudes de ingreso al grupo en formato:
nombre-apellido

Los reportes generados por el programa utilizan los siguientes nombres:

`ReporteC1-VX.txt`
Contiene los integrantes del grupo pertenecientes al paralelo C1.

`ReporteC2-VX.txt`
Contiene los integrantes del grupo pertenecientes al paralelo C2.

`Rechazados-VX.txt`
Contiene las solicitudes de ingreso rechazadas.

La `X` corresponde al número de versión del reporte generado.

---

## Instrucciones de ejecución

Tener instalado Java (JDK) y Git.

Clonar el repositorio:

```bash
git clone https://github.com/AndyAlfaroPerez/Taller01Poo.git
```

Verificar que los archivos `Alumnos.txt` y `Solicitudes.txt` se encuentren disponibles para la ejecución del programa.

Compilar el programa:

```bash
javac Taller/Main.java
```

Ejecutar el programa:

```bash
java Taller.Main
```

Interactuar con el sistema mediante el menú en consola.

---

## Testeo

Para comprobar el funcionamiento general del sistema:

1. Cargar `Alumnos.txt` y `Solicitudes.txt` mediante la opción 1.
2. Procesar las solicitudes mediante la opción 2.
3. Probar una inscripción manual por nombre o RUT mediante la opción 3.
4. Probar las opciones de administración mediante la opción 4.
5. Generar los reportes mediante la opción 5.
6. Consultar el análisis estadístico mediante la opción 6.
7. Finalizar el programa mediante la opción 7.

También se pueden probar entradas incorrectas, como letras en los menús, opciones fuera de rango, campos vacíos, RUT inexistentes, RUT repetidos y paralelos diferentes de C1 o C2, verificando que el programa controle el error y continúe su ejecución.
