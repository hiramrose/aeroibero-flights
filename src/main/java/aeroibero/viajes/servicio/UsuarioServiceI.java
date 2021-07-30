package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Usuario;

import java.util.List;

public interface UsuarioServiceI {
    List<Usuario> listaUsuario();
    void guardar(Usuario usuario);
    void eliminar(Usuario usuario);
    Usuario encontrarUsuario(Usuario usuario);
}
