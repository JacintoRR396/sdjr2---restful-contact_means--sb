package com.sdjr2.rest_contact_meanssb.exceptions;

import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;

import java.io.Serial;
import java.util.UUID;

/**
 * {@link CustomException} class.
 * <p>
 * <strong>Exception</strong> - Represents a class with the custom exception.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Exception
 * @upgrade 24/06/18
 * @since 24/06/14
 */
@Getter
public class CustomException extends ResponseStatusException {

	@Serial
	private static final long serialVersionUID = 7890133225255297908L;

	private final String id;
	private final AppExceptionCodeEnum appExCode;
	private final Throwable originalException;

	public CustomException (Throwable originalException, AppExceptionCodeEnum appExCode) {
		super(appExCode.getHttpStatusCode(), appExCode.getMessage());
		this.id = String.valueOf(UUID.randomUUID());
		this.appExCode = appExCode;
		this.originalException = originalException;
	}

	public CustomException (AppExceptionCodeEnum appExCode) {
		this(null, appExCode);
	}
}
