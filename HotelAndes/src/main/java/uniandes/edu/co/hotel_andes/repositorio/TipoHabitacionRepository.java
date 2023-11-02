package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.TipoHabitacion;

public interface TipoHabitacionRepository extends JpaRepository<TipoHabitacion, Integer> {

        @Query(value = "SELECT * FROM TiposHabitacion", nativeQuery = true)
        Collection<TipoHabitacion> darTiposHabitacion();

        @Query(value = "SELECT * FROM TiposHabitacion WHERE id = :id", nativeQuery = true)
        TipoHabitacion darTipoHabitacion(@Param("id") long id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO TiposHabitacion (id, tipo, capacidad, camas, costoPorNoche) VALUES ( hotelandes_sequence.nextval , :tipo, :capacidad, :camas, :costoPorNoche)", nativeQuery = true)
        void insertarTipoHabitacion(@Param("tipo") String tipo, @Param("capacidad") Integer capacidad,
                        @Param("camas") Integer camas, @Param("costoPorNoche") Float costoPorNoche);

        @Modifying
        @Transactional
        @Query(value = "UPDATE TiposHabitacion SET tipo = :tipo, capacidad = :capacidad, camas = :camas, costoPorNoche = :costoPorNoche WHERE id = :id", nativeQuery = true)
        void actualizarTipoHabitacion(@Param("id") long id, @Param("tipo") String tipo,
                        @Param("capacidad") Integer capacidad,
                        @Param("camas") Integer camas, @Param("costoPorNoche") Float costoPorNoche);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM TiposHabitacion WHERE id = :id", nativeQuery = true)
        void eliminarTipoHabitacion(@Param("id") long id);
}
