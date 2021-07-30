package aeroibero.viajes.repositorio;

import aeroibero.viajes.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRespository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsername(String email);
}
