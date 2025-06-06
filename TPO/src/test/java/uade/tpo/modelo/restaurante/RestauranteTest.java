package uade.tpo.modelo.restaurante;

import org.junit.jupiter.api.Test;
import uade.tpo.modelo.menu.Menu;
import uade.tpo.modelo.producto.Producto;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class RestauranteTest {

    @Test
    void crearRestauranteCorrectamente() {

        Menu menu = new Menu();
        Restaurante restaurante = new Restaurante("La Pizzería", menu);

        assertEquals("La Pizzería", restaurante.getNombre());
        assertEquals(menu, restaurante.getMenu());
    }

    @Test
    void obtenerNombreDelRestaurante() {

        Menu menu = new Menu();
        Restaurante restaurante = new Restaurante("El Rincón del Sabor", menu);

        String nombre = restaurante.getNombre();

        assertEquals("El Rincón del Sabor", nombre);
    }

    @Test
    void obtenerMenuDelRestaurante() {

        Menu menu = new Menu();
        Producto pizza = new Producto(
                "Pizza",
                "Pizza de muzzarella",
                1500.0,
                Collections.emptyList(),
                null,
                20f
        );
        menu.agregarProducto(pizza);
        Restaurante restaurante = new Restaurante("Pizzería Central", menu);

        Menu menuObtenido = restaurante.getMenu();

        assertNotNull(menuObtenido);
        assertEquals(1, menuObtenido.getProductos().size());
        assertEquals("Pizza", menuObtenido.getProductos().get(0).getNombre());
    }

    @Test
    void cambiarNombreDelRestaurante() {

        Menu menu = new Menu();
        Restaurante restaurante = new Restaurante("Antiguo Nombre", menu);

        restaurante.cambiarNombre("Nuevo Nombre");

        assertEquals("Nuevo Nombre", restaurante.getNombre());
    }

    @Test
    void actualizarMenuDelRestaurante() {

        Menu menuViejo = new Menu();
        Producto pizzaVieja = new Producto(
                "Pizza vieja",
                "Pizza vieja sin queso",
                1200.0,
                Collections.emptyList(),
                null,
                20f
        );
        menuViejo.agregarProducto(pizzaVieja);

        Restaurante restaurante = new Restaurante("Restaurante Moderno", menuViejo);

        Menu menuNuevo = new Menu();
        Producto pizzaNueva = new Producto(
                "Pizza Nueva",
                "Pizza moderna con ingredientes premium",
                2500.0,
                Collections.emptyList(),
                null,
                20f
        );
        menuNuevo.agregarProducto(pizzaNueva);

        restaurante.actualizarMenu(menuNuevo);

        Menu menuActualizado = restaurante.getMenu();
        assertEquals(1, menuActualizado.getProductos().size());
        assertEquals("Pizza Nueva", menuActualizado.getProductos().get(0).getNombre());
    }

    @Test
    void actualizarMenuDelRestauranteConNull() {

        Menu menuViejo = new Menu();
        Producto pizzaVieja = new Producto(
                "Pizza vieja",
                "Pizza vieja sin queso",
                1200.0,
                Collections.emptyList(),
                null,
                20f
        );
        menuViejo.agregarProducto(pizzaVieja);

        Restaurante restaurante = new Restaurante("Restaurante Moderno", menuViejo);

        restaurante.actualizarMenu(null);

        assertNotNull(restaurante.getMenu());
    }
}
