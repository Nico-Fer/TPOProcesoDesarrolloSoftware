package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Carrito;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

public class EntregadoState implements EstadoPedidoState{
    private Pedido pedido;

    @Override
    public void avanzarEstadoPedido() {
        throw new IllegalStateException("El pedido ya fue entregado. No puede avanzar más.");
    }

    @Override
    public String getNombreEstado() {
        return "Pedido Entregado";
    }

    @Override
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
        this.pedido.limpiarSuscriptores();
    }

    @Override
    public Float calcularTiempoRestantePedido(int cantidadPedidos) {
        return 0f;
    }

    @Override
    public boolean cancelarPedido() {
        System.out.println("El pedido ya fue entregado. No se puede cancelar.");
        return false;
    }

    @Override
    public void agregarProducto(Producto producto, Carrito carrito) {
        throw new IllegalStateException("No se puede agregar productos en este estado.");
    }

    @Override
    public void pagar() {
        throw new IllegalStateException("El pedido ya fue pagado o está en proceso.");
    }

}
