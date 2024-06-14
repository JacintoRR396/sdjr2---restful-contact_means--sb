package com.sdjr2.rest_contact_meanssb.models.errors;

import lombok.Data;

/**
 * {@link RespEntityErrorDTO} class.
 * <p>
 * <strong>Exception</strong> - Represents a class with a custom error, usually thrown by an exception.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Exception
 * @upgrade 24/06/14
 * @since 24/06/14
 */
@Data
public class RespEntityErrorDTO {

  private String id;
  private String date;
  private int httpStatusCode;			// HttpStatus
  private int errorCode;			    // AppExceptionCode.code
  private String errorMessage;		// AppExceptionCode.msg
  private String exMessage;
  private String exTrackTrace;
}
