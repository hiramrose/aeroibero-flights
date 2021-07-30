package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Usuario;
import aeroibero.viajes.repositorio.UsuarioRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioServiceI{

    @Autowired
    private UsuarioRespository usuarioRespository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listaUsuario() {
        return (List<Usuario>) usuarioRespository.findAll();
     }

    @Override
    @Transactional
    public void guardar(Usuario usuario) {
        usuarioRespository.save(usuario);

    }

    @Override
    @Transactional
    public void eliminar(Usuario usuario) {
        usuarioRespository.delete(usuario);

    }

    @Override
    @Transactional(readOnly = true)
    public Usuario encontrarUsuario(Usuario usuario) {
        return usuarioRespository.findById(usuario.getIdUsuario()).orElse(null);
    }
}
