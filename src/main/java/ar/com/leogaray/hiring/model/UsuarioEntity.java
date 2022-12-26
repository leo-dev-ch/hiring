package ar.com.leogaray.hiring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
@Table(name = "usuarios")
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="nombre")
    private String nombre;

    @NotNull
    @Column(name="email")
    private String email;

    @NotNull
    @Column(name="password")
    private String password;

    @NotNull
    @Column(name="estado")
    private EstadoUsuario estado;

    @NotNull
    @Column(name="fecha_registro")
    private LocalTime fechaRegistro;

    @ManyToMany
    @JoinTable(name = "usuarios_perfil",
            joinColumns = { @JoinColumn( name="usuario_id") },
            inverseJoinColumns = { @JoinColumn(name = "perfil_id") })
    Set<PerfilEntity> perfiles;
}
