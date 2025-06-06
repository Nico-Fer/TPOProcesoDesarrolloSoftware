package uade.tpo.modelo.metodoPagoFactory;

import org.junit.jupiter.api.Test;
import uade.tpo.modelo.dtos.GooglePayDTO;
import uade.tpo.modelo.pago.MetodoPago;

import static org.junit.jupiter.api.Assertions.*;

public class GooglePayFactoryTest {

    @Test
    void googlePayFactoryCreaPagoValido() {
        GooglePayDTO dto = new GooglePayDTO("usuario@gmail.com", "DEVICE123", "TOKEN123");
        GooglePayFactory factory = new GooglePayFactory();

        MetodoPago pago = factory.crearMetodoPago(dto);

        assertNotNull(pago);
        assertDoesNotThrow(() -> pago.procesarPago(900.0));
    }

    @Test
    void googlePayFactoryLanzaExcepcionSiCuentaVacia() {
        GooglePayDTO dto = new GooglePayDTO(" ", "DEVICE123", "TOKEN123");
        GooglePayFactory factory = new GooglePayFactory();

        assertThrows(IllegalArgumentException.class, () -> factory.crearMetodoPago(dto));
    }

    @Test
    void googlePayFactoryLanzaExcepcionSiDeviceIdMuyCorto() {
        GooglePayDTO dto = new GooglePayDTO("usuario@gmail.com", "abc", "TOKEN123");
        GooglePayFactory factory = new GooglePayFactory();

        assertThrows(IllegalArgumentException.class, () -> factory.crearMetodoPago(dto));
    }
}
