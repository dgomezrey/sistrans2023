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
@Table(name = "ReservasAlojamiento")
public class ReservaAlojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date fechaIn;

    private Date fechaOut;

    private Integer numPersonas;

    @ManyToOne
    @JoinColumn(name = "Usuarios_id", referencedColumnName = "id")
    private Usuario Usuarios_id;

    @ManyToOne
    @JoinColumn(name = "PlanesConsumo_id", referencedColumnName = "id")
    private PlanConsumo PlanesConsumo_id;

    @ManyToOne
    @JoinColumn(name = "Habitaciones_id", referencedColumnName = "id")
    private Habitacion Habitaciones_id;

    public ReservaAlojamiento() {;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaIn() {
        return fechaIn;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechaIn = fechaIn;
    }

    public Date getFechaOut() {
        return fechaOut;
    }

    public void setFechaOut(Date fechaOut) {
        this.fechaOut = fechaOut;
    }

    public Integer getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(Integer numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Usuario getUsuarios_id() {
        return Usuarios_id;
    }

    public void setUsuarios_id(Usuario usuarios_id) {
        Usuarios_id = usuarios_id;
    }

    public PlanConsumo getPlanesConsumo_id() {
        return PlanesConsumo_id;
    }

    public void setPlanesConsumo_id(PlanConsumo planesConsumo_id) {
        PlanesConsumo_id = planesConsumo_id;
    }

    public Habitacion getHabitaciones_id() {
        return Habitaciones_id;
    }

    public void setHabitaciones_id(Habitacion habitaciones_id) {
        Habitaciones_id = habitaciones_id;
    }

}