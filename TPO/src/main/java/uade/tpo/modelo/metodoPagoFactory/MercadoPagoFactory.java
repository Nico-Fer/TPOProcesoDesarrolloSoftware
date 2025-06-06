package uade.tpo.modelo.metodoPagoFactory;

import uade.tpo.modelo.dtos.MercadoPagoDTO;
import uade.tpo.modelo.pago.MercadoPago;
import uade.tpo.modelo.pago.MetodoPago;

public class MercadoPagoFactory implements MetodoPagoFactory<MercadoPagoDTO>{
    @Override
    public MetodoPago crearMetodoPago(MercadoPagoDTO datos) {
        if (datos == null) {
            throw new IllegalArgumentException("Los datos de MercadoPago no pueden ser nulos.");
        }

        String email = datos.getEmail();
        String cbu = datos.getCbu();

        if (email == null || !email.matches("^\\S+@\\S+\\.\\S+$")) {
            throw new IllegalArgumentException("Email inválido para MercadoPago.");
        }

        if (cbu == null || !cbu.matches("\\d{22}")) {
            throw new IllegalArgumentException("CBU inválido. Debe contener 22 dígitos numéricos.");
        }

        return new MercadoPago(email, cbu);
    }
}
