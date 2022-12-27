package ar.com.leogaray.hiring.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaCrearRequest {
    @NotEmpty(message = "El nombre es requerido")
    @Size(min = 2, max = 50, message = "El tamaño es entre 2 y 100 caracteres")
    private String nombre;
    private String descripcion;
}
