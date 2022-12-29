package ar.com.leogaray.hiring.rest.controller;

import ar.com.leogaray.hiring.common.utils.converters.ConverterService;
import ar.com.leogaray.hiring.model.CategoriaEntity;
import ar.com.leogaray.hiring.rest.model.CategoriaCrearRequest;
import ar.com.leogaray.hiring.rest.model.CategoriaModificarRequest;
import ar.com.leogaray.hiring.rest.model.CategoriaResponse;
import ar.com.leogaray.hiring.services.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
@Tag(name = "Categorias")
public class CategoriaController {
    private final ConverterService converterService;
    private CategoriaService categoriaService;

    @GetMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Retorna una lista de usuario")
    public List<CategoriaResponse> getAll() {
        return categoriaService.getAll()
                .stream()
                .map(converterService.getConverter(CategoriaEntity.class, CategoriaResponse.class))
                .collect(Collectors.toList());
    }


    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Crea una categoria")
    public CategoriaResponse crear(@RequestBody @Valid CategoriaCrearRequest source) {
        CategoriaEntity categoriaEntity = categoriaService
                .crear(converterService.convert(source, CategoriaEntity.class));
        return converterService.convert(categoriaEntity, CategoriaResponse.class);
    }

    @PutMapping(value = "/modificar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Modifica una categoria")
    public CategoriaResponse modificar(@RequestBody @Valid CategoriaModificarRequest source) {
        CategoriaEntity categoriaEntity = categoriaService
                .modificar(converterService.convert(source, CategoriaEntity.class));
        return converterService.convert(categoriaEntity, CategoriaResponse.class);
    }

    @DeleteMapping(value = "/eliminar")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Elimina una categoria")
    public Boolean eliminar(@Parameter(description = "Categoria Id") @RequestParam(value = "categoriaId") Integer categoriaId) {
        return categoriaService.eliminar(categoriaId);
    }


    @GetMapping(value = "/{categoriaId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Busca una categoria por su Id")
    public CategoriaResponse get(@PathVariable Integer categoriaId) {
        CategoriaEntity categoriaEntity = categoriaService.get(categoriaId);
        return converterService.convert(categoriaEntity, CategoriaResponse.class);
    }
}
