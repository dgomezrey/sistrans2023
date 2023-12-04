package com.example.mdbspringboot.modelo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("servicios")
public class Servicio {
    
    @Id
    private String id;

    private String nombre;

    private String descripcion;

    private String tipo_servicio;

    private double costo_unidad;

    private String horario;

    private int capacidad;

    List<Producto> productos;

    public Servicio() {;}

    public Servicio(String nombre, String descripcion, String tipo_servicio, List<Producto> productos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo_servicio = tipo_servicio;
        this.productos = productos;
    }

    public Servicio(String nombre, String descripcion, String tipo_servicio, double costo_unidad, String horario,
            int capacidad, List<Producto> productos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo_servicio = tipo_servicio;
        this.costo_unidad = costo_unidad;
        this.horario = horario;
        this.capacidad = capacidad;
        this.productos = productos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public double getCosto_unidad() {
        return costo_unidad;
    }

    public void setCosto_unidad(double costo_unidad) {
        this.costo_unidad = costo_unidad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    //CLase Producto embebida
    public static class Producto {

        private String nombre;

        private double precio;

        public Producto() {;}

        public Producto(String nombre, double precio) {
            this.nombre = nombre;
            this.precio = precio;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public double getPrecio() {
            return precio;
        }

        public void setPrecio(double precio) {
            this.precio = precio;
        }

        
    }
    
}
