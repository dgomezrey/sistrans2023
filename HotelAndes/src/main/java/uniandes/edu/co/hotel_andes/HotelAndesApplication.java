package uniandes.edu.co.hotel_andes;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import uniandes.edu.co.hotel_andes.modelo.Producto;

import uniandes.edu.co.hotel_andes.repositorio.ProductoRepository;


@SpringBootApplication
public class HotelAndesApplication implements CommandLineRunner {
 
	@Autowired
	private ProductoRepository ProductoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HotelAndesApplication.class, args);
	}

	@Override
	public void run (String... arg){
		Collection <Producto> productos = ProductoRepository.darProductos();


		for (Producto s : productos){

			System.out.println(s);
		}

	}

}
