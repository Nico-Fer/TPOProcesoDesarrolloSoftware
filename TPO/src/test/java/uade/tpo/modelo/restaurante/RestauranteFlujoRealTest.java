package uade.tpo.modelo.restaurante;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uade.tpo.modelo.PlataformaStrategy.MobileStrategy;
import uade.tpo.modelo.cliente.Cliente;
import uade.tpo.modelo.menu.Menu;
import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pago.TarjetaCredito;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.personalRestaurante.Mesero;
import uade.tpo.modelo.producto.Producto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteFlujoRealTest {
    private Restaurante restaurante;
    private Cliente cliente;
    private Mesero mesero;
    private Pedido pedido;

    private List<String> notificacionesCliente;
    private List<String> notificacionesMesero;

    @BeforeEach
    void setUp() {

        restaurante = new Restaurante("Resto Central", new Menu());
        mesero = new Mesero("Lucía", "Mesero");
        restaurante.registrarPersonal(mesero);

        cliente = new Cliente("Juan", "Pérez", "Av. Siempre Viva 123");
        cliente.iniciarPedido(new MobileStrategy(), restaurante.getPedidoController());

        pedido = cliente.getPedidoActivo();
        Producto producto = new Producto("Pizza", "Mozzarella", 1000.0, Collections.emptyList(), null, 20f);
        pedido.agregarProducto(producto);
        pedido.asignarMetodoPago(new TarjetaCredito("1234567812345678", "123"));

        notificacionesCliente = new ArrayList<>();
        notificacionesMesero = new ArrayList<>();

        pedido.agregarSuscriptor(new ObserverPedido() {
            @Override
            public void notificarEstado(Pedido p) {
                notificacionesCliente.add("Cliente " + cliente.getNombre() + ": " + p.getNombreEstado());
            }
        });

        restaurante.suscribirPersonalAPedido(pedido.getId());
        pedido.agregarSuscriptor(new ObserverPedido() {
            @Override
            public void notificarEstado(Pedido p) {
                if (p.getNombreEstado().equalsIgnoreCase("Listo Para Entregar")) {
                    notificacionesMesero.add("Mesero " + mesero.getNombre() + ": " + p.getNombreEstado());
                }
            }
        });
    }

    @Test
    void registrarPersonalYVerificarCantidad() {
        assertEquals(1, restaurante.getPersonalRegistrados().size());
        assertTrue(restaurante.getPersonalRegistrados().contains(mesero));
    }

    @Test
    void clienteRecibeNotificacionesEnTodosLosEstados() {
        pedido.confirmarPedido();
        pedido.avanzarEstado();
        pedido.avanzarEstado();

        assertEquals(3, notificacionesCliente.size());
        assertTrue(notificacionesCliente.get(0).contains("En espera"));
        assertTrue(notificacionesCliente.get(1).contains("En Preparación"));
        assertTrue(notificacionesCliente.get(2).contains("Listo Para Entregar"));
    }

    @Test
    void meseroSoloRecibeNotificacionCuandoPedidoEstaListo() {
        pedido.confirmarPedido();
        pedido.avanzarEstado();
        assertEquals(0, notificacionesMesero.size());

        pedido.avanzarEstado();
        assertEquals(1, notificacionesMesero.size());
        assertTrue(notificacionesMesero.get(0).contains("Listo Para Entregar"));
    }
}

