package ar.com.leogaray.hiring.configuration.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthRequest implements Serializable {

  private static final long serialVersionUID = 1L;
  @NotNull(message = "El username es requerido")
  private String username;

  @NotNull(message = "La clave es requerida")
  private String password;
}
