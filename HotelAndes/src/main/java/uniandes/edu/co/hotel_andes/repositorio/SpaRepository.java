package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import uniandes.edu.co.hotel_andes.modelo.Spa;

public interface SpaRepository extends  JpaRepository<Spa, Integer>{

    @Query(value = "SELECT * FROM spas", nativeQuery = true)
    Collection<Spa> darSpas();


    @Query(value = "SELECT * FROM spas WHERE servicoId = :servicoId", nativeQuery = true)
    Spa sarSpa(@Param("servicoId") int  servicoId );


    @Modifying
    @Query(value = "INSERT INTO spas (servicioId, duracion, costo) VALUES (:servicioId , :duracion, :costo )", nativeQuery = true)
    void insertarSpa (@Param ("servicioId") Integer  servicioId ,@Param ("duracion") Integer duracion, @Param("costo") Integer  costo);
    


    @Modifying
    @Query(value = "UPDATE spas ( duracion, costo) VALUES (duracion, :costo ) WHERE servicioId = :servicioId", nativeQuery = true)
    void modificarSpa (@Param ("servicioId") Integer  servicioId ,@Param ("duracion") Integer duracion, @Param("costo") Integer  costo);
    
    @Modifying
    @Query(value = "DELETE FROM spas WHERE servicioId = :servicioId", nativeQuery = true)
    void borrarrSpa (@Param ("servicioId") Integer  servicioId );
    


}
