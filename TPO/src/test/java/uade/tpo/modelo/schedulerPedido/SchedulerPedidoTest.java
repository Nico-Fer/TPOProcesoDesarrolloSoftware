package uade.tpo.modelo.schedulerPedido;

import org.junit.jupiter.api.Test;
import uade.tpo.modelo.PlataformaStrategy.MobileStrategy;
import uade.tpo.modelo.pedido.Pedido;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SchedulerPedidoTest {
    @Test
    public void pedidoProgramadoDebeCambiarAEstadoEnEsperaAutomaticamente() throws InterruptedException {

        Pedido pedido = new Pedido(new MobileStrategy());
        LocalDateTime fechaProgramada = LocalDateTime.now().plusSeconds(1);
        pedido.programarPedido(fechaProgramada);

        Thread.sleep(2000);  // 2 segundos para dar tiempo al scheduler

        assertEquals("En espera", pedido.getNombreEstado());
    }

    @Test
    public void pedidoNoDebeCambiarSiHoraProgramadaYaPaso() throws InterruptedException {

        Pedido pedido = new Pedido(new MobileStrategy());
        LocalDateTime fechaProgramada = LocalDateTime.now().minusSeconds(1);
        pedido.programarPedido(fechaProgramada);

        Thread.sleep(1000);

        assertEquals("Reservado", pedido.getNombreEstado());
    }

    @Test
    public void pedidoProgramadoPuedeSerCanceladoAntesDeHora() {

        Pedido pedido = new Pedido(new MobileStrategy());
        LocalDateTime fechaProgramada = LocalDateTime.now().plusMinutes(10);
        pedido.programarPedido(fechaProgramada);

        boolean cancelado = pedido.cancelarPedido();

        assertTrue(cancelado);
        assertEquals("Reservado", pedido.getNombreEstado());
    }
}
