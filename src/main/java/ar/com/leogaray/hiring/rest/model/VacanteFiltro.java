package ar.com.leogaray.hiring.rest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VacanteFiltro {

    private String detalle;
    private String nombre;
    private String descripcion;
    private VacanteEstado estado;
    private Integer categoriaId;

}
