package uade.tpo.modelo.metodoPagoFactory;

import org.junit.Test;
import uade.tpo.modelo.dtos.TarjetaCreditoDTO;
import uade.tpo.modelo.enums.TipoMetodoPago;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pago.TarjetaCredito;

import static org.junit.jupiter.api.Assertions.*;

public class TarjetaCreditoFactoryTest {

    @Test
    public void tarjetaCreditoFactoryCreaMetodoPagoCorrectamente() {

        TipoMetodoPago tipoMetodoPago = TipoMetodoPago.TARJETA_CREDITO;
        TarjetaCreditoDTO datos = new TarjetaCreditoDTO("1234567890123456", "123");
        MetodoPagoFactory<TarjetaCreditoDTO> factory = (MetodoPagoFactory<TarjetaCreditoDTO>) MetodoPagoFactoryProvider.getFactory(tipoMetodoPago);

        MetodoPago metodoPago = factory.crearMetodoPago(datos);

        assertNotNull(metodoPago);
        assertInstanceOf(TarjetaCredito.class, metodoPago);

        TarjetaCredito tarjetaCredito = (TarjetaCredito) metodoPago;
        assertEquals("1234567890123456", tarjetaCredito.getNumeroTarjeta());
        assertEquals("123", tarjetaCredito.getCvv());
    }

    @Test
    public void crearMetodoPagoConDatosNulosDebeFallar() {
        TarjetaCreditoFactory factory = new TarjetaCreditoFactory();

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            MetodoPago metodoPago = factory.crearMetodoPago(null);
        });

        assertEquals("Los datos de tarjeta no pueden ser nulos.", excepcion.getMessage());
    }

    @Test
    public void crearMetodoPagoSinCvvDebeFallar() {
        TarjetaCreditoFactory factory = new TarjetaCreditoFactory();
        TarjetaCreditoDTO datos = new TarjetaCreditoDTO("1234567890123456", "");

        IllegalArgumentException excepcion = assertThrows(IllegalArgumentException.class, () -> {
            MetodoPago metodoPago = factory.crearMetodoPago(datos);
        });

        assertEquals("El CVV debe tener exactamente 3 dígitos.", excepcion.getMessage());
    }

}
