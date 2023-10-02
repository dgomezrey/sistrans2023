package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "spas")
public class Spa {

    
    @Id
    @OneToOne
    @JoinColumn(name = "servicioId", referencedColumnName = "id")
    private Servicio servicio; 

    private Integer duracion;

    private Float costo;



    public Spa( Integer duracion, Float costo, Servicio servicio){
        this.duracion = duracion;
        this.costo = costo;
        this.servicio = servicio;

    }

    public Spa(){;}

    

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public void setCosto(Float costo) {
        this.costo = costo;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    

    public Integer getDuracion() {
        return duracion;
    }

    public Float getCosto() {
        return costo;
    }

    public Servicio getServicio() {
        return servicio;
    }

    
}
