package uade.tpo.modelo.cliente;

public class Cliente {
    private String nombre;
    private String apellido;
    private String ubicacion;


    public Cliente(String nombre, String apellido, String ubicacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUbicacion() {
        return ubicacion;
    }
}
