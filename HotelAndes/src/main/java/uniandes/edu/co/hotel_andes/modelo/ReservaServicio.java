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
@Table(name = "Reservasservicio")
public class ReservaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Date fecha;

    private Date horainicio;

    private Date horafin;

    @ManyToOne
    @JoinColumn(name = "servicios_id", referencedColumnName = "id")
    private Servicio servicios_id;

    @ManyToOne
    @JoinColumn(name = "habitaciones_id", referencedColumnName = "id")
    private Habitacion habitaciones_id;

    public ReservaServicio() {;}

    public ReservaServicio(Date fecha, Date horaInicio, Date horaFin, Servicio servicios_id,
            Habitacion habitaciones_id) {
        this.fecha = fecha;
        this.horainicio = horaInicio;
        this.horafin = horaFin;
        this.servicios_id = servicios_id;
        this.habitaciones_id = habitaciones_id;
    }

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

    public Date getHoraInicio() {
        return horainicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horainicio = horaInicio;
    }

    public Date getHoraFin() {
        return horafin;
    }

    public void setHoraFin(Date horaFin) {
        this.horafin = horaFin;
    }

    public Servicio getServicios_id() {
        return servicios_id;
    }

    public void setServicios_id(Servicio servicios_id) {
        this.servicios_id = servicios_id;
    }

    public Habitacion getHabitaciones_id() {
        return habitaciones_id;
    }

    public void setHabitaciones_id(Habitacion habitaciones_id) {
        this.habitaciones_id = habitaciones_id;
    }
    
}
