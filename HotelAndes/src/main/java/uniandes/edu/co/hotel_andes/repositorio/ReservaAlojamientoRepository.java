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
    @Query(value = "INSERT INTO ReservasAlojamiento (id, fechaIn, fechaOut, numPersonas, Usuarios_id, PlanesConsumo_id, Habitaciones_id) VALUES ( hotelandes_sequence.nextval , :fechaIn, :fechaOut, :numPersonas, :Usuarios_id, :PlanesConsumo_id, :Habitaciones_id)", nativeQuery = true)
    void insertarReservaAlojamiento(@Param("fechaIn") Date fechaIn, @Param("fechaOut") Date fechaOut,
            @Param("numPersonas") Integer numPersonas, @Param("Usuarios_id") long Usuarios_id,
            @Param("PlanesConsumo_id") long PlanesConsumo_id, @Param("Habitaciones_id") long Habitaciones_id);

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

}
