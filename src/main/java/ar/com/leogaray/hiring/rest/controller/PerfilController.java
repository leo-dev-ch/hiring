package ar.com.leogaray.hiring.rest.controller;

import ar.com.leogaray.hiring.common.utils.converters.ConverterService;
import ar.com.leogaray.hiring.model.PerfilEntity;
import ar.com.leogaray.hiring.rest.converter.PerfilConverter;
import ar.com.leogaray.hiring.rest.model.PerfilCrearRequest;
import ar.com.leogaray.hiring.rest.model.PerfilModificarRequest;
import ar.com.leogaray.hiring.rest.model.PerfilResponse;
import ar.com.leogaray.hiring.services.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/perfiles")
@AllArgsConstructor
@Tag(name = "Perfiles")
public class PerfilController {
    private final ConverterService converterService;
    private final PerfilService perfilService;
    private final PerfilConverter perfilConverter;

    @GetMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Retorna una lista de perfiles")
    public List<PerfilResponse> getAll() {
        return perfilService.getAll()
                .stream()
                .map(converterService.getConverter(PerfilEntity.class, PerfilResponse.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Crea un  Perfil")
    @Transactional
    public PerfilResponse crear(@RequestBody @Valid PerfilCrearRequest source) {
        return perfilConverter.toApiObject(perfilService.crear(source));
    }

    @DeleteMapping(value = "/eliminar")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Elimina un perfil")
    public Boolean eliminar(@Parameter(description = "Perfil Id") @RequestParam(value = "perfilId") Long perfilId) {
        return perfilService.eliminar(perfilId);
    }

    @GetMapping(value = "/{perfilId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Busca un Perfil por su Id")
    public PerfilResponse get(@PathVariable Long perfilId) {
        return perfilConverter.toApiObject(perfilService.get(perfilId));
    }

    @PutMapping(value = "/modificar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Modifica un  Perfil")
    @Transactional
    public PerfilResponse modificar(@RequestBody @Valid PerfilModificarRequest source) {
        return perfilConverter.toApiObject(perfilService.modificar(source));
    }
}
