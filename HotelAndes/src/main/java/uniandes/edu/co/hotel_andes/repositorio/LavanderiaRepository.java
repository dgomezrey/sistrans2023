package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hotel_andes.modelo.Lavanderia;

public interface LavanderiaRepository extends  JpaRepository<Lavanderia, Integer>  {

    @Query(value = "SELECT * FROM lavanderias", nativeQuery = true)
    Collection<Lavanderia> darPrestamo();


    @Query(value = "SELECT * FROM lavanderias WHERE servicioid = :servicioid", nativeQuery = true)
    Lavanderia darLavanderia(@Param("servicioid") int  servicioid );


    @Modifying
    @Query(value = "INSERT INTO lavanderias (servicioid, tipoPrenda, costoPorPrenda) VALUES (:servicioid , :tipoPrenda, :costoPorPrenda )", nativeQuery = true)
    void insertarLavanderia (@Param ("servicioid") Integer  servicioid ,@Param ("tipoPrenda") Integer tipoPrenda, @Param("costoPorPrenda") Integer  costoPorPrenda);
    


    @Modifying
    @Query(value = "UPDATE lavanderias ( tipoPrenda, costoPorPrenda ) VALUES (:tipoPrenda, :costo ) WHERE servicioid = :servicioid", nativeQuery = true)
    void modificarLavanderia  (@Param ("servicioid") Integer  servicioid ,@Param ("tipoPrenda") Integer tipoPrenda, @Param("costo") Integer  costo);
    
    @Modifying
    @Query(value = "DELETE FROM lavanderias WHERE servicioid = :servicioid", nativeQuery = true)
    void borrarlavanderia (@Param ("servicioid") Integer  servicioid );
    
}
