package com.example.mdbspringboot.Repositorio;


import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.Modelo.Habitacion;

public interface HabitacionRepository extends MongoRepository<Habitacion,String> {

    @Query("{'numero': ?0}")
    Habitacion findByNumero(int numero);

    @Query("{'tipo_habitacion_id': ?0}")
    List<Habitacion> findByTipoHabitacionId(String id);
    
}
