package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{

    @Query(value = "SELECT * FROM usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM usuarios WHERE id = :id", nativeQuery = true)
    Usuario darUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM usuarios WHERE id = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE usuarios SET nombre = :nombre, apellido = :apellido, documento = :documento, tipoUsuario = :tipoUsuario WHERE id = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") long id, @Param("nombre") String nombre, @Param("apellido") String apellido, @Param("documento") String documento, @Param("tipoUsuario") long tipoUsuario);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO usuarios (id, nombre, apellido, documento, tipoUsuario) VALUES ( hotelandesSequence , :nombre, :apellido, :documento, :tipoUsuario)", nativeQuery = true)
    void insertarUsuario();
    
}
