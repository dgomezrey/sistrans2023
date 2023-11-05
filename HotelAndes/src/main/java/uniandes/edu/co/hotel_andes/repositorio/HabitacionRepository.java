package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

        @Query(value = "SELECT * FROM Habitaciones", nativeQuery = true)
        Collection<Habitacion> darHabitaciones();

        @Query(value = "SELECT * FROM Habitaciones WHERE id = :id", nativeQuery = true)
        Habitacion darHabitacion(long id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Habitaciones (id, numero, piso, ocupada, TiposHabitacion_id) VALUES ( habitaciones_sequence.nextval , :numero, :piso, :ocupada, :TiposHabitacion_id)", nativeQuery = true)
        void insertarHabitacion(@Param("numero") Integer numero, @Param("piso") Integer piso,
                        @Param("ocupada") Integer ocupada, @Param("TiposHabitacion_id") long TiposHabitacion_id);

        @Modifying
        @Transactional
        @Query(value = "UPDATE Habitaciones SET numero = :numero, piso = :piso, ocupada = :ocupada, TiposHabitacion_id = :TiposHabitacion_id WHERE id = :id", nativeQuery = true)
        void actualizarHabitacion(@Param("id") long id, @Param("numero") Integer numero, @Param("piso") Integer piso,
                        @Param("ocupada") Integer ocupada, @Param("TiposHabitacion_id") long TiposHabitacion_id);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM Habitaciones WHERE id = :id", nativeQuery = true)
        void eliminarHabitacion(@Param("id") long id);

}