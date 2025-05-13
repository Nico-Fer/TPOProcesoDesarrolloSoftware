package uade.tpo.modelo.observerPedido;

import uade.tpo.modelo.cliente.Cliente;
import uade.tpo.modelo.pedido.Pedido;

public class ObserverCliente implements ObserverPedido {

    private final Cliente cliente;

    public ObserverCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void notificarEstado(Pedido pedido) {
        System.out.println("Cliente " + cliente.getNombre() + ": tu pedido está en estado [" + pedido.getNombreEstado() + "]");
    }
}
