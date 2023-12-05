package com.example.mdbspringboot.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.modelo.Consumo;

public interface ConsumoRepository extends MongoRepository<Consumo, String> {

    @Query("{'reserva_id': ?0}")
    List<Consumo> darConsumosReserva(String reservaId);

    @Query("{'servicio_id': ?0}")
    List<Consumo> findByServicioId(String servicioId);

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'reservas', localField: 'reserva_id', foreignField: '_id', as: 'reserva' } }",
            "{ $unwind: '$reserva' }",
            "{ $match: { 'reserva.cliente_id': ?0 } }",
            "{ $project: { cantidad: 1, fecha: 1, total: 1, 'servicio_id': 1, 'reserva_id': 1, 'cliente_id': '$reserva.cliente_id' } }"
    })
    List<Consumo> darConsumosCliente(String clienteId);

    public class ConsumoDetalle {
        private int cantidad;
        private Date fecha;
        private double total;
        private String nombreCliente;
        private int numeroHabitacion;
        private String nombreServicio;
        private String tipoServicio;

        public ConsumoDetalle(int cantidad, Date fecha, double total, String nombreCliente, int numeroHabitacion,
                String nombreServicio, String tipoServicio) {
            this.cantidad = cantidad;
            this.fecha = fecha;
            this.total = total;
            this.nombreCliente = nombreCliente;
            this.numeroHabitacion = numeroHabitacion;
            this.nombreServicio = nombreServicio;
            this.tipoServicio = tipoServicio;
        }

        public int getCantidad() {
            return cantidad;
        }

        public Date getFecha() {
            return fecha;
        }

        public double getTotal() {
            return total;
        } 

        public String getNombreCliente() {
            return nombreCliente;
        }

        public int getNumeroHabitacion() {
            return numeroHabitacion;
        }

        public String getNombreServicio() {
            return nombreServicio;
        }

        public String getTipoServicio() {
            return tipoServicio;
        }

        public void setCantidad(int cantidad) {
            this.cantidad = cantidad;
        }

        public void setFecha(Date fecha) {
            this.fecha = fecha;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public void setNombreCliente(String nombreCliente) {
            this.nombreCliente = nombreCliente;
        }

        public void setNumeroHabitacion(int numeroHabitacion) {
            this.numeroHabitacion = numeroHabitacion;
        }

        public void setNombreServicio(String nombreServicio) {
            this.nombreServicio = nombreServicio;
        }

        public void setTipoServicio(String tipoServicio) {
            this.tipoServicio = tipoServicio;
        }

    }

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'reservas', localField: 'reserva_id', foreignField: '_id', as: 'reserva' } }",
            "{ $unwind: '$reserva' }",
            "{ $lookup: { from: 'clientes', localField: 'reserva.cliente_id', foreignField: '_id', as: 'cliente' } }",
            "{ $unwind: '$cliente' }",
            "{ $lookup: { from: 'habitaciones', localField: 'reserva.habitacion_id', foreignField: '_id', as: 'habitacion' } }",
            "{ $unwind: '$habitacion' }",
            "{ $lookup: { from: 'servicios', localField: 'servicio_id', foreignField: '_id', as: 'servicio' } }",
            "{ $unwind: '$servicio' }",
            "{ $project: { cantidad: 1, fecha: 1, total: 1, 'cliente.nombre': 1, 'habitacion.numero': 1, 'servicio.nombre': 1, 'servicio.tipo_servicio': 1 } }"
    })
    List<ConsumoDetalle> darConsumosDetalle();

    // Faltan RFCs
}
