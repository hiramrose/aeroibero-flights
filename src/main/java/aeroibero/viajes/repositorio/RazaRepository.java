package aeroibero.viajes.repositorio;

import aeroibero.viajes.modelos.Raza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Integer> {

}
