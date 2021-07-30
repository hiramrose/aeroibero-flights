package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Raza;
import aeroibero.viajes.repositorio.RazaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RazaServiceImpl implements  RazaService{

    @Autowired
    RazaRepository razaRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Raza> listarRaza() {
        return razaRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Raza raza) {
        razaRepository.save(raza);
    }

    @Override
    @Transactional
    public void eliminar(Raza raza) {
        razaRepository.delete(raza);

    }

    @Override
    @Transactional(readOnly = true)
    public Raza encontrarRaza(Raza raza) {
        return razaRepository.findById(raza.getIdRaza()).orElse(null);
    }

}
