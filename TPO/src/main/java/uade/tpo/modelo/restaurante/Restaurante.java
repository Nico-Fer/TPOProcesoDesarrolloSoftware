package uade.tpo.modelo.restaurante;

import uade.tpo.modelo.menu.Menu;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.pedidoController.PedidoController;
import uade.tpo.modelo.personalRestaurante.Mesero;
import uade.tpo.modelo.personalRestaurante.PersonalRestaurante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Restaurante {
    private String nombre;
    private Menu menu;
    private List<PersonalRestaurante> personalRestaurante = new ArrayList<>();
    private PedidoController pedidoController;

    public Restaurante(String nombre, Menu menu) {
        this.nombre = nombre;
        this.menu = Objects.requireNonNullElseGet(menu, Menu::new);
        this.pedidoController = new PedidoController(this);
    }

    public String getNombre() {
        return nombre;
    }

    public Menu getMenu() {
        return menu;
    }

    public void cambiarNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void actualizarMenu(Menu nuevoMenu) {
        if (nuevoMenu == null) return;
        this.menu = nuevoMenu;
    }

    public void registrarPersonal(PersonalRestaurante personal) {
        if (personal != null) {
            personalRestaurante.add(personal);
        }
    }

    public PedidoController getPedidoController() {
        return pedidoController;
    }

    public Float calcularTiempoRestantePedido(String idPedido) {
        return pedidoController.calcularTiempoRestante(idPedido);
    }

    public void avanzarEstadoPedido(String idPedido) {
        pedidoController.avanzarEstadoPedido(idPedido);
    }

    public void suscribirPersonalAPedido(String idPedido) {
        for (PersonalRestaurante p : personalRestaurante) {
            if (p instanceof Mesero mesero) {
                pedidoController.agregarSuscriptor(idPedido, mesero.suscribirPedido());
            }
        }
    }

    public List<PersonalRestaurante> getPersonalRegistrados() {
        return Collections.unmodifiableList(personalRestaurante);
    }
}
