
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList lista = null;

        while (true) {
            System.out.println("Menu principal");
            System.out.println("1 Crear lista simple");
            System.out.println("2 Crear lista doblemente enlazada");
            System.out.println("3 Crear lista circular");
            System.out.println("4 Ver ejemplos de listas");
            System.out.println("5 Salir");
            System.out.print("Seleccione una opcion: ");

            try {
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        lista = new LinkedList("simple");
                        menuOperaciones(lista, scanner);
                        break;
                    case 2:
                        lista = new LinkedList("doble");
                        menuOperaciones(lista, scanner);
                        break;
                    case 3:
                        lista = new LinkedList("circular");
                        menuOperaciones(lista, scanner);
                        break;
                    case 4:
                        DataTypeExamples.mostrarEjemplos();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Opcin no válida intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error debes ingresar un nnmero entre 1 y 5.");
            }
        }
    }

    private static void menuOperaciones(LinkedList lista, Scanner scanner) {
        while (true) {
            System.out.println("OPERACIONES");
            System.out.println("1 Agregar contacto");
            System.out.println("2 Eliminar contacto");
            System.out.println("3 Buscar contacto");
            System.out.println("4 Mostrar lista completa");
            System.out.println("5 Regresar al menú principal");
            System.out.print("Seleccione: ");

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
                            System.out.println("Error: todos los espacios tienen que ser llenados");
                        } else {
                            Contacto nuevoContacto = new Contacto(nombre, direccion, telefono);
                            lista.insert(nuevoContacto);
                            System.out.println("Contacto agregado correctamente");
                        }
                        break;
                    case 2:
                        System.out.println("ELIMINAR CONTACTO:");
                        System.out.print("Nombre del contacto prara eliminar: ");
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
                        System.out.println("Contacto completo");
                        lista.display();
                        break;
                    case 5:
                        return;
                    default:
                        System.out.println("Opcion no valida");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Debes pone un numero entre 1 y 5 ");
            }
        }
    }
}