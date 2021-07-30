package aeroibero.viajes.modelos;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

@Entity
@Data
public class Vuelo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVuelo;

    private String tipo_vuelo;


    private String hora;

    private Integer id_aeropuerto_origen;

    private String destino;

    private Integer id_avion_fk;

    private Integer id_viaje_fk;

    private Integer id_usuario_fk;

    private Integer id_pago_fk;


}
