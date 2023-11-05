package uniandes.edu.co.hotel_andes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.ReservaServicio;

import java.util.Collection;
import java.sql.Date;

public interface ReservaServicioRepository extends JpaRepository<ReservaServicio, Integer> {

    @Query(value = "SELECT * FROM ReservasServicio", nativeQuery = true)
    Collection<ReservaServicio> darReservasServicio();

    @Query(value = "SELECT * FROM ReservasServicio WHERE id = :id", nativeQuery = true)
    ReservaServicio darReservaServicio(long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ReservasServicio (id, fecha, horaInicio, horaFin, Servicios_id, Habitaciones_id) VALUES ( reservasservicio_sequence.nextval , :fecha, TO_DATE(:horaInicio, 'HH24:MI'), TO_DATE(:horaFin, 'HH24:MI'), :Servicios_id, :Habitaciones_id)", nativeQuery = true)
    void insertarReservaServicio(@Param("fecha") Date fecha, @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin, @Param("Servicios_id") long Servicios_id,
            @Param("Habitaciones_id") long Habitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ReservasServicio SET fecha = :fecha, horaInicio = TO_DATE(:horaInicio, 'HH24:MI'), horaFin = TO_DATE(:horaFin, 'HH24:MI'), Servicios_id = :Servicios_id, Habitaciones_id = :Habitaciones_id WHERE id = :id", nativeQuery = true)
    void actualizarReservaServicio(@Param("id") long id, @Param("fecha") Date fecha,
            @Param("horaInicio") String horaInicio, @Param("horaFin") String horaFin,
            @Param("Servicios_id") long Servicios_id, @Param("Habitaciones_id") long Habitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ReservasServicio WHERE id = :id", nativeQuery = true)
    void eliminarReservaServicio(@Param("id") long id);

}
