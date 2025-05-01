package uade.tpo.modelo.cliente;

import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponDescuento.CuponVacio;
import uade.tpo.modelo.menu.Menu;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedido.Pedido;
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

    public void IniciarPedido() {
        Pedido pedido = new Pedido();
        this.pedidoActivo = pedido;
        pedidos.add(pedido);
    }

    public void CancelarPedido() {
        if (pedidoActivo == null) {
            throw new IllegalStateException("No hay pedido activo para cancelar.");
        }
        pedidos.remove(pedidoActivo);
        pedidoActivo = null;
    }

    public void AgregarProductoAPedidoActivo(Menu menu, String nombreProducto) {
        if (pedidoActivo == null) {
            throw new IllegalStateException("No hay pedido activo para agregar productos.");
        }
        Producto producto = menu.buscarProductoPorNombre(nombreProducto);
        if (producto == null) {
            throw new IllegalArgumentException("Producto no encontrado en el menú.");
        }
        pedidoActivo.agregarProducto(producto);
    }

    public void AplicarCuponACarrito(CuponDescuento cupon) {
        if (pedidoActivo == null) {
            throw new IllegalStateException("No hay pedido activo para aplicar cupón.");
        }
        this.pedidoActivo.AplicarCupon(cupon);
    }

    public void PagarPedido(MetodoPago metodoPago) {
        if (pedidoActivo == null) {
            throw new IllegalStateException("No hay pedido activo para pagar.");
        }

        pedidoActivo.asignarMetodoPago(metodoPago);
        pedidoActivo.pagarPedido(pedidoActivo.calcularPrecioTotal());
        pedidoActivo = null;
    }
}
