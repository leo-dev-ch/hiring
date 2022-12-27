package ar.com.leogaray.hiring.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaCrearRequest {
    private String nombre;
    private String descripcion;
}
