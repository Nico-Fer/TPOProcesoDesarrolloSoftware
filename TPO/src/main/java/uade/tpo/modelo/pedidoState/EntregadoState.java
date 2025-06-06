package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public class EntregadoState implements EstadoPedidoState{
    private Pedido pedido;

    @Override
    public void avanzarEstadoPedido() {
        throw new IllegalStateException("El pedido ya fue entregado. No puede avanzar más.");
    }

    @Override
    public String getNombreEstado() {
        return "Pedido Entregado";
    }

    @Override
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        this.pedido.limpiarSuscriptores();
    }

    @Override
    public Float calcularTiempoRestantePedido(int cantidadPedidos) {
        return 0f;
    }
}
