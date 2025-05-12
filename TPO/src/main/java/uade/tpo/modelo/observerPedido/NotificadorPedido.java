package uade.tpo.modelo.observerPedido;

import uade.tpo.modelo.pedido.Pedido;

import java.util.ArrayList;
import java.util.List;

public class NotificadorPedido {
    private List<ObserverPedido> suscriptores = new ArrayList<>();

    public void agregarSuscriptor(ObserverPedido suscriptor) {
        suscriptores.add(suscriptor);
    }

    public void notificarSuscriptores(Pedido pedido) {
        for (ObserverPedido o : suscriptores) {
            o.notificarEstado(pedido);
        }
    }

    public void limpiarSuscriptores() {
        suscriptores.clear();
    }
}
