package ar.com.leogaray.hiring.services.implementation;

import ar.com.leogaray.hiring.common.exceptions.EntityNotFountException;
import ar.com.leogaray.hiring.model.CategoriaEntity;
import ar.com.leogaray.hiring.model.EstadoVacante;
import ar.com.leogaray.hiring.model.VacanteEntity;
import ar.com.leogaray.hiring.repository.CategoriaRepository;
import ar.com.leogaray.hiring.repository.VacanteRepository;
import ar.com.leogaray.hiring.rest.converter.VacanteConverter;
import ar.com.leogaray.hiring.rest.model.VacanteCrearRequest;
import ar.com.leogaray.hiring.rest.model.VacanteFiltro;
import ar.com.leogaray.hiring.rest.model.VacanteModificarRequest;
import ar.com.leogaray.hiring.services.VacanteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class VacanteServiceImp implements VacanteService {
    private final VacanteRepository vacanteRepository;
    private final VacanteConverter vacanteConverter;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<VacanteEntity> getAll() {
        return vacanteRepository.findAll();
    }

    @Override
    public List<VacanteEntity> buscar(VacanteFiltro filtro) {
        VacanteEntity example = new VacanteEntity();
        if (filtro.getCategoriaId() != null) {
            CategoriaEntity categoriaEntity = categoriaRepository.findById(filtro.getCategoriaId()).orElseThrow(() -> new EntityNotFountException(CategoriaEntity.class, filtro.getCategoriaId()));
            example.setCategoriaEntity(categoriaEntity);
        }
        if (filtro.getEstado() != null) {
            example.setEstado(EstadoVacante.valueOf(filtro.getEstado().name()));
        }

        example.setDescripcion(filtro.getDescripcion());
        example.setDetalle(filtro.getDetalle());
        example.setNombre(filtro.getNombre());
        ExampleMatcher vacanteMatcher = ExampleMatcher
                .matchingAny()
                .withIgnoreCase("descripcion", "nombre", "detalle")
                .withNullHandler(ExampleMatcher.NullHandler.INCLUDE)
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return vacanteRepository.findAll(Example.of(example, vacanteMatcher));
    }

    @Override
    public VacanteEntity get(Long id) {
        return vacanteRepository.findById(id).orElseThrow(() -> new EntityNotFountException(VacanteEntity.class, id));
    }

    @Override
    public VacanteEntity crear(VacanteCrearRequest request) {
        return vacanteRepository.save(vacanteConverter.toEntity(request));
    }

    @Override
    public VacanteEntity modificar(VacanteModificarRequest request) {
        VacanteEntity vacanteEntity = vacanteRepository.findById(request.getId()).orElseThrow(() -> new EntityNotFountException(VacanteEntity.class, request.getId()));
        CategoriaEntity categoriaEntity = categoriaRepository.findById(request.getCategoriaId()).orElseThrow(() -> new EntityNotFountException(CategoriaEntity.class, request.getCategoriaId()));
        vacanteEntity.setNombre(request.getNombre());
        vacanteEntity.setDescripcion(request.getDescripcion());
        vacanteEntity.setSalario(request.getSalario());
        vacanteEntity.setDetalle(request.getDetalle());
        vacanteEntity.setDestacado(request.getDestacado());
        vacanteEntity.setImagen(request.getImagen());
        vacanteEntity.setEstado(EstadoVacante.valueOf(request.getEstado().name()));
        vacanteEntity.setCategoriaEntity(categoriaEntity);

        return vacanteRepository.save(vacanteEntity);
    }

    @Override
    public Boolean eliminar(Long id) {
        VacanteEntity vacanteEntity = vacanteRepository.findById(id).orElseThrow(() -> new EntityNotFountException(VacanteEntity.class, id));
        vacanteRepository.delete(vacanteEntity);
        return true;
    }
}
