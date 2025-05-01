package uade.tpo.modelo.cuponDescuento;

public class CuponVacio implements CuponDescuento{

    @Override
    public double AplicarDescuento(double total) {
        return total;
    }

    @Override
    public boolean EsValido() {
        return true;
    }

    @Override
    public String GetCodigo() {
        return "SIN_CUPON";
    }
}
