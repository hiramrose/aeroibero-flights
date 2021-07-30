package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Aeropuerto;

import java.util.List;

public interface AeropuertoService {

    public List<Aeropuerto> listarAeropuertos();

    public void guardar(Aeropuerto aeropuerto);

    public void eliminar(Aeropuerto aeropuerto);

    public Aeropuerto encontrarPago(Aeropuerto aeropuerto);

}
