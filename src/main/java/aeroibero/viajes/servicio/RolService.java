package aeroibero.viajes.servicio;



import aeroibero.viajes.modelos.Rol;

import java.util.List;

public interface RolService {

    public List<Rol> listarRol();

    public void guardar(Rol rol);

    public void eliminar(Rol rol);

    public Rol encontrarRaza(Rol rol);

}
