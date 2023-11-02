package uniandes.edu.co.hotel_andes;



import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.hotel_andes.modelo.Servicio;
import uniandes.edu.co.hotel_andes.repositorio.ServicioRepository;


@SpringBootApplication
public class HotelAndesApplication  implements CommandLineRunner {
 
	@Autowired
	private ServicioRepository servicioRepository;

	public static void main(String[] args) {
		SpringApplication.run(HotelAndesApplication.class, args);
	}

	@Override
	public void run(String... arg) {
		Collection<Servicio> servicios = servicioRepository.darServicios();
		for (Servicio servicio : servicios) {
			System.out.println(servicio);
		}

	}

	

}
