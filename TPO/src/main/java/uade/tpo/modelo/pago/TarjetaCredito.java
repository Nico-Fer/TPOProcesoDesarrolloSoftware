package uade.tpo.modelo.pago;

public class TarjetaCredito implements MetodoPago{
    private String numeroTarjeta;
    private String cvv;

    public TarjetaCredito(String numeroTarjeta, String cvv) {
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
    public void procesarPago(double monto) {
        System.out.println("Procesando pago de $" + monto + " con tarjeta de crédito.");
    }

    @Override
    public void reembolsarMonto(double montoReembolso) {
        System.out.println("Se realizó un reembolso del 75%. Monto final: $" + montoReembolso);
    }
}
