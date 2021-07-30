package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Nacionalidad;
import aeroibero.viajes.repositorio.NacionalidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class NacionalidadServiceImpl implements NacionalidadService{

    @Autowired
    NacionalidadRepository nacionalidadRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Nacionalidad> listarNacionalidad() {
        return (List<Nacionalidad>)  nacionalidadRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Nacionalidad nacionalidad) {
        nacionalidadRepository.save(nacionalidad);
    }

    @Override
    @Transactional
    public void eliminar(Nacionalidad nacionalidad) {
        nacionalidadRepository.delete(nacionalidad);
    }

    @Override
    @Transactional(readOnly = true)
    public Nacionalidad encontrarNacionalidad(Nacionalidad nacionalidad) {
        return nacionalidadRepository.findById(nacionalidad.getIdNacionalidad()).orElse(null);
    }
}
