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
@Table(name = "Reservasalojamiento")
public class ReservaAlojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date fechain;

    private Date fechaout;

    private Integer numpersonas;

    @ManyToOne
    @JoinColumn(name = "Usuarios_id", referencedColumnName = "id")
    private Usuario usuarios_id;

    @ManyToOne
    @JoinColumn(name = "Planesconsumo_id", referencedColumnName = "id")
    private PlanConsumo planesconsumo_id;

    @ManyToOne
    @JoinColumn(name = "Habitaciones_id", referencedColumnName = "id")
    private Habitacion habitaciones_id;

    public ReservaAlojamiento() {;}


    public ReservaAlojamiento(Date fechain, Date fechaout, Integer numPersonas, Usuario usuarios_id,
            PlanConsumo planesconsumo_id, Habitacion habitaciones_id) {
        this.fechain = fechain;
        this.fechaout = fechaout;
        this.numpersonas = numPersonas;
        this.usuarios_id = usuarios_id;
        this.planesconsumo_id = planesconsumo_id;
        this.habitaciones_id = habitaciones_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaIn() {
        return fechain;
    }

    public void setFechaIn(Date fechaIn) {
        this.fechain = fechaIn;
    }

    public Date getFechaOut() {
        return fechaout;
    }

    public void setFechaOut(Date fechaOut) {
        this.fechaout = fechaOut;
    }

    public Integer getNumPersonas() {
        return numpersonas;
    }

    public void setNumPersonas(Integer numPersonas) {
        this.numpersonas = numPersonas;
    }

    public Usuario getUsuarios_id() {
        return usuarios_id;
    }

    public void setUsuarios_id(Usuario usuarios_id) {
        this.usuarios_id = usuarios_id;
    }

    public PlanConsumo getPlanesConsumo_id() {
        return planesconsumo_id;
    }

    public void setPlanesConsumo_id(PlanConsumo planesConsumo_id) {
        this.planesconsumo_id = planesConsumo_id;
    }

    public Habitacion getHabitaciones_id() {
        return habitaciones_id;
    }

    public void setHabitaciones_id(Habitacion habitaciones_id) {
        this.habitaciones_id = habitaciones_id;
    }

}