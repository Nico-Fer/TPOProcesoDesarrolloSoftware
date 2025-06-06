package uade.tpo.modelo.producto;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductoTest {

    @Test
    void crearProductoCorrectamente() {

        Ingrediente queso = new Ingrediente("Queso", false);
        Ingrediente tomate = new Ingrediente("Tomate", false);

        Producto pizzaMuzarella = new Producto(
                "Pizza de Muzarella",
                "Pizza clásica con queso y tomate",
                1500.0,
                List.of(queso, tomate),
                null,
                20f
        );

        assertEquals("Pizza de Muzarella", pizzaMuzarella.getNombre());
        assertEquals("Pizza clásica con queso y tomate", pizzaMuzarella.getDescripcion());
        assertEquals(1500.0, pizzaMuzarella.getPrecio());
        assertEquals(2, pizzaMuzarella.getIngredientes().size());
        assertTrue(pizzaMuzarella.getIngredientes().contains(queso));
        assertTrue(pizzaMuzarella.getIngredientes().contains(tomate));
    }

    @Test
    void obtenerNombreDelProducto() {

        Ingrediente jamon = new Ingrediente("Jamón", false);
        Producto sandwich = new Producto(
                "Sándwich de Jamón",
                "Pan con jamón",
                900.0,
                List.of(jamon),
                null,
                10f
        );

        String nombre = sandwich.getNombre();

        assertEquals("Sándwich de Jamón", nombre);
    }

    @Test
    void obtenerDescripcionDelProducto() {

        Ingrediente lechuga = new Ingrediente("Lechuga", false);
        Producto ensalada = new Producto(
                "Ensalada verde",
                "Lechuga fresca con aderezo",
                750.0,
                List.of(lechuga),
                null,
                10f
        );

        String descripcion = ensalada.getDescripcion();

        assertEquals("Lechuga fresca con aderezo", descripcion);
    }

    @Test
    void obtenerPrecioDelProducto() {

        Ingrediente cebolla = new Ingrediente("Cebolla", false);
        Producto empanada = new Producto(
                "Empanada de cebolla",
                "Empanada con cebolla caramelizada",
                800.0,
                List.of(cebolla),
                null,
                15f
        );

        double precio = empanada.getPrecio();

        assertEquals(800.0, precio);
    }

    @Test
    void obtenerListaDeIngredientesDelProducto() {

        Ingrediente pollo = new Ingrediente("Pollo", false);
        Ingrediente arroz = new Ingrediente("Arroz", false);
        Producto plato = new Producto(
                "Arroz con Pollo",
                "Plato tradicional de arroz con pollo",
                1800.0,
                List.of(pollo, arroz),
                null,
                30f
        );

        List<Ingrediente> ingredientes = plato.getIngredientes();

        assertNotNull(ingredientes);
        assertEquals(2, ingredientes.size());
        assertTrue(ingredientes.contains(pollo));
        assertTrue(ingredientes.contains(arroz));
    }

    @Test
    void productoContieneIngredientesAlergenicos() {

        Ingrediente mani = new Ingrediente("Maní", true);
        Ingrediente chocolate = new Ingrediente("Chocolate", false);
        Producto postreDeMani = new Producto(
                "Postre de Maní",
                "Delicioso postre con maní",
                2200.0,
                List.of(mani, chocolate),
                null,
                20f
        );

        boolean contieneAlergenos = postreDeMani.tieneIngredientesAlergenicos();

        assertTrue(contieneAlergenos);
    }

    @Test
    void productoSinIngredientesAlergenicos() {

        Ingrediente queso = new Ingrediente("Queso", false);
        Ingrediente tomate = new Ingrediente("Tomate", false);
        Producto pizzaComun = new Producto(
                "Pizza común",
                "Pizza sin ingredientes alergénicos",
                1600.0,
                List.of(queso, tomate),
                null,
                20f
        );

        boolean contieneAlergenos = pizzaComun.tieneIngredientesAlergenicos();

        assertFalse(contieneAlergenos);
    }

    @Test
    void productoSinIngredientesDebeNoTenerAlergenicos() {

        Producto bebida = new Producto(
                "Agua Mineral",
                "Botella de agua sin gas",
                500.0,
                Collections.emptyList(),
                null,
                0f
        );

        boolean contieneAlergenos = bebida.tieneIngredientesAlergenicos();

        assertFalse(contieneAlergenos);
    }

    @Test
    void productoConIngredientesNullDebeNoTenerAlergenicos() {

        Producto gaseosa = new Producto(
                "Gaseosa",
                "Bebida carbonatada",
                700.0,
                null,
                null,
                0f
        );

        assertFalse(gaseosa.tieneIngredientesAlergenicos());
    }
}
