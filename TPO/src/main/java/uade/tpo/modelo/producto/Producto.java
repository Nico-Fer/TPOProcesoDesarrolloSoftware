package uade.tpo.modelo.producto;

import uade.tpo.modelo.enums.Categoria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Producto {

    private String nombre;
    private String descripcion;
    private double precio;
    private List<Ingrediente> ingredientes;
    private List<Categoria> categorias;
    private Float tiempoPreparacion;

    public Producto(String nombre, String descripcion, double precio, List<Ingrediente> ingredientes, List<Categoria> categorias, Float tiempoPreparacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.ingredientes = Objects.requireNonNullElseGet(ingredientes, ArrayList::new);
        this.categorias = Objects.requireNonNullElseGet(categorias, ArrayList::new);
        this.tiempoPreparacion = tiempoPreparacion;
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

    public Float getTiempoPreparacion() {
        return tiempoPreparacion;
    }

    public boolean tieneIngredientesAlergenicos() {
        if (ingredientes == null) {
            return false;
        }
        return ingredientes.stream()
                .anyMatch(Ingrediente::esAlergenico);
    }

    public boolean perteneceACategoria(Categoria categoria) {
        return categorias.contains(categoria);
    }
}
