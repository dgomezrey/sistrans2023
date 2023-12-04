package com.example.mdbspringboot.repositorio;

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

    // Faltan RFCs
}
