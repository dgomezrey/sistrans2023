package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hotel_andes.modelo.ReservaServicio;
import uniandes.edu.co.hotel_andes.repositorio.ReservaServicioRepository;
import uniandes.edu.co.hotel_andes.repositorio.ServicioRepository;
import uniandes.edu.co.hotel_andes.repositorio.HabitacionRepository;

@Controller
public class ReservasServicioController {

    @Autowired
    private ReservaServicioRepository reservaServicioRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/reservasServicio")
    public String reservasServicio(Model model) {
        model.addAttribute("reservasServicio", reservaServicioRepository.darReservasServicio());
        return "reservasServicio";
    }

    @GetMapping("/reservasServicio/new")
    public String reservaServicioForm(Model model) {
        model.addAttribute("reservaServicio", new ReservaServicio());
        model.addAttribute("servicios", servicioRepository.darServicios());
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        return "reservaServicioNuevo";
    }

    @PostMapping("/reservasServicio/new/save")
    public String reservaServicioGuardar(@ModelAttribute ReservaServicio reservaServicio) {
        reservaServicioRepository.insertarReservaServicio(reservaServicio.getFecha(), reservaServicio.getHoraInicio(),
                reservaServicio.getHoraFin(), reservaServicio.getServicios_id().getId(),
                reservaServicio.getHabitaciones_id().getId());
        return "redirect:/reservasServicio";
    }

    @GetMapping("/reservasServicio/{id}/edit")
    public String reservaServicioEditarForm(@PathVariable("id") Long id, Model model) {
        ReservaServicio reservaServicio = reservaServicioRepository.darReservaServicio(id);
        if (reservaServicio != null) {
            model.addAttribute("reservaServicio", reservaServicio);
            model.addAttribute("servicios", servicioRepository.darServicios());
            model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
            return "reservaServicioEditar";
        } else {
            return "redirect:/reservasServicio";
        }
    }

    @PostMapping("/reservasServicio/{id}/edit/save")
    public String reservaServicioEditarGuardar(@PathVariable("id") Long id,
            @ModelAttribute ReservaServicio reservaServicio) {
        reservaServicioRepository.actualizarReservaServicio(id, reservaServicio.getFecha(),
                reservaServicio.getHoraInicio(), reservaServicio.getHoraFin(),
                reservaServicio.getServicios_id().getId(), reservaServicio.getHabitaciones_id().getId());
        return "redirect:/reservasServicio";
    }

    @GetMapping("/reservasServicio/{id}/delete")
    public String reservaServicioEliminar(@PathVariable("id") Long id) {
        reservaServicioRepository.eliminarReservaServicio(id);
        return "redirect:/reservasServicio";
    }

}
