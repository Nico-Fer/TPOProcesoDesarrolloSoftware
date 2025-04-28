package uade.tpo.modelo.dtos;

public class TarjetaDebitoDTO {
    private String numeroTarjeta;
    private String cvv;

    public TarjetaDebitoDTO(String numeroTarjeta, String cvv) {
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
