
class Contacto {
    String nombre;
    String direccion;
    String telefono;

    public Contacto(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public String toString() {
        return nombre + " - " + direccion + " - " + telefono;
    }
}