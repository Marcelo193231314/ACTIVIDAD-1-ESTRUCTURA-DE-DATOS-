import java.util.Stack;

class Pila {
    private Node cima;
    private Stack<String> historialComandos;
    private Stack<String> historialDatos;

    public Pila() {
        this.cima = null;
        this.historialComandos = new Stack<>();
        this.historialDatos = new Stack<>();
    }

    public void push(String dato) {
        Node nuevoNodo = new Node(dato);
        nuevoNodo.next = cima;
        cima = nuevoNodo;

        // Registrar en historial
        historialComandos.push("push");
        historialDatos.push(dato);

        System.out.println("push " + dato);
        mostrarVerticalConLineas();
    }

    public String pop() {
        System.out.println("pop");
        if (estaVacia()) {
            System.out.println("┌─────────────┐");
            System.out.println("│ VACIO       │");
            System.out.println("└─────────────┘");
            System.out.println();
            return null;
        }

        String datoEliminado = (String) cima.data;

        // Registrar en historial
        historialComandos.push("pop");
        historialDatos.push(datoEliminado);

        cima = cima.next;

        mostrarVerticalConLineas();
        return datoEliminado;
    }

    public String peek() {
        if (estaVacia()) {
            System.out.println("esta vacio");
            return null;
        }
        System.out.println("Elemento en la cima: " + (String) cima.data);
        return (String) cima.data;
    }

    public boolean estaVacia() {
        return cima == null;
    }

    public void mostrarVerticalConLineas() {
        if (estaVacia()) {
            System.out.println("┌─────────────┐");
            System.out.println("│ VACIO       │");
            System.out.println("└─────────────┘");
            System.out.println();
            return;
        }

        int count = 0;
        Node actual = cima;
        while (actual != null) {
            count++;
            actual = actual.next;
        }

        String[] elementos = new String[count];
        actual = cima;
        for (int i = count - 1; i >= 0; i--) {
            elementos[i] = (String) actual.data;
            actual = actual.next;
        }

        System.out.println("┌─────────────┐");
        for (int i = 0; i < count; i++) {
            System.out.println("│     " + elementos[i] + "      │");
            System.out.println("├─────────────┤");
        }
        System.out.println("└─────────────┘");
        System.out.println();
    }

    public int tamaño() {
        int contador = 0;
        Node actual = cima;
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
        System.out.println("│        HISTORIAL DE PILA        │");
        System.out.println("├─────────────────────────────────┤");

        // Mostrar historial en orden inverso (mas reciente primero)
        for (int i = historialComandos.size() - 1; i >= 0; i--) {
            String comando = historialComandos.get(i);
            String dato = historialDatos.get(i);
            System.out.printf("│ %-4s : %-20s │\n", comando, dato);
        }

        System.out.println("└─────────────────────────────────┘");
    }

    public boolean deshacerUltimoComando() {
        if (historialComandos.isEmpty()) {
            System.out.println("No hay comandos para deshacer");
            return false;
        }

        String ultimoComando = historialComandos.pop();
        String ultimoDato = historialDatos.pop();

        switch (ultimoComando) {
            case "push":
                // Deshacer push = hacer pop
                if (!estaVacia()) {
                    cima = cima.next;
                    System.out.println("Deshaciendo push: removido '" + ultimoDato + "'");
                }
                break;

            case "pop":
                // Deshacer pop = hacer push
                Node nuevoNodo = new Node(ultimoDato);
                nuevoNodo.next = cima;
                cima = nuevoNodo;
                System.out.println("Deshaciendo pop: agregado '" + ultimoDato + "'");
                break;
        }

        mostrarVerticalConLineas();
        return true;
    }
}