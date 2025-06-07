package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedido.Carrito;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

public interface EstadoPedidoState {
    void avanzarEstadoPedido();
    String getNombreEstado();
    void setPedido(Pedido pedido);
    Float calcularTiempoRestantePedido(int cantidadPedidos);
    boolean cancelarPedido();
    void agregarProducto(Producto producto, Carrito carrito);
    void pagar();
}
