package uade.tpo.modelo.metodoPagoFactory;

import uade.tpo.modelo.enums.TipoMetodoPago;

public class MetodoPagoFactoryProvider {

    public static MetodoPagoFactory<?> getFactory(TipoMetodoPago tipo) {
        return switch (tipo) {
            case TARJETA_CREDITO -> new TarjetaCreditoFactory();
            case TARJETA_DEBITO -> new TarjetaDebitoFactory();
            case MERCADO_PAGO -> new MercadoPagoFactory();
            case GOOGLE_PAY -> new GooglePayFactory();
            case EFECTIVO -> new EfectivoFactory();
            default -> throw new IllegalArgumentException("Tipo de método de pago no soportado: " + tipo);
        };
    }
}
