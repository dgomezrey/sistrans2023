package uniandes.edu.co.hotel_andes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.OfrecenProducto;

import java.util.Collection;

public interface OfrecenProductoRepository extends JpaRepository<OfrecenProducto, Integer> {

    @Query(value = "SELECT * FROM OfrecenProductos", nativeQuery = true)
    Collection<OfrecenProducto> darOfrecenProductos();

    @Query(value = "SELECT * FROM OfrecenProductos WHERE Servicios_id = :Servicios_id AND Productos_id = :Productos_id", nativeQuery = true)
    OfrecenProducto darOfrecenProducto(@Param("Servicios_id") long Servicios_id,
            @Param("Productos_id") long Productos_id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO OfrecenProductos (Servicios_id, Productos_id) VALUES ( :Servicios_id, :Productos_id)", nativeQuery = true)
    void insertarOfrecenProducto(@Param("Servicios_id") long Servicios_id, @Param("Productos_id") long Productos_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE OfrecenProductos SET Servicios_id = :Servicios_id, Productos_id = :Productos_id WHERE Servicios_id = :Servicios_id AND Productos_id = :Productos_id", nativeQuery = true)
    void actualizarOfrecenProducto(@Param("Servicios_id") long Servicios_id, @Param("Productos_id") long Productos_id);

}
