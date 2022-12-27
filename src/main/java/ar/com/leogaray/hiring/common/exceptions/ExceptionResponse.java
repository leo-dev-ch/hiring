package ar.com.leogaray.hiring.common.exceptions;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder
@Schema(description = "Error response")
public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = 4072776901522795387L;
    private ErrorType errorType;
    private String message;
    private String stacktrace;
}
