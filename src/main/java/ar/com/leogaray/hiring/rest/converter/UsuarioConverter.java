package ar.com.leogaray.hiring.rest.converter;

import ar.com.leogaray.hiring.model.Rol;
import ar.com.leogaray.hiring.model.UsuarioEntity;
import ar.com.leogaray.hiring.rest.model.UsuarioCrearRequest;
import ar.com.leogaray.hiring.rest.model.UsuarioModificarRequest;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter implements EntityConverter<UsuarioEntity, UsuarioCrearRequest> {

    @Override
    public UsuarioCrearRequest toApiObject(UsuarioEntity entity) {
        return null;
    }

    @Override
    public UsuarioEntity toEntity(UsuarioCrearRequest usuarioCrearRequest) {
        Rol rol = Rol.valueOf(usuarioCrearRequest.getRol().name());
        return UsuarioEntity.builder().username(usuarioCrearRequest.getUsername()).password(usuarioCrearRequest.getPassword()).nombre(usuarioCrearRequest.getNombre()).email(usuarioCrearRequest.getEmail()).rol(rol).build();
    }

    public UsuarioEntity toEntity(UsuarioModificarRequest usuarioRequest) {
        Rol rol = Rol.valueOf(usuarioRequest.getRol().name());
        return UsuarioEntity.builder().id(usuarioRequest.getId()).nombre(usuarioRequest.getNombre()).email(usuarioRequest.getEmail()).rol(rol).build();
    }
}
