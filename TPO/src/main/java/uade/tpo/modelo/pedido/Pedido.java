package uade.tpo.modelo.pedido;

import uade.tpo.modelo.PlataformaStrategy.PlataformaStrategy;
import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedidoState.EnEsperaState;
import uade.tpo.modelo.pedidoState.EstadoPedidoState;
import uade.tpo.modelo.producto.Producto;

import java.util.List;

public class Pedido {
    private MetodoPago metodoPago;
    private EstadoPedidoState estadoPedido;
    private String numeroOrden;
    private static int contadorOrdenes = 1;
    private final Carrito carrito = new Carrito();
    private final PlataformaStrategy plataformaStrategy;

    public Pedido(PlataformaStrategy plataformaStrategy) {
        this.plataformaStrategy = plataformaStrategy;
    }

    public void setEstado(EstadoPedidoState estado) {
        this.estadoPedido = estado;
        this.estadoPedido.setPedido(this);
        this.plataformaStrategy.notificarCambioEstado(this);
    }

    public void agregarProducto(Producto producto) {
        if (producto != null) carrito.agregarProducto(producto);
    }

    public List<Producto> getProductos() {
        return carrito.getProductos();
    }

    public void setCupon(CuponDescuento cupon) {
        plataformaStrategy.setCupon(cupon);
    }

    public double calcularPrecioTotal() {
        double total = carrito.calcularTotal();
        return plataformaStrategy.aplicarDescuento(total);
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
        plataformaStrategy.agregarObserver(suscriptor);
    }

    public void limpiarSuscriptores() {
        plataformaStrategy.limpiarObservers();
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public String getNombreEstado() {
        return estadoPedido != null ? estadoPedido.getNombreEstado() : "No confirmado";
    }

}
