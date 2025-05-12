package uade.tpo.modelo.cuponDescuento;

public interface CuponDescuento {
    double aplicarDescuento(double total);
    boolean esValido();
    String getCodigo();
}
