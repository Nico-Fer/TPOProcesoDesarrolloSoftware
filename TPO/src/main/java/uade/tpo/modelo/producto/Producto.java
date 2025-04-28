package uade.tpo.modelo.producto;

import java.util.ArrayList;
import java.util.List;

public class Producto {

    private String nombre;
    private String descripcion;
    private double precio;
    private List<Ingrediente> ingredientes;

    public Producto(String nombre, String descripcion, double precio, List<Ingrediente> ingredientes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;

        if(ingredientes == null) {
            this.ingredientes = new ArrayList<Ingrediente>();
        }else {
            this.ingredientes = ingredientes;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public List<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public boolean tieneIngredientesAlergenicos() {
        if (ingredientes == null) {
            return false;
        }
        return ingredientes.stream()
                .anyMatch(Ingrediente::EsAlergenico);
    }
}
