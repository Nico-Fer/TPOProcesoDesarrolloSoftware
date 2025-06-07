package uade.tpo.modelo.pedido;

import uade.tpo.modelo.PlataformaStrategy.PlataformaStrategy;
import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.observerPedido.ObserverPedido;
import uade.tpo.modelo.pago.MetodoPago;
import uade.tpo.modelo.pedidoState.EnArmadoState;
import uade.tpo.modelo.pedidoState.EnEsperaState;
import uade.tpo.modelo.pedidoState.EstadoPedidoState;
import uade.tpo.modelo.producto.Producto;

import java.util.List;
import java.util.UUID;

public class Pedido {
    private final String id = UUID.randomUUID().toString();
    private MetodoPago metodoPago;
    private EstadoPedidoState estadoPedido;
    private String numeroOrden;
    private static int contadorOrdenes = 1;
    private final Carrito carrito = new Carrito();
    private final PlataformaStrategy plataformaStrategy;

    public Pedido(PlataformaStrategy plataformaStrategy) {
        this.estadoPedido = new EnArmadoState();
        this.estadoPedido.setPedido(this);
        this.plataformaStrategy = plataformaStrategy;
    }

    public void setEstado(EstadoPedidoState estado) {
        this.estadoPedido = estado;
        this.estadoPedido.setPedido(this);
        this.plataformaStrategy.notificarCambioEstado(this);
    }

    public String getId() {
        return id;
    }

    public PlataformaStrategy getPlataformaStrategy() {
        return plataformaStrategy;
    }

    public void agregarProducto(Producto producto) {
        if (producto != null) estadoPedido.agregarProducto(producto, carrito);
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
        estadoPedido.pagar();
        carrito.marcarComoPagados();
    }

    public void confirmarPedido() {
        if (!(estadoPedido instanceof EnArmadoState)) {
            throw new IllegalStateException("El pedido ya fue confirmado.");
        }
        this.numeroOrden = String.format("%05d", contadorOrdenes++);
        this.setEstado(new EnEsperaState());
    }

    public EstadoPedidoState getEstado() { return estadoPedido;}

    public void avanzarEstado() {
        if (estadoPedido instanceof EnArmadoState) {
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

    public Float calcularTiempoRestante(int cantidadPedidos) {
        return estadoPedido.calcularTiempoRestantePedido(cantidadPedidos);
    }

    public boolean cancelarPedido() {
        return estadoPedido.cancelarPedido();
    }
}
