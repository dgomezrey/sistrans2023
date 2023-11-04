package uniandes.edu.co.hotel_andes;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.hotel_andes.modelo.Habitacion;
import uniandes.edu.co.hotel_andes.repositorio.HabitacionRepository;


@SpringBootApplication
public class HotelAndesApplication  implements CommandLineRunner {
 
	@Autowired
	private HabitacionRepository habitacionRepository;

	public static void main(String[] args) {
		SpringApplication.run(HotelAndesApplication.class, args);
	}

	@Override
	public void run(String... arg) {
		Collection<Habitacion> habitaciones = habitacionRepository.darHabitaciones();
		for (Habitacion habitacion : habitaciones) {
			System.out.println(habitacion.getNumero());
		}

	}

	

}
