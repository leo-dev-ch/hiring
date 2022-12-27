package ar.com.leogaray.hiring.rest.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
public class UsuarioModificarRequest {

    @NotNull(message = "El id es requerido")
    private Long id;
    @NotEmpty(message = "El nombre es requerido")
    @Size(min = 2, max = 100, message = "El tamaño es entre 2 y 100 caracteres")
    private String nombre;
    @NotEmpty(message = "El email es requerido")
    @Email(message = "El email es inhabilido", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;
}
