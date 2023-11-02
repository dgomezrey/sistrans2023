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
@Table(name = "Consumos")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer cantidad;

    private Date fecha;

    private Float total;

    @ManyToOne
    @JoinColumn(name = "habitaciones_id", referencedColumnName = "id")
    private Habitacion habitaciones_id;

    @ManyToOne
    @JoinColumn(name = "servicios_id", referencedColumnName = "id")
    private Servicio servicios_id;

    @ManyToOne
    @JoinColumn(name = "productos_id", referencedColumnName = "id")
    private Producto productos_id;

    public Consumo() {;}

    public Consumo(Integer cantidad, Date fecha, Float total, Habitacion habitaciones_id, Servicio servicios_id,
            Producto productos_id) {
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = total;
        habitaciones_id = habitaciones_id;
        servicios_id = servicios_id;
        productos_id = productos_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Habitacion getHabitaciones_id() {
        return habitaciones_id;
    }

    public void setHabitaciones_id(Habitacion habitaciones_id) {
        this.habitaciones_id = habitaciones_id;
    }

    public Servicio getServicios_id() {
        return servicios_id;
    }

    public void setServicios_id(Servicio servicios_id) {
        this.servicios_id = servicios_id;
    }

    public Producto getProductos_id() {
        return productos_id;
    }

    public void setProductos_id(Producto productos_id) {
        this.productos_id = productos_id;
    }

}
