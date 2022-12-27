package ar.com.leogaray.hiring.services;

import ar.com.leogaray.hiring.model.UsuarioEntity;
import ar.com.leogaray.hiring.rest.model.UsuarioFiltro;

import java.util.List;

public interface UsuarioService {
    List<UsuarioEntity> getAll();

    List<UsuarioEntity> buscarUsuarios(UsuarioFiltro filtro);

    UsuarioEntity crearUsuario(UsuarioEntity usuario);

    UsuarioEntity modificarUsuario(UsuarioEntity usuario);

    Boolean eliminarUsuario(Long usuarioId);


    UsuarioEntity getUsuario(Long usuarioId);
}
