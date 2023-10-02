package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hotel_andes.modelo.Internet;


public interface InternetRepository extends  JpaRepository<Internet, Integer >
{


    @Query(value = "SELECT * FROM internet", nativeQuery = true)
    Collection<Internet> darInternet();


    @Query(value = "SELECT * FROM internet WHERE servicioid = :servicioid", nativeQuery = true)
    Internet darInternet(@Param("servicioid") int  servicioid );


    @Modifying
    @Query(value = "INSERT INTO internet (servicioid, capacidad, costoPorDia) VALUES (:servicioid , :capacidad, :costoPorDia )", nativeQuery = true)
    void insertarInternet (@Param ("servicioid") Integer  servicioid ,@Param ("capacidad") Integer capacidad, @Param("costoPorDia") Integer  costoPorDia);
    


    @Modifying
    @Query(value = "UPDATE internet ( capacidad, costoPorDia) VALUES (:capacidad, :costoPorDia ) WHERE servicioid = :servicioid", nativeQuery = true)
    void modificarInternet  (@Param ("servicioid") Integer  servicioid ,@Param ("capacidad") Integer capacidad, @Param("costoPorDia") Integer  costoPorDia);
    
    @Modifying
    @Query(value = "DELETE FROM internet WHERE servicioid = :servicioid", nativeQuery = true)
    void borrarInternet(@Param ("servicioid") Integer  servicioid );
    
    
}
