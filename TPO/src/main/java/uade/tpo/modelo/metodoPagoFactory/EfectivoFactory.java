package uade.tpo.modelo.metodoPagoFactory;

import uade.tpo.modelo.pago.Efectivo;
import uade.tpo.modelo.pago.MetodoPago;

public class EfectivoFactory implements MetodoPagoFactory<Void>{

    @Override
    public MetodoPago crearMetodoPago(Void datos) {
        return new Efectivo();
    }

}
