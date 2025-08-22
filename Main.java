import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(" MENU PRINCIPAL");
            System.out.println("1 Sistema de gestion de contactos");
            System.out.println("2 Sistema Pila y cola");
            System.out.println("3 Salir");
            System.out.print("Selecciona una opcion (1-3): ");

            try {
                int opcionPrincipal = Integer.parseInt(scanner.nextLine());

                switch (opcionPrincipal) {
                    case 1:
                        menuGestionContactos(scanner);
                        break;
                    case 2:
                        menuEstructurasDatos(scanner);
                        break;
                    case 3:
                        System.out.println("Saliendo del programa");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opcion no valida inentan otra vez ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un numero valido.");
            }
        }
    }

    private static void menuGestionContactos(Scanner scanner) {
        LinkedList lista = null;

        while (true) {
            System.out.println(" GESTION DE CONTACTOS ");
            System.out.println("1. Crear lista simple");
            System.out.println("2. Crear lista doblemente enlazada");
            System.out.println("3. Crear lista circular");
            System.out.println("4. Ver ejemplos de listas");
            System.out.println("5. Volver al menu principal");
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        lista = new LinkedList("simple");
                        menuOperacionesContactos(lista, scanner);
                        break;
                    case 2:
                        lista = new LinkedList("doble");
                        menuOperacionesContactos(lista, scanner);
                        break;
                    case 3:
                        lista = new LinkedList("circular");
                        menuOperacionesContactos(lista, scanner);
                        break;
                    case 4:
                        DataTypeExamples.mostrarEjemplos();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opcion no valida intenta otra vez ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un numero valido");
            }
        }
    }

    private static void menuOperacionesContactos(LinkedList lista, Scanner scanner) {
        while (true) {
            System.out.println("OPERACIONES CON CONTACTOS ");
            System.out.println("1. Agregar contacto");
            System.out.println("2. Eliminar contacto");
            System.out.println("3. Buscar contacto");
            System.out.println("4. Mostrar lista completa");
            System.out.println("5. Volver al menu anterior");
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("AGREGAR CONTACTO:");
                        System.out.print("Nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.print("Direccion: ");
                        String direccion = scanner.nextLine();
                        System.out.print("Telefono: ");
                        String telefono = scanner.nextLine();

                        if (nombre.isEmpty() || direccion.isEmpty() || telefono.isEmpty()) {
                            System.out.println("Error: Todos los campos deben ser llenados");
                        } else {
                            Contacto nuevoContacto = new Contacto(nombre, direccion, telefono);
                            lista.insert(nuevoContacto);
                            System.out.println("Contacto agregado correctamente");
                        }
                        break;
                    case 2:
                        System.out.println("ELIMINAR CONTACTO:");
                        System.out.print("Nombre del contacto para eliminar: ");
                        String nombreEliminar = scanner.nextLine();
                        if (lista.delete(nombreEliminar)) {
                            System.out.println("Contacto eliminado correctamente");
                        } else {
                            System.out.println("Contacto no encontrado");
                        }
                        break;
                    case 3:
                        System.out.println("BUSCAR CONTACTO:");
                        System.out.print("Nombre del contacto para buscar: ");
                        String nombreBuscar = scanner.nextLine();
                        if (!lista.search(nombreBuscar)) {
                            System.out.println("Contacto no encontrado!");
                        }
                        break;
                    case 4:
                        System.out.println("CONTACTOS:");
                        lista.display();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debe ingresar un numero valido");
            }
        }
    }

    private static void menuEstructurasDatos(Scanner scanner) {
        Pila pila = new Pila();
        Cola cola = new Cola();

        while (true) {
            System.out.println(" PILAS Y COLAS ");
            System.out.println("1. Operaciones con Pila");
            System.out.println("2. Operaciones con Cola");
            System.out.println("3. Volver al menu principal");
            System.out.print("Seleccione una opcion (1-3): ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        menuPila(pila, scanner);
                        break;
                    case 2:
                        menuCola(cola, scanner);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Opcion no valida. intenta otra vez ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error debes ingresar un numero valido");
            }
        }
    }

    private static void menuPila(Pila pila, Scanner scanner) {
        while (true) {
            System.out.println(" OPERACIONES CON PILA ");
            System.out.println("1. Push (Agregar elemento)");
            System.out.println("2. Pop (Remover elemento)");
            System.out.println("3. Peek (Ver cima)");
            System.out.println("4. Mostrar pila");
            System.out.println("5. Mostrar historial de comandos");
            System.out.println("6. Deshacer ultimo comando");
            System.out.println("7. Volver al menu anterior");
            System.out.print("Seleccione una opcion (1-7): ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingresa el dato (no vacio): ");
                        String datoPila = scanner.nextLine().trim();
                        if (!datoPila.isEmpty()) {
                            pila.push(datoPila);
                        } else {
                            System.out.println("Error el dato no puede estar vacio.");
                        }
                        break;
                    case 2:
                        pila.pop();
                        break;
                    case 3:
                        pila.peek();
                        break;
                    case 4:
                        pila.mostrarVerticalConLineas();
                        break;
                    case 5:
                        pila.mostrarHistorial();
                        break;
                    case 6:
                        pila.deshacerUltimoComando();
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Opcion no valida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes ingresar un numero valido.");
            }
        }
    }

    private static void menuCola(Cola cola, Scanner scanner) {
        while (true) {
            System.out.println(" OPERACIONES CON COLA ");
            System.out.println("1. Push (Agregar elemento)");
            System.out.println("2. Pop (Remover elemento)");
            System.out.println("3. Peek (Ver frente)");
            System.out.println("4. Mostrar cola");
            System.out.println("5. Mostrar historial de comandos");
            System.out.println("6. Deshacer ultimo comando");
            System.out.println("7. Volver al menu anterior");
            System.out.print("Seleccione una opcion (1-7): ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.print("Ingresa el dato (no vacio): ");
                        String datoCola = scanner.nextLine().trim();
                        if (!datoCola.isEmpty()) {
                            cola.push(datoCola);
                        } else {
                            System.out.println("Error el dato no puede estar vacio.");
                        }
                        break;
                    case 2:
                        cola.pop();
                        break;
                    case 3:
                        cola.peek();
                        break;
                    case 4:
                        cola.mostrarFormatoHorizontal();
                        break;
                    case 5:
                        cola.mostrarHistorial();
                        break;
                    case 6:
                        cola.deshacerUltimoComando();
                        break;
                    case 7:
                        return;
                    default:
                        System.out.println("Opcion no valida tenta otra vez ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error Debes ingresar un numero valido");
            }
        }
    }
}