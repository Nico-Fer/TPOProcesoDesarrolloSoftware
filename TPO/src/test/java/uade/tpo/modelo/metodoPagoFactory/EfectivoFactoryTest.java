package uade.tpo.modelo.metodoPagoFactory;

import org.junit.jupiter.api.Test;
import uade.tpo.modelo.pago.MetodoPago;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EfectivoFactoryTest {
    @Test
    void efectivoFactoryCreaPagoCorrectamente() {
        EfectivoFactory factory = new EfectivoFactory();
        MetodoPago pago = factory.crearMetodoPago(null);

        assertNotNull(pago);
        assertDoesNotThrow(() -> pago.procesarPago(1000.0));
    }
}
