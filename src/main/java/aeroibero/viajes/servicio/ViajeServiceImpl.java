package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Viaje;
import aeroibero.viajes.repositorio.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ViajeServiceImpl implements  ViajeService{

    @Autowired
    ViajeRepository viajeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Viaje> listarViaje() {
        return viajeRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Viaje viaje) {
        viajeRepository.save(viaje);
    }

    @Override
    @Transactional
    public void eliminar(Viaje viaje) {
        viajeRepository.delete(viaje);

    }

    @Override
    @Transactional(readOnly = true)
    public Viaje encontrarViaje(Viaje viaje) {
        return viajeRepository.findById(viaje.getIdViaje()).orElse(null);
    }
}
