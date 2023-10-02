package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "internet")
public class Internet {

    @Id
    @OneToOne
    @JoinColumn(name = "servicioId", referencedColumnName = "id")
    private Servicio servicio; 

    private Integer capacidad;
    private Integer CostoPorDia;

    
    public Internet(Servicio servicio, Integer capacidad, Integer costoPorDia) {
        this.servicio = servicio;
        this.capacidad = capacidad;
        CostoPorDia = costoPorDia;
    }

    public Internet(){;}

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

    public Integer getCostoPorDia() {
        return CostoPorDia;
    }

    public void setCostoPorDia(Integer costoPorDia) {
        CostoPorDia = costoPorDia;
    }


   

}
