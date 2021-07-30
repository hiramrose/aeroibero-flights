package aeroibero.viajes.modelos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@Table(name="pais")
public class Pais implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pais")
    private Integer idPais;
    @NotEmpty
    @Column(name = "pais")
    private String pais;

    @Override
    public String toString() {
        return "Pais{" +
                "idPais=" + idPais +
                ", pais='" + pais + '\'' +
                '}';
    }
}
