package uade.tpo.modelo.factura;

import uade.tpo.modelo.cliente.Cliente;
import uade.tpo.modelo.pedido.Pedido;

public class Factura {
    private final Pedido pedido;
    private final Cliente cliente;

    public Factura(Pedido pedido, Cliente cliente) {
        this.pedido = pedido;
        this.cliente = cliente;
    }

    public String generarFactura() {
        return "Factura para " + cliente.getNombre() + " " + cliente.getApellido() + "\n" +
                "Total: $" + pedido.calcularPrecioTotal() + "\n" +
                "Productos:\n" +
                pedido.getProductos().stream()
                        .map(p -> "- " + p.getNombre() + " $" + p.getPrecio())
                        .reduce("", (a, b) -> a + b + "\n");
    }
}
