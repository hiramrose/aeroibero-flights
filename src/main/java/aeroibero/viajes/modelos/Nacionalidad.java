package aeroibero.viajes.modelos;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name="nacionalidad")
public class Nacionalidad implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nacionalidad")
    private Integer idNacionalidad;
    @NotEmpty
    @Column(name = "nacionalidad")
    private String nacionalidad;

    @Override
    public String toString() {
        return "Nacionalidad{" +
                "idNacionalidad=" + idNacionalidad +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}
