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
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(), null, 20f);

        pedido.agregarProducto(pizza);

        assertEquals(1, pedido.getProductos().size());
        assertTrue(pedido.getProductos().contains(pizza));
    }

    @Test
    public void obtenerListaDeProductosDelPedido() {

        Pedido pedido = new Pedido(new MobileStrategy());
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(), null, 20f);
        Producto empanada = new Producto("Empanada", "Empanada de carne", 800.0, Collections.emptyList(), null, 15f);
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
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(), null, 20f);
        Producto empanada = new Producto("Empanada", "Empanada de carne", 800.0, Collections.emptyList(), null, 15f);
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

    @Test
    public void agregarProductoEnEstadoEnArmadoDebeAgregarCorrectamente() {
        Pedido pedido = new Pedido(new MobileStrategy());
        Producto empanada = new Producto("Empanada", "Empanada de carne", 800.0, Collections.emptyList(), null, 15f);

        pedido.agregarProducto(empanada);

        assertEquals(1, pedido.getProductos().size());
        assertTrue(pedido.getProductos().contains(empanada));
    }

    @Test
    public void agregarProductoEnEstadoEnEsperaDebeAgregarCorrectamente() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.confirmarPedido();
        Producto bebida = new Producto("Bebida", "Agua", 300.0, Collections.emptyList(), null, 5f);

        pedido.agregarProducto(bebida);

        assertEquals(1, pedido.getProductos().size());
        assertTrue(pedido.getProductos().contains(bebida));
    }

    @Test
    public void agregarProductoEnEstadoNoPermitidoNoDebeAgregarProducto() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.confirmarPedido();
        pedido.avanzarEstado();
        Producto pizza = new Producto("Pizza", "Muzzarella", 1500.0, Collections.emptyList(), null, 20f);

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            pedido.agregarProducto(pizza);
        });

        assertEquals("No se puede agregar productos en este estado.", exception.getMessage());
    }

    @Test
    public void pagarEnEstadoNoPermitidoDebeLanzarExcepcion() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.asignarMetodoPago(new TarjetaCredito("1234567890123456", "123"));

        Producto pizza = new Producto("Pizza", "Muzzarella", 1500.0, Collections.emptyList(), null, 20f);
        pedido.agregarProducto(pizza);

        pedido.pagarPedido();
        pedido.confirmarPedido();
        pedido.avanzarEstado();
        pedido.avanzarEstado();

        IllegalStateException exception = assertThrows(IllegalStateException.class, pedido::pagarPedido);

        assertEquals("El pedido ya fue pagado o está en proceso.", exception.getMessage());
    }

    @Test
    public void pagarPedidoDebeMarcarProductosComoPagados() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.asignarMetodoPago(new TarjetaCredito("1234567890123456", "123"));
        Producto pizza = new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList(), null, 20f);
        pedido.agregarProducto(pizza);

        pedido.pagarPedido();

        assertEquals(0.0, pedido.calcularPrecioTotal());
    }

    @Test
    public void pagarNuevosProductosLuegoDePagoInicialDebeCobrarSoloLosNuevos() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.asignarMetodoPago(new TarjetaCredito("1234567890123456", "123"));

        Producto pizza = new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList(), null, 20f);
        pedido.agregarProducto(pizza);
        pedido.pagarPedido();

        Producto empanada = new Producto("Empanada", "De carne", 500.0, Collections.emptyList(), null, 15f);
        pedido.agregarProducto(empanada);

        double nuevoTotal = pedido.calcularPrecioTotal();

        assertEquals(500.0, nuevoTotal);
    }

}
