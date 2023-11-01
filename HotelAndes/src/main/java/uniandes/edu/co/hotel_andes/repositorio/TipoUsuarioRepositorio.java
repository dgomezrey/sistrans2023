package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.TipoUsuario;

public interface TipoUsuarioRepositorio extends JpaRepository<TipoUsuario, Integer> {

    @Query(value = "SELECT * FROM tiposusuario", nativeQuery = true)
    Collection<TipoUsuario> darTiposUsuarios();

    @Query(value = "SELECT * FROM tiposusuario WHERE id = :id", nativeQuery = true)
    TipoUsuario darTipoUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tiposusuario WHERE id = :id", nativeQuery = true)
    void eliminarTipoUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE tiposusuario SET tipo = :tipo WHERE id = :id", nativeQuery = true)
    void actualizarTipoUsuario(@Param("id") long id, @Param("tipo") String tipo);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO tiposusuario (id, tipo) VALUES ( hotelandesSequence , :tipo)", nativeQuery = true)
    void insertarTipoUsuario(@Param("tipo") String tipo);
    
}
