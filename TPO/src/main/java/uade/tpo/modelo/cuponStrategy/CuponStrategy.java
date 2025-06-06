package uade.tpo.modelo.cuponStrategy;

import uade.tpo.modelo.cuponDescuento.CuponDescuento;

public interface CuponStrategy {
    void setCupon(CuponDescuento cupon);
    double aplicarDescuento(double subtotal);
}
