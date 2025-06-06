package uade.tpo.modelo.dtos;

public class GooglePayDTO {
    private final String cuentaGoogle;
    private final String deviceId;
    private final String tokenPago;

    public GooglePayDTO(String cuentaGoogle, String deviceId, String tokenPago) {
        this.cuentaGoogle = cuentaGoogle;
        this.deviceId = deviceId;
        this.tokenPago = tokenPago;
    }

    public String getCuentaGoogle() {
        return cuentaGoogle;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getTokenPago() {
        return tokenPago;
    }
}
