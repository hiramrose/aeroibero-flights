package aeroibero.viajes.modelos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name="aeropuerto")
public class Aeropuerto implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_aeropuerto")
    private Integer idAeropuerto;

    @NotEmpty
    private String nombreAeropuerto;

    @NotEmpty
    private Integer cantidad_puertas_embarque;

    @NotEmpty
    private Integer cantidad_salas_de_espera;

    @JoinColumn(name="id_ciudad_fk")
    private Integer id_ciudad_fk;


}

