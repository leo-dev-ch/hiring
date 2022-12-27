package ar.com.leogaray.hiring.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
@Table(name = "perfiles")
public class PerfilEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Column(name = "perfil")
    private String perfil;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuarios_perfiles",
            joinColumns = {@JoinColumn(name = "perfil_id")},
            inverseJoinColumns = {@JoinColumn(name = "usuario_id")})

    private List<UsuarioEntity> usuarios = new ArrayList<>();

    public void addUsuario(UsuarioEntity usuario) {
        this.usuarios.add(usuario);
    }
}
