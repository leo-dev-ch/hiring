package ar.com.leogaray.hiring.rest.model;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class UsuarioCrearRequest {
    @NotEmpty(message = "El nombre de usuario es requerido")
    @Size(min = 2, max = 15, message = "El tamaño es entre 2 y 15 caracteres")
    private String username;
    @NotEmpty(message = "El nombre es requerido")
    @Size(min = 2, max = 100, message = "El tamaño es entre 2 y 100 caracteres")
    private String nombre;
    @NotEmpty(message = "El email es requerido")
    @Email(message = "El email es inhabilido", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;
    @NotNull(message = "El rol es requerido")
    private Rol rol;
    @NotBlank(message = "La contraseña es requerida")
    private String password;
    @NotBlank(message = "La contraseña es requerida")
    private String passwordRepeat;
}
