package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import uniandes.edu.co.hotel_andes.modelo.TipoUsuario;
import uniandes.edu.co.hotel_andes.modelo.Usuario;
import uniandes.edu.co.hotel_andes.repositorio.TipoUsuarioRepository;

@Controller
public class TiposUsuarioController {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping("/tiposUsuario")
    public String tiposUsuario(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("loggedInUser");
        String tipo = user.getTiposUsuario_id().getTipo();
        if (user != null && (tipo.equals("Administrador") || tipo.equals("Gerente"))) {
            model.addAttribute("tiposUsuario", tipoUsuarioRepository.darTiposUsuario());
            return "tiposUsuario";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/tiposUsuario/new")
    public String tipoUsuarioForm(Model model) {
        model.addAttribute("tipoUsuario", new TipoUsuario());
        return "tipoUsuarioNuevo";
    }

    @PostMapping("/tiposUsuario/new/save")
    public String tipoUsuarioGuardar(@ModelAttribute TipoUsuario tipoUsuario) {
        tipoUsuarioRepository.insertarTipoUsuario(tipoUsuario.getTipo());
        return "redirect:/tiposUsuario";
    }

    @GetMapping("/tiposUsuario/{id}/edit")
    public String tipoUsuarioEditarForm(@PathVariable("id") Long id, Model model) {
        TipoUsuario tipoUsuario = tipoUsuarioRepository.darTipoUsuario(id);
        if (tipoUsuario != null) {
            model.addAttribute("tipoUsuario", tipoUsuario);
            return "tipoUsuarioEditar";
        } else {
            return "redirect:/tiposUsuario";
        }
    }

    @PostMapping("/tiposUsuario/{id}/edit/save")
    public String tipoUsuarioEditarGuardar(@PathVariable("id") Long id, @ModelAttribute TipoUsuario tipoUsuario) {
        tipoUsuarioRepository.actualizarTipoUsuario(id, tipoUsuario.getTipo());
        return "redirect:/tiposUsuario";
    }

    @GetMapping("/tiposUsuario/{id}/delete")
    public String tipoUsuarioEliminar(@PathVariable("id") Long id) {
        tipoUsuarioRepository.eliminarTipoUsuario(id);
        return "redirect:/tiposUsuario";
    }

}