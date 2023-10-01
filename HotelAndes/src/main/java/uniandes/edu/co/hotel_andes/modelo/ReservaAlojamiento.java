package uniandes.edu.co.hotel_andes.modelo;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reservasalojamiento")
public class ReservaAlojamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date fecha;

    private Integer numNoches;

    private Integer numAdultos;

    private Integer numMenores;

    @ManyToOne
    @JoinColumn(name = "usuario", referencedColumnName = "id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "planConsumo", referencedColumnName = "id")
    private PlanConsumo planConsumo;

    public ReservaAlojamiento(Date fecha, Integer numNoches, Integer numAdultos, Integer numMenores, Usuario usuario,
            PlanConsumo planConsumo) {
        this.fecha = fecha;
        this.numNoches = numNoches;
        this.numAdultos = numAdultos;
        this.numMenores = numMenores;
        this.usuario = usuario;
        this.planConsumo = planConsumo;
    }

    public ReservaAlojamiento() {;}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getNumNoches() {
        return numNoches;
    }

    public void setNumNoches(Integer numNoches) {
        this.numNoches = numNoches;
    }

    public Integer getNumAdultos() {
        return numAdultos;
    }

    public void setNumAdultos(Integer numAdultos) {
        this.numAdultos = numAdultos;
    }

    public Integer getNumMenores() {
        return numMenores;
    }

    public void setNumMenores(Integer numMenores) {
        this.numMenores = numMenores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PlanConsumo getPlanConsumo() {
        return planConsumo;
    }

    public void setPlanConsumo(PlanConsumo planConsumo) {
        this.planConsumo = planConsumo;
    }

}
