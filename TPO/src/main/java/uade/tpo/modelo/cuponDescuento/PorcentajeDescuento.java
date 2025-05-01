package uade.tpo.modelo.cuponDescuento;

public class PorcentajeDescuento implements CuponDescuento{
    private String codigo;
    private double porcentaje;
    private boolean usado = false;

    public PorcentajeDescuento(String codigo, double porcentaje) {
        this.codigo = codigo;
        this.porcentaje = porcentaje;
    }

    @Override
    public double AplicarDescuento(double total) {
        if (!EsValido()) {
            throw new IllegalStateException("El cupón ya fue usado o no es válido.");
        }
        usado = true;
        return total * (1 - porcentaje);
    }

    @Override
    public boolean EsValido() {
        return !usado;
    }

    @Override
    public String GetCodigo() {
        return codigo;
    }
}
