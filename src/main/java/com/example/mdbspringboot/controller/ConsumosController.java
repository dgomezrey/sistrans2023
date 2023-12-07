package com.example.mdbspringboot.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.mdbspringboot.modelo.Cliente;
import com.example.mdbspringboot.modelo.Consumo;
import com.example.mdbspringboot.modelo.Habitacion;
import com.example.mdbspringboot.modelo.Reserva;
import com.example.mdbspringboot.modelo.Servicio;
import com.example.mdbspringboot.repositorio.ClienteRepository;
import com.example.mdbspringboot.repositorio.ConsumoRepository;
import com.example.mdbspringboot.repositorio.HabitacionRepository;
import com.example.mdbspringboot.repositorio.ReservaRepository;
import com.example.mdbspringboot.repositorio.ServicioRepository;

@Controller
public class ConsumosController {

    @Autowired
    ConsumoRepository consumoRepository;

    @Autowired
    ServicioRepository servicioRepository;

    @Autowired
    ClienteRepository usuarioRepository;

    @Autowired
    ReservaRepository reservaHabitacionRepository;

    @Autowired
    HabitacionRepository habitacionRepository;

    @GetMapping("/consumos")
    public String consumos(Model model) {
        model.addAttribute("consumos", consumoRepository.darConsumosDetalle());

        return "consumos";
    }

    @GetMapping("/consumos/new")
    public String consumoForm(Model model) {
        model.addAttribute("consumo", new Consumo());
        model.addAttribute("servicios", servicioRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("reservas", reservaHabitacionRepository.darReservasDetalle());

        return "consumoNuevo";
    }

    @PostMapping("/consumos/new/save")
    public String consumoGuardar(Model model, @RequestParam("cantidad") int cantidad,
            @RequestParam("fecha") String fecha,
            @RequestParam("total") double total, @RequestParam("servicio_id") String servicio_id,
            @RequestParam("reserva_id") String reserva_id) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha1 = format.parse(fecha);
        Consumo consumo = new Consumo(null, cantidad, fecha1, total, new ObjectId(servicio_id), new ObjectId(reserva_id));

        consumoRepository.insert(consumo);
        return "redirect:/consumos";
    }

    @GetMapping("/consumos/{id}/edit")
    public String consumoEditar(Model model, @PathVariable("id") String id) {
        Consumo consumo = consumoRepository.findById(id).get();
        model.addAttribute("consumo", consumo);
        model.addAttribute("servicios", servicioRepository.findAll());
        model.addAttribute("usuarios", usuarioRepository.findAll());
        model.addAttribute("reservas", reservaHabitacionRepository.findAll());

        return "consumoEditar";
    }

    @PostMapping("/consumos/{id}/edit/save")
    public String consumoEditarGuardar(Model model, @PathVariable("id") String id,
            @RequestParam("cantidad") int cantidad, @RequestParam("fecha") String fecha,
            @RequestParam("total") double total, @RequestParam("servicio_id") String servicio_id,
            @RequestParam("reserva_id") String reserva_id) throws ParseException {

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date fecha1 = format.parse(fecha);

        Consumo consumo = consumoRepository.findById(id).get();
        consumo.setCantidad(cantidad);
        consumo.setFecha(fecha1);
        consumo.setTotal(total);
        consumo.setServicio_id(new ObjectId(servicio_id));
        consumo.setReserva_id(new ObjectId(reserva_id));
        consumoRepository.save(consumo);

        return "redirect:/consumos";
    }

    @GetMapping("/consumos/{id}/delete")
    public String consumoEliminar(@PathVariable("id") String id) {
        consumoRepository.deleteById(id);
        return "redirect:/consumos";
    }

}
