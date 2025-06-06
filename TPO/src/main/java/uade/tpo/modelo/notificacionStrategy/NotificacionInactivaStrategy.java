package uade.tpo.modelo.notificacionStrategy;

import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pedido.Pedido;

public class NotificacionInactivaStrategy implements NotificacionStrategy {
    @Override
    public void agregarObserver(ObserverPedido observer) {

    }

    @Override
    public void notificarCambioEstado(Pedido pedido) {

    }

    @Override
    public void limpiarObservers() {

    }
}
