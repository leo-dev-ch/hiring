package ar.com.leogaray.hiring.services;

import ar.com.leogaray.hiring.model.PerfilEntity;
import ar.com.leogaray.hiring.rest.model.PerfilCrearRequest;
import ar.com.leogaray.hiring.rest.model.PerfilModificarRequest;

import java.util.List;

public interface PerfilService {

    List<PerfilEntity> getAll();

    PerfilEntity get(Long id);

    PerfilEntity crear(PerfilCrearRequest usuario);

    PerfilEntity modificar(PerfilModificarRequest entity);

    Boolean eliminar(Long id);

}
