package uade.tpo.modelo.cuponDescuento;

public interface CuponDescuento {
    double AplicarDescuento(double total);
    boolean EsValido();
    String GetCodigo();
}
