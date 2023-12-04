package com.example.mdbspringboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.modelo.Cliente;
import com.example.mdbspringboot.repositorio.ClienteRepository;
import com.example.mdbspringboot.repositorio.ServicioRepository;
//import com.example.mdbspringboot.repositorio.ClienteRepository.respuestaRFC3;

@Controller
public class ClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostMapping("/login")
    public String login(Model model, @RequestParam("usuario") String usuario,
            @RequestParam("contrasena") String constrasena) {
        Cliente cliente = clienteRepository.findCredentials(usuario, constrasena).get();
        if (cliente != null) {
            model.addAttribute("nombre", cliente.getNombre());
            model.addAttribute("id_cliente", cliente.getId());
            return "home";
        } else {
            return "index";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

/*     @GetMapping("/RFC3")
    String RFC3(Model model) {

        model.addAttribute("usuarios", usuarioRepository.findAll());

        return "/Formularios/RFC3.html";
    }

    @GetMapping("RFC3/mostrar")
    String RFC3Mostrar(Model model, @RequestParam("idUsuario") String idUsuario,
            @RequestParam("fechaInicio") String fechaInicio,
            @RequestParam("fechaFin") String fechaFin) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<respuestaRFC3> datos = usuarioRepository.RFC3(sdf.parse(fechaInicio), sdf.parse(fechaFin), idUsuario);
        for (respuestaRFC3 res : datos) {
            res.setServicio(servicioRepository.findById(res.getServicio()).get().getNombre());
            res.setId(usuarioRepository.findById(res.getId()).get().getNombre());
        }
        model.addAttribute("datos", datos);

        return "/RFC3.html";
    } */
}
