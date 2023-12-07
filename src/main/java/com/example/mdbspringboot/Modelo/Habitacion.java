package com.example.mdbspringboot.modelo;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("habitaciones")
public class Habitacion {
    
    @Id
    private String id;

    private int numero;
    
    private int piso;

    private ObjectId tipo_habitacion_id;

    public Habitacion() {;}

    public Habitacion(String id, int numero, int piso, ObjectId tipo_habitacion_id) {
        this.id = id;
        this.numero = numero;
        this.piso = piso;
        this.tipo_habitacion_id = tipo_habitacion_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

    public ObjectId getTipo_habitacion_id() {
        return tipo_habitacion_id;
    }

    public void setTipo_habitacion_id(ObjectId tipo_habitacion_id) {
        this.tipo_habitacion_id = tipo_habitacion_id;
    }
    
}
