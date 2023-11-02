package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String usuario;

    private String contrasena;

    private String nombre;

    private String email;

    private String tipodocumento;

    private String documento;

    @ManyToOne
    @JoinColumn(name = "Tiposusuario_id", referencedColumnName = "id")
    private TipoUsuario tiposusuario_id;

    public Usuario() {;}

    public Usuario(String usuario, String contrasena, String nombre, String email, String tipoDocumento,
            String documento, TipoUsuario tiposUsuario_id) {
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.email = email;
        this.tipodocumento = tipoDocumento;
        this.documento = documento;
        this.tiposusuario_id = tiposUsuario_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoDocumento() {
        return tipodocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipodocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoUsuario getTiposUsuario_id() {
        return tiposusuario_id;
    }

    public void setTiposUsuario_id(TipoUsuario tiposUsuario_id) {
        this.tiposusuario_id = tiposUsuario_id;
    }

}