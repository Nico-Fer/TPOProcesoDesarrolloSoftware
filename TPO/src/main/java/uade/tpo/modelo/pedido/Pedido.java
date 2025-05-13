package uade.tpo.modelo.pedido;

import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponDescuento.CuponVacio;
import uade.tpo.modelo.observerPedido.NotificadorPedido;
import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedidoState.EnEsperaState;
import uade.tpo.modelo.pedidoState.EstadoPedidoState;
import uade.tpo.modelo.producto.Producto;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private List<Producto> productos = new ArrayList<>();
    private MetodoPago metodoPago;
    private EstadoPedidoState estadoPedido;
    private String numeroOrden;
    private static int contadorOrdenes = 1;
    private CuponDescuento cuponDescuento = new CuponVacio();
    NotificadorPedido notificadorPedido = new NotificadorPedido();

    public void setEstado(EstadoPedidoState estado) {
        this.estadoPedido = estado;
        this.estadoPedido.setPedido(this);
        this.notificadorPedido.notificarSuscriptores(this);
    }

    public void agregarProducto(Producto producto) {
        if (producto != null) productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void aplicarCupon(CuponDescuento cupon) {
        if (cupon == null || !cupon.esValido()) {
            throw new IllegalArgumentException("Cupón inválido.");
        }
        this.cuponDescuento = cupon;
    }

    public double calcularPrecioTotal() {
        double total = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        return cuponDescuento.aplicarDescuento(total);
    }

    public void asignarMetodoPago(MetodoPago metodoPago) {
        if(metodoPago != null) this.metodoPago = metodoPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void pagarPedido() {
        if (metodoPago == null) {
            throw new IllegalStateException("No se asignó un método de pago.");
        }
        metodoPago.procesarPago(this.calcularPrecioTotal());
    }

    public void confirmarPedido() {
        if (estadoPedido != null) {
            throw new IllegalStateException("El pedido ya fue confirmado.");
        }
        this.numeroOrden = String.format("%05d", contadorOrdenes++);
        this.setEstado(new EnEsperaState());
    }

    public void avanzarEstado() {
        if (estadoPedido == null) {
            throw new IllegalStateException("El pedido debe ser confirmado primero.");
        }
        estadoPedido.avanzarEstadoPedido();
    }

    public void agregarSuscriptor(ObserverPedido suscriptor) {
        notificadorPedido.agregarSuscriptor(suscriptor);
    }

    public void limpiarSuscriptores() {
        notificadorPedido.limpiarSuscriptores();
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public String getNombreEstado() {
        return estadoPedido != null ? estadoPedido.getNombreEstado() : "No confirmado";
    }

}
