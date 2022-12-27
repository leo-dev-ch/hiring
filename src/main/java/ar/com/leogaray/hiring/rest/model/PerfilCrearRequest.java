package ar.com.leogaray.hiring.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PerfilCrearRequest {

    private String perfil;
    private Long usuarioId;
}
