package uade.tpo.modelo.personalRestaurante;

import uade.tpo.modelo.observerPedido.ObserverMesero;
import uade.tpo.modelo.observerPedido.ObserverPedido;

public class Mesero extends PersonalRestaurante{

    public Mesero(String nombre, String rol) {
        super(nombre, "Mesero");
    }

    public ObserverPedido suscribirPedido() {
        return new ObserverMesero(this);
    }
}
