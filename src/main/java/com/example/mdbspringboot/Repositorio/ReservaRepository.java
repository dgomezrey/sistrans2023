package com.example.mdbspringboot.Repositorio;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.mdbspringboot.Modelo.Reserva;

public interface ReservaRepository extends MongoRepository<Reserva,String> {

    @Query("{'cliente_id': ?0}")
    List<Reserva> findByClienteId(String clienteId);

    @Query("{'habitacion_id': ?0}")
    List<Reserva> findByHabitacionId(String habitacionId);

    //RFC faltan acá
}
