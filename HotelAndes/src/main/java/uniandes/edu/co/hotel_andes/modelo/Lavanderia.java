package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table ( name = "lavanderias")
public class Lavanderia {


    @Id
    @OneToOne
    @JoinColumn(name = "servicioId", referencedColumnName = "id")
    private Servicio servicio; 


    private String tipoPrenda;

    private float costoRemplazo;

    
    public Lavanderia(Servicio servicio, String tipoPrenda, float costoRemplazo) {
        this.servicio = servicio;
        this.tipoPrenda = tipoPrenda;
        this.costoRemplazo = costoRemplazo;
    }
     public Lavanderia(){;}

    public Servicio getServicio() {
        return servicio;
    }

    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    public String getTipoPrenda() {
        return tipoPrenda;
    }

    public void setTipoPrenda(String tipoPrenda) {
        this.tipoPrenda = tipoPrenda;
    }

    public float getCostoRemplazo() {
        return costoRemplazo;
    }

    public void setCostoRemplazo(float costoRemplazo) {
        this.costoRemplazo = costoRemplazo;
    }






    



    
    

    
}
