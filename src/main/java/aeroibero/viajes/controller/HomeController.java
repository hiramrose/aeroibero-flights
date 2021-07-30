package aeroibero.viajes.controller;

import aeroibero.viajes.modelos.CiudadesGrafo;
import aeroibero.viajes.modelos.Rol;
import aeroibero.viajes.modelos.Usuario;
import aeroibero.viajes.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {


    private CiudadService ciudadService;

    private UsuarioServiceI usuarioService;

    private NacionalidadService nacionalidadService;

    private RazaService razaService;

    private RolService rolService;

    @Autowired
    public void setCiudadService(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    @Autowired
    public void setUsuarioServiceB(UsuarioServiceI usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Autowired
    public void setNacionalidadService(NacionalidadService nacionalidadService) {
        this.nacionalidadService = nacionalidadService;
    }

    @Autowired
    public void setRazaService(RazaService razaService) {
        this.razaService = razaService;
    }

    @Autowired
    public void setRoleService(RolService rolService) {
        this.rolService = rolService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String mostrarHome(Model model) {

        /*ArrayList<CiudadesGrafo> rutaPrecioV = new ArrayList<>();
        ArrayList<CiudadesGrafo> rutaDistancia = new ArrayList<>();
        ArrayList<CiudadesGrafo> rutaTiempo = new ArrayList<>();
        String jsonText = "[{\"vector\":\"Moria\",\"peso\":0},{\"vector\":\"Isengard\",\"peso\":1.3},{\"vector\": \"Rohan\",\"peso\":2.6},{\"vector\": \"Rivendel\",\"peso\":4.2},{ \"vector\": \"Telmar\",\"peso\":9.4},&]}[{ \"vector\":\"Moria\",\"peso\":0},{ \"vector\": \"Isengard\",\"peso\":400.0},{ \"vector\": \"Rohan\",\"peso\":800.0},{ \"vector\": \"Gondor\",\"peso\":1400.0},{ \"vector\": \"Narnia\",\"peso\":1950.0},{ \"vector\": \"Telmar\",\"peso\":2350.0},&]}[{ \"vector\":\"Moria\",\"peso\":0},{ \"vector\": \"Isengard\",\"peso\":1300.0},{ \"vector\": \"Rohan\",\"peso\":2600.0},{ \"vector\": \"Gondor\",\"peso\":4150.0},{ \"vector\": \"Narnia\",\"peso\":6125.0},{ \"vector\": \"Telmar\",\"peso\":7925.0},&]}";

        String[] parts = jsonText.split("&");
        String rutaPrecioJson = parts[0]; // 123
        String rutaTiempoJson = parts[1]; // 654321
        String rutaDistanciaJson = parts[2]; // 654321

        CiudadesGrafo ciudad1 = new CiudadesGrafo();
        /*System.out.println(ciudad1.toString());
        String prueba = "3.1";
        double dato1 = Double.parseDouble(prueba);
        System.out.println(dato1);*/
        /*ciudad1.fromJsonList(rutaPrecioJson, rutaPrecioV);

        System.out.println("Precio");
        for (int i = 0; i < rutaPrecioV.size(); i++) {
            System.out.println(rutaPrecioV.get(i).toString());
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
        }*/

        return "index";
    }

    @ModelAttribute
    public void setGenericos(Model model){
        model.addAttribute("ciudades", ciudadService.listarCiudad() );
    }

    @GetMapping("/registrar")
    public String insertar(Usuario usuario) {
        return "register";
    }

    @PostMapping("/guardarRegistro")
    public String registrarCuenta(@Valid Usuario usuario, Errors error) {
        if (error.hasErrors()) {
            return "register";
        }


        String contra = usuario.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        usuario.setPassword(encoder.encode(contra));

        System.out.println(usuario.getPassword());



        System.out.println(usuario.getNombre());
        System.out.println(usuario.getA_paterno());
        System.out.println(usuario.getA_materno());
        System.out.println(usuario.getFecha_nacimiento());
        System.out.println(usuario.getTelefono());
        System.out.println(usuario.getUsername());
        System.out.println(usuario.getPassword());
        System.out.println(usuario.getRaza());
        System.out.println(usuario.getNacionalidad());
        usuarioService.guardar(usuario);

        List<Usuario> usuarios = usuarioService.listaUsuario();
        Integer id_usuario = usuarios.get(usuarios.size()-1).getIdUsuario();

        System.out.println(id_usuario);
        Rol rol = new Rol();

        rol.setNombre("USER");
        rol.setId_usuario_fk(id_usuario);
        rolService.guardar(rol);

        return "login";

    }


}
