package uade.tpo.modelo.pago;

public class MercadoPago implements MetodoPago{
    private final String email;
    private final String cbu;

    public MercadoPago(String email, String cbu) {
        this.email = email;
        this.cbu = cbu;
    }

    @Override
    public void procesarPago(double monto) {
        System.out.println("Pago con MercadoPago de $" + monto + " realizado desde la cuenta: " + email);
    }

    @Override
    public void reembolsarMonto(double montoReembolso) {
        System.out.println("Se realizó un reembolso del 75%. Monto final: $" + montoReembolso);
    }
}
