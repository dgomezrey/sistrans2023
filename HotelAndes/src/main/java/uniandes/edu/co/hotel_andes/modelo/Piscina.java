package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "piscinas")
public class Piscina {

    @Id
    @OneToOne
    @JoinColumn(name = "servicioId", referencedColumnName = "id")
    private Servicio servicio; 
    private Integer capacidad;
    private Float profundidad;
    private String horarioServicio;

    

  
    public Piscina(Servicio servicio, Integer capacidad, Float profundidad, String horarioServicio) {
        this.servicio = servicio;
        this.capacidad = capacidad;
        this.profundidad = profundidad;
        this.horarioServicio = horarioServicio;
    }




    public Piscina(){;}




    public Servicio getServicio() {
        return servicio;
    }




    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }




    public Integer getCapacidad() {
        return capacidad;
    }




    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }




    public Float getProfundidad() {
        return profundidad;
    }




    public void setProfundidad(Float profundidad) {
        this.profundidad = profundidad;
    }




    public String getHorarioServicio() {
        return horarioServicio;
    }




    public void setHorarioServicio(String horarioServicio) {
        this.horarioServicio = horarioServicio;
    }
 
    
}
