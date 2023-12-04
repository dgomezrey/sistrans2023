package com.example.mdbspringboot.repositorio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.mdbspringboot.modelo.Cliente;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente,String> {

    @Query("{usuario: '?0', contrasena: '?1'}")
    Optional<Cliente> findCredentials(String usuario, String contrasena);

}


