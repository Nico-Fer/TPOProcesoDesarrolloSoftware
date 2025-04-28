package uade.tpo.modelo.metodoPagoFactory;

import org.junit.Test;
import uade.tpo.modelo.dtos.TarjetaCreditoDTO;
import uade.tpo.modelo.dtos.TarjetaDebitoDTO;
import uade.tpo.modelo.enums.TipoMetodoPago;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pago.TarjetaDebito;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TarjetaDebitoFactoryTest {
    @Test
    public void tarjetaDebitoFactoryCreaMetodoPagoCorrectamente() {

        TipoMetodoPago tipoMetodoPago = TipoMetodoPago.TARJETA_DEBITO;
        TarjetaDebitoDTO datos = new TarjetaDebitoDTO("6543210987654321", "456");
        MetodoPagoFactory<TarjetaDebitoDTO> factory = (MetodoPagoFactory<TarjetaDebitoDTO>) MetodoPagoFactoryProvider.getFactory(tipoMetodoPago);

        MetodoPago metodoPago = factory.crearPago(datos);

        assertNotNull(metodoPago);
        assertInstanceOf(TarjetaDebito.class, metodoPago);;

        TarjetaDebito tarjetaDebito = (TarjetaDebito) metodoPago;
        assertEquals("6543210987654321", tarjetaDebito.getNumeroTarjeta());
        assertEquals("456", tarjetaDebito.getCvv());
    }

    @Test
    public void crearPagoConDatosNulosDebeFallar() {

        TarjetaCreditoFactory factory = new TarjetaCreditoFactory();

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            MetodoPago metodoPago = factory.crearPago(null);
        });

        assertEquals("Los datos de tarjeta no pueden ser nulos.", excepcion.getMessage());
    }

    @Test
    public void crearPagoSinCvvDebeFallar() {
        TarjetaDebitoFactory factory = new TarjetaDebitoFactory();
        TarjetaDebitoDTO datos = new TarjetaDebitoDTO("1234567890123456", "");

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            MetodoPago metodoPago = factory.crearPago(datos);
        });

        assertEquals("El CVV debe tener exactamente 3 dígitos.", excepcion.getMessage());
    }
}
