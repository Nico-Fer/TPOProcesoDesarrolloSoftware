package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedido.Carrito;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

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

    @Override
    public void agregarProducto(Producto producto, Carrito carrito) {
        throw new IllegalStateException("No se puede agregar productos en este estado.");
    }

    @Override
    public void pagar() {
        throw new IllegalStateException("El pedido ya fue pagado o está en proceso.");
    }
}
