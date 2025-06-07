package uade.tpo.modelo.pedidoStateTest;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import uade.tpo.modelo.PlataformaStrategy.MobileStrategy;
import uade.tpo.modelo.PlataformaStrategy.TotemStrategy;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.producto.Producto;

import java.util.Collections;

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
    public void nombreEstadoDebeSerEnArmadoInicialmente() {
        Pedido pedido = new Pedido(new MobileStrategy());

        assertEquals("En armado", pedido.getNombreEstado());
    }

    @Test
    public void tiempoRestanteEnEsperaConMenosDeDiezPedidosDebeSerCincoMinutosEnMobile() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.confirmarPedido(); // Cambia a EnEspera
        Float tiempo = pedido.calcularTiempoRestante(5);

        assertEquals(5.0f, tiempo);
    }

    @Test
    public void tiempoRestanteEnEsperaConMenosDeDiezPedidosDebeSerCincoMinutosEnTotem() {
        Pedido pedido = new Pedido(new TotemStrategy());
        pedido.confirmarPedido(); // Cambia a EnEspera
        Float tiempo = pedido.calcularTiempoRestante(5);

        assertEquals(5.0f, tiempo);
    }

    @Test
    public void tiempoRestanteEnEsperaConMultiploDeDiezPedidosDebeSerVeintePorCadaDiezEnMobile() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.confirmarPedido(); // Cambia a EnEspera

        Float tiempo = pedido.calcularTiempoRestante(30);

        assertEquals(60.0f, tiempo);
    }

    @Test
    public void tiempoRestanteEnEsperaConMultiploDeDiezPedidosDebeSerVeintePorCadaDiezEnTotem() {
        Pedido pedido = new Pedido(new TotemStrategy());
        pedido.confirmarPedido(); // Cambia a EnEspera

        Float tiempo = pedido.calcularTiempoRestante(30);

        assertEquals(60.0f, tiempo);
    }

    @Test
    public void tiempoRestanteEnPreparacionEsSumaDeTiemposDeProductosEnMobile() {
        Pedido pedido = new Pedido(new MobileStrategy());
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(), null, 20f);
        Producto empanada = new Producto("Empanada", "Carne", 800.0, Collections.emptyList(), null, 15f);

        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        pedido.confirmarPedido(); // EnEspera
        pedido.avanzarEstado();   // EnPreparacion

        Float tiempo = pedido.calcularTiempoRestante(0);

        assertEquals(35.0f, tiempo);
    }

    @Test
    public void tiempoRestanteEnPreparacionEsSumaDeTiemposDeProductosEnTotem() {
        Pedido pedido = new Pedido(new TotemStrategy());
        Producto pizza = new Producto("Pizza", "Pizza de muzzarella", 1500.0, Collections.emptyList(), null, 20f);
        Producto empanada = new Producto("Empanada", "Carne", 800.0, Collections.emptyList(), null, 15f);

        pedido.agregarProducto(pizza);
        pedido.agregarProducto(empanada);

        pedido.confirmarPedido(); // EnEspera
        pedido.avanzarEstado();   // EnPreparacion

        Float tiempo = pedido.calcularTiempoRestante(0);

        assertEquals(35.0f, tiempo);
    }

    @Test
    public void tiempoRestanteListoParaEntregarEnMobileDebeSerDeliverySimulado() {
        Pedido pedido = new Pedido(new MobileStrategy());
        pedido.confirmarPedido(); // EnEspera
        pedido.avanzarEstado();   // EnPreparacion
        pedido.avanzarEstado();   // Listo para entregar

        Float tiempo = pedido.calcularTiempoRestante(0);

        assertEquals(15.0f, tiempo);
    }

    @Test
    public void tiempoRestanteListoParaEntregarEnRestauranteDebeSerCero() {
        Pedido pedido = new Pedido(new TotemStrategy());
        pedido.confirmarPedido(); // EnEspera
        pedido.avanzarEstado();   // EnPreparacion
        pedido.avanzarEstado();   // Listo para entregar

        Float tiempo = pedido.calcularTiempoRestante(0);

        assertEquals(0f, tiempo);
    }
}
