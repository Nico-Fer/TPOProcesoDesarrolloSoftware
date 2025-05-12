package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public class EnEsperaState implements EstadoPedidoState{
    private Pedido pedido;

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
}
