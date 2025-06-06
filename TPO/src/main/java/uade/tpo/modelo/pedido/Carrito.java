package uade.tpo.modelo.pedido;

import uade.tpo.modelo.producto.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrito {
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        if (producto != null) productos.add(producto);
    }

    public List<Producto> getProductos() {
        return Collections.unmodifiableList(productos);
    }

    public double calcularTotal() {
        return productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
    }
}
