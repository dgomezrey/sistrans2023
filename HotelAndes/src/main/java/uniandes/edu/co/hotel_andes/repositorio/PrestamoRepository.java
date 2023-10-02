package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hotel_andes.modelo.Prestamo;


public interface  PrestamoRepository  extends  JpaRepository<Prestamo, Integer> {
    
    @Query(value = "SELECT * FROM prestamos", nativeQuery = true)
    Collection<Prestamo> darPrestamo();


    @Query(value = "SELECT * FROM prestamos WHERE servicoid = :servicoid", nativeQuery = true)
    Prestamo darPrestamo(@Param("servicoid") int  servicoid );


    @Modifying
    @Query(value = "INSERT INTO prestamos (servicioid, utensilio, costoRemplazo) VALUES (:servicioid , :utensilio, :costoRemplazo )", nativeQuery = true)
    void insertarPrestamo (@Param ("servicioid") Integer  servicioid ,@Param ("utensilio") Integer utensilio, @Param("costoRemplazo") Integer  costoRemplazo);
    


    @Modifying
    @Query(value = "UPDATE prestamos ( utensilio, costoRemplazo) VALUES (duracion, :costo ) WHERE servicioid = :servicioid", nativeQuery = true)
    void modificarPrestamo  (@Param ("servicioid") Integer  servicioid ,@Param ("utensilio") Integer utensilio, @Param("costoRemplazo") Integer  costoRemplazo);
    
    @Modifying
    @Query(value = "DELETE FROM prestamos WHERE servicioid = :servicioid", nativeQuery = true)
    void borrarrPrestamo  (@Param ("servicioid") Integer  servicioid );
}
