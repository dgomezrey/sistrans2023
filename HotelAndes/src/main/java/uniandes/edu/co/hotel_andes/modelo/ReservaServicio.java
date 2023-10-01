package uniandes.edu.co.hotel_andes.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservasservicios")
public class ReservaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date fechaInicio;

    private Date fechaFin;

    @ManyToOne
    @JoinColumn(name = "reservaAlojamiento", referencedColumnName = "id")
    private ReservaAlojamiento reservaAlojamiento;

    @ManyToOne
    @JoinColumn(name = "servicio", referencedColumnName = "id")
    private Servicio servicio;

    public ReservaServicio(Date fechaInicio, Date fechaFin, ReservaAlojamiento reservaAlojamiento,
            Servicio servicio) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.reservaAlojamiento = reservaAlojamiento;
        this.servicio = servicio;
    }

    public ReservaServicio() {;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public ReservaAlojamiento getReservaAlojamiento() {
        return reservaAlojamiento;
    }

    public void setReservaAlojamiento(ReservaAlojamiento reservaAlojamiento) {
        this.reservaAlojamiento = reservaAlojamiento;
    }

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }
    
}
