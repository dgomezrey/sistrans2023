package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import oracle.net.aso.h;
import uniandes.edu.co.hotel_andes.modelo.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer> {

        public interface RespuestaInformacionHabitaciones {
                int getID();

                int getNUMERO();

                float getCONSUMO_TOTAL();
        }

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

        @Query(value = "SELECT h.id ,h.numero, COALESCE(SUM(c.total),0) as consumo_total FROM habitaciones h LEFT JOIN reservasalojamiento r ON h.id = r.habitaciones_id LEFT JOIN consumos c ON h.id = c.habitaciones_id WHERE r.fechain BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE GROUP BY h.id, h.numero ORDER BY consumo_total desc FETCH FIRST 30 ROWS ONLY", nativeQuery = true)
        Collection<Object[]> darRFC1();

        @Query(value = "SELECT h.id, h.numero, COALESCE(ROUND(100 * SUM(NVL(r.fechaout, SYSDATE) - r.fechain) / 365, 2), 0) AS \"%\" " + //
                        "FROM habitaciones h " + //
                        "LEFT JOIN reservasalojamiento r ON h.id = r.habitaciones_id AND r.fechain BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE " + //
                        "GROUP BY  h.id, h.numero " + //
                        "ORDER BY h.id", nativeQuery = true)
        Collection<Object[]> darRFC3();

        @Query(value = "SELECT COUNT(*) AS TOTAL_HABITACIONES")
        Collection<RespuestaInformacionHabitaciones> darInformacionHabitaciones();
}