package uade.tpo.modelo.observerPedido;

import uade.tpo.modelo.pedido.Pedido;

public interface ObserverPedido {
    void notificarEstado(Pedido pedido);
}
