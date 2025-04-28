package uade.tpo.modelo.metodoPagoFactory;

import uade.tpo.modelo.dtos.TarjetaCreditoDTO;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pago.TarjetaCredito;

public class TarjetaCreditoFactory implements MetodoPagoFactory<TarjetaCreditoDTO>{
    @Override
    public MetodoPago crearPago(TarjetaCreditoDTO datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos de tarjeta no pueden ser nulos.");
        }
        if (datos.getCvv() == null || datos.getCvv().length() != 3) {
            throw new IllegalArgumentException("El CVV debe tener exactamente 3 dígitos.");
        }
        if (datos.getNumeroTarjeta() == null || datos.getNumeroTarjeta().isBlank()) {
            throw new IllegalArgumentException("El número de tarjeta no puede ser vacío.");
        }
        return new TarjetaCredito(datos.getNumeroTarjeta(), datos.getCvv());
    }
}
