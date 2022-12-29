package ar.com.leogaray.hiring.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponse {
    @ReadOnlyProperty
    private Long id;
    private String username;
    private String nombre;
    private String email;
    private UsuarioEstado estado;
    private Rol rol;
}
