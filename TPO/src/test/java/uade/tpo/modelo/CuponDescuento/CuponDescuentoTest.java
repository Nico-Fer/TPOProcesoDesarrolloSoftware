package uade.tpo.modelo.CuponDescuento;

import org.junit.Test;
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
        Pedido pedido = new Pedido();
        Producto pizza = new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList());
        Producto empanada = new Producto("Empanada", "Carne", 500.0, Collections.emptyList());
        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);
        pedido.AplicarCupon(cupon);
        double total = pedido.calcularPrecioTotal();
        assertEquals(1350.0, total, 0.01);
    }

    @Test
    public void aplicarCuponInvalidoLanzaExcepcion() {
        Pedido pedido = new Pedido();
        Producto pizza = new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList());
        Producto empanada = new Producto("Empanada", "Carne", 500.0, Collections.emptyList());
        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);
        cupon.AplicarDescuento(1000.0);

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            pedido.AplicarCupon(cupon);
        });
        assertEquals("Cupón inválido.", ex.getMessage());
    }

    @Test
    public void cuponVacioNoModificaElTotal() {
        Pedido pedido = new Pedido();
        Producto pizza = new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList());
        Producto empanada = new Producto("Empanada", "Carne", 500.0, Collections.emptyList());
        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        CuponDescuento cupon = new CuponVacio();
        pedido.AplicarCupon(cupon);
        double total = pedido.calcularPrecioTotal();
        assertEquals(1500.0, total);
    }
}
