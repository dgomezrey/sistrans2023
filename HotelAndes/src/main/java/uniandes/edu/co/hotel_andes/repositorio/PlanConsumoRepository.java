package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.PlanConsumo;

public interface PlanConsumoRepository extends JpaRepository<PlanConsumo, Integer> {

    @Query(value = "SELECT * FROM PlanesConsumo", nativeQuery = true)
    Collection<PlanConsumo> darPlanesConsumo();

    @Query(value = "SELECT * FROM PlanesConsumo WHERE id = :id", nativeQuery = true)
    PlanConsumo darPlanConsumo(long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PlanesConsumo (id, nombre, descripcion, descuento) VALUES ( planesconsumo_sequence.nextval , :nombre, :descripcion, :descuento)", nativeQuery = true)
    void insertarPlanConsumo(@Param("nombre") String nombre, @Param("descripcion") String descripcion,
            @Param("descuento") Float descuento);

    @Modifying
    @Transactional
    @Query(value = "UPDATE PlanesConsumo SET nombre = :nombre, descripcion = :descripcion, descuento = :descuento WHERE id = :id", nativeQuery = true)
    void actualizarPlanConsumo(@Param("id") long id, @Param("nombre") String nombre,
            @Param("descripcion") String descripcion, @Param("descuento") Float descuento);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM PlanesConsumo WHERE id = :id", nativeQuery = true)
    void eliminarPlanConsumo(@Param("id") long id);

}