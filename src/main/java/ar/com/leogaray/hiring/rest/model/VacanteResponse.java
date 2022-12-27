package ar.com.leogaray.hiring.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class VacanteResponse {

    private Long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fecha;
    private Long salario;
    private Boolean destacado;
    private String imagen;
    private VacanteEstado estado;
    private String detalle;
    private CategoriaResponse categoria;
}
