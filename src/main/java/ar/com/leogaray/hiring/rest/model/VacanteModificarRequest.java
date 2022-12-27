package ar.com.leogaray.hiring.rest.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacanteModificarRequest {

    @NotNull(message = "El id es requerido")
    private Long id;
    @NotEmpty(message = "El nombre es requerido")
    @Size(min = 2, max = 50, message = "El tamaño es entre 2 y 50 caracteres")
    private String nombre;
    @NotEmpty(message = "La descripcion es requerida")
    @Size(min = 10, max = 20, message = "El tamaño es entre 10 y 200 caracteres")
    private String descripcion;
    @NotNull(message = "El estado es requerido")
    private VacanteEstado estado;
    @NotEmpty(message = "El detalle es requerido")
    private String detalle;
    @NotNull(message = "La categoriaId es requerida")
    private Integer categoriaId;
    private Long salario;
    private Boolean destacado;
    private String imagen;
}
