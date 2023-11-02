package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;

    private String descripcion;

    private String tiposervicio;

    private Float costoporunidad;

    private String horario;

    private Integer capacidad;

    public Servicio() {;}

    public Servicio(String nombre, String descripcion, String tipoServicio, Float costoPorUnidad, String horario,
            Integer capacidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tiposervicio = tipoServicio;
        this.costoporunidad = costoPorUnidad;
        this.horario = horario;
        this.capacidad = capacidad;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoServicio() {
        return tiposervicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tiposervicio = tipoServicio;
    }

    public Float getCostoPorUnidad() {
        return costoporunidad;
    }

    public void setCostoPorUnidad(Float costoPorUnidad) {
        this.costoporunidad = costoPorUnidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    @Override
    public String toString() {
        return this.nombre+"|"+this.descripcion+"|"+this.tiposervicio+"|"+this.costoporunidad+"|"+this.horario+"|"+this.capacidad;
    }

}