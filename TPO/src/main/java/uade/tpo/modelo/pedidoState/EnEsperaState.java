package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public class EnEsperaState implements EstadoPedidoState{
    private Pedido pedido;

    @Override
    public void AvanzarEstadoPedido() {
        pedido.SetEstado(new EnPreparacionState());
    }

    @Override
    public String GetNombreEstado() {
        return "En espera";
    }

    @Override
    public void SetPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
