package uade.tpo.modelo.pago;

public interface MetodoPago {

    void procesarPago(double monto);

    void reembolsarMonto(double montoReembolso);
}
