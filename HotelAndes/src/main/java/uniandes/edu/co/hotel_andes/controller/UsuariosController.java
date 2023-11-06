package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import uniandes.edu.co.hotel_andes.modelo.Usuario;
import uniandes.edu.co.hotel_andes.repositorio.UsuarioRepository;
import uniandes.edu.co.hotel_andes.repositorio.TipoUsuarioRepository;

@Controller
public class UsuariosController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @PostMapping("/login")
    public String login(Model modelo, @RequestParam("usuario") String username,
            @RequestParam("password") String password, HttpSession session) {
        Usuario usuario = usuarioRepository.encontrarUsuarioPorUsuarioYcontrasena(username, password);
        if (usuario != null) {
            // Store the user object in the session
            session.setAttribute("loggedInUser", usuario);

            modelo.addAttribute("nombre", usuario.getNombre());
            modelo.addAttribute("tipo", usuario.getTiposUsuario_id().getTipo());
            modelo.addAttribute("id_usuario", usuario.getId());
            return "home";
        } else {
            // Optionally invalidate the session
            session.invalidate();
            return "index";
        }
    }

    @GetMapping("/home")
    public String homePage(HttpSession session, Model model) {
        Usuario user = (Usuario) session.getAttribute("loggedInUser");
        if (user != null) {
            model.addAttribute("username", user.getUsername());
            model.addAttribute("tipo", user.getTiposUsuario_id().getTipo());
            // User is logged in, render the home page
            return "home";
        } else {
            // User is not logged in, redirect to login page
            return "redirect:/";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate the session to remove all session attributes
        session.invalidate();

        // Redirect to the login page or another appropriate page
        return "redirect:/";
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("loggedInUser");
        String tipo = user.getTiposUsuario_id().getTipo();
        if (user != null && (tipo.equals("Administrador") || tipo.equals("Gerente"))) {
            model.addAttribute("usuarios", usuarioRepository.darUsuarios());
            return "usuarios";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/usuarios/new")
    public String usuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tipos", tipoUsuarioRepository.darTiposUsuario());
        return "usuarioNuevo";
    }

    @PostMapping("/usuarios/new/save")
    public String usuarioGuardar(@ModelAttribute Usuario usuario) {
        usuarioRepository.insertarUsuario(usuario.getUsername(), usuario.getContrasena(), usuario.getNombre(),
                usuario.getEmail(), usuario.getTipoDocumento(), usuario.getDocumento(),
                usuario.getTiposUsuario_id().getId());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/edit")
    public String usuarioEditarForm(@PathVariable("id") Long id, Model model) {
        Usuario usuario = usuarioRepository.darUsuario(id);
        if (usuario != null) {
            model.addAttribute("usuario", usuario);
            model.addAttribute("tipos", tipoUsuarioRepository.darTiposUsuario());
            return "usuarioEditar";
        } else {
            return "redirect:/usuarios";
        }
    }

    @PostMapping("/usuarios/{id}/edit/save")
    public String usuarioEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Usuario usuario) {
        usuarioRepository.actualizarUsuario(id, usuario.getUsername(), usuario.getContrasena(), usuario.getNombre(),
                usuario.getEmail(), usuario.getTipoDocumento(), usuario.getDocumento(),
                usuario.getTiposUsuario_id().getId());
        return "redirect:/usuarios";
    }

    @GetMapping("/usuarios/{id}/delete")
    public String usuarioEliminar(@PathVariable("id") Long id) {
        usuarioRepository.eliminarUsuario(id);
        return "redirect:/usuarios";
    }
}