package com.sdjr2.rest_contact_meanssb.exceptions;

import com.sdjr2.rest_contact_meanssb.utils.UConstants;
import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * {@link CustomException} class.
 * <p>
 * <strong>Exception</strong> - Represents a class with the custom exception.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Exception
 * @upgrade 24/06/14
 * @since 24/06/14
 */
@Getter
public class CustomException extends ResponseStatusException {

  private final String id;
  private final String date;
  private final AppExceptionCodeEnum appExCode;
  private final Throwable originalException;

  public CustomException(AppExceptionCodeEnum appExCode, Throwable originalException) {
    super(appExCode.getHttpStatusCode(), appExCode.getMessage());
    this.id = String.valueOf(UUID.randomUUID());
    this.date = LocalDateTime.now().format(
      DateTimeFormatter.ofPattern(UConstants.S_FORMAT_DATETIME_BACK));
    this.appExCode = appExCode;
    this.originalException = originalException;
  }

  public CustomException(AppExceptionCodeEnum appExCode) {
    this(appExCode, null);
  }
}
