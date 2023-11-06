package uniandes.edu.co.hotel_andes.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import uniandes.edu.co.hotel_andes.modelo.ReservaServicio;
import uniandes.edu.co.hotel_andes.modelo.Usuario;
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
    public String reservasServicio(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("loggedInUser");
        String tipo = user.getTiposUsuario_id().getTipo();
        if (user != null && tipo.equals("Gerente")) {
            model.addAttribute("reservasServicio", reservaServicioRepository.darReservasServicio());
            return "reservasServicio";
        } else {
            return "redirect:/home";
        }

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

    private java.sql.Date convertToSqlDate(String datetime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm"); // Adjust if the format is different
        Date date = sdf.parse(datetime);
        return new java.sql.Date(date.getTime());
    }
}
