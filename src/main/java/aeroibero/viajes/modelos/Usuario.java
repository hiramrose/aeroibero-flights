package aeroibero.viajes.modelos;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String a_paterno;

    @NotEmpty
    private String a_materno;

    @NotEmpty
    private Date fecha_nacimiento;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "nacionalidad")
    private Nacionalidad nacionalidad;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "raza")
    private Raza raza;

    @NotEmpty
    private String telefono;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @OneToMany
    @JoinColumn(name = "id_usuario_fk")
    private List<Rol> roles;

}
