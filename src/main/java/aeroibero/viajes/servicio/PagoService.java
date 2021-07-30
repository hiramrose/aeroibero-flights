package aeroibero.viajes.servicio;



import aeroibero.viajes.modelos.Pago;

import java.util.List;

public interface PagoService {

    public List<Pago> listarPagos();

    public void guardar(Pago pago);

    public void eliminar(Pago pago);

    public Pago encontrarPago(Pago pago);

}
