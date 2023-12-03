package com.example.mdbspringboot.Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.Modelo.TipoHabitacion;
import com.example.mdbspringboot.Repositorio.HabitacionRepository;
import com.example.mdbspringboot.Repositorio.TipoHabitacionRepository;

@Controller
public class TiposHabitacionController {

    @Autowired
    TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired
    HabitacionRepository habitacionRepository;

    @GetMapping("/tiposHabitacion")
    String tiposHabitacion(Model model) {
        model.addAttribute("tiposHabitacion", tipoHabitacionRepository.findAll());
        return "/tiposHabitacion";
    }

    @GetMapping("/tiposHabitacion/new")
    public String tipoHabitacionForm(Model model) {
        model.addAttribute("tipoHabitacion", new TipoHabitacion());
        return "tipoHabitacionNuevo";
    }

    @PostMapping("/tiposHabitacion/new/save")
    public String tipoHabitacionGuardar(Model model, @RequestParam("tipo") String tipo,
            @RequestParam("capacidad") int capacidad, @RequestParam("camas") int camas,
            @RequestParam("costo_noche") double costo_noche) {
        TipoHabitacion tipoHabitacion = new TipoHabitacion(null, tipo, capacidad, camas, costo_noche);

        tipoHabitacionRepository.insert(tipoHabitacion);

        return "redirect:/tiposHabitacion";
    }

    @GetMapping("/RF1/{id}/delete")
    String borrar(Model model, @PathVariable("id") String id) {

        if (!habitacionRepository.findByTipoHabitacion(id).isEmpty()) {
            model.addAttribute("causa", "HAY HABITACIONES CON ESTE TIPO DE HABITACION ASOCIADO");
            return "error.html";
        }

        tipoHabitacionRepository.deleteById(id);
        return "redirect:/RF1";
    }

    @GetMapping("/tiposHabitacion/{id}/edit")
    public String tipoHabitacionEditarForm(@PathVariable("id") String id, Model model) {

        Optional<TipoHabitacion> optional = tipoHabitacionRepository.findById(id);
        TipoHabitacion tipoHabitacion = optional.get();
        model.addAttribute("tipoHabitacion", tipoHabitacion);

        return "tipoHabitacionEditar";

    }

    @PostMapping("/RF1/{id}/edit/save")
    String saveEdit(Model model, @PathVariable("id") String id, @RequestParam("capacidad") String capacidad,
            @RequestParam("nombre") String nombre, @RequestParam("listaElementos") String listaElementos) {
        List<ElementoHabitacion> lista = new ArrayList<>();
        for (String elem : listaElementos.split(",")) {
            lista.add(new ElementoHabitacion(elem));
        }

        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.findById(id).get();
        tipoHabitacion.setCapacidad(Integer.parseInt(capacidad));
        tipoHabitacion.setElementosHabitaciones(lista);
        tipoHabitacion.setNombre(nombre);

        tipoHabitacionRepository.save(tipoHabitacion);

        return "redirect:/RF1";
    }

    @PostMapping("/tiposHabitacion/{id}/edit/save")
    public String tipoHabitacionEditarGuardar(@PathVariable("id") Long id,
            @ModelAttribute TipoHabitacion tipoHabitacion) {
        tipoHabitacionRepository.actualizarTipoHabitacion(id, tipoHabitacion.getTipo(), tipoHabitacion.getCapacidad(),
                tipoHabitacion.getCamas(), tipoHabitacion.getCostoPorNoche());
        return "redirect:/tiposHabitacion";
    }
}
