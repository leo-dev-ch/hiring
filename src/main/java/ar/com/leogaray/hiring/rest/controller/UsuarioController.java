package ar.com.leogaray.hiring.rest.controller;

import ar.com.leogaray.hiring.common.exceptions.BusinessException;
import ar.com.leogaray.hiring.common.utils.converters.ConverterService;
import ar.com.leogaray.hiring.model.UsuarioEntity;
import ar.com.leogaray.hiring.rest.converter.UsuarioConverter;
import ar.com.leogaray.hiring.rest.model.*;
import ar.com.leogaray.hiring.services.UsuarioService;
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
@RequestMapping("/usuarios")
@AllArgsConstructor
@Tag(name = "Usuarios")
public class UsuarioController {
    private final ConverterService converterService;
    private final UsuarioConverter usuarioConverter;
    private UsuarioService usuarioService;

    @GetMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Retorna una lista de usuario")
    public List<UsuarioResponse> getAll() {
        return usuarioService.getAll().stream().map(converterService.getConverter(UsuarioEntity.class, UsuarioResponse.class)).collect(Collectors.toList());
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Crea un  Usuario")
    @Transactional
    public UsuarioResponse crear(@Valid @RequestBody UsuarioCrearRequest usuarioCrear) {
        if (!usuarioCrear.getPassword().equals(usuarioCrear.getPasswordRepeat())) {
            throw new BusinessException("Las contraseñas no coincide");
        }
        UsuarioEntity usuario = usuarioService.crearUsuario(usuarioConverter.toEntity(usuarioCrear));
        return converterService.convert(usuario, UsuarioResponse.class);
    }

    @PutMapping(value = "/modificar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Modifica un  Usuario")
    @Transactional
    public UsuarioResponse modificar(@RequestBody @Valid UsuarioModificarRequest usuarioRequest) {
        UsuarioEntity usuario = usuarioService.modificarUsuario(usuarioConverter.toEntity(usuarioRequest));
        return converterService.convert(usuario, UsuarioResponse.class);
    }

    @DeleteMapping(value = "/eliminar")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Elimina un  Usuario")
    public Boolean eliminar(@Parameter(description = "Usuario Id") @RequestParam(value = "usuarioId") Long usuarioId) {
        return usuarioService.eliminarUsuario(usuarioId);
    }

    @GetMapping(value = "/buscar")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Busca usuarios by example")
    public List<UsuarioResponse> buscar(@Parameter(description = "Usuario Id") @RequestParam(value = "usuarioId", required = false) Long usuarioId, @Parameter(description = "username") @RequestParam(value = "username", required = false) String username, @Parameter(description = "nombre") @RequestParam(value = "nombre", required = false) String nombre, @Parameter(description = "estado del") @RequestParam(value = "estado", required = false) UsuarioEstado estado) {
        UsuarioFiltro filtro = new UsuarioFiltro();
        filtro.builder(usuarioId, username, nombre, estado);
        return usuarioService.buscarUsuarios(filtro).stream().map(converterService.getConverter(UsuarioEntity.class, UsuarioResponse.class)).collect(Collectors.toList());
    }

    @GetMapping(value = "/{usuarioId}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Busca un Usuario por su Id")
    public UsuarioResponse get(@PathVariable Long usuarioId) {
        UsuarioEntity usuario = usuarioService.getUsuario(usuarioId);
        return converterService.convert(usuario, UsuarioResponse.class);
    }
}
