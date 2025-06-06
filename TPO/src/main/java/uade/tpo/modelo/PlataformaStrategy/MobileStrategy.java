package uade.tpo.modelo.PlataformaStrategy;

import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponStrategy.CuponStrategy;
import uade.tpo.modelo.cuponStrategy.CuponValidadoStrategy;
import uade.tpo.modelo.notificacionStrategy.NotificacionActivaStrategy;
import uade.tpo.modelo.notificacionStrategy.NotificacionStrategy;
import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pedido.Pedido;

public class MobileStrategy implements PlataformaStrategy {
    private final CuponStrategy cuponStrategy = new CuponValidadoStrategy();
    private final NotificacionStrategy notificacionStrategy = new NotificacionActivaStrategy();

    @Override
    public void setCupon(CuponDescuento cupon) {
        cuponStrategy.setCupon(cupon);
    }

    @Override
    public double aplicarDescuento(double subtotal) {
        return cuponStrategy.aplicarDescuento(subtotal);
    }

    @Override
    public void agregarObserver(ObserverPedido observer) {
        notificacionStrategy.agregarObserver(observer);
    }

    @Override
    public void notificarCambioEstado(Pedido pedido) {
        notificacionStrategy.notificarCambioEstado(pedido);
    }

    @Override
    public void limpiarObservers() {
        notificacionStrategy.limpiarObservers();
    }
}
