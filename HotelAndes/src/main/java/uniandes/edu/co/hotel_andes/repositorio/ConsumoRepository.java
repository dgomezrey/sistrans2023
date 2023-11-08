package uniandes.edu.co.hotel_andes.repositorio;

import org.antlr.v4.runtime.atn.SemanticContext.AND;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import oracle.net.aso.c;
import uniandes.edu.co.hotel_andes.modelo.Consumo;

import java.util.Collection;
import java.util.List;
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

        @Query(value = "SELECT u.id, u.nombre, s.nombre as SERVICIO, c.total FROM consumos c JOIN reservasalojamiento ra ON c.habitaciones_id = ra.id JOIN servicios s ON c.servicios_id = s.id JOIN usuarios u ON ra.usuarios_id = u.id WHERE  u.id = :usuarios_id AND ra.fechain BETWEEN TO_DATE(:fecha1,'YYYY-MM-DD') AND TO_DATE(:fecha2,'YYYY-MM-DD')", nativeQuery = true)
        List<Object[]> darRFC5(@Param("usuarios_id") long usuarios_id, @Param("fecha1") Date fecha1,
                        @Param("fecha2") Date fecha2);

        @Query(value = "SELECT s.nombre,  COUNT(*) " + //
                "FROM Consumos c, servicios s " + //
                "WHERE (c.fecha >= ADD_MONTHS(SYSDATE, -12) and c.servicios_id = s.id) " + //
                "GROUP BY s.nombre, TO_CHAR(c.FECHA, 'IYYY-IW') " + //
                "HAVING COUNT(*) < 3", nativeQuery = true)
        Collection<Object[]> darRFC8();


        @Query(value = "select nombre from servicios where id not in (select servicios_id from consumos) FETCH FIRST 30 ROWS ONLY", nativeQuery = true)
        Collection<Object[]> darRFC8AUX();

}
