package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer numero;

    private Integer piso;

    @ManyToOne
    @JoinColumn(name = "tipoHabitacion", referencedColumnName = "id")
    private TipoHabitacion tipoHabitacion;


    public Habitacion(Integer numero, Integer piso, TipoHabitacion tipoHabitacion) {
        this.numero = numero;
        this.piso = piso;
        this.tipoHabitacion = tipoHabitacion;
    }

    public Habitacion() {;}

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

    public TipoHabitacion getTipoHabitacion() {
        return tipoHabitacion;
    }

    public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
        this.tipoHabitacion = tipoHabitacion;
    }
    
}
