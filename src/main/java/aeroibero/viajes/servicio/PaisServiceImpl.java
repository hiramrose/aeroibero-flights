package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Pais;
import aeroibero.viajes.repositorio.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaisServiceImpl implements PaisService{

    @Autowired
    PaisRepository paisRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pais> listarPaises() {
        return paisRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Pais pais) {
        paisRepository.save(pais);

    }

    @Override
    @Transactional
    public void eliminar(Pais pais) {
        paisRepository.delete(pais);
    }

    @Override
    @Transactional(readOnly = true)
    public Pais encontrarPais(Pais pais) {
        return paisRepository.findById(pais.getIdPais()).orElse(null);
    }
}
