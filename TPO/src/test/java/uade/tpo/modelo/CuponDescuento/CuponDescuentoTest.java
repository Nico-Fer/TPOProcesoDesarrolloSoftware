package uade.tpo.modelo.CuponDescuento;

import org.junit.Test;
import uade.tpo.modelo.PlataformaStrategy.MobileStrategy;
import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponDescuento.CuponVacio;
import uade.tpo.modelo.cuponDescuento.PorcentajeDescuento;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CuponDescuentoTest {
    @Test
    public void aplicarCuponPorcentajeYCalcularTotal() {
        Pedido pedido = new Pedido(new MobileStrategy());
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(), null, 20f);
        Producto empanada = new Producto("Empanada", "Empanada de carne", 800.0, Collections.emptyList(), null, 15f);
        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);
        pedido.setCupon(cupon);
        double total = pedido.calcularPrecioTotal();
        assertEquals(2070.0, total, 0.01);
    }

    @Test
    public void aplicarCuponInvalidoLanzaExcepcion() {
        Pedido pedido = new Pedido(new MobileStrategy());
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(), null, 20f);
        Producto empanada = new Producto("Empanada", "Empanada de carne", 800.0, Collections.emptyList(), null, 15f);
        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);
        cupon.aplicarDescuento(1000.0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            pedido.setCupon(cupon);
        });
        assertEquals("Cupón inválido.", ex.getMessage());
    }

    @Test
    public void cuponVacioNoModificaElTotal() {
        Pedido pedido = new Pedido(new MobileStrategy());
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(), null, 20f);
        Producto empanada = new Producto("Empanada", "Empanada de carne", 800.0, Collections.emptyList(), null, 15f);
        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        CuponDescuento cupon = new CuponVacio();
        pedido.setCupon(cupon);
        double total = pedido.calcularPrecioTotal();
        assertEquals(2300.0, total);
    }
}
