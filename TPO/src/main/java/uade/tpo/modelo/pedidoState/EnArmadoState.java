package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedido.Carrito;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

public class EnArmadoState implements EstadoPedidoState{
    private Pedido pedido;

    @Override
    public void avanzarEstadoPedido() { pedido.setEstado(new EnEsperaState()); }

    @Override
    public String getNombreEstado() {
        return "En armado";
    }

    @Override
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    @Override
    public Float calcularTiempoRestantePedido(int cantidadPedidos) {
        return 0f;
    }

    @Override
    public boolean cancelarPedido() {
        return true;
    }

    @Override
    public void agregarProducto(Producto producto, Carrito carrito) {
        if (producto != null) carrito.agregarProducto(producto);
    }

    @Override
    public void pagar() {
        MetodoPago metodoPago = pedido.getMetodoPago();
        if (metodoPago == null) {
            throw new IllegalStateException("No se asignó un método de pago.");
        }
        metodoPago.procesarPago(pedido.calcularPrecioTotal());
    }
}
