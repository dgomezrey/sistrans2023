package com.example.mdbspringboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.modelo.Habitacion;
import com.example.mdbspringboot.repositorio.HabitacionRepository;
import com.example.mdbspringboot.repositorio.TipoHabitacionRepository;

@Controller
public class HabitacionesController {

    @Autowired
    HabitacionRepository habitacionRepository;

    @Autowired
    TipoHabitacionRepository tipoHabitacionRepository;

    @GetMapping("/habitaciones")
    public String habitaciones(Model model) {
        model.addAttribute("habitaciones", habitacionRepository.darHabitacionesConTipo());

        return "habitaciones";
    }

    @GetMapping("/habitaciones/new")
    public String habitacionForm(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("tipos", tipoHabitacionRepository.findAll());

        return "habitacionNuevo";
    }

    @PostMapping("/habitaciones/new/save")
    public String habitacionGuardar(Model model, @RequestParam("numero") int numero, @RequestParam("piso") int piso,
            @RequestParam("tipo_habitacion_id") String tipo_habitacion_id) {
        Habitacion habitacion = new Habitacion(null, numero, piso, tipo_habitacion_id);
        habitacionRepository.insert(habitacion);

        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id}/edit")
    public String habitacionEditarForm(@PathVariable("id") String id, Model model) {
        Habitacion habitacion = habitacionRepository.findById(id).get();
        model.addAttribute("habitacion", habitacion);
        model.addAttribute("tipos", tipoHabitacionRepository.findAll());

        return "habitacionEditar";
    }

    @PostMapping("/habitaciones/{id}/edit/save")
    public String habitacionEditarGuardar(@PathVariable("id") String id, @RequestParam("numero") int numero,
            @RequestParam("piso") int piso,
            @RequestParam("tipo_habitacion_id") String tipo_habitacion_id) {

        Habitacion habitacion = habitacionRepository.findById(id).get();
        habitacion.setNumero(numero);
        habitacion.setPiso(piso);
        habitacion.setTipo_habitacion_id(tipo_habitacion_id);
        habitacionRepository.save(habitacion);

        return "redirect:/habitaciones";
    }

    @GetMapping("/habitaciones/{id}/delete")
    public String habitacionEliminar(@PathVariable("id") String id) {
        habitacionRepository.deleteById(id);

        return "redirect:/habitaciones";
    }

/*     @GetMapping("/RFC1")
    String RFC1(Model model) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        model.addAttribute("datos", habitacionRepository.RFC1(sdf.parse("2023-01-01"), sdf.parse("2023-12-31")));

        return "/RFC1.html";
    }

    @GetMapping("/RFC2")
    String ocupacion(Model model) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        model.addAttribute("datos", habitacionRepository.RFC2(sdf.parse("2023-01-01"), sdf.parse("2023-12-31")));

        return "RFC2.html";
    } */

}
