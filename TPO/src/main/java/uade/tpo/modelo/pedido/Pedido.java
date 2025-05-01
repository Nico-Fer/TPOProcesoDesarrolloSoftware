package uade.tpo.modelo.pedido;

import uade.tpo.modelo.cuponDescuento.CuponDescuento;
import uade.tpo.modelo.cuponDescuento.CuponVacio;
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
    private CuponDescuento cupon = new CuponVacio();

    public void SetEstado(EstadoPedidoState estado) {
        this.estadoPedido = estado;
        this.estadoPedido.SetPedido(this);
    }

    public void agregarProducto(Producto producto) {
        if (producto != null) productos.add(producto);
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void AplicarCupon(CuponDescuento cupon) {
        if (cupon == null || !cupon.EsValido()) {
            throw new IllegalArgumentException("Cupón inválido.");
        }
        this.cupon = cupon;
    }

    public double calcularPrecioTotal() {
        double total = productos.stream()
                .mapToDouble(Producto::getPrecio)
                .sum();
        return cupon.AplicarDescuento(total);
    }

    public void asignarMetodoPago(MetodoPago metodoPago) {
        if(metodoPago != null) this.metodoPago = metodoPago;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void pagarPedido(double monto) {
        if (metodoPago == null) {
            throw new IllegalStateException("No se asignó un método de pago.");
        }
        metodoPago.ProcesarPago(monto);
    }

    public void confirmarPedido() {
        if (estadoPedido != null) {
            throw new IllegalStateException("El pedido ya fue confirmado.");
        }
        this.numeroOrden = String.format("%05d", contadorOrdenes++);
        this.SetEstado(new EnEsperaState());
    }

    public void avanzarEstado() {
        if (estadoPedido == null) {
            throw new IllegalStateException("El pedido debe ser confirmado primero.");
        }
        estadoPedido.AvanzarEstadoPedido();
    }

    public EstadoPedidoState getEstado() {
        return estadoPedido;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public String getNombreEstado() {
        return estadoPedido != null ? estadoPedido.GetNombreEstado() : "No confirmado";
    }

}
