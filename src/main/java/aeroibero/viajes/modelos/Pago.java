package aeroibero.viajes.modelos;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name="pago")

public class Pago implements Serializable {

    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @Column(name="id_pago")
    private Integer idpago;

    @NotEmpty
    @Column(name="numero_tarjeta")
    private String numeroTarjeta;

    @Column(name="monto")
    private Double monto;

}
