package aeroibero.viajes.modelos;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name="ciudad")
public class Ciudad {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ciudad")
    private Integer idCiudad;
    @NotEmpty
    private String ciudad;

    @ManyToOne
    @JoinColumn(name="id_pais_fk")
    private Pais paisFK;

    @Override
    public String toString() {
        return "Ciudad{" +
                "idCiudad=" + idCiudad +
                ", ciudad='" + ciudad + '\'' +
                ", paisFK=" + paisFK +
                '}';
    }
}
