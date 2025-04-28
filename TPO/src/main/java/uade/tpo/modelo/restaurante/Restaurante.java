package uade.tpo.modelo.restaurante;

import uade.tpo.modelo.menu.Menu;

import java.util.Objects;

public class Restaurante {
    private String nombre;
    private Menu menu;

    public Restaurante(String nombre, Menu menu) {
        this.nombre = nombre;
        this.menu = Objects.requireNonNullElseGet(menu, Menu::new);

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
}
