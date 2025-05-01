package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public class EnPreparacionState implements EstadoPedidoState{
    private Pedido pedido;

    @Override
    public void AvanzarEstadoPedido() {
        pedido.SetEstado(new ListoParaEntregarState());
    }

    @Override
    public String GetNombreEstado() {
        return "En Preparación";
    }

    @Override
    public void SetPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
