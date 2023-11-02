package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer numero;

    private Integer piso;

    private Integer ocupada;

    @ManyToOne
    @JoinColumn(name = "tiposhabitacion_id", referencedColumnName = "id")
    private TipoHabitacion tiposHabitacion_id;

    public Habitacion() {;}

    public Habitacion(Integer numero, Integer piso, Integer ocupada, TipoHabitacion tiposHabitacion_id) {
        this.numero = numero;
        this.piso = piso;
        this.ocupada = ocupada;
        this.tiposHabitacion_id = tiposHabitacion_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public Integer getOcupada() {
        return ocupada;
    }

    public void setOcupada(Integer ocupada) {
        this.ocupada = ocupada;
    }

    public TipoHabitacion getTiposHabitacion_id() {
        return tiposHabitacion_id;
    }

    public void setTiposHabitacion_id(TipoHabitacion tiposHabitacion_id) {
        this.tiposHabitacion_id = tiposHabitacion_id;
    }

}