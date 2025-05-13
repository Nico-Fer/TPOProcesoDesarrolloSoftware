package uade.tpo.modelo.metodoPagoFactory;

import uade.tpo.modelo.pago.MetodoPago;

public interface MetodoPagoFactory<T> {
    MetodoPago crearMetodoPago(T datos);
}
