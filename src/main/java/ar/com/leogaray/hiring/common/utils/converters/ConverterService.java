package ar.com.leogaray.hiring.common.utils.converters;

import java.util.function.Function;

public interface ConverterService {

    <F, T> Function<F, T> getConverter(Class<F> from, Class<T> to);

    <F, T> T convert(F from, Class<T> to);

    <F, T> void updateObject(F from, T to);


}
