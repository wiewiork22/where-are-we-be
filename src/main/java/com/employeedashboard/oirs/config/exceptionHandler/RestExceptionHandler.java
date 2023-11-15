package com.employeedashboard.oirs.config.exceptionHandler;

import com.sun.jdi.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
	private record ExceptionResponse(String errorMessage) {
	}

	private void log(Exception ex) {
		log.warn(String.format("[%s class] Info : %s", ex.getClass().getName(), ex.getMessage()));
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse illegalArgument(final IllegalArgumentException ex) {
		log(ex);
		return new ExceptionResponse(ex.getMessage());
	}

	@ExceptionHandler(value = NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse notFound(final NotFoundException ex) {
		log(ex);
		return new ExceptionResponse(ex.getMessage());
	}

	@ExceptionHandler(value = InternalException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ExceptionResponse internalError(final NotFoundException ex) {
		log(ex);
		return new ExceptionResponse(ex.getMessage());
	}
}
