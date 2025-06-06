package uade.tpo.modelo.notificacionStrategy;

import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pedido.Pedido;

public interface NotificacionStrategy {
    void agregarObserver(ObserverPedido observer);
    void notificarCambioEstado(Pedido pedido);
    void limpiarObservers();
}
