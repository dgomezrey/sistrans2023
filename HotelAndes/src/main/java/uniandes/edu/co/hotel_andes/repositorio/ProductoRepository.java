package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hotel_andes.modelo.Producto;
import uniandes.edu.co.hotel_andes.modelo.Spa;

public interface ProductoRepository extends  JpaRepository<Producto, Integer> {
    


    @Query(value = "SELECT * FROM productos", nativeQuery = true)
    Collection<Producto> darProductos();


    @Query(value = "SELECT * FROM productos WHERE id = :id", nativeQuery = true)
    Spa darProducto(@Param("id") int  id );


    @Modifying
    @Query(value = "INSERT INTO productos (id, nombre, precio) VALUES (:id , :nombre, :precio )", nativeQuery = true)
    void insertarProducto (@Param ("id") Integer  id ,@Param ("nombre") String nombre, @Param("precio") Integer  precio);
    


    @Modifying
    @Query(value = "UPDATE productos ( nombre, precio) VALUES (:nombre, :precio ) WHERE id = :id", nativeQuery = true)
    void modificarProducto (@Param ("id") Integer  id ,@Param ("nombre") String nombre, @Param("precio") Integer  precio);
    
    @Modifying
    @Query(value = "DELETE FROM productos WHERE id = :id", nativeQuery = true)
    void borrarProducto(@Param ("id") Integer  id );
}
