package aeroibero.viajes.servicio;



import aeroibero.viajes.modelos.Ciudad;

import java.util.List;

public interface CiudadService {

    public List<Ciudad> listarCiudad();

    public void guardar(Ciudad ciudad);

    public void eliminar(Ciudad ciudad);

    public Ciudad encontrarCiudad(Ciudad ciudad);


}
