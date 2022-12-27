package ar.com.leogaray.hiring.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PerfilResponse {
    @ReadOnlyProperty
    private Long id;
    private String perfil;
    @ReadOnlyProperty
    private List<UsuarioResponse> usuarios;
}
