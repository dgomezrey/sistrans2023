package uniandes.edu.co.hotel_andes.repositorio;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uniandes.edu.co.hotel_andes.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    @Query(value = "SELECT * FROM Usuarios", nativeQuery = true)
    Collection<Usuario> darUsuarios();

    @Query(value = "SELECT * FROM Usuarios WHERE id = :id", nativeQuery = true)
    Usuario darUsuario(@Param("id") long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Usuarios (id, usuario, contrasena, nombre, email, tipoDocumento, documento, TiposUsuario_id) VALUES ( usuarios_sequence.nextval , :usuario, :contrasena, :nombre, :email, :tipoDocumento, :documento, :TiposUsuario_id)", nativeQuery = true)
    void insertarUsuario(@Param("usuario") String username, @Param("contrasena") String contrasena,
            @Param("nombre") String nombre, @Param("email") String email, @Param("tipoDocumento") String tipoDocumento,
            @Param("documento") String documento, @Param("TiposUsuario_id") long TiposUsuario_id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Usuarios SET usuario = :usuario, contrasena = :contrasena, nombre = :nombre, email = :email, tipoDocumento = :tipoDocumento, documento = :documento, TiposUsuario_id = :TiposUsuario_id WHERE id = :id", nativeQuery = true)
    void actualizarUsuario(@Param("id") long id, @Param("usuario") String username,
            @Param("contrasena") String contrasena, @Param("nombre") String nombre, @Param("email") String email,
            @Param("tipoDocumento") String tipoDocumento, @Param("documento") String documento,
            @Param("TiposUsuario_id") long TiposUsuario_id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Usuarios WHERE id = :id", nativeQuery = true)
    void eliminarUsuario(@Param("id") long id);

    @Query(value = "SELECT * FROM Usuarios  WHERE (contrasena = :contrasena AND usuario = :username)", nativeQuery = true)
    Usuario encontrarUsuarioPorUsuarioYcontrasena(@Param("username") String username, @Param("contrasena") String contrasena);

    @Query(value = "SELECT u.id, u.nombre, u.email " + //
                    "FROM Usuarios u " + //
                    "WHERE u.id IN ( " + //
                    "    SELECT r.usuarios_id " + //
                    "    FROM ReservasAlojamiento r " + //
                    "    WHERE r.fechain BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE " + //
                    "    GROUP BY r.usuarios_id " + //
                    "    HAVING COUNT(DISTINCT r.fechain) >= 14 " + //
                    ") OR u.id IN ( " + //
                    "    SELECT c.id " + //
                    "    FROM Consumos c " + //
                    "    WHERE c.fecha BETWEEN ADD_MONTHS(SYSDATE, -12) AND SYSDATE " + //
                    "    GROUP BY c.id " + //
                    "    HAVING SUM(c.total) > 15000000 " + //
                    ")", nativeQuery = true)
    Collection<Object[]> darRFC7();
}
