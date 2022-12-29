package ar.com.leogaray.hiring.common.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
class DefaultExceptionHandler {

	private final ExceptionResponseBuilder exceptionResponseBuilder;

	public DefaultExceptionHandler(ExceptionResponseBuilder exceptionResponseBuilder) {
		this.exceptionResponseBuilder = exceptionResponseBuilder;
	}

	@ExceptionHandler({
			MethodArgumentTypeMismatchException.class, ServletRequestBindingException.class,
			IllegalArgumentException.class
	})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse invalidRequest(Exception ex) {
		log.info(ex.getMessage(), ex);
		return exceptionResponseBuilder.build(ErrorType.INVALID_REQUEST, ex);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse invalidArgumentExceptionHandler(
			MethodArgumentNotValidException ex) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		String errorMessage = errors.stream()
				.map(error -> error.getField() + " " + error.getDefaultMessage())
				.collect(Collectors.joining(" // "));
		log.info(errorMessage, ex);
		return exceptionResponseBuilder.build(ErrorType.INVALID_REQUEST, ex, errorMessage);
	}

	@ExceptionHandler(EntityAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ExceptionResponse entityAlreadyExists(EntityAlreadyExistsException ex) {
		log.warn(ex.getMessage(), ex);
		return exceptionResponseBuilder.build(ErrorType.GENERAL_ERROR, ex);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse defaultExceptionHandler(Exception ex) {
		log.error(ex.getMessage(), ex);
		return exceptionResponseBuilder.build(ErrorType.UNEXPECTED_ERROR, ex);
	}

	@ExceptionHandler(BusinessException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ExceptionResponse bussinessException(BusinessException ex) {
		log.warn(ex.getMessage(), ex);
		return exceptionResponseBuilder.build(ErrorType.GENERAL_ERROR, ex);
	}

	@ExceptionHandler(EntityNotFountException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse bussinessException(EntityNotFountException ex) {
		log.warn(ex.getMessage(), ex);
		return exceptionResponseBuilder.build(ErrorType.GENERAL_ERROR, ex);
	}
}
