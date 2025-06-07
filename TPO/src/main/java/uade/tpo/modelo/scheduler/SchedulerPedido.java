package uade.tpo.modelo.scheduler;

import uade.tpo.modelo.pedidoState.ProgramadoState;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulerPedido {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void agendar(ProgramadoState estado, LocalDateTime fechaHora) {
        long delay = ChronoUnit.MILLIS.between(LocalDateTime.now(), fechaHora);
        if (delay < 0) return;

        scheduler.schedule(estado::notificarHoraAlcanzada, delay, TimeUnit.MILLISECONDS);
    }
}
