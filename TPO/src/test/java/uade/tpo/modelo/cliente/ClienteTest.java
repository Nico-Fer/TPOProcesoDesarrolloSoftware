package uade.tpo.modelo.cliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponDescuento.PorcentajeDescuento;
import uade.tpo.modelo.menu.Menu;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pago.TarjetaCredito;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    private Cliente cliente;
    private Menu menu;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Juan", "Pérez", "Calle Falsa 123");

        Producto pizza = new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList());
        Producto empanada = new Producto("Empanada", "Carne", 500.0, Collections.emptyList());

        menu = new Menu();
        menu.agregarProducto(pizza);
        menu.agregarProducto(empanada);
    }

    @Test
    void crearClienteCorrectamente() {

        assertEquals("Juan", cliente.getNombre());
        assertEquals("Pérez", cliente.getApellido());
        assertEquals("Calle Falsa 123", cliente.getUbicacion());
        assertTrue(cliente.getPedidos().isEmpty());
    }

    @Test
    void iniciarPedidoAgregaAPedidosDelCliente() {
        cliente.IniciarPedido();
        assertNotNull(cliente.getPedidos());
        assertEquals(1, cliente.getPedidos().size());
    }

    @Test
    void agregarProductoAlPedidoActivoExitosamente() {
        cliente.IniciarPedido();
        cliente.AgregarProductoAPedidoActivo(menu, "Pizza");

        Pedido pedido = cliente.getPedidos().get(0);
        assertEquals(1, pedido.getProductos().size());
        assertEquals("Pizza", pedido.getProductos().get(0).getNombre());
    }

    @Test
    void agregarProductoSinPedidoActivoLanzaExcepcion() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.AgregarProductoAPedidoActivo(menu, "Pizza");
        });
        assertEquals("No hay pedido activo para agregar productos.", ex.getMessage());
    }

    @Test
    void agregarProductoInexistenteLanzaExcepcion() {
        cliente.IniciarPedido();

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cliente.AgregarProductoAPedidoActivo(menu, "Sushi");
        });
        assertEquals("Producto no encontrado en el menú.", ex.getMessage());
    }

    @Test
    void aplicarCuponCorrectamente() {
        cliente.IniciarPedido();
        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);

        assertDoesNotThrow(() -> cliente.AplicarCuponACarrito(cupon));
    }

    @Test
    void aplicarCuponSinPedidoActivoLanzaExcepcion() {
        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.AplicarCuponACarrito(cupon);
        });

        assertEquals("No hay pedido activo para aplicar cupón.", ex.getMessage());
    }

    @Test
    void pagarPedidoFlujoCompleto() {
        cliente.IniciarPedido();
        cliente.AgregarProductoAPedidoActivo(menu, "Pizza");

        MetodoPago metodo = new TarjetaCredito("1234567890123456", "123");

        assertDoesNotThrow(() -> cliente.PagarPedido(metodo));

        assertThrows(IllegalStateException.class, () -> cliente.AgregarProductoAPedidoActivo(menu, "Pizza"));
    }

    @Test
    void pagarPedidoSinPedidoActivoLanzaExcepcion() {
        MetodoPago metodo = new TarjetaCredito("1234567890123456", "123");

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.PagarPedido(metodo);
        });

        assertEquals("No hay pedido activo para pagar.", ex.getMessage());
    }

    @Test
    void cancelarPedidoActivoCorrectamente() {
        cliente.IniciarPedido();
        assertDoesNotThrow(() -> cliente.CancelarPedido());
    }

    @Test
    void cancelarPedidoDebeEliminarElPedidoActivo() {
        cliente.IniciarPedido();
        cliente.CancelarPedido();

        Menu menu = new Menu();
        menu.agregarProducto(new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList()));

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.AgregarProductoAPedidoActivo(menu, "Pizza");
        });

        assertEquals("No hay pedido activo para agregar productos.", ex.getMessage());
    }

    @Test
    void cancelarPedidoSinPedidoActivoDebeLanzarExcepcion() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.CancelarPedido();
        });

        assertEquals("No hay pedido activo para cancelar.", ex.getMessage());
    }
}
