package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public class EnPreparacionState implements EstadoPedidoState{
    private Pedido pedido;

    @Override
    public void avanzarEstadoPedido() {
        pedido.setEstado(new ListoParaEntregarState());
    }

    @Override
    public String getNombreEstado() {
        return "En Preparación";
    }

    @Override
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
