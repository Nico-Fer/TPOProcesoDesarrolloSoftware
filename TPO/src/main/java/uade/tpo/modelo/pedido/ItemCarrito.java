package uade.tpo.modelo.pedido;

import uade.tpo.modelo.producto.Producto;

public class ItemCarrito {
    private final Producto producto;
    private boolean pagado;

    public ItemCarrito(Producto producto) {
        this.producto = producto;
        this.pagado = false;
    }

    public Producto getProducto() {
        return producto;
    }

    public boolean estaPagado() {
        return pagado;
    }

    public void marcarPagado() {
        this.pagado = true;
    }

    public double getPrecio() {
        return producto.getPrecio();
    }

    public boolean tieneAlergenos() {
        return producto.tieneIngredientesAlergenicos();
    }
}
