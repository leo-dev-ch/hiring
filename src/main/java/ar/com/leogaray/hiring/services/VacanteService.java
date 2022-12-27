package ar.com.leogaray.hiring.services;

import ar.com.leogaray.hiring.model.VacanteEntity;
import ar.com.leogaray.hiring.rest.model.VacanteCrearRequest;
import ar.com.leogaray.hiring.rest.model.VacanteFiltro;
import ar.com.leogaray.hiring.rest.model.VacanteModificarRequest;

import java.util.List;

public interface VacanteService {

    List<VacanteEntity> getAll();

    List<VacanteEntity> buscar(VacanteFiltro filtro);

    VacanteEntity get(Long id);

    VacanteEntity crear(VacanteCrearRequest request);

    VacanteEntity modificar(VacanteModificarRequest request);

    Boolean eliminar(Long id);
}
