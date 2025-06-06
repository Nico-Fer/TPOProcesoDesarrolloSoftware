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
}
