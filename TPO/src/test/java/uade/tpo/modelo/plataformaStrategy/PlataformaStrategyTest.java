package uade.tpo.modelo.plataformaStrategy;

import org.junit.Test;
import uade.tpo.modelo.PlataformaStrategy.MobileStrategy;
import uade.tpo.modelo.PlataformaStrategy.PlataformaStrategy;
import uade.tpo.modelo.PlataformaStrategy.TotemStrategy;
import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponDescuento.PorcentajeDescuento;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PlataformaStrategyTest {

    @Test
    public void sePuedeCrearPedidoConTotemStrategy() {
        PlataformaStrategy estrategia = new TotemStrategy();
        Pedido pedido = new Pedido(estrategia);
        assertNotNull(pedido);
    }

    @Test
    public void sePuedeCrearPedidoConMobileStrategy() {
        PlataformaStrategy estrategia = new MobileStrategy();
        Pedido pedido = new Pedido(estrategia);
        assertNotNull(pedido);
    }

    @Test
    public void totemPermiteAplicarCuponSinValidar() {
        PlataformaStrategy estrategia = new TotemStrategy();
        Pedido pedido = new Pedido(estrategia);
        Producto pizza = new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList(),
                null);
        pedido.agregarProducto(pizza);

        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);
        cupon.aplicarDescuento(1000.0);

        assertDoesNotThrow(() -> {
            pedido.setCupon(cupon);
            double total = pedido.calcularPrecioTotal();
            assertTrue(total < 1000);
        });
    }

    @Test
    public void mobileRechazaCuponInvalido() {
        PlataformaStrategy estrategia = new MobileStrategy();
        Pedido pedido = new Pedido(estrategia);
        Producto pizza = new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList(),
                null);
        pedido.agregarProducto(pizza);

        CuponDescuento cupon = new PorcentajeDescuento("XYZ", 0.5);
        cupon.aplicarDescuento(1000);

        assertThrows(IllegalArgumentException.class, () -> pedido.setCupon(cupon));
    }

    @Test
    public void mobileAplicaCuponValidoCorrectamente() {
        PlataformaStrategy estrategia = new MobileStrategy();
        Pedido pedido = new Pedido(estrategia);
        Producto pizza = new Producto("Pizza", "Muzzarella", 500.0, Collections.emptyList(),
                null);
        pedido.agregarProducto(pizza);

        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);
        pedido.setCupon(cupon);

        double total = pedido.calcularPrecioTotal();
        assertEquals(450.0, total, 0.01);
    }

}

