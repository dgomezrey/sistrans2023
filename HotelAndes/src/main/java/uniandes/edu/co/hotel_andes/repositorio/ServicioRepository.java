package uniandes.edu.co.hotel_andes.repositorio;

import java.sql.Date;
import java.util.Collection;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import oracle.net.aso.c;
import uniandes.edu.co.hotel_andes.modelo.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

        @Query(value = "SELECT * FROM Servicios", nativeQuery = true)
        Collection<Servicio> darServicios();

        @Query(value = "SELECT * FROM Servicios WHERE id = :id", nativeQuery = true)
        Servicio darServicio(long id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Servicios (id, nombre, descripcion, tipoServicio, costoPorUnidad, horario, capacidad) VALUES ( servicios_sequence.nextval , :nombre, :descripcion, :tipoServicio, :costoPorUnidad, :horario, :capacidad)", nativeQuery = true)
        void insertarServicio(@Param("nombre") String nombre, @Param("descripcion") String descripcion,
                        @Param("tipoServicio") String tipoServicio, @Param("costoPorUnidad") Float costoPorUnidad,
                        @Param("horario") String horario, @Param("capacidad") Integer capacidad);

        @Modifying
        @Transactional
        @Query(value = "UPDATE Servicios SET nombre = :nombre, descripcion = :descripcion, tipoServicio = :tipoServicio, costoPorUnidad = :costoPorUnidad, horario = :horario, capacidad = :capacidad WHERE id = :id", nativeQuery = true)
        void actualizarServicio(@Param("id") long id, @Param("nombre") String nombre,
                        @Param("descripcion") String descripcion, @Param("tipoServicio") String tipoServicio,
                        @Param("costoPorUnidad") Float costoPorUnidad, @Param("horario") String horario,
                        @Param("capacidad") Integer capacidad);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM Servicios WHERE id = :id", nativeQuery = true)
        void eliminarServicio(@Param("id") long id);

        @Query(value = "SELECT s.id,s.nombre,COUNT(c.id)as cantidad FROM servicios s JOIN consumos c ON s.id = c.servicios_id WHERE EXISTS (SELECT 1 FROM reservasalojamiento r WHERE c.habitaciones_id = r.id AND r.fechain BETWEEN TO_DATE(:fecha1, 'YYYY-MM-DD') AND TO_DATE(:fecha2, 'YYYY-MM-DD')) GROUP BY s.id, s.nombre ORDER BY count(c.id) DESC FETCH FIRST 20 ROWS ONLY", nativeQuery = true)
        Collection<Object[]> darRFC2(@Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2);

        @Query(value = "SELECT distinct s.nombre, s.descripcion, s.costoporunidad, s.horario, s.tiposervicio, s.capacidad FROM servicios s LEFT JOIN consumos c ON s.id = c.servicios_id " + //
                        "WHERE (:costo1 IS NULL OR (s.costoporunidad BETWEEN :costo1 AND :costo2)) " + //
                        "AND (:fecha1 IS NULL OR (c.fecha BETWEEN to_date(:fecha1,'YYYY-MM-DD') AND to_date(:fecha2,'YYYY-MM-DD'))) " + //
                        "AND (:habitacion_id IS NULL OR c.habitaciones_id = :habitacion_id) " + //
                        "AND (:tiposervicio IS NULL OR s.tiposervicio = :tiposervicio) FETCH FIRST 30 ROWS ONLY", nativeQuery = true)
        Collection<Object[]> darRFC4(@Param("costo1") Float costo1, @Param("costo2") Float costo2,
                        @Param("fecha1") Date fecha1, @Param("fecha2") Date fecha2,
                        @Param("habitacion_id") long habitacion_id, @Param("tiposervicio") String tiposervicio);

}