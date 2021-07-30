package aeroibero.viajes.servicio;



import aeroibero.viajes.modelos.Pais;

import java.util.List;

public interface PaisService {

    public List<Pais> listarPaises();

    public void guardar(Pais pais);

    public void eliminar(Pais pais);

    public Pais encontrarPais(Pais pais);

}
