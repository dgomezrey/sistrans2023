package com.example.mdbspringboot.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.modelo.Servicio;

public interface ServicioRepository extends MongoRepository<Servicio,String> {

    @Query("{'tipo_servicio': ?0}")
    Servicio findByTipoServicio(String tipoServicio);

    @Query("{}")
    List<Servicio> darServicios();
    
}
