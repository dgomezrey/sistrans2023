package com.example.mdbspringboot.Modelo;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("reservas")
public class Reserva {
    
    @Id
    private String id;

    private Date fecha_inicio;

    private Date fecha_fin;

    private Date fecha_checkin;

    private Date fecha_checkout;

    private int num_personas;

    private String cliente_id;

    private String habitacion_id;

    public Reserva() {;}

    public Reserva(String id, Date fecha_inicio, Date fecha_fin, Date fecha_checkin, Date fecha_checkout,
            int num_personas, String cliente_id, String habitacion_id) {
        this.id = id;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.fecha_checkin = fecha_checkin;
        this.fecha_checkout = fecha_checkout;
        this.num_personas = num_personas;
        this.cliente_id = cliente_id;
        this.habitacion_id = habitacion_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(Date fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public Date getFecha_checkin() {
        return fecha_checkin;
    }

    public void setFecha_checkin(Date fecha_checkin) {
        this.fecha_checkin = fecha_checkin;
    }

    public Date getFecha_checkout() {
        return fecha_checkout;
    }

    public void setFecha_checkout(Date fecha_checkout) {
        this.fecha_checkout = fecha_checkout;
    }

    public int getNum_personas() {
        return num_personas;
    }

    public void setNum_personas(int num_personas) {
        this.num_personas = num_personas;
    }

    public String getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(String cliente_id) {
        this.cliente_id = cliente_id;
    }

    public String getHabitacion_id() {
        return habitacion_id;
    }

    public void setHabitacion_id(String habitacion_id) {
        this.habitacion_id = habitacion_id;
    }

}
