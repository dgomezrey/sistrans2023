package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import uniandes.edu.co.hotel_andes.modelo.ReservaAlojamiento;
import uniandes.edu.co.hotel_andes.modelo.Usuario;
import uniandes.edu.co.hotel_andes.repositorio.ReservaAlojamientoRepository;
import uniandes.edu.co.hotel_andes.repositorio.UsuarioRepository;
import uniandes.edu.co.hotel_andes.repositorio.PlanConsumoRepository;
import uniandes.edu.co.hotel_andes.repositorio.HabitacionRepository;

@Controller
public class ReservasAlojamientoController {

    @Autowired
    private ReservaAlojamientoRepository reservaAlojamientoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PlanConsumoRepository planConsumoRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @GetMapping("/reservasAlojamiento")
    public String reservasAlojamiento(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("loggedInUser");
        String tipo = user.getTiposUsuario_id().getTipo();
        if (user != null && tipo.equals("Gerente")) {
            model.addAttribute("reservasAlojamiento", reservaAlojamientoRepository.darReservasAlojamiento());
        } else if (user != null && tipo.equals("Cliente")) {
            model.addAttribute("reservasAlojamiento",
                    reservaAlojamientoRepository.darReservasAlojamientoUsuario(user.getId()));
        } else {
            return "redirect:/home";
        }
        return "reservasAlojamiento";

    }

    @GetMapping("/reservasAlojamiento/new")
    public String reservaAlojamientoForm(Model model) {
        model.addAttribute("reservaAlojamiento", new ReservaAlojamiento());
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        model.addAttribute("planesConsumo", planConsumoRepository.darPlanesConsumo());
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        return "reservaAlojamientoNuevo";
    }

    @PostMapping("/reservasAlojamiento/new/save")
    public String reservaAlojamientoGuardar(@ModelAttribute ReservaAlojamiento reservaAlojamiento) {
        reservaAlojamientoRepository.insertarReservaAlojamiento(reservaAlojamiento.getFechaIn(),
                reservaAlojamiento.getFechaOut(), reservaAlojamiento.getNumPersonas(),
                reservaAlojamiento.getUsuarios_id().getId(), reservaAlojamiento.getPlanesConsumo_id().getId(),
                reservaAlojamiento.getHabitaciones_id().getId());
        return "redirect:/reservasAlojamiento";
    }

    @GetMapping("/reservasAlojamiento/{id}/edit")
    public String reservaAlojamientoEditarForm(@PathVariable("id") Long id, Model model) {
        ReservaAlojamiento reservaAlojamiento = reservaAlojamientoRepository.darReservaAlojamiento(id);
        if (reservaAlojamiento != null) {
            model.addAttribute("reservaAlojamiento", reservaAlojamiento);
            model.addAttribute("usuarios", usuarioRepository.darUsuarios());
            model.addAttribute("planesConsumo", planConsumoRepository.darPlanesConsumo());
            model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
            return "reservaAlojamientoEditar";
        } else {
            return "redirect:/reservasAlojamiento";
        }
    }

    @PostMapping("/reservasAlojamiento/{id}/edit/save")
    public String reservaAlojamientoEditarGuardar(@PathVariable("id") Long id,
            @ModelAttribute ReservaAlojamiento reservaAlojamiento) {
        reservaAlojamientoRepository.actualizarReservaAlojamiento(id, reservaAlojamiento.getFechaIn(),
                reservaAlojamiento.getFechaOut(), reservaAlojamiento.getNumPersonas(),
                reservaAlojamiento.getUsuarios_id().getId(), reservaAlojamiento.getPlanesConsumo_id().getId(),
                reservaAlojamiento.getHabitaciones_id().getId());
        return "redirect:/reservasAlojamiento";
    }

    @GetMapping("/reservasAlojamiento/{id}/delete")
    public String reservaAlojamientoEliminar(@PathVariable("id") Long id) {
        reservaAlojamientoRepository.eliminarReservaAlojamiento(id);
        return "redirect:/reservasAlojamiento";
    }

}
