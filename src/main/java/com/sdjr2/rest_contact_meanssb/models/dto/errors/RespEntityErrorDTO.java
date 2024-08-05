package com.sdjr2.rest_contact_meanssb.models.dto.errors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

/**
 * {@link RespEntityErrorDTO} class.
 * <p>
 * <strong>Exception</strong> - Represents a class with a custom error, usually thrown by an exception. This
 * extends from {@link BaseDTO}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Exception
 * @upgrade 24/07/16
 * @since 24/06/14
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RespEntityErrorDTO implements BaseDTO, Serializable {

	@Serial
	private static final long serialVersionUID = -7048689708460974248L;

	private String id;

	private String timestamp;

	@JsonProperty("http_status_code")
	private int httpStatusCode;      // HttpStatus

	@JsonProperty("error_code")
	private int errorCode;          // AppExceptionCode.code

	@JsonProperty("error_message")
	private String errorMessage;    // AppExceptionCode.msg

	@JsonProperty("validation_errors")
	private Map<String, String> validationErrors;

	@JsonProperty("ex_message")
	private String exMessage;

	@JsonProperty("ex_track_trace")
	private String exTrackTrace;
}
