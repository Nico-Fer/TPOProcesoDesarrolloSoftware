package uade.tpo.modelo.producto;

public class Ingrediente {

    private String nombre;
    private boolean esAlergenico;

    public Ingrediente(String nombre, boolean esAlergenico) {
        this.nombre = nombre;
        this.esAlergenico = esAlergenico;
    }

    public boolean esAlergenico() {
        return esAlergenico;
    }
}
