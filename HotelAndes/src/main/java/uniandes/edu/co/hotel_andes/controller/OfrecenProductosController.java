package uniandes.edu.co.hotel_andes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.hotel_andes.modelo.Servicio;
import uniandes.edu.co.hotel_andes.modelo.Producto;
import uniandes.edu.co.hotel_andes.modelo.OfrecenProducto;
import uniandes.edu.co.hotel_andes.modelo.OfrecenProductoPK;
import uniandes.edu.co.hotel_andes.repositorio.ServicioRepository;
import uniandes.edu.co.hotel_andes.repositorio.ProductoRepository;
import uniandes.edu.co.hotel_andes.repositorio.OfrecenProductoRepository;

@Controller
public class OfrecenProductosController {

    @Autowired
    private OfrecenProductoRepository ofrecenProductoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/ofrecenProductos/new")
    public String ofrecenProductoForm(Model model) {
        model.addAttribute("servicios", servicioRepository.darServicios());
        model.addAttribute("productos", productoRepository.darProductos());
        return "ofrecenProductoNuevo";
    }

    @PostMapping("/ofrecenProductos/new/save")
    public String ofrecenProductoGuardar(@ModelAttribute("servicios_id") long servicios_id, @ModelAttribute("productos_id") long productos_id) {
        Servicio servicio = servicioRepository.darServicio(servicios_id);
        Producto producto = productoRepository.darProducto(productos_id);
        OfrecenProductoPK pk = new OfrecenProductoPK(servicio, producto);
        OfrecenProducto ofrecenProducto = new OfrecenProducto();
        ofrecenProducto.setPk(pk);
        ofrecenProductoRepository.insertarOfrecenProducto(ofrecenProducto.getPk().getServicios_id().getId(), ofrecenProducto.getPk().getProductos_id().getId());
        return "redirect:/servicios";
    }

    
}
