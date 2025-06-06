package uade.tpo.modelo.cuponStrategy;

import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponDescuento.CuponVacio;

public class CuponValidadoStrategy implements CuponStrategy {
    private CuponDescuento cupon = new CuponVacio();

    public void setCupon(CuponDescuento cupon) {
        if (cupon == null || !cupon.esValido()) {
            throw new IllegalArgumentException("Cupón inválido.");
        }
        this.cupon = cupon;
    }

    public double aplicarDescuento(double subtotal) {
        if (!cupon.esValido()) {
            throw new IllegalStateException("Cupón ya fue utilizado o inválido.");
        }
        return cupon.aplicarDescuento(subtotal);
    }
}
