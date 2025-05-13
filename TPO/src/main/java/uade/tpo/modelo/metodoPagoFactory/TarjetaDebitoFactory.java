package uade.tpo.modelo.metodoPagoFactory;

import uade.tpo.modelo.dtos.TarjetaDebitoDTO;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pago.TarjetaDebito;

public class TarjetaDebitoFactory implements MetodoPagoFactory<TarjetaDebitoDTO> {

    @Override
    public MetodoPago crearMetodoPago(TarjetaDebitoDTO datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos de tarjeta no pueden ser nulos.");
        }
        if (datos.getCvv() == null || datos.getCvv().length() != 3) {
            throw new IllegalArgumentException("El CVV debe tener exactamente 3 dígitos.");
        }
        if (datos.getNumeroTarjeta() == null || datos.getNumeroTarjeta().isBlank()) {
            throw new IllegalArgumentException("El número de tarjeta no puede ser vacío.");
        }

        return new TarjetaDebito(datos.getNumeroTarjeta(), datos.getCvv());
    }
}
