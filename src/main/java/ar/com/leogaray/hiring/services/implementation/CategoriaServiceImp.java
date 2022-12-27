package ar.com.leogaray.hiring.services.implementation;

import ar.com.leogaray.hiring.common.exceptions.EntityNotFountException;
import ar.com.leogaray.hiring.model.CategoriaEntity;
import ar.com.leogaray.hiring.repository.CategoriaRepository;
import ar.com.leogaray.hiring.services.CategoriaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoriaServiceImp implements CategoriaService {
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaEntity> getAll() {
        return categoriaRepository.findAll();
    }


    @Override
    public CategoriaEntity crear(CategoriaEntity source) {

        return categoriaRepository.save(source);
    }

    @Override
    public CategoriaEntity modificar(CategoriaEntity source) {
        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(source.getId());
        if (categoriaEntity.isPresent()) {
            categoriaEntity.get().setNombre(source.getNombre());
            categoriaEntity.get().setDescripcion(source.getDescripcion());
            return categoriaRepository.save(categoriaEntity.get());
        } else {
            throw new EntityNotFountException(CategoriaEntity.class, source.getId());
        }
    }

    @Override
    public Boolean eliminar(Integer id) {

        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(id);
        if (categoriaEntity.isPresent()) {

            categoriaRepository.delete(categoriaEntity.get());
        } else {
            throw new EntityNotFountException(CategoriaEntity.class, id);
        }
        return true;
    }

    @Override
    public CategoriaEntity get(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFountException(CategoriaEntity.class, id));
    }
}
