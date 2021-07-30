package aeroibero.viajes.servicio;



import aeroibero.viajes.modelos.Vuelo;

import java.util.List;

public interface VueloService {

    public List<Vuelo> listarVuelos();

    public void guardar(Vuelo vuelo);

    public void eliminar(Vuelo vuelo);

    public Vuelo encontrarVuelo(Vuelo vuelo);

}
