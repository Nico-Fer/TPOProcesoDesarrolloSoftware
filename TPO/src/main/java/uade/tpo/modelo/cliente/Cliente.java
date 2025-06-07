package uade.tpo.modelo.cliente;

import uade.tpo.modelo.PlataformaStrategy.PlataformaStrategy;
import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.menu.Menu;
import uade.tpo.modelo.observerPedido.ObserverCliente;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedido.Pedido;
import uade.tpo.modelo.pedidoController.PedidoController;
import uade.tpo.modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nombre;
    private String apellido;
    private String ubicacion;
    private List<Pedido> pedidos = new ArrayList<Pedido>();
    private Pedido pedidoActivo;

    public Cliente(String nombre, String apellido, String ubicacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ubicacion = ubicacion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public Pedido getPedidoActivo() { return pedidoActivo; }

    public void iniciarPedido(PlataformaStrategy strategy, PedidoController controller) {
        Pedido pedido = controller.crearPedido(strategy);
        this.pedidoActivo = pedido;
        pedidos.add(pedido);
    }

    public void cancelarPedido(String pedidoId) {
        Pedido pedido = pedidos.stream().filter(p -> p.getId().equals(pedidoId)).findFirst().orElse(null);
        if (pedido == null) {
            throw new IllegalStateException("No hay pedido activo para cancelar.");
        }
        if(pedido.cancelarPedido()){
            pedidos.remove(pedido);
            if (pedidoActivo != null && pedidoActivo.getId().equals(pedido.getId())) {
                pedidoActivo = null;
            }
        }
    }

    public void agregarProductoAPedido(Menu menu, String nombreProducto) {
        if (pedidoActivo == null) {
            throw new IllegalStateException("No hay pedido activo para agregar productos.");
        }
        Producto producto = menu.buscarProductoPorNombre(nombreProducto);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado en el menú.");
        }
        pedidoActivo.agregarProducto(producto);
    }

    public void aplicarCupon(CuponDescuento cupon) {
        if (pedidoActivo == null) {
            throw new IllegalStateException("No hay pedido activo para aplicar cupón.");
        }
        this.pedidoActivo.setCupon(cupon);
    }

    public void pagarPedido(MetodoPago metodoPago) {
        if (pedidoActivo == null) {
            throw new IllegalStateException("No hay pedido activo para pagar.");
        }

        pedidoActivo.asignarMetodoPago(metodoPago);
        pedidoActivo.pagarPedido();
        suscribirPedido();
        pedidoActivo = null;
    }

    public void suscribirPedido() {
        if (pedidoActivo != null) {
            pedidoActivo.agregarSuscriptor(new ObserverCliente(this));
        }
    }
}
