package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public class EntregadoState implements EstadoPedidoState{
    private Pedido pedido;

    @Override
    public void AvanzarEstadoPedido() {
        throw new IllegalStateException("El pedido ya fue entregado. No puede avanzar más.");
    }

    @Override
    public String GetNombreEstado() {
        return "Pedido Entregado";
    }

    @Override
    public void SetPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
