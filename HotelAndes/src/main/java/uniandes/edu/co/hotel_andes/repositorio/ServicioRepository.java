package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {

        @Query(value = "SELECT * FROM Servicios", nativeQuery = true)
        Collection<Servicio> darServicios();

        @Query(value = "SELECT * FROM Servicios WHERE id = :id", nativeQuery = true)
        Servicio darServicio(long id);

        @Modifying
        @Transactional
        @Query(value = "INSERT INTO Servicios (id, nombre, descripcion, tipoServicio, costoPorUnidad, horario, capacidad) VALUES ( hotelandes_sequence.nextval , :nombre, :descripcion, :tipoServicio, :costoPorUnidad, :horario, :capacidad)", nativeQuery = true)
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

}