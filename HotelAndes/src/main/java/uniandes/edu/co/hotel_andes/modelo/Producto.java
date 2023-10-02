package uniandes.edu.co.hotel_andes.modelo;

import jakarta.persistence.Entity;

import jakarta.persistence.Table;

@Entity
@Table(name = "productos")
public class Producto {


    


    private Integer id;
    private String nombre;
    private float precio;


    public Producto(Integer id, String nombre, float precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }


    public Producto(){;}

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public float getPrecio() {
        return precio;
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }

    


}
