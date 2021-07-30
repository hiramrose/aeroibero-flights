package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Pago;
import aeroibero.viajes.repositorio.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagoServiceImpl implements PagoService{
    @Autowired
    PagoRepository pagoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Pago pago) {
        pagoRepository.save(pago);
    }

    @Override
    @Transactional
    public void eliminar(Pago pago) {
        pagoRepository.delete(pago);
    }

    @Override
    @Transactional(readOnly = true)
    public Pago encontrarPago(Pago pago) {
        return pagoRepository.findById(pago.getIdpago()).orElse(null);
    }
}
