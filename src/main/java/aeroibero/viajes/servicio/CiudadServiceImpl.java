package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Ciudad;
import aeroibero.viajes.repositorio.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CiudadServiceImpl implements  CiudadService{

    @Autowired
    CiudadRepository ciudadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Ciudad> listarCiudad() {
        return ciudadRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Ciudad ciudad) {
        ciudadRepository.save(ciudad);
    }

    @Override
    @Transactional
    public void eliminar(Ciudad ciudad) {
        ciudadRepository.delete(ciudad);
    }

    @Override
    @Transactional(readOnly = true)
    public Ciudad encontrarCiudad(Ciudad ciudad) {
        return ciudadRepository.findById(ciudad.getIdCiudad()).orElse(null);
    }

}
