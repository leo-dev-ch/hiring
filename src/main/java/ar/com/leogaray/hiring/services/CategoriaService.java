package ar.com.leogaray.hiring.services;

import ar.com.leogaray.hiring.model.CategoriaEntity;

import java.util.List;

public interface CategoriaService {

    List<CategoriaEntity> getAll();

    CategoriaEntity get(Integer id);

    CategoriaEntity crear(CategoriaEntity usuario);

    CategoriaEntity modificar(CategoriaEntity entity);

    Boolean eliminar(Integer id);

}
