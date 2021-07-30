package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Nacionalidad;

import java.util.List;

public interface NacionalidadService {

    public List<Nacionalidad> listarNacionalidad();

    public void guardar(Nacionalidad nacionalidad);

    public void eliminar(Nacionalidad nacionalidad);

    public Nacionalidad encontrarNacionalidad(Nacionalidad nacionalidad);

}
