package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hotel_andes.modelo.Consumo;
import uniandes.edu.co.hotel_andes.repositorio.ConsumoRepository;
import uniandes.edu.co.hotel_andes.repositorio.HabitacionRepository;
import uniandes.edu.co.hotel_andes.repositorio.ServicioRepository;
import uniandes.edu.co.hotel_andes.repositorio.ProductoRepository;

@Controller
public class ConsumosController {

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/consumos")
    public String consumos(Model model) {
        model.addAttribute("consumos", consumoRepository.darConsumos());
        return "consumos";
    }

    @GetMapping("/consumos/new")
    public String consumoForm(Model model) {
        model.addAttribute("consumo", new Consumo());
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        model.addAttribute("servicios", servicioRepository.darServicios());
        model.addAttribute("productos", productoRepository.darProductos());
        return "consumoNuevo";
    }

    @PostMapping("/consumos/new/save")
    public String consumoGuardar(@ModelAttribute Consumo consumo) {
        consumoRepository.insertarConsumo(consumo.getCantidad(), consumo.getFecha(), consumo.getTotal(),
                consumo.getHabitaciones_id().getId(), consumo.getServicios_id().getId(),
                consumo.getProductos_id().getId());
        return "redirect:/consumos";
    }

    @GetMapping("/consumos/{id}/edit")
    public String consumoEditarForm(@PathVariable("id") Long id, Model model) {
        Consumo consumo = consumoRepository.darConsumo(id);
        if (consumo != null) {
            model.addAttribute("consumo", consumo);
            model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
            model.addAttribute("servicios", servicioRepository.darServicios());
            model.addAttribute("productos", productoRepository.darProductos());
            return "consumoEditar";
        } else {
            return "redirect:/consumos";
        }
    }

    @PostMapping("/consumos/{id}/edit/save")
    public String consumoEditarGuardar(@PathVariable("id") Long id, @ModelAttribute Consumo consumo) {
        consumoRepository.actualizarConsumo(id, consumo.getCantidad(), consumo.getFecha(), consumo.getTotal(),
                consumo.getHabitaciones_id().getId(), consumo.getServicios_id().getId(),
                consumo.getProductos_id().getId());
        return "redirect:/consumos";
    }

    @GetMapping("/consumos/{id}/delete")
    public String consumoEliminar(@PathVariable("id") Long id) {
        consumoRepository.eliminarConsumo(id);
        return "redirect:/consumos";
    }

}
