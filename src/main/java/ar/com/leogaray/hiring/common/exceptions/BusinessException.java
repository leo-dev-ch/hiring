package ar.com.leogaray.hiring.common.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.CONFLICT)
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 3722118869346319456L;
    private static final String DEFAULT_MESSAGE = "Not Found";

    public BusinessException() {
        super(DEFAULT_MESSAGE);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String errorCode, String message) {
        super("Error Code: " + errorCode + " Message: " + message);
    }
}
