package uade.tpo.modelo.menu;

import uade.tpo.modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Menu {

    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        if(producto == null) {
            return;
        }

        if( productos.stream().anyMatch(p -> p.getNombre().equalsIgnoreCase(producto.getNombre())) ) {
            return;
        }
        productos.add(producto);
    };

    public List<Producto> getProductos() {
        return productos;
    };

    public Producto buscarProductoPorNombre(String nombre) {
        return productos.stream().filter(producto -> producto.getNombre().equals(nombre)).findFirst().orElse(null);
    };

    public List<Producto> filtrarProductosPorPrecio(double precioMaximo) {
        return productos.stream().filter(producto -> producto.getPrecio() <= precioMaximo).collect(Collectors.toList());
    };
}
