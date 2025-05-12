package uade.tpo.modelo.pago;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PagoTest {

    @Test
    public void crearTarjetaCreditoConDatosValidos() {

        String numero = "1234567890123456";
        String cvv = "123";

        TarjetaCredito tarjeta = new TarjetaCredito(numero, cvv);


        assertEquals(numero, tarjeta.getNumeroTarjeta());
        assertEquals(cvv, tarjeta.getCvv());
    }

    @Test
    public void procesarPagoConTarjetaCreditoNoLanzaExcepciones() {

        MetodoPago tarjeta = new TarjetaCredito("1234567890123456", "123");

        assertDoesNotThrow(() -> tarjeta.procesarPago(5000.0));
    }
}
