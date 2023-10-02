package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hotel_andes.modelo.EstablecimientoConsumo;


public interface EstablecimientosConsumoRepository  extends  JpaRepository<EstablecimientoConsumo, Integer >{

    

     @Query(value = "SELECT * FROM establecimientos", nativeQuery = true)
    Collection<EstablecimientoConsumo> darEstablecimientos();


    @Query(value = "SELECT * FROM establecimientos WHERE servicioid = :servicioid", nativeQuery = true)
    EstablecimientoConsumo darEstablecimiento(@Param("servicioid") int  servicioid );


    @Modifying
    @Query(value = "INSERT INTO establecimientos (servicioid, capacidad, tipoEstablecimiento, estilo) VALUES (:servicioid , :capacidad, :tipoEstablecimiento, :estilo )", nativeQuery = true)
    void insertarEstablecimiento(@Param ("servicioid") Integer  servicioid ,@Param ("capacidad") Integer capacidad, @Param("tipoEstablecimiento") String  tipoEstablecimiento , @Param("estilo") String  estilo);
    


    @Modifying
    @Query(value = "UPDATE establecimientos ( capacidad,  tipo, estilo) VALUES (:capacidad, :tipo, :estilo ) WHERE servicioid = :servicioid", nativeQuery = true)
    void modificarEstablecimiento   (@Param ("servicioid") Integer  servicioid ,@Param ("capacidad") Integer capacidad, @Param("tipo") String  tipo, @Param("estilo") String  estilo);
    
    @Modifying
    @Query(value = "DELETE FROM establecimientos WHERE servicioid = :servicioid", nativeQuery = true)
    void borrarEstablecimiento (@Param ("servicioid") Integer  servicioid );


    
    
}
