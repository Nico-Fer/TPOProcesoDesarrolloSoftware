package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pago.MetodoPago;
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

    @Override
    public Float calcularTiempoRestantePedido(int cantidadPedidos) {
        return pedido.getPlataformaStrategy().calcularRutaPedido();
    }

    @Override
    public boolean cancelarPedido() {
        System.out.println("El pedido ya esta en camino. No se puede cancelar.");
        return false;
    }
}
