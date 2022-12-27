package ar.com.leogaray.hiring.rest.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class VacanteModificarRequest {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long salario;
    private Boolean destacado;
    private String imagen;
    private VacanteEstado estado;
    private String detalle;
    private Integer categoriaId;
}
