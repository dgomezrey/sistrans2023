package uniandes.edu.co.hotel_andes.controller;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.hotel_andes.repositorio.ServicioRepository;
import uniandes.edu.co.hotel_andes.repositorio.UsuarioRepository;
import uniandes.edu.co.hotel_andes.repositorio.ConsumoRepository;
import uniandes.edu.co.hotel_andes.repositorio.HabitacionRepository;
import uniandes.edu.co.hotel_andes.repositorio.ReservaAlojamientoRepository;

@Controller
public class RFCController {
    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private ConsumoRepository consumoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ReservaAlojamientoRepository reservaAlojamientoRepository;

    @GetMapping("/RFC1")
    public String mostrar1(Model model) {
        model.addAttribute("datos1", habitacionRepository.darRFC1());
        return "RFC/RFC1.html";
    }

    @GetMapping("/RFC2")
    public String formFechas(Model model) {
        return "/RFCAUX/RFC2Form.html";
    }

    @GetMapping("/RFC2/mostrar")
    public String mostrar2(@RequestParam(name = "fecha1") Date fecha1,
            @RequestParam(name = "fecha2") Date fecha2,
            Model model) {
        model.addAttribute("datos2", servicioRepository.darRFC2(fecha1, fecha2));
        return "/RFC/RFC2.html";
    }

    @GetMapping("/RFC3")
    private String mostrar3(Model model) {
        model.addAttribute("datos3", habitacionRepository.darRFC3());
        return "RFC/RFC3.html";
    }

    @GetMapping("/RFC4")
    private String form4(Model model) {
        model.addAttribute("habitaciones", habitacionRepository.darHabitaciones());
        model.addAttribute("servicios", servicioRepository.darServicios());

        return "RFCAUX/RFC4Form.html";
    }

    @GetMapping("/RFC4/mostrar")
    private String mostrar4(Model model, @RequestParam(name = "fecha1") Date fecha1,
            @RequestParam(name = "fecha2") Date fecha2, @RequestParam(name = "tipoServicio") String tipoServicio,
            @RequestParam(name = "costo1") Float costo1, @RequestParam(name = "costo2") Float costo2,
            @RequestParam(name = "habitaciones_id") long habitacion_id) {

        if (costo1 == null || costo2 == null) {
            costo1 = null;
            costo2 = null;
        }
        if (fecha1 == null || fecha2 == null) {
            fecha1 = null;
            fecha2 = null;
        }
        if (tipoServicio == null || tipoServicio.isEmpty()) {
            tipoServicio = null;
        }

        model.addAttribute("datos4",
                servicioRepository.darRFC4(costo1, costo2, fecha1, fecha2, habitacion_id, tipoServicio));
        return "RFC/RFC4.html";
    }

    @GetMapping("/RFC5")
    private String form5(Model model) {
        model.addAttribute("usuarios", usuarioRepository.darUsuarios());
        return "RFCAUX/RFC5Form";
    }

    @GetMapping("/RFC5/mostrar")
    private String mostrar5(Model model, @RequestParam(name = "fecha1") Date fecha1,
            @RequestParam(name = "fecha2") Date fecha2,
            @RequestParam(name = "usuario_id") Long usuarios_id) {
        List<Object[]> datos = consumoRepository.darRFC5(usuarios_id, fecha1, fecha2);
        BigInteger total = BigInteger.ZERO;
        for (int i = 0; i < datos.size(); i++) {
            BigDecimal decimalValue = (BigDecimal) datos.get(i)[3];
            BigInteger intValue = decimalValue.toBigInteger();

            total = total.add(intValue);
        }
        model.addAttribute("datos5", datos);
        model.addAttribute("total", total);

        return "RFC/RFC5.html";

    }

    @GetMapping("/RFC6")
    private String mostrar6(Model model) {
        model.addAttribute("datos6_1", reservaAlojamientoRepository.darRFC6A());
        model.addAttribute("datos6_2", reservaAlojamientoRepository.darRFC6B());
        model.addAttribute("datos6_3", reservaAlojamientoRepository.darRFC6C());

        return "RFC/RFC6.html";
    }

    @GetMapping("/RFC7")
    private String mostrar7(Model model) {
        model.addAttribute("datos7", usuarioRepository.darRFC7());

        return "RFC/RFC7.html";
    }

    @GetMapping("/RFC8")
    private String mostrar8(Model model) {
        model.addAttribute("datos8_1", consumoRepository.darRFC8());
        model.addAttribute("datos8_2", consumoRepository.darRFC8AUX());

        return "RFC/RFC8.html";
    }

    @GetMapping("/RFC9")
    private String form9(Model model) {

        model.addAttribute("servicios", servicioRepository.darServicios());
        return "RFCAUX/RFC9Form.html";
    }

    @GetMapping("/RFC9/mostrar")
    private String mostrar9(Model model, @RequestParam(name = "fecha1") Date fecha1,
            @RequestParam(name = "fecha2") Date fecha2,
            @RequestParam(name = "ordenamiento") String ordenamiento,
            @RequestParam(name = "agrupamiento") String agrupamiento,
            @RequestParam(name = "servicio_id") long servicio_id) {
        // model.addAttribute("datos9", consumoRepository.darRFC9(fecha1, fecha2,
        // agrupamiento, ordenamiento, servicio_id));

        return "RFC/RFC9.html";
    }

    @GetMapping("/RFC10")
    private String form10(Model model) {
        model.addAttribute("servicios", servicioRepository.darServicios());

        return "RFCAUX/RFC10Form.html";
    }

    @GetMapping("/RFC10/mostrar")
    private String mostrar10(Model model, @RequestParam(name = "fecha1") Date fecha1,
            @RequestParam(name = "fecha2") Date fecha2,
            @RequestParam(name = "ordenamiento") String ordenamiento,
            @RequestParam(name = "agrupamiento") String agrupamiento,
            @RequestParam(name = "servicio_id") long servicio_id) {
        // model.addAttribute("datos", consumoRepository.darRFC10(fecha1, fecha2,
        // agrupamiento, ordenamiento, servicio_id));

        return "RFC/RFC10.html";
    }

    @GetMapping("/RFC11")
    private String form11() {
        return "RFCAUX/RFC11Form.html";
    }

    @GetMapping("/RFC11/mostrar")
    private String mostrar11(Model model, @RequestParam("razon") String razon) {
        // model.addAttribute("datos1", consumoRepository.darRFC11A(razon));
        // model.addAttribute("datos2", consumoRepository.darRFC11B());

        return "/RFC/RFC11.html";
    }

    @GetMapping("/RFC12")
    private String mostrar12(Model model) {
        //model.addAttribute("datos", usuarioRepository.darRFC12());

        return "/RFC/RFC12.html";
    }

}
