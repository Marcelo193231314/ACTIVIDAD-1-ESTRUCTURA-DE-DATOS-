class LinkedList {
    private Node head;
    private Node tail;
    private String tipo;

    public LinkedList(String tipo) {
        this.head = null;
        this.tail = null;
        this.tipo = tipo;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void insert(Object data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
            if (tipo.equals("circular")) {
                head.next = head;
            }
            return;
        }

        switch (tipo) {
            case "simple":
                tail.next = newNode;
                tail = newNode;
                break;
            case "doble":
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
                break;
            case "circular":
                newNode.next = head;
                tail.next = newNode;
                tail = newNode;
                break;
        }
    }

    public boolean delete(String nombre) {
        if (head == null) return false;

        Node current = head;
        Node prev = null;
        boolean encontrado = false;

        do {
            Contacto contacto = (Contacto) current.data;
            if (contacto.nombre.equals(nombre)) {
                encontrado = true;
                break;
            }
            prev = current;
            current = current.next;
        } while (current != null && current != head);

        if (!encontrado) return false;

        if (current == head && current == tail) {
            head = null;
            tail = null;
            return true;
        }

        if (current == head) {
            head = head.next;
            if (tipo.equals("doble")) {
                head.prev = null;
            }
            if (tipo.equals("circular")) {
                tail.next = head;
            }
        }
        else if (current == tail) {
            tail = prev;
            if (tipo.equals("doble")) {
                tail.next = null;
            }
            if (tipo.equals("circular")) {
                tail.next = head;
            }
        }
        else {
            prev.next = current.next;
            if (tipo.equals("doble")) {
                current.next.prev = prev;
            }
        }

        return true;
    }

    public boolean search(String nombre) {
        if (head == null) return false;

        Node current = head;
        int posicion = 0;
        boolean encontrado = false;

        do {
            Contacto contacto = (Contacto) current.data;
            if (contacto.nombre.equals(nombre)) {
                System.out.println("Contacto encontrado en la posicion " + posicion + ":");
                System.out.println("Nombre: " + contacto.nombre);
                System.out.println("Direccion: " + contacto.direccion);
                System.out.println("Telefono: " + contacto.telefono);
                encontrado = true;
                break;
            }
            posicion++;
            current = current.next;
        } while (current != null && current != head);

        return encontrado;
    }

    public void display() {
        if (head == null) {
            System.out.println("Lista vacia");
            return;
        }

        StringBuilder sb = new StringBuilder();

        switch (tipo) {
            case "simple":
                Node temp = head;
                while (temp != null) {
                    Contacto contacto = (Contacto) temp.data;
                    sb.append("[")
                            .append(contacto.nombre).append(" -> ")
                            .append(contacto.direccion).append(" -> ")
                            .append(contacto.telefono)
                            .append("]");
                    if (temp.next != null) sb.append(" -> ");
                    temp = temp.next;
                }
                break;

            case "doble":
                Node current = head;
                while (current != null) {
                    Contacto contacto = (Contacto) current.data;
                    sb.append("<- [")
                            .append(contacto.nombre).append(" <-> ")
                            .append(contacto.direccion).append(" <-> ")
                            .append(contacto.telefono)
                            .append("]");
                    if (current.next != null) sb.append(" <-> ");
                    current = current.next;
                }
                sb.append(" ->");
                break;

            case "circular":
                Node node = head;
                do {
                    Contacto contacto = (Contacto) node.data;
                    sb.append("[")
                            .append(contacto.nombre).append(" -> ")
                            .append(contacto.direccion).append(" -> ")
                            .append(contacto.telefono)
                            .append("]");
                    if (node.next != head) sb.append(" -> ");
                    node = node.next;
                } while (node != head);
                sb.append(" ↻");
                break;
        }

        System.out.println(sb.toString());
    }
}