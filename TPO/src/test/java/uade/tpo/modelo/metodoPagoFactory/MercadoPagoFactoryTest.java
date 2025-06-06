package uade.tpo.modelo.metodoPagoFactory;

import org.junit.jupiter.api.Test;
import uade.tpo.modelo.dtos.MercadoPagoDTO;
import uade.tpo.modelo.pago.MetodoPago;

import static org.junit.jupiter.api.Assertions.*;

public class MercadoPagoFactoryTest {
    @Test
    void mercadoPagoFactoryCreaPagoValido() {
        MercadoPagoDTO dto = new MercadoPagoDTO("cliente@correo.com", "1234567890123456789012");
        MercadoPagoFactory factory = new MercadoPagoFactory();

        MetodoPago pago = factory.crearMetodoPago(dto);

        assertNotNull(pago);
        assertDoesNotThrow(() -> pago.procesarPago(1000.0));
    }

    @Test
    void mercadoPagoFactoryLanzaExcepcionSiCBUInvalido() {
        MercadoPagoDTO dto = new MercadoPagoDTO("cliente@correo.com", "1234");
        MercadoPagoFactory factory = new MercadoPagoFactory();

        assertThrows(IllegalArgumentException.class, () -> factory.crearMetodoPago(dto));
    }

    @Test
    void mercadoPagoFactoryLanzaExcepcionSiEmailInvalido() {
        MercadoPagoDTO dto = new MercadoPagoDTO("sin-formato", "1234567890123456789012");
        MercadoPagoFactory factory = new MercadoPagoFactory();

        assertThrows(IllegalArgumentException.class, () -> factory.crearMetodoPago(dto));
    }
}
