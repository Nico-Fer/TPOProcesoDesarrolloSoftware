package uade.tpo.modelo.dtos;

public class TarjetaCreditoDTO {
    private String numeroTarjeta;
    private String cvv;

    public TarjetaCreditoDTO(String numeroTarjeta, String cvv) {
        this.numeroTarjeta = numeroTarjeta;
        this.cvv = cvv;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getCvv() {
        return cvv;
    }
}
