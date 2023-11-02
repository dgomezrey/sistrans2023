package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "OfrecenProductos")
public class OfrecenProducto {

    @EmbeddedId
    private OfrecenProductoPK pk;

    public OfrecenProducto() {;}

    public OfrecenProducto(Servicio Servicios_id, Producto Productos_id) {
        this.pk = new OfrecenProductoPK(Servicios_id, Productos_id);
    }

    public OfrecenProductoPK getPk() {
        return pk;
    }

    public void setPk(OfrecenProductoPK pk) {
        this.pk = pk;
    }
    
}