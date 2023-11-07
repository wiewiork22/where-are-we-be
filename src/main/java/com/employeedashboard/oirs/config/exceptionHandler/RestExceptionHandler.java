package com.employeedashboard.oirs.config.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {
	private record ExceptionResponse(String errorMessage) {
	}

	@ExceptionHandler(value = IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ExceptionResponse illegalArgument(final IllegalArgumentException ex) {
		log.warn(String.format("[%s class] Info : %s", ex.getClass().getName(), ex.getMessage()));
		return new ExceptionResponse(ex.getMessage());
	}

	@ExceptionHandler(value = NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ExceptionResponse notFound(final NotFoundException ex) {
		log.warn(String.format("[%s class] Info : %s", ex.getClass().getName(), ex.getMessage()));
		return new ExceptionResponse(ex.getMessage());
	}
}
