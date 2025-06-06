package uade.tpo.modelo.PlataformaStrategy;

import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pedido.Pedido;

public interface PlataformaStrategy {
    void setCupon(CuponDescuento cupon);
    double aplicarDescuento(double subtotal);

    void agregarObserver(ObserverPedido observer);
    void notificarCambioEstado(Pedido pedido);
    void limpiarObservers();
}
