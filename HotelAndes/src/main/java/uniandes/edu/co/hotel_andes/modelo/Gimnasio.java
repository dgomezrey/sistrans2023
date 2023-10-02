package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "gimnasios")
public class Gimnasio {
    @Id
    @OneToOne
    @JoinColumn(name = "servicioId", referencedColumnName = "id")
    private Servicio servicio; 
    private Integer numMaquinas;
    private Integer capacidad;
    private String horarioServico;

    

    public Gimnasio(Servicio servicio, Integer numMaquinas, Integer capacidad, String horarioServico) {
        this.servicio = servicio;
        this.numMaquinas = numMaquinas;
        this.capacidad = capacidad;
        this.horarioServico = horarioServico;
    }


    public Gimnasio(){;}


    public Servicio getServicio() {
        return servicio;
    }


    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }


    public Integer getNumMaquinas() {
        return numMaquinas;
    }


    public void setNumMaquinas(Integer numMaquinas) {
        this.numMaquinas = numMaquinas;
    }


    public Integer getCapacidad() {
        return capacidad;
    }


    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }


    public String getHorarioServico() {
        return horarioServico;
    }


    public void setHorarioServico(String horarioServico) {
        this.horarioServico = horarioServico;
    }
    

}
