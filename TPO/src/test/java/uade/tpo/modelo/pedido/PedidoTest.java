package uade.tpo.modelo.pedido;

import org.junit.Test;
import uade.tpo.modelo.PlataformaStrategy.MobileStrategy;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pago.TarjetaCredito;
import uade.tpo.modelo.producto.Producto;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoTest {

    @Test
    public void agregarProductoAlPedidoCorrectamente() {

        Pedido pedido = new Pedido(new MobileStrategy());
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(),
                null);

        pedido.agregarProducto(pizza);

        assertEquals(1, pedido.getProductos().size());
        assertTrue(pedido.getProductos().contains(pizza));
    }

    @Test
    public void obtenerListaDeProductosDelPedido() {

        Pedido pedido = new Pedido(new MobileStrategy());
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(),
                null);
        Producto empanada = new Producto("Empanada", "Empanada de carne", 800.0, Collections.emptyList(),
                null);
        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        List<Producto> productos = pedido.getProductos();

        assertEquals(2, productos.size());
        assertTrue(productos.contains(pizza));
        assertTrue(productos.contains(empanada));
    }

    @Test
    public void calcularPrecioTotalDeProductos() {

        Pedido pedido = new Pedido(new MobileStrategy());
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(),
                null);
        Producto empanada = new Producto("Empanada", "Empanada de carne", 800.0, Collections.emptyList(),
                null);
        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        double total = pedido.calcularPrecioTotal();

        assertEquals(2300.0, total);
    }

    @Test
    public void calcularPrecioTotalDePedidoVacioDebeSerCero() {

        Pedido pedido = new Pedido(new MobileStrategy());
        double total = pedido.calcularPrecioTotal();

        assertEquals(0.0, total);
    }

    @Test
    public void asignarMetodoPagoCorrectamente() {

        Pedido pedido = new Pedido(new MobileStrategy());
        MetodoPago metodoPago = new TarjetaCredito("1234567890123456", "123");

        pedido.asignarMetodoPago(metodoPago);

        assertEquals(metodoPago, pedido.getMetodoPago());
    }

    @Test
    public void pagarPedidoConMetodoPagoAsignadoNoLanzaExcepciones() {

        Pedido pedido = new Pedido(new MobileStrategy());
        MetodoPago metodoPago = new TarjetaCredito("1234567890123456", "123");
        pedido.asignarMetodoPago(metodoPago);

        assertDoesNotThrow(pedido::pagarPedido);
    }

    @Test
    public void pagarPedidoSinMetodoPagoAsignadoDebeLanzarExcepcion() {

        Pedido pedido = new Pedido(new MobileStrategy());
        IllegalStateException excepcion = assertThrows(IllegalStateException.class, pedido::pagarPedido);

        assertEquals("No se asignó un método de pago.", excepcion.getMessage());
    }

    @Test
    public void confirmarPedidoDebeAsignarNumeroDeOrden() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.confirmarPedido();
        String numeroOrden = pedido.getNumeroOrden();

        assertNotNull(numeroOrden);
        assertEquals(5, numeroOrden.length());
        assertTrue(numeroOrden.matches("\\d{5}"));
    }

    @Test
    public void numeroDeOrdenDebeSerUnicoPorPedido() {
        Pedido pedido1 = new Pedido(new MobileStrategy());
        Pedido pedido2 = new Pedido(new MobileStrategy());

        pedido1.confirmarPedido();
        pedido2.confirmarPedido();

        assertNotEquals(pedido1.getNumeroOrden(), pedido2.getNumeroOrden());
    }

}
