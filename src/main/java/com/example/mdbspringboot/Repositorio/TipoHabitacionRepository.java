package com.example.mdbspringboot.Repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.Modelo.TipoHabitacion;

public interface TipoHabitacionRepository extends MongoRepository<TipoHabitacion,String>{

    @Query("{tipo: '?0'}")
    TipoHabitacion findByTipo(String tipo);
    
}
