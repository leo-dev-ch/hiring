package ar.com.leogaray.hiring.common.exceptions;

import com.google.common.base.Throwables;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ExceptionResponseBuilder {

	private final boolean showStacktrace;

	public ExceptionResponseBuilder(@Value("${rest.error.stacktrace:#{true}}") boolean showStacktrace) {
		this.showStacktrace = showStacktrace;
	}

	public ExceptionResponse build(@NonNull ErrorType errorType, @NonNull Exception ex, String message) {
		return ExceptionResponse.builder()
				.errorType(errorType)
				.message(message)
				.stacktrace(showStacktrace ? Throwables.getStackTraceAsString(ex) : "")
				.build();
	}

	public ExceptionResponse build(@NonNull ErrorType errorType, @NonNull Exception ex) {
		return build(errorType, ex, ex.getMessage());
	}

}
