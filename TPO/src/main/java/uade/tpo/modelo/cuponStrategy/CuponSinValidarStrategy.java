package uade.tpo.modelo.cuponStrategy;

import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponDescuento.CuponVacio;

public class CuponSinValidarStrategy implements CuponStrategy {
    private CuponDescuento cupon = new CuponVacio();

    public void setCupon(CuponDescuento cupon) {
        if (cupon == null) {
            throw new IllegalArgumentException("Cupón inválido.");
        }
        this.cupon = cupon;
    }

    public double aplicarDescuento(double subtotal) {
        return cupon.aplicarDescuento(subtotal);
    }
}
