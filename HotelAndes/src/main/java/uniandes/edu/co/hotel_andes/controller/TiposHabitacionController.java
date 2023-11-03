package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hotel_andes.modelo.TipoHabitacion;
import uniandes.edu.co.hotel_andes.repositorio.TipoHabitacionRepository;

@Controller
public class TiposHabitacionController {

    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/tiposHabitacion")
    public String tiposHabitacion(Model model) {
        model.addAttribute("tiposHabitacion", tipoHabitacionRepository.darTiposHabitacion());
        return "tiposHabitacion";
    }

    @GetMapping("/tiposHabitacion/new")
    public String tipoHabitacionForm(Model model) {
        model.addAttribute("tipoHabitacion", new TipoHabitacion());
        return "tipoHabitacionNuevo";
    }

    @PostMapping("/tiposHabitacion/new/save")
    public String tipoHabitacionGuardar(@ModelAttribute TipoHabitacion tipoHabitacion) {
        tipoHabitacionRepository.insertarTipoHabitacion(tipoHabitacion.getTipo(), tipoHabitacion.getCapacidad(),
                tipoHabitacion.getCamas(), tipoHabitacion.getCostoPorNoche());
        return "redirect:/tiposHabitacion";
    }

    @GetMapping("/tiposHabitacion/{id}/edit")
    public String tipoHabitacionEditarForm(@PathVariable("id") Long id, Model model) {
        TipoHabitacion tipoHabitacion = tipoHabitacionRepository.darTipoHabitacion(id);
        if (tipoHabitacion != null) {
            model.addAttribute("tipoHabitacion", tipoHabitacion);
            return "tipoHabitacionEditar";
        } else {
            return "redirect:/tiposHabitacion";
        }
    }

    @PostMapping("/tiposHabitacion/{id}/edit/save")
    public String tipoHabitacionEditarGuardar(@PathVariable("id") Long id,
            @ModelAttribute TipoHabitacion tipoHabitacion) {
        tipoHabitacionRepository.actualizarTipoHabitacion(id, tipoHabitacion.getTipo(), tipoHabitacion.getCapacidad(),
                tipoHabitacion.getCamas(), tipoHabitacion.getCostoPorNoche());
        return "redirect:/tiposHabitacion";
    }

    @GetMapping("/tiposHabitacion/{id}/delete")
    public String tipoHabitacionEliminar(@PathVariable("id") Long id) {
        tipoHabitacionRepository.eliminarTipoHabitacion(id);
        return "redirect:/tiposHabitacion";
    }

}
