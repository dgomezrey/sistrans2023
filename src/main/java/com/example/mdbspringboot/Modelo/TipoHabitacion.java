package com.example.mdbspringboot.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("tipos_habitacion")
public class TipoHabitacion {
    
    @Id
    private String id;

    private String tipo;

    private int capacidad;

    private int camas;

    private double costo_noche;

    public TipoHabitacion() {;}

    public TipoHabitacion(String id, String tipo, int capacidad, int camas, double costo_noche) {
        this.id = id;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.camas = camas;
        this.costo_noche = costo_noche;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCamas() {
        return camas;
    }

    public void setCamas(int camas) {
        this.camas = camas;
    }

    public double getCosto_noche() {
        return costo_noche;
    }

    public void setCosto_noche(double costo_noche) {
        this.costo_noche = costo_noche;
    }
    
}
