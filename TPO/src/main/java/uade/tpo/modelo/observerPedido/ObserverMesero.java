package uade.tpo.modelo.observerPedido;

import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.personalRestaurante.Mesero;

public class ObserverMesero implements ObserverPedido{
    private final Mesero mesero;

    public ObserverMesero(Mesero mesero) {
        this.mesero = mesero;
    }

    @Override
    public void notificarEstado(Pedido pedido) {
        if (pedido.getNombreEstado().equalsIgnoreCase("Listo Para Entregar")) {
            System.out.println("Mesero " + mesero.getNombre() + ": el pedido #" + pedido.getNumeroOrden() + " está listo para entregar.");
        }
    }
}
