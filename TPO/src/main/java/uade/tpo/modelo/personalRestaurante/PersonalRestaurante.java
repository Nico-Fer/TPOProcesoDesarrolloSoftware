package uade.tpo.modelo.personalRestaurante;

public class PersonalRestaurante {
    protected String nombre;
    protected String rol;

    public PersonalRestaurante(String nombre, String rol) {
        this.nombre = nombre;
        this.rol = rol;
    }

    public String getNombre() { return nombre; }
}
