package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Establecimientos")
public class EstablecimientoConsumo {


    @Id
    @OneToOne
    @JoinColumn(name = "servicioId", referencedColumnName = "id")
    private Servicio servicio; 

    private String tipoEstablecimiento;


    private String estilo;

    private Integer capacidad;

    



    


    





    public EstablecimientoConsumo(Servicio servicio, String tipoEstablecimiento, String estilo, Integer capacidad) {
        this.servicio = servicio;
        this.tipoEstablecimiento = tipoEstablecimiento;
        this.estilo = estilo;
        this.capacidad = capacidad;
    }










    public EstablecimientoConsumo(){;}
    



    public Servicio getServicio() {
        return servicio;
    }




    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }




    public String getTipoEstablecimiento() {
        return tipoEstablecimiento;
    }














    public void setTipoEstablecimiento(String tipoEstablecimiento) {
        this.tipoEstablecimiento = tipoEstablecimiento;
    }














    public String getEstilo() {
        return estilo;
    }














    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }














    public Integer getCapacidad() {
        return capacidad;
    }














    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }













   

    



}
