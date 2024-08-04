package com.sdjr2.rest_contact_meanssb.models.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * {@link UserAuthRespDTO} class.
 * <p>
 * <strong>DTO</strong> - Represents a User about login resp in the Request / Response, implements to
 * {@link BaseDTO}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category DTO
 * @upgrade 24/08/04
 * @since 24/08/04
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserAuthRespDTO implements BaseDTO {

	public static final String ATTR_USERNAME = "username";
	public static final String ATTR_TOKEN = "token";
	public static final String ATTR_MESSAGE = "message";
	public static final String ATTR_ERROR = "error";

	private String username;

	private String token;

	private String error;

	@NotNull
	private String message;
}
