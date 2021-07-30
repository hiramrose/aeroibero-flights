package aeroibero.viajes.controller;

import aeroibero.viajes.modelos.*;
import aeroibero.viajes.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PagoController {

    private ViajeService viajeService;

    private PagoService pagoService;

    private VueloService vueloService;

    private BoletoService boletoService;

    private AeropuertoService aeropuertoService;

    private CiudadService ciudadService;

    private UsuarioServiceI usuarioServiceI;

    @Autowired
    public void setViajeService(ViajeService viajeService) {
        this.viajeService = viajeService;
    }

    @Autowired
    public void setPagoService(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @Autowired
    public void setVueloService(VueloService vueloService) {
        this.vueloService = vueloService;
    }

    @Autowired
    public void setBoletoService(BoletoService boletoService) {
        this.boletoService = boletoService;
    }

    @Autowired
    public void setAeropuertoService(AeropuertoService aeropuertoService) {
        this.aeropuertoService = aeropuertoService;
    }

    @Autowired
    public void setCiudadService(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @Autowired
    public void setUsuarioServiceI(UsuarioServiceI usuarioServiceI) {
        this.usuarioServiceI = usuarioServiceI;
    }

    @GetMapping("/registroPago")
    public String registroPago(Pago pago, Model model){
        model.addAttribute("totalPrecio", VuelosController.monto);
        model.addAttribute("rutaPrecio", VuelosController.rutaPrecioV);
        model.addAttribute("totalPasajero", VuelosController.totalPasajeroV);
        model.addAttribute("cantidad",VuelosController.cantidadBoletos);
        return "pago";
    }

    @PostMapping("/guardarPago")
    public String registroInfo(@Valid Pago pago,Model model, Errors error){
        if (error.hasErrors()){
            return "pago";
        }

        viajeService.guardar(VuelosController.viaje);

        System.out.println(pago.getNumeroTarjeta());

        pago.setMonto(VuelosController.monto);

        pagoService.guardar(pago);

        Vuelo vuelo = new Vuelo();

        if (VuelosController.viaje.getOrigen().equals("Rivendel") && VuelosController.viaje.getDestino().equals("Telmar")){
            vuelo.setTipo_vuelo("Internacional");
        }else if(VuelosController.viaje.getOrigen().equals("Gondor") && VuelosController.viaje.getDestino().equals("Narnia")){
            vuelo.setTipo_vuelo("Internacional");
        }else if(VuelosController.viaje.getOrigen().equals("Gondor") && VuelosController.viaje.getDestino().equals("Ciudad Esmeralda")){
            vuelo.setTipo_vuelo("Internacional");
        }else if(VuelosController.viaje.getOrigen().equals("Mordor") && VuelosController.viaje.getDestino().equals("Winkie")){
            vuelo.setTipo_vuelo("Internacional");
        }else if(VuelosController.viaje.getOrigen().equals("Narnia") && VuelosController.viaje.getDestino().equals("Gondor")){
            vuelo.setTipo_vuelo("Internacional");
        }else if (VuelosController.viaje.getOrigen().equals("Narnia") && VuelosController.viaje.getDestino().equals("Ciudad Esmeralda")){
            vuelo.setTipo_vuelo("Internacional");
        }else if(VuelosController.viaje.getOrigen().equals("Telmar") && VuelosController.viaje.getDestino().equals("Rivendel")){
            vuelo.setTipo_vuelo("Internacional");
        }else if(VuelosController.viaje.getOrigen().equals("Charn") && VuelosController.viaje.getDestino().equals("Winkie")){
            vuelo.setTipo_vuelo("Internacional");
        }else if (VuelosController.viaje.getOrigen().equals("Ciudad Esmeralda") && VuelosController.viaje.getDestino().equals("Gondor")){
            vuelo.setTipo_vuelo("Internacional");
        }else if (VuelosController.viaje.getOrigen().equals("Ciudad Esmeralda") && VuelosController.viaje.getDestino().equals("Narnia")){
            vuelo.setTipo_vuelo("Internacional");
        }else if(VuelosController.viaje.getOrigen().equals("Winkie") && VuelosController.viaje.getDestino().equals("Mordor")){
            vuelo.setTipo_vuelo("Internacional");
        }else if (VuelosController.viaje.getOrigen().equals("Winkie") && VuelosController.viaje.getDestino().equals("Charn")){
            vuelo.setTipo_vuelo("Internacional");
        }else {
            vuelo.setTipo_vuelo("Nacional");
        }

        vuelo.setHora(VuelosController.horaV);

        ArrayList<Aeropuerto> aeropuertos = (ArrayList<Aeropuerto>) aeropuertoService.listarAeropuertos();

        Ciudad ciudad = new Ciudad();

        ArrayList<Ciudad> ciudades = (ArrayList<Ciudad>)   ciudadService.listarCiudad();

        int id_ciudad;

        for (int i = 0; i < ciudades.size(); i++) {
            if(VuelosController.viaje.getOrigen().equals(ciudades.get(i).getCiudad())){
                id_ciudad = ciudades.get(i).getIdCiudad();
                System.out.println(id_ciudad);
                ciudad.setIdCiudad(id_ciudad);
            }
        }
        int id_aero;
        String nombreAero = "";

        for (int i = 0; i < aeropuertos.size(); i++) {
            if(ciudad.getIdCiudad() == aeropuertos.get(i).getId_ciudad_fk()){
                id_aero = aeropuertos.get(i).getId_ciudad_fk();
                System.out.println(id_aero);
                vuelo.setId_aeropuerto_origen(id_aero);
                nombreAero = aeropuertos.get(i).getNombreAeropuerto();
            }
        }

        vuelo.setDestino(VuelosController.viaje.getDestino());
        int numeroAvion = (int) (Math.random()*4+1);

        vuelo.setId_avion_fk(numeroAvion);

        int id_viaje;

        List<Viaje> viajes = viajeService.listarViaje();
        id_viaje = viajes.get(viajes.size() - 1).getIdViaje();

        vuelo.setId_viaje_fk(id_viaje);

        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) usuarioServiceI.listaUsuario();

        Usuario usuAct = new Usuario();

        usuAct.setUsername(UsuarioService.username);

        System.out.println(usuAct.getUsername());

        int id_usu;

        String usuarioNombreCompleto = "";

        for (int i = 0; i < usuarios.size(); i++) {
            if (usuAct.getUsername().equals(usuarios.get(i).getUsername())) {
                id_usu = Math.toIntExact(usuarios.get(i).getIdUsuario());
                vuelo.setId_usuario_fk(id_usu);
                usuarioNombreCompleto += usuarios.get(i).getNombre() + " " + usuarios.get(i).getA_paterno() + " " + usuarios.get(i).getA_materno();
            }
        }

        System.out.println("Aquíiiiiiiii");

        System.out.println(usuarioNombreCompleto);
        int id_pago;

        List<Pago> pagos = pagoService.listarPagos();
        id_pago = Math.toIntExact(pagos.get(pagos.size() - 1).getIdpago());

        vuelo.setId_pago_fk(id_pago);

        /*
        System.out.println(vuelo.getTipo_vuelo());
        System.out.println(vuelo.getHora());
        System.out.println(vuelo.getId_aeropuerto_origen());
        System.out.println(vuelo.getDestino());
        System.out.println(vuelo.getId_avion_fk());
        System.out.println(vuelo.getId_viaje_fk());
        System.out.println(vuelo.getId_usuario_fk());
        System.out.println(vuelo.getId_pago_fk());
         */
        String tipoVuelo = vuelo.getTipo_vuelo();
        String ruta = VuelosController.viaje.getRuta();

        int idUsuario = vuelo.getId_usuario_fk();
        vueloService.guardar(vuelo);

        int id_vuelo;

        List<Vuelo> vuelos = vueloService.listarVuelos();
        id_vuelo = vuelos.get(vuelos.size() - 1).getIdVuelo();

        for (int i = 0; i < VuelosController.cantidadBoletos ; i++) {
            Boleto boleto = new Boleto();
            boleto.setNumero_asiento("A" + i);
            boleto.setId_vuelo(id_vuelo);
            boletoService.guardar(boleto);
        }

        String asientos = "";
        List<Boleto> boletos = boletoService.listarBoletos();
        List<Boleto> boletosVuelo = new ArrayList<>();
        for (int i = 0; i < boletos.size(); i++) {
            if (boletos.get(i).getId_vuelo() == id_vuelo){
                asientos += boletos.get(i).getNumero_asiento();
                boletosVuelo.add(boletos.get(i));

            }
        }

        int cantidadBoletos = VuelosController.cantidadBoletos;

        String origen = VuelosController.viaje.getOrigen();
        String destino = VuelosController.viaje.getDestino();
        String hora = VuelosController.horaV;
        String fecha = String.valueOf(VuelosController.viaje.getFecha());

        if ((VuelosController.cantidadBoletos == 1)){

            model.addAttribute("idVuelo",id_vuelo);
            model.addAttribute("tipoVuelo", tipoVuelo);
            model.addAttribute("nombreAero",nombreAero);
            model.addAttribute("cantidadBoletos",cantidadBoletos);
            model.addAttribute("asientos",asientos);
            model.addAttribute("origen",origen);
            model.addAttribute("destino",destino);
            model.addAttribute("fecha",fecha);
            model.addAttribute("hora", hora);
            model.addAttribute("ruta",ruta);
            model.addAttribute("idUsuario", usuarioNombreCompleto);

            String idBoleto1 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(0).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto1", idBoleto1);

            return "boletos/listBoletos1";

        }else if ((VuelosController.cantidadBoletos == 2)){

            model.addAttribute("idVuelo",id_vuelo);
            model.addAttribute("tipoVuelo", tipoVuelo);
            model.addAttribute("nombreAero",nombreAero);
            model.addAttribute("cantidadBoletos",cantidadBoletos);
            model.addAttribute("asientos",asientos);
            model.addAttribute("origen",origen);
            model.addAttribute("destino",destino);
            model.addAttribute("fecha",fecha);
            model.addAttribute("hora", hora);
            model.addAttribute("ruta",ruta);
            model.addAttribute("idUsuario", usuarioNombreCompleto);

            String idBoleto1 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(0).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto1", idBoleto1);
            String idBoleto2 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(1).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto2", idBoleto2);

            return "boletos/listBoletos2";

        }else if((VuelosController.cantidadBoletos == 3)){

            model.addAttribute("idVuelo",id_vuelo);
            model.addAttribute("tipoVuelo", tipoVuelo);
            model.addAttribute("nombreAero",nombreAero);
            model.addAttribute("cantidadBoletos",cantidadBoletos);
            model.addAttribute("asientos",asientos);
            model.addAttribute("origen",origen);
            model.addAttribute("destino",destino);
            model.addAttribute("fecha",fecha);
            model.addAttribute("hora", hora);
            model.addAttribute("ruta",ruta);
            model.addAttribute("idUsuario", usuarioNombreCompleto);

            String idBoleto1 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(0).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto1", idBoleto1);
            String idBoleto2 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(1).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto2", idBoleto2);
            String idBoleto3 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(2).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto3", idBoleto3);

            return "boletos/listBoletos3";

        } else if((VuelosController.cantidadBoletos == 4)){

            model.addAttribute("idVuelo",id_vuelo);
            model.addAttribute("tipoVuelo", tipoVuelo);
            model.addAttribute("nombreAero",nombreAero);
            model.addAttribute("cantidadBoletos",cantidadBoletos);
            model.addAttribute("asientos",asientos);
            model.addAttribute("origen",origen);
            model.addAttribute("destino",destino);
            model.addAttribute("fecha",fecha);
            model.addAttribute("hora", hora);
            model.addAttribute("ruta",ruta);
            model.addAttribute("idUsuario", usuarioNombreCompleto);

            String idBoleto1 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(0).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto1", idBoleto1);
            String idBoleto2 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(1).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto2", idBoleto2);
            String idBoleto3 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(2).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto3", idBoleto3);
            String idBoleto4 = "https://chart.googleapis.com/chart?chs=500x500&cht=qr&chl=?" + boletosVuelo.get(3).getIdBoleto() + "&chld=L|1&choe=UTF-8g";
            model.addAttribute("idBoleto4", idBoleto4);

            return "boletos/listBoletos4";
        }

     return "login";

   }

}
