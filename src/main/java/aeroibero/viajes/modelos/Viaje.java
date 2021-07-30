package aeroibero.viajes.modelos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Data
@Table(name="viaje")
public class Viaje implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name="id_viaje")
    private Integer idViaje;

    @NotEmpty
    @Column(name="origen")
    private String origen;

    @NotEmpty
    @Column(name="destino")
    private String destino;

    @NotEmpty
    @Column(name="fecha")
    private Date fecha;

    @NotEmpty
    @Column(name="ruta")
    private String ruta;

}
