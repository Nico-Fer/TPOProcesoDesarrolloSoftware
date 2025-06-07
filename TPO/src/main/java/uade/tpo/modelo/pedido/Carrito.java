package uade.tpo.modelo.pedido;

import uade.tpo.modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Carrito {
    private List<ItemCarrito> items = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        if (producto != null) items.add(new ItemCarrito(producto));
    }

    public List<Producto> getProductos() {
        return items.stream().map(ItemCarrito::getProducto).collect(Collectors.toList());
    }

    public double calcularTotal() {
        return items.stream()
                .filter(i -> !i.estaPagado())
                .mapToDouble(ItemCarrito::getPrecio)
                .sum();
    }

    public void marcarComoPagados() {
        for (ItemCarrito i : items) {
            i.marcarPagado();
        }
    }
}
