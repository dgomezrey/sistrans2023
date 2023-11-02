package uniandes.edu.co.hotel_andes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.Producto;

import java.util.Collection;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM Productos", nativeQuery = true)
    Collection<Producto> darProductos();

    @Query(value = "SELECT * FROM Productos WHERE id = :id", nativeQuery = true)
    Producto darProducto(long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Productos (id, nombre, precio) VALUES ( hotelandes_sequence.nextval , :nombre, :precio)", nativeQuery = true)
    void insertarProducto(@Param("nombre") String nombre, @Param("precio") Float precio);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Productos SET nombre = :nombre, precio = :precio WHERE id = :id", nativeQuery = true)
    void actualizarProducto(@Param("id") long id, @Param("nombre") String nombre, @Param("precio") Float precio);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Productos WHERE id = :id", nativeQuery = true)
    void eliminarProducto(@Param("id") long id);

}
