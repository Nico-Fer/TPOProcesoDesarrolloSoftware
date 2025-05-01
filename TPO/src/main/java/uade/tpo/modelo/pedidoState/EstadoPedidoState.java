package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public interface EstadoPedidoState {
    void AvanzarEstadoPedido();
    String GetNombreEstado();
    void SetPedido(Pedido pedido);
}
