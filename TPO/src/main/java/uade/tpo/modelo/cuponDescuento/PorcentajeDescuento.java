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
    public double aplicarDescuento(double total) {
        if (!esValido()) {
            throw new IllegalStateException("El cupón ya fue usado o no es válido.");
        }
        usado = true;
        return total * (1 - porcentaje);
    }

    @Override
    public boolean esValido() {
        return !usado;
    }
}
