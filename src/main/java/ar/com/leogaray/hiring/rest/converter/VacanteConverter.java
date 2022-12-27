package ar.com.leogaray.hiring.rest.converter;

import ar.com.leogaray.hiring.common.exceptions.EntityNotFountException;
import ar.com.leogaray.hiring.common.utils.converters.ConverterService;
import ar.com.leogaray.hiring.model.CategoriaEntity;
import ar.com.leogaray.hiring.model.EstadoVacante;
import ar.com.leogaray.hiring.model.VacanteEntity;
import ar.com.leogaray.hiring.repository.CategoriaRepository;
import ar.com.leogaray.hiring.rest.model.CategoriaResponse;
import ar.com.leogaray.hiring.rest.model.VacanteCrearRequest;
import ar.com.leogaray.hiring.rest.model.VacanteEstado;
import ar.com.leogaray.hiring.rest.model.VacanteResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class VacanteConverter implements EntityConverter<VacanteEntity, VacanteResponse> {

    private final ConverterService converterService;
    private final CategoriaRepository categoriaRepository;

    public VacanteConverter(ConverterService converterService, CategoriaRepository categoriaRepository) {
        this.converterService = converterService;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public VacanteResponse toApiObject(VacanteEntity entity) {
        return VacanteResponse.builder().id(entity.getId()).nombre(entity.getNombre()).descripcion(entity.getDescripcion()).salario(entity.getSalario()).destacado(entity.getDestacado()).imagen(entity.getImagen()).fecha(entity.getFecha()).detalle(entity.getDetalle()).estado(VacanteEstado.valueOf(entity.getEstado().name())).categoria(converterService.convert(entity.getCategoriaEntity(), CategoriaResponse.class)).build();
    }

    @Override
    public VacanteEntity toEntity(VacanteResponse apiObject) {
        return null;
    }

    public VacanteEntity toEntity(VacanteCrearRequest source) {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(source.getCategoriaId()).orElseThrow(() -> new EntityNotFountException(CategoriaEntity.class, source.getCategoriaId()));
        Boolean defaultDestacado = (source.getDestacado() == null) ? Boolean.FALSE : source.getDestacado();
        return VacanteEntity.builder().nombre(source.getNombre()).descripcion(source.getDescripcion()).salario(source.getSalario()).detalle(source.getDetalle()).destacado(defaultDestacado).imagen(source.getImagen()).fecha(LocalDateTime.now()).estado(EstadoVacante.ACTIVO).categoriaEntity(categoriaEntity).build();
    }
}
