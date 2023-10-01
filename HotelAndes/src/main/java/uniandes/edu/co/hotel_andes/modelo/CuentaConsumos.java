package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentaconsumos")
public class CuentaConsumos {

    @EmbeddedId
    private CuentaConsumosPK pk;

    public CuentaConsumos(ReservaAlojamiento reservaAlojamientoId, Servicio servicioId) {
        this.pk = new CuentaConsumosPK(reservaAlojamientoId, servicioId);
    }

    public CuentaConsumos() {;}

    public CuentaConsumosPK getPk() {
        return pk;
    }

    public void setPk(CuentaConsumosPK pk) {
        this.pk = pk;
    }

}