package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hotel_andes.modelo.Habitacion;
import uniandes.edu.co.hotel_andes.repositorio.HabitacionRepository;
import uniandes.edu.co.hotel_andes.repositorio.TipoHabitacionRepository;

@Controller
public class HabitacionesController {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/habitaciones")
    public String habitaciones(Model model) {
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        return "habitaciones";
    }

    @GetMapping("/habitaciones/new")
    public String habitacionForm(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("tipos", tipoHabitacionRepository.darTiposHabitacion());
        return "habitacionNuevo";
    }

    @PostMapping("/habitaciones/new/save")
    public String habitacionGuardar(@ModelAttribute Habitacion habitacion) {
        habitacionRepository.insertarHabitacion(habitacion.getNumero(), habitacion.getPiso(), habitacion.getOcupada(),
                habitacion.getTiposHabitacion_id().getId());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id}/edit")
    public String habitacionEditarForm(@PathVariable("id") Long id, Model model) {
        Habitacion habitacion = habitacionRepository.darHabitacion(id);
        if (habitacion != null) {
            model.addAttribute("habitacion", habitacion);
            model.addAttribute("tipos", tipoHabitacionRepository.darTiposHabitacion());
            return "habitacionEditar";
        } else {
            return "redirect:/habitaciones";
        }
    }

    @PostMapping("/habitaciones/{id}/edit/save")
    public String habitacionEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Habitacion habitacion) {
        habitacionRepository.actualizarHabitacion(id, habitacion.getNumero(), habitacion.getPiso(),
                habitacion.getOcupada(), habitacion.getTiposHabitacion_id().getId());
        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id}/delete")
    public String habitacionEliminar(@PathVariable("id") Long id) {
        habitacionRepository.eliminarHabitacion(id);
        return "redirect:/habitaciones";
    }

}
