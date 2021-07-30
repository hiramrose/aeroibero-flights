package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Rol;
import aeroibero.viajes.modelos.Viaje;
import aeroibero.viajes.repositorio.RolRepository;
import aeroibero.viajes.repositorio.ViajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RolServiceImpl implements  RolService{

    @Autowired
    RolRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Rol> listarRol() {
        return rolRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Rol rol) {
        rolRepository.save(rol);
    }

    @Override
    @Transactional
    public void eliminar(Rol rol) {
        rolRepository.delete(rol);

    }

    @Override
    @Transactional(readOnly = true)
    public Rol encontrarRaza(Rol rol) {
        return rolRepository.findById(rol.getId_rol()).orElse(null);
    }

}