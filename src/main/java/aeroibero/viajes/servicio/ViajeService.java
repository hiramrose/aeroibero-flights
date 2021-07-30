package aeroibero.viajes.servicio;



import aeroibero.viajes.modelos.Viaje;

import java.util.List;

public interface ViajeService {

    public List<Viaje> listarViaje();

    public void guardar(Viaje viaje);

    public void eliminar(Viaje viaje);

    public Viaje encontrarViaje(Viaje viaje);

}
