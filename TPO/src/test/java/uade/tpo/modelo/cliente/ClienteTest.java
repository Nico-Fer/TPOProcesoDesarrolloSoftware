package uade.tpo.modelo.cliente;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uade.tpo.modelo.PlataformaStrategy.MobileStrategy;
import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponDescuento.PorcentajeDescuento;
import uade.tpo.modelo.menu.Menu;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pago.TarjetaCredito;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;
import uade.tpo.modelo.restaurante.Restaurante;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {
    private Cliente cliente;
    private Menu menu;
    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Juan", "Pérez", "Calle Falsa 123");

        Producto pizza = new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList(), null, 15f);
        Producto empanada = new Producto("Empanada", "Carne", 500.0, Collections.emptyList(), null, 15f);

        menu = new Menu();
        menu.agregarProducto(pizza);
        menu.agregarProducto(empanada);
        restaurante = new Restaurante("La Pizzería", menu);
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
        cliente.iniciarPedido(new MobileStrategy(), restaurante.getPedidoController());
        assertNotNull(cliente.getPedidos());
        assertEquals(1, cliente.getPedidos().size());
    }

    @Test
    void agregarProductoAlPedidoActivoExitosamente() {
        cliente.iniciarPedido(new MobileStrategy(), restaurante.getPedidoController());
        cliente.agregarProductoAPedido(menu, "Pizza");

        Pedido pedido = cliente.getPedidos().get(0);
        assertEquals(1, pedido.getProductos().size());
        assertEquals("Pizza", pedido.getProductos().get(0).getNombre());
    }

    @Test
    void agregarProductoSinPedidoActivoLanzaExcepcion() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.agregarProductoAPedido(menu, "Pizza");
        });
        assertEquals("No hay pedido activo para agregar productos.", ex.getMessage());
    }

    @Test
    void agregarProductoInexistenteLanzaExcepcion() {
        cliente.iniciarPedido(new MobileStrategy(), restaurante.getPedidoController());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            cliente.agregarProductoAPedido(menu, "Sushi");
        });
        assertEquals("Producto no encontrado en el menú.", ex.getMessage());
    }

    @Test
    void aplicarCuponCorrectamente() {
        cliente.iniciarPedido(new MobileStrategy(), restaurante.getPedidoController());
        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);

        assertDoesNotThrow(() -> cliente.aplicarCupon(cupon));
    }

    @Test
    void aplicarCuponSinPedidoActivoLanzaExcepcion() {
        CuponDescuento cupon = new PorcentajeDescuento("DESC10", 0.1);

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.aplicarCupon(cupon);
        });

        assertEquals("No hay pedido activo para aplicar cupón.", ex.getMessage());
    }

    @Test
    void pagarPedidoFlujoCompleto() {
        cliente.iniciarPedido(new MobileStrategy(), restaurante.getPedidoController());
        cliente.agregarProductoAPedido(menu, "Pizza");

        MetodoPago metodo = new TarjetaCredito("1234567890123456", "123");

        assertDoesNotThrow(() -> cliente.pagarPedido(metodo));

        assertThrows(IllegalStateException.class, () -> cliente.agregarProductoAPedido(menu, "Pizza"));
    }

    @Test
    void pagarPedidoSinPedidoActivoLanzaExcepcion() {
        MetodoPago metodo = new TarjetaCredito("1234567890123456", "123");

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.pagarPedido(metodo);
        });

        assertEquals("No hay pedido activo para pagar.", ex.getMessage());
    }

    @Test
    void cancelarPedidoActivoCorrectamente() {
        cliente.iniciarPedido(new MobileStrategy(), restaurante.getPedidoController());
        assertDoesNotThrow(() -> cliente.cancelarPedido(cliente.getPedidoActivo().getId()));
    }

    @Test
    void cancelarPedidoDebeEliminarElPedidoActivo() {
        cliente.iniciarPedido(new MobileStrategy(), restaurante.getPedidoController());
        cliente.cancelarPedido(cliente.getPedidoActivo().getId());

        Menu menu = new Menu();
        menu.agregarProducto(new Producto("Pizza", "Muzzarella", 1000.0, Collections.emptyList(), null, 20f));

        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.agregarProductoAPedido(menu, "Pizza");
        });

        assertEquals("No hay pedido activo para agregar productos.", ex.getMessage());
    }

    @Test
    void cancelarPedidoSinPedidoDebeLanzarExcepcion() {
        IllegalStateException ex = assertThrows(IllegalStateException.class, () -> {
            cliente.cancelarPedido("12345");
        });

        assertEquals("No hay pedido activo para cancelar.", ex.getMessage());
    }
}
