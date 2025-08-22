import java.util.LinkedList;
import java.util.Queue;

class Cola {
    private Node frente;
    private Node fin;
    private Queue<String> historialComandos;
    private Queue<String> historialDatos;

    public Cola() {
        this.frente = null;
        this.fin = null;
        this.historialComandos = new LinkedList<>();
        this.historialDatos = new LinkedList<>();
    }

    public void push(String dato) {
        Node nuevoNodo = new Node(dato);

        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.next = nuevoNodo;
            fin = nuevoNodo;
        }

        // Registrar en historial
        historialComandos.add("push");
        historialDatos.add(dato);

        System.out.println("push " + dato);
        mostrarFormatoHorizontal();
    }

    public String pop() {
        System.out.println("pop");
        if (estaVacia()) {
            System.out.println("Inicio->[]<-Fin");
            System.out.println();
            return null;
        }

        String datoEliminado = (String) frente.data;

        // Registrar en historial
        historialComandos.add("pop");
        historialDatos.add(datoEliminado);

        frente = frente.next;

        if (frente == null) {
            fin = null;
        }

        mostrarFormatoHorizontal();
        return datoEliminado;
    }

    public String peek() {
        if (estaVacia()) {
            System.out.println("La cola esta vacia");
            return null;
        }
        System.out.println("Elemento al frente: " + (String) frente.data);
        return (String) frente.data;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public void mostrarFormatoHorizontal() {
        if (estaVacia()) {
            System.out.println("Inicio->[]<-Fin");
            System.out.println();
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Inicio->[");

        Node actual = frente;
        while (actual != null) {
            sb.append((String) actual.data);
            if (actual.next != null) {
                sb.append(" -> ");
            }
            actual = actual.next;
        }

        sb.append("]<-Fin");
        System.out.println(sb.toString());
        System.out.println();
    }

    public int tamaño() {
        int contador = 0;
        Node actual = frente;
        while (actual != null) {
            contador++;
            actual = actual.next;
        }
        return contador;
    }

    public void mostrarHistorial() {
        if (historialComandos.isEmpty()) {
            System.out.println("No hay historial de comandos");
            return;
        }

        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│        HISTORIAL DE COLA        │");
        System.out.println("├─────────────────────────────────┤");

        // Convertir a array para mantener el orden
        String[] comandos = historialComandos.toArray(new String[0]);
        String[] datos = historialDatos.toArray(new String[0]);

        for (int i = 0; i < comandos.length; i++) {
            System.out.printf("│ %-4s : %-20s │\n", comandos[i], datos[i]);
        }

        System.out.println("└─────────────────────────────────┘");
    }

    public boolean deshacerUltimoComando() {
        if (historialComandos.isEmpty()) {
            System.out.println("No hay comandos para deshacer");
            return false;
        }

        String ultimoComando = historialComandos.remove();
        String ultimoDato = historialDatos.remove();

        switch (ultimoComando) {
            case "push":
                // Deshacer push = hacer pop (pero sin registrar en historial)
                if (!estaVacia()) {
                    // Buscar el nodo anterior al final para actualizar 'fin'
                    if (frente == fin) {
                        // Solo hay un elemento
                        frente = null;
                        fin = null;
                    } else {
                        Node actual = frente;
                        while (actual.next != fin) {
                            actual = actual.next;
                        }
                        actual.next = null;
                        fin = actual;
                    }
                    System.out.println("Deshaciendo push: removido '" + ultimoDato + "'");
                }
                break;

            case "pop":
                // Deshacer pop = hacer push (pero sin registrar en historial)
                Node nuevoNodo = new Node(ultimoDato);
                if (estaVacia()) {
                    frente = nuevoNodo;
                    fin = nuevoNodo;
                } else {
                    fin.next = nuevoNodo;
                    fin = nuevoNodo;
                }
                System.out.println("Deshaciendo pop: agregado '" + ultimoDato + "'");
                break;
        }

        mostrarFormatoHorizontal();
        return true;
    }
}