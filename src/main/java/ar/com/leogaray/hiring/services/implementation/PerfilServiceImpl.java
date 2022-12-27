package ar.com.leogaray.hiring.services.implementation;

import ar.com.leogaray.hiring.common.exceptions.BusinessException;
import ar.com.leogaray.hiring.common.exceptions.EntityNotFountException;
import ar.com.leogaray.hiring.model.PerfilEntity;
import ar.com.leogaray.hiring.model.UsuarioEntity;
import ar.com.leogaray.hiring.repository.PerfilRepository;
import ar.com.leogaray.hiring.repository.UsuarioRepository;
import ar.com.leogaray.hiring.rest.model.PerfilCrearRequest;
import ar.com.leogaray.hiring.rest.model.PerfilModificarRequest;
import ar.com.leogaray.hiring.services.PerfilService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PerfilServiceImpl implements PerfilService {
    private final PerfilRepository perfilRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    public List<PerfilEntity> getAll() {
        return perfilRepository.findAll();
    }

    @Override
    public PerfilEntity get(Long id) {
        return perfilRepository.findById(id).orElseThrow(() -> new EntityNotFountException(PerfilEntity.class, id));
    }

    @Override
    public PerfilEntity crear(PerfilCrearRequest request) {
        UsuarioEntity usuario = usuarioRepository.findById(request.getUsuarioId()).orElseThrow(() -> new BusinessException("El usuario no existe"));
        PerfilEntity perfil = new PerfilEntity();
        perfil.setPerfil(request.getPerfil());
        perfil.addUsuario(usuario);
        return perfilRepository.save(perfil);
    }

    @Override
    public PerfilEntity modificar(PerfilModificarRequest request) {
        PerfilEntity perfilEntity = perfilRepository.findById(request.getId()).orElseThrow(() -> new EntityNotFountException(PerfilEntity.class, request.getId()));
        UsuarioEntity usuarioEntity = perfilEntity.getUsuarios().stream().findFirst().orElseThrow(() -> new BusinessException("El usuario no existe"));

        if (!request.getUsuarioId().equals(usuarioEntity.getId())) {
            throw new BusinessException("Los usuarios no coinciden");
        }
        perfilEntity.setPerfil(request.getPerfil());
        return perfilRepository.save(perfilEntity);

    }

    @Override
    public Boolean eliminar(Long id) {

        PerfilEntity perfilEntity = perfilRepository.findById(id).orElseThrow(() -> new EntityNotFountException(PerfilEntity.class, id));
        perfilRepository.delete(perfilEntity);
        return true;
    }
}
