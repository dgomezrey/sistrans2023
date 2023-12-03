package com.example.mdbspringboot.Repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.Modelo.Consumo;


public interface ConsumoRepository extends MongoRepository<Consumo,String>{

    @Query("{'reserva_id': ?0}")
    List<Consumo> findByReservaId(String reservaId);

    @Query("{'servicio_id': ?0}")
    List<Consumo> findByServicioId(String servicioId);

    //Faltan RFCs
}
