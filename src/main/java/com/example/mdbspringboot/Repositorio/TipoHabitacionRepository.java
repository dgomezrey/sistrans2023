package com.example.mdbspringboot.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.modelo.TipoHabitacion;

public interface TipoHabitacionRepository extends MongoRepository<TipoHabitacion,String>{

    @Query("{tipo: '?0'}")
    TipoHabitacion findByTipo(String tipo);
    
}
