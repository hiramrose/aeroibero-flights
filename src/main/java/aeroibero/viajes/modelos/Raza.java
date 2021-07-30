package aeroibero.viajes.modelos;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
public class Raza implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_raza")
    private Integer idRaza;
    @NotEmpty
    @Column(name = "raza")
    private String raza;

    @Override
    public String toString() {
        return "Raza{" +
                "idRaza=" + idRaza +
                ", raza='" + raza + '\'' +
                '}';
    }
}
