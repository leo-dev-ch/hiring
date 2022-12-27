package ar.com.leogaray.hiring.common.utils.converters;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Function;

@Service
public class ModelmapperConverterService implements ConverterService {

    private final ModelMapper modelMapper;

    @Autowired
    public ModelmapperConverterService(Set<Converter<?, ?>> converters) {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSourceNameTokenizer(NameTokenizers.CAMEL_CASE)
                .setDestinationNameTokenizer(NameTokenizers.CAMEL_CASE);
        converters.forEach(modelMapper::addConverter);
    }

    @Override
    public <F, T> Function<F, T> getConverter(Class<F> from, Class<T> to) {
        return (F source) -> modelMapper.map(source, to);
    }

    @Override
    public <F, T> T convert(F from, Class<T> to) {
        return modelMapper.map(from, to);
    }

    @Override
    public <F, T> void updateObject(F from, T to) {
        modelMapper.map(from, to);
    }
}
