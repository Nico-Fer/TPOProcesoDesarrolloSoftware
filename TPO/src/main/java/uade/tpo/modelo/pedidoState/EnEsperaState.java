package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedido.Carrito;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

public class EnEsperaState implements EstadoPedidoState{
    private Pedido pedido;
    private final double porcentajeReembolso = 0.25;

    @Override
    public void avanzarEstadoPedido() {
        pedido.setEstado(new EnPreparacionState());
    }

    @Override
    public String getNombreEstado() {
        return "En espera";
    }

    @Override
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public Float calcularTiempoRestantePedido(int cantidadPedidos) {
        if (cantidadPedidos < 10) return 5.0f;
        return (cantidadPedidos / 10) * 20.0f;
    }

    @Override
    public boolean cancelarPedido() {
        MetodoPago metodoPago = pedido.getMetodoPago();
        double total = pedido.calcularPrecioTotal();
        metodoPago.reembolsarMonto(total * porcentajeReembolso);
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
