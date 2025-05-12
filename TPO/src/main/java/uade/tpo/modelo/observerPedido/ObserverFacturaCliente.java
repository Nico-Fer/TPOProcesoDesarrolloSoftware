package uade.tpo.modelo.observerPedido;

import uade.tpo.modelo.cliente.Cliente;
import uade.tpo.modelo.factura.CorreoFactura;
import uade.tpo.modelo.factura.Factura;
import uade.tpo.modelo.pedido.Pedido;

public class ObserverFacturaCliente implements ObserverPedido{
    private final Cliente cliente;

    public ObserverFacturaCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void notificarEstado(Pedido pedido) {
        if (pedido.getNombreEstado().equalsIgnoreCase("Pedido Entregado")) {
            Factura factura = new Factura(pedido, cliente);
            CorreoFactura.enviarFactura(factura, cliente);
        }
    }

}
