package uniandes.edu.co.hotel_andes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import oracle.net.aso.l;
import uniandes.edu.co.hotel_andes.modelo.ReservaAlojamiento;

import java.sql.Date;
import java.util.Collection;

public interface ReservaAlojamientoRepository extends JpaRepository<ReservaAlojamiento, Integer> {

    @Query(value = "SELECT * FROM ReservasAlojamiento", nativeQuery = true)
    Collection<ReservaAlojamiento> darReservasAlojamiento();

    @Query(value = "SELECT * FROM ReservasAlojamiento WHERE id = :id", nativeQuery = true)
    ReservaAlojamiento darReservaAlojamiento(long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ReservasAlojamiento (id, fechaIn, fechaOut, numPersonas, Usuarios_id, PlanesConsumo_id, Habitaciones_id) VALUES ( reservasalojamiento_sequence.nextval , :fechaIn, :fechaOut, :numPersonas, :Usuarios_id, :PlanesConsumo_id, :Habitaciones_id)", nativeQuery = true)
    void insertarReservaAlojamiento(@Param("fechaIn") Date fechaIn, @Param("fechaOut") Date fechaOut,
            @Param("numPersonas") Integer numPersonas, @Param("Usuarios_id") long Usuarios_id,
            @Param("PlanesConsumo_id") long PlanesConsumo_id,
            @Param("Habitaciones_id") long Habitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE ReservasAlojamiento SET fechaIn = :fechaIn, fechaOut = :fechaOut, numPersonas = :numPersonas, Usuarios_id = :Usuarios_id, PlanesConsumo_id = :PlanesConsumo_id, Habitaciones_id = :Habitaciones_id WHERE id = :id", nativeQuery = true)
    void actualizarReservaAlojamiento(@Param("id") long id, @Param("fechaIn") Date fechaIn,
            @Param("fechaOut") Date fechaOut, @Param("numPersonas") Integer numPersonas,
            @Param("Usuarios_id") long Usuarios_id, @Param("PlanesConsumo_id") long PlanesConsumo_id,
            @Param("Habitaciones_id") long Habitaciones_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM ReservasAlojamiento WHERE id = :id", nativeQuery = true)
    void eliminarReservaAlojamiento(@Param("id") long id);

    @Query(value = "SELECT * FROM ReservasAlojamiento WHERE Usuarios_id = :id", nativeQuery = true)
    Collection<ReservaAlojamiento> darReservasAlojamientoUsuario(@Param("id") long id);

    @Query(value = "SELECT * FROM ReservasAlojamiento WHERE Habitaciones_id = :idHabitacion AND fechain >= TO_DATE(:fechaIn, 'YYYY-MM-DD') AND fechaOut <= TO_DATE(:fechaOut, 'YYYY-MM-DD')", nativeQuery = true)
    Collection<ReservaAlojamiento> darReservasAlojamientoHabitacion(@Param("idHabitacion") long idHabitacion,
            @Param("fechaIn") String fechaIn, @Param("fechaOut") String fechaOut);

    @Query(value = "SELECT fechain as Fecha, COUNT(*) AS ocupacion " + //
            "FROM reservasalojamiento " + //
            "GROUP BY fechain " + //
            "ORDER BY ocupacion DESC " + //
            "FETCH FIRST 10 ROWS ONLY", nativeQuery = true)
    Collection<Object[]> darRFC6A();

    @Query(value = "SELECT c.fecha, SUM(c.total) AS ingresos " + //
            "FROM consumos c " + //
            "GROUP BY c.fecha " + //
            "ORDER BY ingresos DESC " + //
            "FETCH FIRST 10 ROWS ONLY", nativeQuery = true)
    Collection<Object[]> darRFC6B();

    @Query(value = "SELECT fechain, COUNT(*) AS ocupacion " + //
            "FROM reservasalojamiento " + //
            "GROUP BY fechain " + //
            "ORDER BY ocupacion ASC " + //
            "FETCH FIRST 10 ROWS ONLY", nativeQuery = true)
    Collection<Object[]> darRFC6C();

}
