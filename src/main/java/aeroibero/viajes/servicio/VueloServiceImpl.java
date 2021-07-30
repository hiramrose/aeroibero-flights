package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Vuelo;
import aeroibero.viajes.repositorio.VueloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VueloServiceImpl implements VueloService{

    @Autowired
    VueloRepository vueloRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Vuelo> listarVuelos() {
        return vueloRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Vuelo vuelo) {
        vueloRepository.save(vuelo);
    }

    @Override
    @Transactional
    public void eliminar(Vuelo vuelo) {

        vueloRepository.delete(vuelo);
    }

    @Override
    @Transactional(readOnly = true)
    public Vuelo encontrarVuelo(Vuelo vuelo) {
        return  vueloRepository.findById(vuelo.getIdVuelo()).orElse(null);
    }
}
