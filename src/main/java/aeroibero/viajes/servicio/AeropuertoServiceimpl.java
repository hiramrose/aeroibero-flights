package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Aeropuerto;
import aeroibero.viajes.repositorio.AeropuertoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AeropuertoServiceimpl implements AeropuertoService {

    @Autowired
    private AeropuertoRepository aeropuertoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Aeropuerto> listarAeropuertos() {
        return aeropuertoRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Aeropuerto aeropuerto) {
        aeropuertoRepository.save(aeropuerto);
    }

    @Override
    @Transactional
    public void eliminar(Aeropuerto aeropuerto) {
        aeropuertoRepository.delete(aeropuerto);
    }

    @Override
    @Transactional(readOnly = true)
    public Aeropuerto encontrarPago(Aeropuerto aeropuerto) {
        return aeropuertoRepository.findById(aeropuerto.getIdAeropuerto()).orElse(null);
    }

}
