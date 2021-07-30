package aeroibero.viajes.servicio;



import aeroibero.viajes.modelos.Raza;

import java.util.List;

public interface RazaService {

    public List<Raza> listarRaza();

    public void guardar(Raza raza);

    public void eliminar(Raza raza);

    public Raza encontrarRaza(Raza raza);

}
