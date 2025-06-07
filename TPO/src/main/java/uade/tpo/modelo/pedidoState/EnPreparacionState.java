package uade.tpo.modelo.pedidoState;

import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

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

    @Override
    public Float calcularTiempoRestantePedido(int cantidadPedidos) {
        return pedido.getProductos().stream()
                .map(Producto::getTiempoPreparacion)
                .reduce(0.0f, Float::sum);
    }

    @Override
    public boolean cancelarPedido() {
        return true;
    }
}
