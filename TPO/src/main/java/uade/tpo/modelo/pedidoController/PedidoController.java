package uade.tpo.modelo.pedidoController;

import uade.tpo.modelo.PlataformaStrategy.PlataformaStrategy;
import uade.tpo.modelo.cliente.Cliente;
import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.restaurante.Restaurante;

import java.util.ArrayList;
import java.util.List;

public class PedidoController {
    private Restaurante restaurante;
    private List<Pedido> pedidos = new ArrayList<>();

    public PedidoController(Restaurante restaurante) {
        this.restaurante = restaurante;
    }

    public Pedido crearPedido(PlataformaStrategy strategy) {
        Pedido pedido = new Pedido(strategy);
        pedidos.add(pedido);
        return pedido;
    }

    public Pedido buscarPedidoPorId(String id) {
        Pedido pedido = pedidos.stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido no encontrado: " + id);
        }
        return pedido;
    }

    public Float calcularTiempoRestante(String idPedido) {
        Pedido pedido = buscarPedidoPorId(idPedido);
        int cantidadPedidos = 0;

        for (Pedido p: pedidos ) {
            if (pedido.getEstado().getClass().equals(p.getEstado().getClass())) {
                cantidadPedidos++;
            }
        }

        return pedido.calcularTiempoRestante(cantidadPedidos);
    }

    public void avanzarEstadoPedido(String idPedido) {
        Pedido pedido = buscarPedidoPorId(idPedido);
        pedido.avanzarEstado();
    }

    public void agregarSuscriptor(String idPedido, ObserverPedido suscriptor) {
        Pedido pedido = buscarPedidoPorId(idPedido);
        pedido.agregarSuscriptor(suscriptor);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
