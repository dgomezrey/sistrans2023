package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hotel_andes.modelo.Servicio;
import uniandes.edu.co.hotel_andes.repositorio.ServicioRepository;

@Controller
public class ServiciosController {

    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String servicios(Model model) {
        model.addAttribute("servicios", servicioRepository.darServicios());
        return "servicios";
    }

    @GetMapping("/servicios/new")
    public String servicioForm(Model model) {
        model.addAttribute("servicio", new Servicio());
        return "servicioNuevo";
    }

    @PostMapping("/servicios/new/save")

    public String servicioGuardar(@ModelAttribute Servicio servicio) {
        servicioRepository.insertarServicio(servicio.getNombre(), servicio.getDescripcion(), servicio.getTipoServicio(),
                servicio.getCostoPorUnidad(), servicio.getHorario(), servicio.getCapacidad());
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/edit")
    public String servicioEditarForm(@PathVariable("id") Long id, Model model) {
        Servicio servicio = servicioRepository.darServicio(id);
        if (servicio != null) {
            model.addAttribute("servicio", servicio);
            return "servicioEditar";
        } else {
            return "redirect:/servicios";
        }
    }

    @PostMapping("/servicios/{id}/edit/save")
    public String servicioEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Servicio servicio) {
        servicioRepository.actualizarServicio(id, servicio.getNombre(), servicio.getDescripcion(),
                servicio.getTipoServicio(),
                servicio.getCostoPorUnidad(), servicio.getHorario(), servicio.getCapacidad());
        return "redirect:/servicios";
    }

    @GetMapping("/servicios/{id}/delete")
    public String servicioEliminar(@PathVariable("id") Long id) {
        servicioRepository.eliminarServicio(id);
        return "redirect:/servicios";
    }

}
