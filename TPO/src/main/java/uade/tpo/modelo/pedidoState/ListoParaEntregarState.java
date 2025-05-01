package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public class ListoParaEntregarState implements EstadoPedidoState{
    private Pedido pedido;
    @Override
    public void AvanzarEstadoPedido() {
        pedido.SetEstado(new EntregadoState());
    }

    @Override
    public String GetNombreEstado() {
        return "Listo Para Entregar";
    }

    @Override
    public void SetPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
