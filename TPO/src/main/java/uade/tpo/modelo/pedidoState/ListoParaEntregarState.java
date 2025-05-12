package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public class ListoParaEntregarState implements EstadoPedidoState{
    private Pedido pedido;
    @Override
    public void avanzarEstadoPedido() {
        pedido.setEstado(new EntregadoState());
    }

    @Override
    public String getNombreEstado() {
        return "Listo Para Entregar";
    }

    @Override
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
