package ar.com.leogaray.hiring.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntityAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EntityAlreadyExistsException() {
        super(String.format("La entidad ya existe"));
    }

    public EntityAlreadyExistsException(Integer id) {
        super(String.format("La entidad con el id %s ya existe", id));
    }

}
