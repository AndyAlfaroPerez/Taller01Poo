package Taller;

import java.io.*;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner scan = new Scanner(System.in);

		// Datos de los alumnos
		String[] nombres = new String[100];
		String[] apellidos = new String[100];
		String[] ruts = new String[100];
		String[] paralelos = new String[100];
		int cantidadAlumnos = 0;

		// Miembros admitidos al grupo
		String[] miembroNombres = new String[100];
		String[] miembroApellidos = new String[100];
		String[] miembroRuts = new String[100];
		String[] miembroParalelos = new String[100];
		int cantidadMiembros = 0;

		// Solicitudes rechazadas
		String[] rechazadoNombres = new String[100];
		String[] rechazadoApellidos = new String[100];
		String[] rechazadoRuts = new String[100];
		int cantidadRechazados = 0;

		// Solicitudes de ingreso
		String[] solicitudNombres = new String[100];
		String[] solicitudApellidos = new String[100];
		int cantidadSolicitudes = 0;

		boolean archivosCargados = false;
		int intentosManuales = 0;
		int opcion = 0;
		boolean valido = false;

		// Menu principal
		do {

			valido = false;

			while (!valido) {

				System.out.println("\n===== Sistema de Control del Grupo POO =====");
				System.out.println("1) Cargar archivos (Alumnos y Solicitudes)");
				System.out.println("2) Procesar solicitudes (Filtrado automatico)");
				System.out.println("3) Inscripcion manual al grupo");
				System.out.println("4) Administracion del curso");
				System.out.println("5) Generar reportes");
				System.out.println("6) Analisis estadistico");
				System.out.println("7) Salir");
				System.out.print("Ingrese opcion: ");

				// Validar opcion ingresada
				try {

					opcion = Integer.valueOf(scan.nextLine());

					if (opcion >= 1 && opcion <= 7) {
						valido = true;
					} else {
						System.out.println("Error: opcion fuera de rango.");
					}

				} catch (Exception e) {
					System.out.println("Error: debe ingresar un numero.");
				}
			}

			// Aqui agregaremos las opciones del sistema
			// Cargar archivos
			if (opcion == 1) {

				cantidadAlumnos = 0;
				cantidadSolicitudes = 0;

				try {

					// Lectura de alumnos
					Scanner leerAlumnos = new Scanner(new File("Alumnos.txt"));

					while (leerAlumnos.hasNextLine() && cantidadAlumnos < 100) {

						String linea = leerAlumnos.nextLine();
						String[] partes = linea.split(";");

						if (partes.length == 4) {

							nombres[cantidadAlumnos] = partes[0];
							apellidos[cantidadAlumnos] = partes[1];
							ruts[cantidadAlumnos] = partes[2];
							paralelos[cantidadAlumnos] = partes[3];

							cantidadAlumnos++;
						}
					}

					leerAlumnos.close();

					// Lectura de solicitudes
					Scanner leerSolicitudes = new Scanner(new File("Solicitudes.txt"));

					while (leerSolicitudes.hasNextLine() && cantidadSolicitudes < 100) {

						String linea = leerSolicitudes.nextLine();
						String[] partes = linea.split("-");

						if (partes.length == 2) {

							solicitudNombres[cantidadSolicitudes] = partes[0];
							solicitudApellidos[cantidadSolicitudes] = partes[1];

							cantidadSolicitudes++;
						}
					}

					leerSolicitudes.close();

					archivosCargados = true;

					System.out.println("\nArchivos cargados con exito!");
					System.out.println("- " + cantidadAlumnos + " alumnos en la lista.");
					System.out.println("- " + cantidadSolicitudes + " solicitudes de ingreso.");

				} catch (Exception e) {

					archivosCargados = false;
					System.out.println("Error: no se pudieron cargar los archivos.");
				}
			}
			// Procesar solicitudes
			if (opcion == 2) {

				if (!archivosCargados) {

					System.out.println("Error: primero debe cargar los archivos.");

				} else {

					System.out.println("\nProcesando solicitudes...\n");

					for (int i = 0; i < cantidadSolicitudes; i++) {

						int encontrado = -1;

						// Buscar solicitud en la lista de alumnos
						for (int j = 0; j < cantidadAlumnos; j++) {

							if (solicitudNombres[i].equalsIgnoreCase(nombres[j])
									&& solicitudApellidos[i].equalsIgnoreCase(apellidos[j])) {

								encontrado = j;
							}
						}

						if (encontrado != -1) {

							boolean repetido = false;

							// Revisar si ya fue admitido
							for (int j = 0; j < cantidadMiembros; j++) {

								if (ruts[encontrado].equalsIgnoreCase(miembroRuts[j])) {
									repetido = true;
								}
							}

							if (!repetido) {

								miembroNombres[cantidadMiembros] = nombres[encontrado];
								miembroApellidos[cantidadMiembros] = apellidos[encontrado];
								miembroRuts[cantidadMiembros] = ruts[encontrado];
								miembroParalelos[cantidadMiembros] = paralelos[encontrado];

								cantidadMiembros++;

								System.out.println("[OK] " + nombres[encontrado] + " " + apellidos[encontrado]
										+ " -> admitido en " + paralelos[encontrado]);

							} else {

								System.out.println("[DUPLICADO] " + solicitudNombres[i] + " " + solicitudApellidos[i]
										+ " -> ya estaba admitido");
							}

						} else {

							rechazadoNombres[cantidadRechazados] = solicitudNombres[i];
							rechazadoApellidos[cantidadRechazados] = solicitudApellidos[i];
							rechazadoRuts[cantidadRechazados] = "";

							cantidadRechazados++;

							System.out.println("[RECHAZO] " + solicitudNombres[i] + " " + solicitudApellidos[i]
									+ " -> no pertenece a ningun paralelo");
						}
					}

					System.out.println(
							"\nResumen: " + cantidadMiembros + " admitidos / " + cantidadRechazados + " rechazados.");
				}
			} // Inscripcion manual
			if (opcion == 3) {

				if (!archivosCargados) {

					System.out.println("Error: primero debe cargar los archivos.");

				} else {

					int opcionManual = 0;
					boolean opcionManualValida = false;

					while (!opcionManualValida) {

						System.out.println("\n--- Inscripcion manual ---");
						System.out.println("1) Por nombre completo");
						System.out.println("2) Por RUT");
						System.out.println("3) Volver");
						System.out.print("Ingrese opcion: ");

						try {

							opcionManual = Integer.valueOf(scan.nextLine());

							if (opcionManual >= 1 && opcionManual <= 3) {
								opcionManualValida = true;
							} else {
								System.out.println("Error: opcion fuera de rango.");
							}

						} catch (Exception e) {
							System.out.println("Error: debe ingresar un numero.");
						}
					}

					// Inscripcion por nombre
					if (opcionManual == 1) {

						System.out.print("Ingrese nombre: ");
						String nombreBuscado = scan.nextLine();

						System.out.print("Ingrese apellido: ");
						String apellidoBuscado = scan.nextLine();

						if (nombreBuscado.equals("") || apellidoBuscado.equals("")) {

							System.out.println("Error: los campos no pueden estar vacios.");

						} else {
							intentosManuales++;
							int encontrado = -1;

							// Buscar alumno por nombre y apellido
							for (int i = 0; i < cantidadAlumnos; i++) {

								if (nombreBuscado.equalsIgnoreCase(nombres[i])
										&& apellidoBuscado.equalsIgnoreCase(apellidos[i])) {

									encontrado = i;
								}
							}

							if (encontrado != -1) {

								boolean repetido = false;

								// Revisar si ya pertenece al grupo
								for (int i = 0; i < cantidadMiembros; i++) {

									if (ruts[encontrado].equalsIgnoreCase(miembroRuts[i])) {
										repetido = true;
									}
								}

								if (!repetido) {

									miembroNombres[cantidadMiembros] = nombres[encontrado];
									miembroApellidos[cantidadMiembros] = apellidos[encontrado];
									miembroRuts[cantidadMiembros] = ruts[encontrado];
									miembroParalelos[cantidadMiembros] = paralelos[encontrado];

									cantidadMiembros++;

									System.out.println("Alumno inscrito correctamente al grupo.");

								} else {

									System.out.println("El alumno ya pertenece al grupo.");
								}

							} else {

								// Guardar persona rechazada
								if (cantidadRechazados < 100) {

									rechazadoNombres[cantidadRechazados] = nombreBuscado;
									rechazadoApellidos[cantidadRechazados] = apellidoBuscado;
									rechazadoRuts[cantidadRechazados] = "";

									cantidadRechazados++;

									System.out.println("La persona no pertenece a ningun paralelo del curso.");

								} else {

									System.out.println("Error: no hay espacio para mas rechazados.");
								}
							}
						}
					} // Inscripcion por RUT
					if (opcionManual == 2) {

						System.out.print("Ingrese RUT: ");
						String rutBuscado = scan.nextLine();

						if (rutBuscado.equals("")) {

							System.out.println("Error: el RUT no puede estar vacio.");

						} else {

							int encontrado = -1;

							// Buscar alumno por RUT

							for (int i = 0; i < cantidadAlumnos; i++) {

								if (rutBuscado.equalsIgnoreCase(ruts[i])) {

									encontrado = i;

								}

							}

							if (encontrado != -1) {

								boolean repetido = false;

								// Revisar si ya pertenece al grupo

								for (int i = 0; i < cantidadMiembros; i++) {

									if (ruts[encontrado].equalsIgnoreCase(miembroRuts[i])) {

										repetido = true;

									}

								}

								if (!repetido) {

									miembroNombres[cantidadMiembros] = nombres[encontrado];

									miembroApellidos[cantidadMiembros] = apellidos[encontrado];

									miembroRuts[cantidadMiembros] = ruts[encontrado];

									miembroParalelos[cantidadMiembros] = paralelos[encontrado];

									cantidadMiembros++;

									System.out.println("Alumno inscrito correctamente al grupo.");

								} else {

									System.out.println("El alumno ya pertenece al grupo.");

								}

							} else {

								// Rechazado del que solo conocemos el RUT

								if (cantidadRechazados < 100) {

									rechazadoNombres[cantidadRechazados] = "";

									rechazadoApellidos[cantidadRechazados] = "";

									rechazadoRuts[cantidadRechazados] = rutBuscado;

									cantidadRechazados++;

									System.out.println(
											"El RUT " + rutBuscado + " no pertenece a ningun paralelo del curso.");

									System.out.println("Se registrara solo el RUT en los rechazados.");

								} else {

									System.out.println("Error: no hay espacio para mas rechazados.");

								}

							}

						}

					}

				}

			} // Administracion del curso

			if (opcion == 4) {

				if (!archivosCargados) {

					System.out.println("Error: primero debe cargar los archivos.");

				} else {

					int opcionAdmin = 0;

					do {

						boolean opcionAdminValida = false;

						while (!opcionAdminValida) {

							System.out.println("\n--- Administracion del curso ---");

							System.out.println("1) Cambiar paralelo de un alumno");

							System.out.println("2) Eliminar alumno del curso");

							System.out.println("3) Inscribir alumno nuevo");

							System.out.println("4) Volver");

							System.out.print("Ingrese opcion: ");

							try {

								opcionAdmin = Integer.valueOf(scan.nextLine());

								if (opcionAdmin >= 1 && opcionAdmin <= 4) {

									opcionAdminValida = true;

								} else {

									System.out.println("Error: opcion fuera de rango.");

								}

							} catch (Exception e) {

								System.out.println("Error: debe ingresar un numero.");

							}

						}

						// Cambiar paralelo

						if (opcionAdmin == 1) {

							System.out.print("Ingrese RUT del alumno: ");

							String rutBuscado = scan.nextLine();

							int encontrado = -1;

							for (int i = 0; i < cantidadAlumnos; i++) {

								if (rutBuscado.equalsIgnoreCase(ruts[i])) {

									encontrado = i;

								}

							}

							if (encontrado == -1) {

								System.out.println("Error: el alumno no existe.");

							} else {

								System.out.println("Alumno: " + nombres[encontrado] + " " + apellidos[encontrado]);

								System.out.println("Paralelo actual: " + paralelos[encontrado]);

								String nuevoParalelo = "";

								boolean paraleloValido = false;

								while (!paraleloValido) {

									System.out.print("Nuevo paralelo (C1/C2): ");

									nuevoParalelo = scan.nextLine();

									if (nuevoParalelo.equalsIgnoreCase("C1") || nuevoParalelo.equalsIgnoreCase("C2")) {

										paraleloValido = true;

									} else {

										System.out.println("Error: el paralelo debe ser C1 o C2.");

									}

								}

								paralelos[encontrado] = nuevoParalelo.toUpperCase();

								// Actualizar paralelo si ya pertenece al grupo

								for (int i = 0; i < cantidadMiembros; i++) {

									if (ruts[encontrado].equalsIgnoreCase(miembroRuts[i])) {

										miembroParalelos[i] = nuevoParalelo.toUpperCase();

									}

								}

								// Reescribir lista de alumnos

								BufferedWriter escribir = new BufferedWriter(new FileWriter("Alumnos.txt"));

								for (int i = 0; i < cantidadAlumnos; i++) {

									escribir.write(nombres[i] + ";" + apellidos[i] + ";" + ruts[i] + ";" + paralelos[i]);

									escribir.newLine();

								}

								escribir.close();

								System.out.println("Paralelo actualizado!");

								System.out.println("Cambios guardados en Alumnos.txt");

							}

						} // Eliminar alumno

						if (opcionAdmin == 2) {

							System.out.print("Ingrese RUT del alumno: ");

							String rutBuscado = scan.nextLine();

							int encontrado = -1;

							// Buscar alumno por RUT

							for (int i = 0; i < cantidadAlumnos; i++) {

								if (rutBuscado.equalsIgnoreCase(ruts[i])) {

									encontrado = i;

								}

							}

							if (encontrado == -1) {

								System.out.println("Error: el alumno no existe.");

							} else {

								// Eliminar alumno de los vectores

								for (int i = encontrado; i < cantidadAlumnos - 1; i++) {

									nombres[i] = nombres[i + 1];

									apellidos[i] = apellidos[i + 1];

									ruts[i] = ruts[i + 1];

									paralelos[i] = paralelos[i + 1];

								}

								cantidadAlumnos--;

								// Eliminarlo del grupo si ya era miembro

								int encontradoMiembro = -1;

								for (int i = 0; i < cantidadMiembros; i++) {

									if (rutBuscado.equalsIgnoreCase(miembroRuts[i])) {

										encontradoMiembro = i;

									}

								}

								if (encontradoMiembro != -1) {

									for (int i = encontradoMiembro; i < cantidadMiembros - 1; i++) {

										miembroNombres[i] = miembroNombres[i + 1];

										miembroApellidos[i] = miembroApellidos[i + 1];

										miembroRuts[i] = miembroRuts[i + 1];

										miembroParalelos[i] = miembroParalelos[i + 1];

									}

									cantidadMiembros--;

								}

								// Guardar cambios en Alumnos.txt

								FileWriter fw = new FileWriter("Alumnos.txt");

								BufferedWriter bw = new BufferedWriter(fw);

								for (int i = 0; i < cantidadAlumnos; i++) {

									bw.write(nombres[i] + ";" + apellidos[i] + ";" + ruts[i] + ";" + paralelos[i] + "\n");

								}

								bw.close();

								System.out.println("Alumno eliminado correctamente.");

								System.out.println("Cambios guardados en Alumnos.txt");

							}

						} // Inscribir alumno nuevo

						if (opcionAdmin == 3) {

							if (cantidadAlumnos >= 100) {

								System.out.println("Error: no hay espacio para mas alumnos.");

							} else {

								System.out.print("Ingrese nombre: ");

								String nuevoNombre = scan.nextLine();

								System.out.print("Ingrese apellido: ");

								String nuevoApellido = scan.nextLine();

								System.out.print("Ingrese RUT: ");

								String nuevoRut = scan.nextLine();

								System.out.print("Ingrese paralelo (C1/C2): ");

								String nuevoParalelo = scan.nextLine();

								boolean datosValidos = true;

								// Validar campos vacios

								if (nuevoNombre.equals("") || nuevoApellido.equals("") || nuevoRut.equals("")) {

									datosValidos = false;

									System.out.println("Error: los campos no pueden estar vacios.");

								}

								// Validar paralelo

								if (!nuevoParalelo.equalsIgnoreCase("C1") && !nuevoParalelo.equalsIgnoreCase("C2")) {

									datosValidos = false;

									System.out.println("Error: el paralelo debe ser C1 o C2.");

								}

								// Revisar que el RUT no este repetido

								boolean rutRepetido = false;

								for (int i = 0; i < cantidadAlumnos; i++) {

									if (nuevoRut.equalsIgnoreCase(ruts[i])) {

										rutRepetido = true;

									}

								}

								if (rutRepetido) {

									datosValidos = false;

									System.out.println("Error: ya existe un alumno con ese RUT.");

								}

								if (datosValidos) {

									nombres[cantidadAlumnos] = nuevoNombre;

									apellidos[cantidadAlumnos] = nuevoApellido;

									ruts[cantidadAlumnos] = nuevoRut;

									paralelos[cantidadAlumnos] = nuevoParalelo.toUpperCase();

									cantidadAlumnos++;

									// Guardar cambios en Alumnos.txt

									FileWriter fw = new FileWriter("Alumnos.txt");

									BufferedWriter bw = new BufferedWriter(fw);

									for (int i = 0; i < cantidadAlumnos; i++) {

										bw.write(nombres[i] + ";" + apellidos[i] + ";" + ruts[i] + ";" + paralelos[i] + "\n");

									}

									bw.close();

									System.out.println("Alumno inscrito correctamente.");

									System.out.println("Cambios guardados en Alumnos.txt");

								}

							}

						}

					} while (opcionAdmin != 4);

				}

			} 
		} while (opcion != 7);

		System.out.println("Saliendo del programa...");
		scan.close();
	}
}
