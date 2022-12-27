package ar.com.leogaray.hiring.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioModificarRequest {

    private Long id;
    private String username;
    private String nombre;
    private String email;
}
