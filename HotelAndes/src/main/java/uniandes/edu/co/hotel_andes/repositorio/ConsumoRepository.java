package uniandes.edu.co.hotel_andes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.Consumo;

import java.util.Collection;
import java.sql.Date;

public interface ConsumoRepository extends JpaRepository<Consumo, Integer> {

    @Query(value = "SELECT * FROM Consumos", nativeQuery = true)
    Collection<Consumo> darConsumos();

    @Query(value = "SELECT * FROM Consumos WHERE id = :id", nativeQuery = true)
    Consumo darConsumo(long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Consumos (id, cantidad, fecha, total, Habitaciones_id, Servicios_id, Productos_id) VALUES ( consumos_sequence.nextval , :cantidad, :fecha, :total, :Habitaciones_id, :Servicios_id, :Productos_id)", nativeQuery = true)
    void insertarConsumo(@Param("cantidad") Integer cantidad, @Param("fecha") Date fecha,
            @Param("total") Float total, @Param("Habitaciones_id") long Habitaciones_id,
            @Param("Servicios_id") long Servicios_id, @Param("Productos_id") long Productos_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Consumos SET cantidad = :cantidad, fecha = :fecha, total = :total, Habitaciones_id = :Habitaciones_id, Servicios_id = :Servicios_id, Productos_id = :Productos_id WHERE id = :id", nativeQuery = true)
    void actualizarConsumo(@Param("id") long id, @Param("cantidad") Integer cantidad,
            @Param("fecha") Date fecha, @Param("total") Float total,
            @Param("Habitaciones_id") long Habitaciones_id, @Param("Servicios_id") long Servicios_id,
            @Param("Productos_id") long Productos_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Consumos WHERE id = :id", nativeQuery = true)
    void eliminarConsumo(@Param("id") long id);

}
