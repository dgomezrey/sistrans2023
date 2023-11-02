package uniandes.edu.co.hotel_andes.modelo;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class OfrecenProductoPK implements Serializable {

    @ManyToOne
    @JoinColumn(name = "Servicios_id", referencedColumnName = "id")
    private Servicio Servicios_id;

    @ManyToOne
    @JoinColumn(name = "Productos_id", referencedColumnName = "id")
    private Producto Productos_id;

    public OfrecenProductoPK() {
        super();
    }

    public OfrecenProductoPK(Servicio servicios_id, Producto productos_id) {
        super();
        Servicios_id = servicios_id;
        Productos_id = productos_id;
    }

    public Servicio getServicios_id() {
        return Servicios_id;
    }

    public void setServicios_id(Servicio servicios_id) {
        Servicios_id = servicios_id;
    }

    public Producto getProductos_id() {
        return Productos_id;
    }

    public void setProductos_id(Producto productos_id) {
        Productos_id = productos_id;
    }
    
}
