package uade.tpo.modelo.cuponDescuento;

public class CuponVacio implements CuponDescuento{

    @Override
    public double aplicarDescuento(double total) {
        return total;
    }

    @Override
    public boolean esValido() {
        return true;
    }

    @Override
    public String getCodigo() {
        return "SIN_CUPON";
    }
}
