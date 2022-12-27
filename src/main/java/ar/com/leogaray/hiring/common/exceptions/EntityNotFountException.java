package ar.com.leogaray.hiring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFountException extends RuntimeException {
    private static final long serialVersionUID = 3722118869346319456L;
    private static final String DEFAULT_MESSAGE = "Not Found";

    public EntityNotFountException() {
        super(DEFAULT_MESSAGE);
    }

    public EntityNotFountException(String message) {
        super(message);
    }

    public EntityNotFountException(Integer id) {
        super(String.format("La entidad con id %d no existe ", id));
    }

    public EntityNotFountException(Class<?> theClass, Long id) {
        super(String.format("La entidad %s no existe id: %s", theClass.getName(), id));
    }

    public EntityNotFountException(Class<?> theClass, Integer id) {
        super(String.format("La entidad %s no existe id: %s", theClass.getName(), id));
    }
}
