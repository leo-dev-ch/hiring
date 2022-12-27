package ar.com.leogaray.hiring.rest.converter;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class that implements this interface can be used to convert
 * a database entity into its API representation and back again.
 *
 * @param <E> the type of the entity object
 * @param <A> the type of the presentation object (API object)
 */
public interface EntityConverter<E, A> {
    A toApiObject(E entity);

    E toEntity(A apiObject);

    default @Nonnull Stream<A> toApiObjectStream(Collection<E> entities) {
        if (entities == null) {
            return Stream.empty();
        }
        return entities.stream().map(this::toApiObject);
    }

    default @Nonnull List<A> toApiObjectList(Collection<E> entities) {
        return toApiObjectStream(entities).collect(Collectors.toList());
    }

    default @Nonnull Stream<E> toEntityStream(Collection<A> apiObjects) {
        if (apiObjects == null) {
            return Stream.empty();
        }
        return apiObjects.stream().map(this::toEntity);
    }

    default @Nonnull List<E> toEntityList(Collection<A> apiObjects) {
        return toEntityStream(apiObjects).collect(Collectors.toList());
    }

}
