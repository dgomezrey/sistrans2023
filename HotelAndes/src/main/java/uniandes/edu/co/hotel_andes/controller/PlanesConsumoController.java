package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import uniandes.edu.co.hotel_andes.modelo.PlanConsumo;
import uniandes.edu.co.hotel_andes.modelo.Usuario;
import uniandes.edu.co.hotel_andes.repositorio.PlanConsumoRepository;

@Controller
public class PlanesConsumoController {

    @Autowired
    private PlanConsumoRepository planConsumoRepository;

    @GetMapping("/planesConsumo")
    public String planesConsumo(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("loggedInUser");
        String tipo = user.getTiposUsuario_id().getTipo();
        if (user != null && (tipo.equals("Administrador") || tipo.equals("Gerente"))) {
            model.addAttribute("planesConsumo", planConsumoRepository.darPlanesConsumo());
            return "planesConsumo";
        } else {
            return "redirect:/home";
        }

    }

    @GetMapping("/planesConsumo/new")
    public String planConsumoForm(Model model) {
        model.addAttribute("planConsumo", new PlanConsumo());
        return "planConsumoNuevo";
    }

    @PostMapping("/planesConsumo/new/save")
    public String planConsumoGuardar(@ModelAttribute PlanConsumo planConsumo) {
        planConsumoRepository.insertarPlanConsumo(planConsumo.getNombre(), planConsumo.getDescripcion(),
                planConsumo.getDescuento());
        return "redirect:/planesConsumo";
    }

    @GetMapping("/planesConsumo/{id}/edit")
    public String planConsumoEditarForm(@PathVariable("id") Long id, Model model) {
        PlanConsumo planConsumo = planConsumoRepository.darPlanConsumo(id);
        if (planConsumo != null) {
            model.addAttribute("planConsumo", planConsumo);
            return "planConsumoEditar";
        } else {
            return "redirect:/planesConsumo";
        }
    }

    @PostMapping("/planesConsumo/{id}/edit/save")
    public String planConsumoEditarGuardar(@PathVariable("id") Long id, @ModelAttribute PlanConsumo planConsumo) {
        planConsumoRepository.actualizarPlanConsumo(id, planConsumo.getNombre(), planConsumo.getDescripcion(),
                planConsumo.getDescuento());
        return "redirect:/planesConsumo";
    }

    @GetMapping("/planesConsumo/{id}/delete")
    public String planConsumoEliminar(@PathVariable("id") Long id) {
        planConsumoRepository.eliminarPlanConsumo(id);
        return "redirect:/planesConsumo";
    }

}
