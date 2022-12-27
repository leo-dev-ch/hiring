package ar.com.leogaray.hiring.rest.controller;

import ar.com.leogaray.hiring.common.utils.converters.ConverterService;
import ar.com.leogaray.hiring.model.VacanteEntity;
import ar.com.leogaray.hiring.rest.converter.VacanteConverter;
import ar.com.leogaray.hiring.rest.model.*;
import ar.com.leogaray.hiring.services.VacanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequestMapping("/vacantes")
@AllArgsConstructor
@Tag(name = "Vacantes")
public class VacanteController {
    private final ConverterService converterService;
    private final VacanteService vacanteService;
    private final VacanteConverter vacanteConverter;


    @GetMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Retorna una lista de vacantes")
    public List<VacanteResponse> getAll() {
        return vacanteService.getAll()
                .stream()
                .map(converterService.getConverter(VacanteEntity.class, VacanteResponse.class))
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/buscar")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Busca vacantes by example")
    public List<VacanteResponse> buscar(@Parameter(description = "nombre") @RequestParam(value = "nombre", required = false) String nombre,
                                        @Parameter(description = "detalle") @RequestParam(value = "detalle", required = false) String detalle,
                                        @Parameter(description = "descripcion") @RequestParam(value = "descripcion", required = false) String descripcion,
                                        @Parameter(description = "categoriaId") @RequestParam(value = "categoriaId", required = false) Integer categoriaId,
                                        @Parameter(description = "estado") @RequestParam(value = "estado", required = false) VacanteEstado estado) {
        VacanteFiltro filtro = VacanteFiltro.builder()
                .categoriaId(categoriaId)
                .descripcion(descripcion)
                .detalle(detalle)
                .nombre(nombre)
                .estado(estado)
                .build();

        return vacanteService.buscar(filtro)
                .stream()
                .map(converterService.getConverter(VacanteEntity.class, VacanteResponse.class))
                .collect(Collectors.toList());
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Crea una vacante")
    @Transactional
    public VacanteResponse crear(@RequestBody @Valid VacanteCrearRequest source) {
        return vacanteConverter.toApiObject(vacanteService.crear(source));
    }

    @DeleteMapping(value = "/eliminar")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Elimina un vacante")
    public Boolean eliminar(@Parameter(description = "Vacante Id") @RequestParam(value = "vacanteId") Long vacanteId) {
        return vacanteService.eliminar(vacanteId);
    }

    @GetMapping(value = "/{vacanteId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Busca un Vacante por su Id")
    public VacanteResponse get(@PathVariable Long vacanteId) {
        return vacanteConverter.toApiObject(vacanteService.get(vacanteId));
    }

    @PutMapping(value = "/modificar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Modifica una  Vacante")
    @Transactional
    public VacanteResponse modificar(@RequestBody @Valid VacanteModificarRequest source) {
        return vacanteConverter.toApiObject(vacanteService.modificar(source));
    }

}
