package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uniandes.edu.co.hotel_andes.modelo.Gimnasio;

public interface GimnasioRepository extends  JpaRepository<Gimnasio, Integer >{

     @Query(value = "SELECT * FROM gimnasios", nativeQuery = true)
    Collection<Gimnasio> darGimnasios();


    @Query(value = "SELECT * FROM gimnasios WHERE servicioid = :servicioid", nativeQuery = true)
    Gimnasio darGimnasio(@Param("servicioid") int  servicioid );


    @Modifying
    @Query(value = "INSERT INTO gimnasios (servicioid, capacidad, numMaquinas, horarioServicio) VALUES (:servicioid , :capacidad, :numMaquinas, :horarioServicio )", nativeQuery = true)
    void insertarGimnasio (@Param ("servicioid") Integer  servicioid ,@Param ("capacidad") Integer capacidad, @Param("numMaquinas") Integer  numMaquinas , @Param("horarioServicio") String  horarioServicio);
    


    @Modifying
    @Query(value = "UPDATE gimnasios ( capacidad, numMaquinas, horarioServicio) VALUES (:capacidad, :numMaquinas, :horarioServicio ) WHERE servicioid = :servicioid", nativeQuery = true)
    void modificarGimnasio   (@Param ("servicioid") Integer  servicioid ,@Param ("capacidad") Integer capacidad, @Param("numMaquinas") Integer  numMaquinas, @Param("horarioServicio") String  horarioServicio);
    
    @Modifying
    @Query(value = "DELETE FROM gimnasios WHERE servicioid = :servicioid", nativeQuery = true)
    void borrarGimnasio (@Param ("servicioid") Integer  servicioid );
    
}
