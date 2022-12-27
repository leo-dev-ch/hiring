package ar.com.leogaray.hiring.rest.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

@Data
@Builder
public class PerfilModificarRequest {
    @ReadOnlyProperty
    private Long id;
    @ReadOnlyProperty
    private Long usuarioId;
    private String perfil;
}
