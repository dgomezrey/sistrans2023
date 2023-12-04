package com.example.mdbspringboot.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.modelo.Reserva;

public interface ReservaRepository extends MongoRepository<Reserva, String> {

    @Query("{'cliente_id': ?0}")
    List<Reserva> findByClienteId(String clienteId);

    @Query("{'habitacion_id': ?0}")
    List<Reserva> findByHabitacionId(String habitacionId);

    public class ReservaDetalle {
        private Date fechaInicio;
        private Date fechaFin;
        private Date fechaCheckin;
        private Date fechaCheckout;
        private int numPersonas;
        private String clienteId;
        private String clienteNombre;
        private int numeroHabitacion;
        private String tipoHabitacion;

        public ReservaDetalle(Date fechaInicio, Date fechaFin, Date fechaCheckin, Date fechaCheckout, int numPersonas,
                String clienteId, String clienteNombre, int numeroHabitacion, String tipoHabitacion) {
            this.fechaInicio = fechaInicio;
            this.fechaFin = fechaFin;
            this.fechaCheckin = fechaCheckin;
            this.fechaCheckout = fechaCheckout;
            this.numPersonas = numPersonas;
            this.clienteId = clienteId;
            this.clienteNombre = clienteNombre;
            this.numeroHabitacion = numeroHabitacion;
            this.tipoHabitacion = tipoHabitacion;
        }

        public Date getFechaInicio() {
            return fechaInicio;
        }

        public Date getFechaFin() {
            return fechaFin;
        }

        public Date getFechaCheckin() {
            return fechaCheckin;
        }

        public Date getFechaCheckout() {
            return fechaCheckout;
        }

        public int getNumPersonas() {
            return numPersonas;
        }

        public String getClienteId() {
            return clienteId;
        }

        public String getClienteNombre() {
            return clienteNombre;
        }

        public int getNumeroHabitacion() {
            return numeroHabitacion;
        }

        public String getTipoHabitacion() {
            return tipoHabitacion;
        }

        public void setFechaInicio(Date fechaInicio) {
            this.fechaInicio = fechaInicio;
        }

        public void setFechaFin(Date fechaFin) {
            this.fechaFin = fechaFin;
        }

        public void setFechaCheckin(Date fechaCheckin) {
            this.fechaCheckin = fechaCheckin;
        }

        public void setFechaCheckout(Date fechaCheckout) {
            this.fechaCheckout = fechaCheckout;
        }

        public void setNumPersonas(int numPersonas) {
            this.numPersonas = numPersonas;
        }

        public void setClienteId(String clienteId) {
            this.clienteId = clienteId;
        }

        public void setClienteNombre(String clienteNombre) {
            this.clienteNombre = clienteNombre;
        }

        public void setNumeroHabitacion(int numeroHabitacion) {
            this.numeroHabitacion = numeroHabitacion;
        }

        public void setTipoHabitacion(String tipoHabitacion) {
            this.tipoHabitacion = tipoHabitacion;
        }

    }

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'clientes', localField: 'cliente_id', foreignField: '_id', as: 'cliente' } }",
            "{ $lookup: { from: 'habitaciones', localField: 'habitacion_id', foreignField: '_id', as: 'habitacion' } }",
            "{ $unwind: '$cliente' }",
            "{ $unwind: '$habitacion' }",
            "{ $lookup: { from: 'tipos_habitacion', localField: 'habitacion.tipo_habitacion_id', foreignField: '_id', as: 'tipoHabitacion' } }",
            "{ $unwind: '$tipoHabitacion' }",
            "{ $project: { 'fecha_inicio': 1, 'fecha_fin': 1, 'fecha_checkin': 1, 'fecha_checkout': 1, 'num_personas': 1, 'cliente_id': '$cliente._id', 'cliente_nombre': '$cliente.nombre', 'habitacion_numero': '$habitacion.numero', 'tipo_habitacion': '$tipoHabitacion.tipo' } }"
    })
    List<ReservaDetalle> darReservasDetalle();

    // RFC faltan acá
}
