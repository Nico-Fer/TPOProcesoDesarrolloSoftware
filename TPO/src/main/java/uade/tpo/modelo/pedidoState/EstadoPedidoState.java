package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedido.Pedido;

public interface EstadoPedidoState {
    void avanzarEstadoPedido();
    String getNombreEstado();
    void setPedido(Pedido pedido);
    Float calcularTiempoRestantePedido(int cantidadPedidos);
    boolean cancelarPedido();
}
