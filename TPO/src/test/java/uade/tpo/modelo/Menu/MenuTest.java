package uade.tpo.modelo.Menu;

import org.junit.jupiter.api.Test;

import uade.tpo.modelo.enums.Categoria;
import uade.tpo.modelo.menu.Menu;
import uade.tpo.modelo.producto.Producto;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenuTest {

    @Test
    void agregarProductoAlMenuCorrectamente() {

        Menu menu = new Menu();
        Producto pizza = new Producto(
                "Pizza de Muzarella",
                "Pizza clásica",
                1500.0,
                Collections.emptyList(),
                null,
                20f
        );

        menu.agregarProducto(pizza);

        assertEquals(1, menu.getProductos().size());
        assertTrue(menu.getProductos().contains(pizza));
    }

    @Test
    void obtenerListaDeProductosDelMenu() {

        Menu menu = new Menu();
        Producto pizza = new Producto(
                "Pizza",
                "Pizza común",
                1500.0,
                Collections.emptyList(),
                null,
                20f
        );
        Producto empanada = new Producto(
                "Empanada",
                "Empanada de carne",
                800.0,
                Collections.emptyList(),
                null,
                15f
        );
        menu.agregarProducto(pizza);
        menu.agregarProducto(empanada);

        List<Producto> productos = menu.getProductos();

        assertEquals(2, productos.size());
        assertTrue(productos.contains(pizza));
        assertTrue(productos.contains(empanada));
    }

    @Test
    void buscarProductoExistentePorNombre() {

        Menu menu = new Menu();
        Producto pizza = new Producto(
                "Pizza",
                "Pizza napolitana",
                1600.0,
                Collections.emptyList(),
                null,
                20f
        );
        menu.agregarProducto(pizza);

        Producto resultado = menu.buscarProductoPorNombre("Pizza");

        assertNotNull(resultado);
        assertEquals("Pizza", resultado.getNombre());
    }

    @Test
    void buscarProductoInexistentePorNombre() {

        Menu menu = new Menu();
        Producto hamburguesa = new Producto(
                "Hamburguesa",
                "Hamburguesa con queso",
                1800.0,
                Collections.emptyList(),
                null,
                10f
        );
        menu.agregarProducto(hamburguesa);

        Producto resultado = menu.buscarProductoPorNombre("Sushi");

        assertNull(resultado);
    }

    @Test
    void filtrarProductosPorPrecioConResultados() {

        Menu menu = new Menu();
        Producto agua = new Producto(
                "Agua",
                "Botella de agua",
                500.0,
                Collections.emptyList(),
                null,
                0f
        );
        Producto pizza = new Producto(
                "Pizza",
                "Pizza especial",
                2000.0,
                Collections.emptyList(),
                null,
                20f
        );
        Producto hamburguesa = new Producto(
                "Hamburguesa",
                "Hamburguesa con queso",
                1000.0,
                Collections.emptyList(),
                null,
                10f
        );
        menu.agregarProducto(agua);
        menu.agregarProducto(pizza);
        menu.agregarProducto(hamburguesa);

        List<Producto> productosFiltrados = menu.filtrarProductosPorPrecio(1000.0);

        assertEquals(2, productosFiltrados.size());
        assertEquals("Agua", productosFiltrados.get(0).getNombre());
    }

    @Test
    void filtrarProductosPorPrecioSinResultados() {

        Menu menu = new Menu();
        Producto sushi = new Producto(
                "Sushi",
                "Rolls de salmón",
                3000.0,
                Collections.emptyList(),
                null,
                40f
        );
        Producto lomo = new Producto(
                "Lomo",
                "Lomo a la pimienta",
                3500.0,
                Collections.emptyList(),
                null,
                30f
        );
        menu.agregarProducto(sushi);
        menu.agregarProducto(lomo);

        List<Producto> productosFiltrados = menu.filtrarProductosPorPrecio(1000.0);

        assertTrue(productosFiltrados.isEmpty());
    }

    @Test
    void agregarProductosDuplicadosAlMenu() {

        Menu menu = new Menu();
        Producto pizza = new Producto(
                "Pizza",
                "Pizza común",
                1500.0,
                Collections.emptyList(),
                null,
                20f
        );


        menu.agregarProducto(pizza);
        menu.agregarProducto(pizza);

        assertEquals(1, menu.getProductos().size());
    }

    @Test
    void filtrarPorCategoriaEntradaDevuelveSoloEntradas() {
        Menu menu = new Menu();

        Producto entrada = new Producto(
                "Bruschetta",
                "Pan tostado con tomate",
                800.0,
                Collections.emptyList(),
                List.of(Categoria.ENTRADA),
                5f
        );

        menu.agregarProducto(entrada);

        List<Producto> resultado = menu.filtrarPorCategoria(Categoria.ENTRADA);
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(entrada));
    }

    @Test
    void filtrarPorCategoriaPrincipalDevuelveSoloPlatosPrincipales() {
        Menu menu = new Menu();

        Producto principal = new Producto(
                "Milanesa con papas",
                "Clásico plato argentino",
                2500.0,
                Collections.emptyList(),
                List.of(Categoria.PLATO_PRINCIPAL),
                15f
        );

        menu.agregarProducto(principal);

        List<Producto> resultado = menu.filtrarPorCategoria(Categoria.PLATO_PRINCIPAL);
        assertEquals(1, resultado.size());
        assertTrue(resultado.contains(principal));
    }
}
