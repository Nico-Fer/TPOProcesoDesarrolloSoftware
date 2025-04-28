package uade.tpo.modelo.pago;

public class TarjetaDebito implements MetodoPago{
    private String numeroTarjeta;
    private String cvv;

    public TarjetaDebito(String numeroTarjeta, String cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getCvv() {
        return cvv;
    }

    @Override
    public void ProcesarPago(double monto) {
        System.out.println("Procesando pago de $" + monto + " con tarjeta de débito.");
    }
}
