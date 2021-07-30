package aeroibero.viajes.servicio;

import aeroibero.viajes.modelos.Boleto;

import java.util.List;

public interface BoletoService {


    public List<Boleto> listarBoletos();

    public void guardar(Boleto boleto);

    public void eliminar(Boleto boleto);

    public Boleto encontrarBoleto(Boleto boleto);


}
