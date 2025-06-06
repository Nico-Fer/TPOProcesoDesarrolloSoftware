package uade.tpo.modelo.dtos;

public class MercadoPagoDTO {
    private final String email;
    private final String cbu;

    public MercadoPagoDTO(String email, String cbu) {
        this.email = email;
        this.cbu = cbu;
    }

    public String getEmail() {
        return email;
    }

    public String getCbu() {
        return cbu;
    }
}
