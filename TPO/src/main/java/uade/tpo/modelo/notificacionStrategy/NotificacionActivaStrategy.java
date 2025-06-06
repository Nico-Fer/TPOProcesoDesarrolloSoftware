package uade.tpo.modelo.notificacionStrategy;

import uade.tpo.modelo.observerPedido.NotificadorPedido;
import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pedido.Pedido;

public class NotificacionActivaStrategy implements NotificacionStrategy {
    private final NotificadorPedido notificador = new NotificadorPedido();

    @Override
    public void agregarObserver(ObserverPedido observer) {
        notificador.agregarSuscriptor(observer);
    }

    @Override
    public void notificarCambioEstado(Pedido pedido) {
        notificador.notificarSuscriptores(pedido);
    }

    @Override
    public void limpiarObservers() {
        notificador.limpiarSuscriptores();
    }
}
