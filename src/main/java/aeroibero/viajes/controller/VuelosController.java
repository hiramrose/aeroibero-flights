package aeroibero.viajes.controller;

import aeroibero.viajes.modelos.CiudadesGrafo;
import aeroibero.viajes.modelos.Viaje;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

@Controller
@RequestMapping("/vuelos")
public class VuelosController {

    public static Viaje viaje = new Viaje();

    public static Double monto;

    public static Integer cantidadBoletos;

    public static String rutaPrecioV;

    public static Double totalPasajeroV;

    public static String horaV;

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String mostrarIndex(Model model, @RequestParam(value = "origen", required = false) String origen, @RequestParam(value = "destino", required = false) String destino, @RequestParam(value = "cantidad", required = false) int cantidad, @RequestParam(value = "fecha",required = false) Date fecha, @RequestParam(value = "hora",required = false) String hora) throws IOException {
        Socket miCliente = null;
        String host="127.0.0.1";
        int puerto = 8000;

        //System.out.println(origen);
        miCliente = new Socket(host , puerto);
        System.out.println("Me conecte al servidor...");

        InputStreamReader streamSocket = new InputStreamReader(miCliente.getInputStream());
        BufferedReader lectorSocket = new BufferedReader(streamSocket);

        PrintWriter escritorSocket = new PrintWriter(miCliente.getOutputStream(), true);


        System.out.println("Valor: " + origen);
        String mensajeTransmitido, mensajeRecibido;
        mensajeTransmitido = "GET:" + origen + ":" + destino;
        System.out.println("Enviando solicitud GET...");
        System.out.println(mensajeTransmitido);
        escritorSocket.println(mensajeTransmitido);
        System.out.println("Esperando mensaje...");
        mensajeRecibido=lectorSocket.readLine();
        System.out.print("Mensaje del Servidor: "+mensajeRecibido +"\n");

        ArrayList<CiudadesGrafo> rutaPrecio = new ArrayList<>();
        ArrayList<CiudadesGrafo> rutaDistancia = new ArrayList<>();
        ArrayList<CiudadesGrafo> rutaTiempo = new ArrayList<>();
        //String jsonText = "{\"vector\":\"Comarca\",\"peso\":3.13},{\"vector\":\"Rivendel\",\"peso\":1}%{\"vector\":\"Rivendel\",\"peso\":2},{\"vector\":\"CiudadEsmeralda\",\"peso\":5}%{\"vector\":\"Minkie\",\"peso\":2},{\"vector\":\"Amazon\",\"peso\":5}";

        String jsonText = mensajeRecibido;
        String[] parts = jsonText.split("&");
        String rutaPrecioJson = parts[0]; // 123
        String rutaTiempoJson = parts[1]; // 654321
        String rutaDistanciaJson = parts[2]; // 654321

        CiudadesGrafo ciudad1 = new CiudadesGrafo();
        /*System.out.println(ciudad1.toString());
        String prueba = "3.1";
        double dato1 = Double.parseDouble(prueba);
        System.out.println(dato1);*/
        ciudad1.fromJsonList(rutaPrecioJson, rutaPrecio);

        System.out.println("Precio");
        for (int i = 0; i < rutaPrecio.size(); i++) {
            System.out.println(rutaPrecio.get(i).toString());
        }

        ciudad1.fromJsonList(rutaDistanciaJson, rutaDistancia);

        System.out.println("Distancia");
        for (int i = 0; i < rutaDistancia.size(); i++) {
            System.out.println(rutaDistancia.get(i).toString());
        }

        ciudad1.fromJsonList(rutaTiempoJson, rutaTiempo);

        System.out.println("Tiempo");
        for (int i = 0; i < rutaTiempo.size(); i++) {
            System.out.println(rutaTiempo.get(i).toString());
        }

        String auxCantidad = String.valueOf(cantidad);


        model.addAttribute("rutaPrecio", rutaPrecio.toString());
        model.addAttribute("rutaTiempo", rutaTiempo.toString());
        model.addAttribute("rutaDistancia", rutaDistancia.toString());
        model.addAttribute("totalPasajero", rutaPrecio.get(rutaPrecio.size()-1).peso);
        model.addAttribute("totalDistancia", rutaDistancia.get(rutaDistancia.size()-1).peso);
        model.addAttribute("totalTiempo", rutaTiempo.get(rutaTiempo.size()-1).peso);
        model.addAttribute("totalPrecio", (rutaPrecio.get(rutaPrecio.size()-1).peso)*cantidad);
        model.addAttribute("cantidad", auxCantidad);

        monto = (rutaPrecio.get(rutaPrecio.size()-1).peso)*cantidad;
        System.out.println(monto);

        cantidadBoletos = cantidad;

        System.out.println(cantidadBoletos);

        rutaPrecioV = rutaPrecio.toString();

        System.out.println(rutaPrecioV);

        totalPasajeroV = rutaPrecio.get(rutaPrecio.size()-1).peso;

        horaV = hora;

        System.out.println(horaV.toString());

        viaje.setOrigen(origen);
        viaje.setDestino(destino);
        viaje.setFecha(fecha);
        viaje.setRuta(rutaPrecio.toString());

        System.out.println(viaje.getOrigen());
        System.out.println(viaje.getDestino());
        System.out.println(viaje.getFecha());
        System.out.println(viaje.getRuta());

        return "vuelos/listVuelos";
    }

}