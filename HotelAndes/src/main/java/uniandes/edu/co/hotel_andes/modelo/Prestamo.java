package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table ( name = "prestamos")
public class Prestamo {
    
    @id 
    @OneToOne
    @JoinColumn(name = "servicioId", referencedColumnName = "id")
    private Servicio servicio; 

    private String utencilio;

    private Float costo;

    


    public Prestamo(Servicio servicio, String utencilio, Float costo) {
        this.servicio = servicio;
        this.utencilio = utencilio;
        this.costo = costo;
    }


    public Servicio getServicio() {
        return servicio;
    }


    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }


    public String getUtencilio() {
        return utencilio;
    }


    public void setUtencilio(String utencilio) {
        this.utencilio = utencilio;
    }


    public Float getCosto() {
        return costo;
    }


    public void setCosto(Float costo) {
        this.costo = costo;
    }


    public Prestamo(){;}

    
    

    


}
