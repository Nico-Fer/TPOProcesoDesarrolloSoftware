package uade.tpo.modelo.pago;

public class GooglePay implements MetodoPago{
    private final String cuentaGoogle;
    private final String deviceId;

    public GooglePay(String cuentaGoogle, String deviceId) {
        this.cuentaGoogle = cuentaGoogle;
        this.deviceId = deviceId;
    }

    @Override
    public void procesarPago(double monto) {
        System.out.println("Pago con Google Pay de $" + monto + " desde cuenta: " + cuentaGoogle);
    }
}
