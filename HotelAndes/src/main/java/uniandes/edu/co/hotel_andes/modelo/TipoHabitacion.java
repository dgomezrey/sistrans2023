package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tiposhabitacion")
public class TipoHabitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String tipo;

    private Integer capacidad;

    private Integer camas;

    private Float costopornoche;

    public TipoHabitacion(String tipo, Integer capacidad, Integer camas, Float costopornoche) {
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.camas = camas;
        this.costopornoche = costopornoche;
    }

    public TipoHabitacion() {;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getCamas() {
        return camas;
    }

    public void setCamas(Integer camas) {
        this.camas = camas;
    }

    public Float getCostoPorNoche() {
        return costopornoche;
    }

    public void setCostoPorNoche(Float costopornoche) {
        this.costopornoche = costopornoche;
    }

    
    
}
