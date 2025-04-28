package uade.tpo.modelo.cliente;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    @Test
    void crearClienteCorrectamente() {
        Cliente cliente = new Cliente("Nicolas", "Fernandez", "Direccion 123");

        assertEquals("Nicolas", cliente.getNombre());
        assertEquals("Fernandez", cliente.getApellido());
        assertEquals("Direccion 123", cliente.getUbicacion());
    }
}
