package uade.tpo.modelo.metodoPagoFactory;

import uade.tpo.modelo.enums.TipoMetodoPago;

public class MetodoPagoFactoryProvider {

    public static MetodoPagoFactory<?> getFactory(TipoMetodoPago tipo) {
        switch (tipo) {
            case TARJETA_CREDITO:
                return new TarjetaCreditoFactory();
            case TARJETA_DEBITO:
                return new TarjetaDebitoFactory();
            default:
                throw new IllegalArgumentException("Tipo de método de pago no soportado: " + tipo);
        }
    }
}
