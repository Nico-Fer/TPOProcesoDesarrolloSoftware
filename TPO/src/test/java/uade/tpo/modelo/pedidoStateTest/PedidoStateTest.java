package uade.tpo.modelo.pedidoStateTest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import uade.tpo.modelo.PlataformaStrategy.MobileStrategy;
import uade.tpo.modelo.pedido.Pedido;

import static org.junit.jupiter.api.Assertions.*;

public class PedidoStateTest {

    @Test
    public void ConfirmarPedidoDebeAsignarEstadoEnEspera() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.confirmarPedido();
        assertEquals("En espera", pedido.getNombreEstado());
    }

    @Test
    public void avanzarDeEnEsperaACorrectamente() {
        Pedido pedido = new Pedido(new MobileStrategy());

        pedido.confirmarPedido();
        pedido.avanzarEstado();
        assertEquals("En Preparación", pedido.getNombreEstado());

        pedido.avanzarEstado();
        assertEquals("Listo Para Entregar", pedido.getNombreEstado());

        pedido.avanzarEstado();
        assertEquals("Pedido Entregado", pedido.getNombreEstado());
    }

    @Test
    public void avanzarDesdeEntregadoDebeLanzarExcepcion() {
        Pedido pedido = new Pedido(new MobileStrategy());

        pedido.confirmarPedido();
        pedido.avanzarEstado();
        pedido.avanzarEstado();
        pedido.avanzarEstado();

        IllegalStateException ex = assertThrows(IllegalStateException.class, pedido::avanzarEstado);
        assertEquals("El pedido ya fue entregado. No puede avanzar más.", ex.getMessage());
    }

    @Test
    public void avanzarSinConfirmarDebeLanzarExcepcion() {
        Pedido pedido = new Pedido(new MobileStrategy());

        IllegalStateException ex = assertThrows(IllegalStateException.class, pedido::avanzarEstado);
        assertEquals("El pedido debe ser confirmado primero.", ex.getMessage());
    }

    @Test
    public void confirmarPedidoDosVecesDebeLanzarExcepcion() {
        Pedido pedido = new Pedido(new MobileStrategy());

        pedido.confirmarPedido();
        IllegalStateException ex = assertThrows(IllegalStateException.class, pedido::confirmarPedido);
        assertEquals("El pedido ya fue confirmado.", ex.getMessage());
    }

    @Test
    public void nombreEstadoDebeSerNoConfirmadoInicialmente() {
        Pedido pedido = new Pedido(new MobileStrategy());

        assertEquals("No confirmado", pedido.getNombreEstado());
    }
}
