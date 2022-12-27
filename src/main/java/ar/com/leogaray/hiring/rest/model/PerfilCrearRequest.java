package ar.com.leogaray.hiring.rest.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class PerfilCrearRequest {

    @NotEmpty(message = "El campo es requerido")
    private String perfil;
    @NotNull(message = "El id usuario es requerido")
    private Long usuarioId;
}
