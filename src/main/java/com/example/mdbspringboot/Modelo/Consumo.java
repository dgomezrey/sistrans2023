package com.example.mdbspringboot.modelo;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("consumos")
public class Consumo {
    
    @Id
    private String id;

    private int cantidad;

    private Date fecha;

    private double total;

    private ObjectId servicio_id;

    private ObjectId reserva_id;

    public Consumo() {;}

    public Consumo(String id, int cantidad, java.util.Date fecha, double total, ObjectId servicio_id, ObjectId reserva_id) {
        this.id = id;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.total = total;
        this.servicio_id = servicio_id;
        this.reserva_id = reserva_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public ObjectId getServicio_id() {
        return servicio_id;
    }

    public void setServicio_id(ObjectId servicio_id) {
        this.servicio_id = servicio_id;
    }

    public ObjectId getReserva_id() {
        return reserva_id;
    }

    public void setReserva_id(ObjectId reserva_id) {
        this.reserva_id = reserva_id;
    }

}
