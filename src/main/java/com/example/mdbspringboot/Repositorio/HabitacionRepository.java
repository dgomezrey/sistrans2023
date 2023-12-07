package com.example.mdbspringboot.repositorio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.modelo.Habitacion;

public interface HabitacionRepository extends MongoRepository<Habitacion, String> {

    @Query("{'numero': ?0}")
    Habitacion findByNumero(int numero);

    @Query("{'tipo_habitacion_id': ?0}")
    List<Habitacion> findByTipoHabitacionId(String id);

    public class HabitacionConTipo {
        private String id;
        private int numero;
        private int piso;
        private String tipo;
        private int capacidad;
        private int camas;
        private double costo_noche;

        public HabitacionConTipo(String id, int numero, int piso, String tipo, int capacidad, int camas,
                double costo_noche) {
            this.id = id;
            this.numero = numero;
            this.piso = piso;
            this.tipo = tipo;
            this.capacidad = capacidad;
            this.camas = camas;
            this.costo_noche = costo_noche;
        }

        public String getId() {
            return id;
        }

        public int getNumero() {
            return numero;
        }

        public int getPiso() {
            return piso;
        }

        public String getTipo() {
            return tipo;
        }

        public int getCapacidad() {
            return capacidad;
        }

        public int getCamas() {
            return camas;
        }

        public double getCostoNoche() {
            return costo_noche;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setNumero(int numero) {
            this.numero = numero;
        }

        public void setPiso(int piso) {
            this.piso = piso;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public void setCapacidad(int capacidad) {
            this.capacidad = capacidad;
        }

        public void setCamas(int camas) {
            this.camas = camas;
        }

        public void setCostoNoche(double costo_noche) {
            this.costo_noche = costo_noche;
        }
    }

    @Aggregation(pipeline = {
            "{ $lookup: { from: 'tipos_habitacion', localField: 'tipo_habitacion_id', foreignField: '_id', as: 'tipoHabitacion' } }",
            "{ $unwind: '$tipoHabitacion' }",
            "{ $project: { numero: 1, piso: 1, 'tipo': '$tipoHabitacion.tipo', 'capacidad': '$tipoHabitacion.capacidad', 'camas': '$tipoHabitacion.camas', 'costo_noche': '$tipoHabitacion.costo_noche' } }"
    })
    List<HabitacionConTipo> darHabitacionesConTipo();

    @Aggregation(pipeline = {
            "{ $match: { '_id': ?0 } }",
            "{ $lookup: { from: 'tipos_habitacion', localField: 'tipo_habitacion_id', foreignField: '_id', as: 'tipoHabitacion' } }",
            "{ $unwind: '$tipoHabitacion' }",
            "{ $project: { numero: 1, piso: 1, 'tipo': '$tipoHabitacion.tipo', 'capacidad': '$tipoHabitacion.capacidad', 'camas': '$tipoHabitacion.camas', 'costo_noche': '$tipoHabitacion.costo_noche' } }"
    })
    Optional<HabitacionConTipo> darHabitacionConTipo(ObjectId id);
    
}
