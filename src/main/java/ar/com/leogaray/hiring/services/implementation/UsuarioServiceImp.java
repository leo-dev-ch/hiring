package ar.com.leogaray.hiring.services.implementation;

import ar.com.leogaray.hiring.common.exceptions.EntityAlreadyExistsException;
import ar.com.leogaray.hiring.common.exceptions.EntityNotFountException;
import ar.com.leogaray.hiring.model.EstadoUsuario;
import ar.com.leogaray.hiring.model.UsuarioEntity;
import ar.com.leogaray.hiring.repository.UsuarioRepository;
import ar.com.leogaray.hiring.rest.model.UsuarioFiltro;
import ar.com.leogaray.hiring.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioEntity> getAll() {
        UsuarioEntity usuarioExample = new UsuarioEntity();
        usuarioExample.setEstado(EstadoUsuario.ACTIVO);
        return usuarioRepository.findAll(Example.of(usuarioExample));
    }


    @Override
    public List<UsuarioEntity> buscarUsuarios(UsuarioFiltro filtro) {
        UsuarioEntity usuarioExample = new UsuarioEntity();
        if (filtro.getEstado() != null) {
            usuarioExample.setEstado(EstadoUsuario.valueOf(filtro.getEstado().name()));
        }
        usuarioExample.setId(filtro.getId());
        usuarioExample.setUsername(filtro.getUsername());
        usuarioExample.setNombre(filtro.getNombre());
        ExampleMatcher usuarioMatcher = ExampleMatcher.matchingAny().withIgnoreCase("username", "nombre").withNullHandler(ExampleMatcher.NullHandler.INCLUDE).withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return usuarioRepository.findAll(Example.of(usuarioExample, usuarioMatcher));
    }

    @Override
    public UsuarioEntity crearUsuario(UsuarioEntity usuario) {
        if (usernameExists(usuario.getUsername())) {
            throw new EntityAlreadyExistsException();
        }
        usuario.setEstado(EstadoUsuario.ACTIVO);
        LocalDateTime fecha = LocalDateTime.now();
        usuario.setFechaRegistro(fecha);
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public UsuarioEntity modificarUsuario(UsuarioEntity usuario) {
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(usuario.getId());
        if (usuarioEntity.isPresent()) {
            usuarioEntity.get().setEmail(usuario.getEmail());
            usuarioEntity.get().setNombre(usuario.getNombre());
            return usuarioRepository.save(usuarioEntity.get());
        } else {
            throw new EntityNotFountException(UsuarioEntity.class, usuario.getId());
        }
    }

    @Override
    public Boolean eliminarUsuario(Long usuarioId) {

        Optional<UsuarioEntity> usuario = usuarioRepository.findById(usuarioId);
        if (usuario.isPresent()) {
            usuario.get().setEstado(EstadoUsuario.INACTIVO);
            usuarioRepository.save(usuario.get());
        } else {
            throw new EntityNotFountException(UsuarioEntity.class, usuarioId);
        }
        return true;
    }

    @Override
    public UsuarioEntity getUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(() -> new EntityNotFountException(UsuarioEntity.class, usuarioId));
    }

    private boolean usernameExists(final String username) {
        return usuarioRepository.findByUsername(username) != null;
    }
}
