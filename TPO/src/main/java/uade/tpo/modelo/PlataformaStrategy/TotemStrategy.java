package uade.tpo.modelo.PlataformaStrategy;

import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponStrategy.CuponSinValidarStrategy;
import uade.tpo.modelo.cuponStrategy.CuponStrategy;
import uade.tpo.modelo.notificacionStrategy.NotificacionInactivaStrategy;
import uade.tpo.modelo.notificacionStrategy.NotificacionStrategy;
import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pedido.Pedido;

public class TotemStrategy implements PlataformaStrategy{
    private final CuponStrategy cuponStrategy = new CuponSinValidarStrategy();
    private final NotificacionStrategy notificacionStrategy = new NotificacionInactivaStrategy();

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
    public void limpiarObservers() { notificacionStrategy.limpiarObservers(); }

    @Override
    public Float calcularRutaPedido() {
        return 0f;
    }
}
