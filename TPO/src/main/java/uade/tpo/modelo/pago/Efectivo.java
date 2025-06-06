package uade.tpo.modelo.pago;

public class Efectivo implements MetodoPago{
    @Override
    public void procesarPago(double monto) {
        double montoConDescuento = monto * 0.9;
        System.out.println("Pago en efectivo realizado con 10% de descuento. Monto final: $" + montoConDescuento);
    }
}
