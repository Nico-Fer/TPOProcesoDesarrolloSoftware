package uade.tpo.modelo.factura;

import uade.tpo.modelo.cliente.Cliente;

public class CorreoFactura {

    public static void enviarFactura(Factura factura, Cliente cliente) {
        String mensaje = "Factura enviada a " + cliente.getNombre() + ": " + factura.generarFactura();
        System.out.println(mensaje);
    }

}
