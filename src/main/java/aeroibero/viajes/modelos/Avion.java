package aeroibero.viajes.modelos;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name="avion")
public class Avion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAvion;
    @NotEmpty
    @Column(name = "nombre")
    private String nombreAvion;

    @Override
    public String toString() {
        return "Avion{" +
                "idAvion=" + idAvion +
                ", nombreAvion='" + nombreAvion + '\'' +
                '}';
    }
}
