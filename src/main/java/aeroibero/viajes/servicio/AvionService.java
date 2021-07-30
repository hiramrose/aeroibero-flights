package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Avion;

import java.util.List;

public interface AvionService {

    public List<Avion> listarAviones();

    public void guardar(Avion avion);

    public void eliminar(Avion avion);

    public Avion encontrarAvion(Avion avion);
}
