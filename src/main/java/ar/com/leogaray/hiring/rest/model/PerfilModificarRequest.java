package ar.com.leogaray.hiring.rest.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class PerfilModificarRequest {
    @ReadOnlyProperty
    @NotNull(message = "El id es requerido")
    private Long id;
    @NotEmpty(message = "El campo es requerido")
    private String perfil;
    @NotNull(message = "El id usuario es requerido")
    private Long usuarioId;
}
