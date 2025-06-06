package uade.tpo.modelo.metodoPagoFactory;

import uade.tpo.modelo.dtos.GooglePayDTO;
import uade.tpo.modelo.pago.GooglePay;
import uade.tpo.modelo.pago.MetodoPago;

public class GooglePayFactory implements MetodoPagoFactory<GooglePayDTO>{
    @Override
    public MetodoPago crearMetodoPago(GooglePayDTO datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Datos de Google Pay no pueden ser nulos.");
        }

        String cuenta = datos.getCuentaGoogle();
        String deviceId = datos.getDeviceId();

        if (cuenta == null || cuenta.trim().isEmpty()) {
            throw new IllegalArgumentException("Cuenta de Google no puede estar vacía.");
        }

        if (deviceId == null || deviceId.trim().length() < 5) {
            throw new IllegalArgumentException("Device ID inválido.");
        }

        return new GooglePay(cuenta, deviceId);
    }
}
