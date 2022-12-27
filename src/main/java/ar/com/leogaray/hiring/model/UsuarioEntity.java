package ar.com.leogaray.hiring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "usuarios")
public class UsuarioEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 3, max = 15)
    @Column(name = "username")
    private String username;
    @NotNull
    @Size(max = 50)
    @Column(name = "nombre")
    private String nombre;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "password")
    private String password;
    @NotNull
    @Column(name = "estado")
    private EstadoUsuario estado;
    @NotNull
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
}
