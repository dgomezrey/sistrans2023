package com.example.mdbspringboot.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.example.mdbspringboot.repositorio.ClienteRepository;
import com.example.mdbspringboot.repositorio.ConsumoRepository;
import com.example.mdbspringboot.repositorio.HabitacionRepository;
import com.example.mdbspringboot.repositorio.ReservaRepository;
import com.example.mdbspringboot.repositorio.TipoHabitacionRepository;
import com.example.mdbspringboot.repositorio.HabitacionRepository.HabitacionConTipo;

@Controller
public class ReservasController {

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    HabitacionRepository habitacionRepository;

    @Autowired
    TipoHabitacionRepository tipoHabitacionRepository;

    @Autowired
    ConsumoRepository consumoRepository;

    @GetMapping("/reservas")
    public String reservas(Model model) {
        model.addAttribute("reservas", reservaRepository.darReservasDetalle());

        return "reservas";
    }

    @GetMapping("/reservas/new")
    public String reservaForm(Model model) {
        model.addAttribute("reserva", new Reserva());
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("habitaciones", habitacionRepository.findAll());

        return "reservaNuevo";
    }

    @PostMapping("/reservas/new/save")
    public String reservaGuardar(Model model, @RequestParam("fecha_inicio") Date fecha_inicio,
            @RequestParam("fecha_fin") Date fecha_fin, @RequestParam("fecha_checkin") Date fecha_checkin,
            @RequestParam("fecha_checkout") Date fecha_checkout, @RequestParam("num_personas") int num_personas,
            @RequestParam("cliente_id") String cliente_id, @RequestParam("habitacion_id") String habitacion_id) {
        Reserva reserva = new Reserva(null, fecha_inicio, fecha_fin, fecha_checkin, fecha_checkout, num_personas,
                new ObjectId(cliente_id), new ObjectId(habitacion_id));

        if (fecha_inicio.after(fecha_fin) || fecha_fin.before(fecha_inicio)) {
            model.addAttribute("causa", "FECHAS NO VALIDAS");
            return "error.html";
        } else {
            reservaRepository.insert(reserva);
            return "redirect:/reservas";
        }

    }

    @GetMapping("/reservas/{id}/edit")
    public String reservaEditarForm(@PathVariable("id") String id, Model model) {
        Reserva reserva = reservaRepository.findById(id).get();
        model.addAttribute("reserva", reserva);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("habitaciones", habitacionRepository.findAll());

        return "reservaEditar";
    }

    @PostMapping("/reservas/{id}/edit/save")
    public String reservaEditarGuardar(@PathVariable("id") String id, @RequestParam("fecha_inicio") Date fecha_inicio,
            @RequestParam("fecha_fin") Date fecha_fin, @RequestParam("fecha_checkin") Date fecha_checkin,
            @RequestParam("fecha_checkout") Date fecha_checkout, @RequestParam("num_personas") int num_personas,
            @RequestParam("cliente_id") String cliente_id, @RequestParam("habitacion_id") String habitacion_id) {

        Reserva reserva = reservaRepository.findById(id).get();
        reserva.setFecha_inicio(fecha_inicio);
        reserva.setFecha_fin(fecha_fin);
        reserva.setFecha_checkin(fecha_checkin);
        reserva.setFecha_checkout(fecha_checkout);
        reserva.setNum_personas(num_personas);
        reserva.setCliente_id(new ObjectId(cliente_id));
        reserva.setHabitacion_id(new ObjectId(habitacion_id));
        reservaRepository.save(reserva);

        return "redirect:/reservas";
    }

    @GetMapping("/reservas/{id}/delete")
    public String reservaEliminar(Model model, @PathVariable("id") String id) {
        if (!consumoRepository.darConsumosReserva(id).isEmpty()) {
            model.addAttribute("causa", "EXISTEN CONSUMOS ASOCIADOS A ESTA RESERVA");
            return "error.html";
        } else {
            reservaRepository.deleteById(id);
            return "redirect:/reservas";
        }
    }

    // CHECKIN

    @GetMapping("/reservas/{id}/checkIn")
    public String checkIn(Model model, @PathVariable("id") String id) {
        Reserva reservaHabitacion = reservaRepository.findById(id).get();

        if (reservaHabitacion.getFecha_checkin() != null) {
            model.addAttribute("causa", "YA SE REALIZO EL CHECKIN");
            return "error.html";
        } else {
            model.addAttribute("id", id);
            return "realizarCheckIn";
        }
    }

    @PostMapping("/reservas/{id}/checkIn/save")
    public String checkInGuardar(Model model, @PathVariable("id") String id,
            @RequestParam("fecha_checkin") Date fecha_checkin) {
        Reserva reservaHabitacion = reservaRepository.findById(id).get();
        reservaHabitacion.setFecha_checkin(fecha_checkin);
        reservaRepository.save(reservaHabitacion);

        return "redirect:/reservas";
    }

    // CHECKOUT

    @GetMapping("/reservas/{id}/checkOut")
    public String checkOut(Model model, @PathVariable("id") String id) {
        Reserva reservaHabitacion = reservaRepository.findById(id).get();

        if (reservaHabitacion.getFecha_checkin() == null) {
            model.addAttribute("causa", "NO SE HA REALIZADO EL CHECKIN");
            return "error.html";
        } else if (reservaHabitacion.getFecha_checkout() != null) {
            model.addAttribute("causa", "YA SE REALIZO EL CHECKOUT");
            return "error.html";
        } else {
            model.addAttribute("id", id);
            return "realizarCheckOut";
        }
    }

    @GetMapping("/reservas/{id}/checkOut/cuenta")
    public String cuenta(Model model, @PathVariable("id") String id,
            @RequestParam("fecha_checkOut") Date fecha_checkOut) {
        Reserva reserva = reservaRepository.findById(id).get();
        HabitacionConTipo habitacion = habitacionRepository.darHabitacionConTipo(reserva.getHabitacion_id()).get();

        long dias = (reserva.getFecha_fin().getTime() - reserva.getFecha_inicio().getTime()) / (24 * 60 * 60 * 1000);

        double dinero = dias * habitacion.getCostoNoche();

        List<Consumo> consumos = consumoRepository.darConsumosReserva(id);

        for (Consumo consumo : consumos) {
            dinero += consumo.getTotal();
        }

        model.addAttribute("dinero", dinero);
        model.addAttribute("fecha_checkOut", fecha_checkOut);
        model.addAttribute("id", id);
        model.addAttribute("consumos", consumos);
        model.addAttribute("reserva", reserva);
        model.addAttribute("habitacion", habitacion);
        model.addAttribute("dias", dias);

        return "confirmarCheckout";
    }

    @PostMapping("/reservas/{id}/checkOut/save")
    public String checkOutGuardar(Model model, @PathVariable("id") String id,
            @RequestParam("fecha_checkOut") Date fecha_checkOut) {
        Reserva reserva = reservaRepository.findById(id).get();
        reserva.setFecha_checkout(fecha_checkOut);
        reservaRepository.save(reserva);

        return "redirect:/reservas";
    }

}
