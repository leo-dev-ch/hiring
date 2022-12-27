package ar.com.leogaray.hiring.rest.converter;

import ar.com.leogaray.hiring.common.utils.converters.ConverterService;
import ar.com.leogaray.hiring.model.PerfilEntity;
import ar.com.leogaray.hiring.model.UsuarioEntity;
import ar.com.leogaray.hiring.rest.model.PerfilResponse;
import ar.com.leogaray.hiring.rest.model.UsuarioResponse;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class PerfilConverter implements EntityConverter<PerfilEntity, PerfilResponse> {

    private final ConverterService converterService;

    public PerfilConverter(ConverterService converterService) {
        this.converterService = converterService;
    }

    @Override
    public PerfilResponse toApiObject(PerfilEntity entity) {

        return PerfilResponse.builder().id(entity.getId()).perfil(entity.getPerfil()).usuarios(entity.getUsuarios().stream().map(converterService.getConverter(UsuarioEntity.class, UsuarioResponse.class)).collect(Collectors.toList())).build();
    }

    @Override
    public PerfilEntity toEntity(PerfilResponse source) {
        return null;
    }

}
