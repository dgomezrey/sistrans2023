package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import uniandes.edu.co.hotel_andes.modelo.Piscina;

public interface PiscinaRepository extends  JpaRepository<Piscina, Integer >{


     @Query(value = "SELECT * FROM piscinas", nativeQuery = true)
    Collection<Piscina> darPiscinas();


    @Query(value = "SELECT * FROM piscinas WHERE servicioid = :servicioid", nativeQuery = true)
    Piscina darPiscina(@Param("servicioid") int  servicioid );


    @Modifying
    @Query(value = "INSERT INTO piscinas (servicioid, capacidad, profundidad, horarioServicio) VALUES (:servicioid , :capacidad, :profundidad, :horarioServicio )", nativeQuery = true)
    void insertarPiscina (@Param ("servicioid") Integer  servicioid ,@Param ("capacidad") Integer capacidad, @Param("profundidad") Integer  profundidad , @Param("horarioServicio") String  horarioServicio);
    


    @Modifying
    @Query(value = "UPDATE piscinas ( capacidad, profundidad, horarioServicio) VALUES (:capacidad, :profundidad, :horarioServicio ) WHERE servicioid = :servicioid", nativeQuery = true)
    void modificarPiscina   (@Param ("servicioid") Integer  servicioid ,@Param ("capacidad") Integer capacidad, @Param("profundidad") Integer  profundidad, @Param("horarioServicio") String  horarioServicio);
    
    @Modifying
    @Query(value = "DELETE FROM piscinas WHERE servicioid = :servicioid", nativeQuery = true)
    void borrarPiscina (@Param ("servicioid") Integer  servicioid );


    
}
