package com.example.mdbspringboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.modelo.Servicio;
import com.example.mdbspringboot.modelo.Servicio.Producto;
import com.example.mdbspringboot.repositorio.ConsumoRepository;
import com.example.mdbspringboot.repositorio.ServicioRepository;

@Controller
public class ServiciosController {
    
    @Autowired
    ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String servicios(Model model){
        model.addAttribute("servicios", servicioRepository.darServicios());
        return "/servicios.html";
    }

    @GetMapping("/servicios/new")
    public String servicioForm(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "/servicioNuevo";
    }

    @PostMapping("/servicios/new/save")
    public String servicioGuardar(Model model, @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion, @RequestParam("tipo_servicio") String tipo_servicio,
        @RequestParam("costo_unidad") double costo_unidad, @RequestParam("horario") String horario,
        @RequestParam("capacidad") int capacidad, @RequestParam("productos") String productos){

        List<Producto> lista = new ArrayList<>();
        for(String elem: productos.split(",")){
            String[] split = elem.split(":");
            lista.add(new Producto(split[0], Double.parseDouble(split[1])));
        }

        Servicio servicio = new Servicio(nombre, descripcion, tipo_servicio, costo_unidad, horario, capacidad, lista);
        servicioRepository.insert(servicio);

        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/edit")
    public String servicioEditar(Model model, @PathVariable("id") String id) {
        Servicio servicio = servicioRepository.findById(id).get();
        String linea = "";
        for(int i = 0; i < servicio.getProductos().size();i++){
            if(i != servicio.getProductos().size()-1){
                linea += servicio.getProductos().get(i).getNombre() + ":" + servicio.getProductos().get(i).getPrecio() + "," ;
            }
            else{
                linea += servicio.getProductos().get(i).getNombre() + ":" + servicio.getProductos().get(i).getPrecio() ;
            }
        }
        model.addAttribute("servicio", servicio);
        model.addAttribute("lista", linea);
        return "/servicioEditar";
    }

    @PostMapping("/servicios/{id}/edit/save")
    public String servicioEditarGuardar(Model model, @RequestParam("nombre") String nombre,
        @RequestParam("descripcion") String descripcion, @RequestParam("tipo_servicio") String tipo_servicio,
        @RequestParam("costo_unidad") double costo_unidad, @RequestParam("horario") String horario,
        @RequestParam("capacidad") int capacidad, @RequestParam("productos") String productos,
        @PathVariable("id") String id){

        List<Producto> lista = new ArrayList<>();
        for(String elem: productos.split(",")){
            String[] split = elem.split(":");
            lista.add(new Producto(split[0], Double.parseDouble(split[1])));
        }

        Servicio servicio = servicioRepository.findById(id).get();
        servicio.setCapacidad(capacidad);
        servicio.setCosto_unidad(costo_unidad);
        servicio.setDescripcion(descripcion);
        servicio.setHorario(horario);
        servicio.setNombre(nombre);
        servicio.setProductos(lista);
        servicio.setTipo_servicio(tipo_servicio);
        
        servicioRepository.save(servicio);

        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/delete")
    public String servicioEliminar(Model model, @PathVariable("id") String id) {
        servicioRepository.deleteById(id);
        return "redirect:/servicios";
    }

/*     @GetMapping("/RFC4")
    String RFC4(Model model){

        model.addAttribute("servicios", servicioRepository.findAll());

        return "Formularios/RFC4";
    } */

/*     @GetMapping("/RFC4/mostrar")
    String RFC4Mostrar(Model model, @RequestParam("fecha1") String fecha1,
        @RequestParam("fecha2") String fecha2, @RequestParam("servicio") String servicio,
        @RequestParam("agrupamiento") String agrupamiento, @RequestParam("ordenamiento") String ordenamiento,
        @RequestParam("orden") int orden) throws ParseException{

            if(ordenamiento.equals("agrupamiento")){
                ordenamiento = agrupamiento;
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            model.addAttribute("datos", servicioRepository.RFC4(servicio, sdf.parse(fecha1), sdf.parse(fecha2), agrupamiento, ordenamiento, orden));

        return "/RFC4.html";
    } */
}
