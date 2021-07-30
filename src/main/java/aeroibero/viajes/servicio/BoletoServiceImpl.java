package aeroibero.viajes.servicio;


import aeroibero.viajes.modelos.Boleto;
import aeroibero.viajes.repositorio.BoletoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoletoServiceImpl implements BoletoService{

    @Autowired
    BoletoRepository boletoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Boleto> listarBoletos() {
        return boletoRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Boleto boleto) {
        boletoRepository.save(boleto);
    }

    @Override
    @Transactional
    public void eliminar(Boleto boleto) {
        boletoRepository.delete(boleto);
    }

    @Override
    @Transactional(readOnly = true)
    public Boleto encontrarBoleto(Boleto boleto) {
        return boletoRepository.findById(boleto.getIdBoleto()).orElse(null);
    }
}
