package ar.com.leogaray.hiring.rest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaModificarRequest {

    @ReadOnlyProperty
    private Integer id;
    private String nombre;
    private String descripcion;
}
