package uniandes.edu.co.hotel_andes.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class CuentaConsumosPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "reservaAlojamientoId", referencedColumnName = "id")
    private ReservaAlojamiento reservaAlojamientoId;

    @ManyToOne
    @JoinColumn(name = "servicioId", referencedColumnName = "id")
    private Servicio servicioId;

    public CuentaConsumosPK(ReservaAlojamiento reservaAlojamientoId, Servicio servicioId) {
        super();
        this.reservaAlojamientoId = reservaAlojamientoId;
        this.servicioId = servicioId;
    }

    public CuentaConsumosPK() {
        super();
    }

    public ReservaAlojamiento getReservaAlojamientoId() {
        return reservaAlojamientoId;
    }

    public void setReservaAlojamientoId(ReservaAlojamiento reservaAlojamientoId) {
        this.reservaAlojamientoId = reservaAlojamientoId;
    }

    public Servicio getServicioId() {
        return servicioId;
    }

    public void setServicioId(Servicio servicioId) {
        this.servicioId = servicioId;
    }
    
}
