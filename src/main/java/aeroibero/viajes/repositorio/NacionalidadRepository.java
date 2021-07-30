package aeroibero.viajes.repositorio;

import aeroibero.viajes.modelos.Nacionalidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NacionalidadRepository extends JpaRepository<Nacionalidad, Integer> {
}
