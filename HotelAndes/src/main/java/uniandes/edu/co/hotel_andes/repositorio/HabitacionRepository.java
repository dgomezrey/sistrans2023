package uniandes.edu.co.hotel_andes.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import uniandes.edu.co.hotel_andes.modelo.Habitacion;

public interface HabitacionRepository extends JpaRepository<Habitacion, Integer>{
    
}
