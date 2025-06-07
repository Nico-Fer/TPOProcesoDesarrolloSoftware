package uade.tpo.modelo.pago;

public class Efectivo implements MetodoPago{
    @Override
    public void procesarPago(double monto) {
        double montoConDescuento = monto * 0.9;
        System.out.println("Pago en efectivo realizado con 10% de descuento. Monto final: $" + montoConDescuento);
    }

    @Override
    public void reembolsarMonto(double montoReembolso) {
        System.out.println("Se realizó un reembolso del 75%. Monto final: $" + montoReembolso);
    }
}
