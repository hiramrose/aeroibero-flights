package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Avion;
import aeroibero.viajes.repositorio.AvionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AvionServiceImpl implements AvionService{

    @Autowired
    private AvionRepository avionRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Avion> listarAviones() {
        return avionRepository.findAll();

    }

    @Override
    @Transactional
    public void guardar(Avion avion) {
        avionRepository.save(avion);

    }

    @Override
    @Transactional
    public void eliminar(Avion avion) {
        avionRepository.delete(avion);
    }

    @Override
    @Transactional(readOnly = true)
    public Avion encontrarAvion(Avion avion) {
        return avionRepository.findById(avion.getIdAvion()).orElse(null);
    }
}
