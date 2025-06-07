package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;

public class EnArmadoState implements EstadoPedidoState{
    private Pedido pedido;

    @Override
    public void avanzarEstadoPedido() { pedido.setEstado(new EnEsperaState()); }

    @Override
    public String getNombreEstado() {
        return "En armado";
    }

    @Override
    public void setPedido(Pedido pedido) { this.pedido = pedido; }

    @Override
    public Float calcularTiempoRestantePedido(int cantidadPedidos) {
        return 0f;
    }

    @Override
    public boolean cancelarPedido() {
        return true;
    }
}
