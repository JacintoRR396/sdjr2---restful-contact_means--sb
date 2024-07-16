package com.sdjr2.rest_contact_meanssb.exceptions;

import org.springframework.http.HttpStatus;

/**
 * {@link AppExceptionCodeEnum} class.
 * <p>
 * <strong>Exception</strong> - Represents a class with the different custom errors that exceptions can throw.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Exception
 * @upgrade 24/07/16
 * @since 24/06/14
 */
public enum AppExceptionCodeEnum {

	STATUS_40000( HttpStatus.BAD_REQUEST, 40000, "Bad Request" ),
	STATUS_40001( HttpStatus.BAD_REQUEST, 40001, "Bad Request: Validation Errors" ),
	STATUS_40002( HttpStatus.BAD_REQUEST, 40002, "Bad Request: There is a record with the same unique fields already exists" ),
	STATUS_40100( HttpStatus.UNAUTHORIZED, 40100, "Unauthorized" ),
	STATUS_40101( HttpStatus.UNAUTHORIZED, 40101, "Unauthorized: Received request without AccessToken cookie" ),
	STATUS_40102( HttpStatus.UNAUTHORIZED, 40102, "Unauthorized: Invalid Access Token" ),
	STATUS_40300( HttpStatus.FORBIDDEN, 40300, "Forbidden" ),
	STATUS_40400( HttpStatus.NOT_FOUND, 40400, "Not Found" ),
	STATUS_40401( HttpStatus.NOT_FOUND, 40401, "Not Found: There is no record with that identifier" ),
	STATUS_40500( HttpStatus.METHOD_NOT_ALLOWED, 40500, "Method Not Allowed" ),
	STATUS_40600( HttpStatus.NOT_ACCEPTABLE, 40600, "Media Type Not Acceptable" ),
	STATUS_41500( HttpStatus.UNSUPPORTED_MEDIA_TYPE, 41500, "Media Type Not Unsupported" ),
	STATUS_50000( HttpStatus.INTERNAL_SERVER_ERROR, 50000, "Internal Server Error" ),
	STATUS_50001( HttpStatus.INTERNAL_SERVER_ERROR, 50001, "Type not valid in enum" ),
	STATUS_50010( HttpStatus.INTERNAL_SERVER_ERROR, 50010, "Hibernate: validation error" ),
	STATUS_50201( HttpStatus.BAD_GATEWAY, 50201, "Bad Gateway: Authentication against Salesforce failed" ),
	STATUS_50300( HttpStatus.SERVICE_UNAVAILABLE, 50300, "Service Unavailable" );

	private final HttpStatus httpStatusCode;
	private final Integer appStatusCode;
	private final String message;

	AppExceptionCodeEnum ( HttpStatus httpStatusCode, Integer appStatusCode, String message ) {
		this.httpStatusCode = httpStatusCode;
		this.appStatusCode = appStatusCode;
		this.message = message;
	}

	public HttpStatus getHttpStatusCode () {
		return this.httpStatusCode;
	}

	public Integer getAppStatusCode () {
		return this.appStatusCode;
	}

	public String getMessage () {
		return this.message;
	}

	public static AppExceptionCodeEnum getByAppStatusCode ( Integer appStatusCode ) {
		for ( AppExceptionCodeEnum appExCode : AppExceptionCodeEnum.values() ) {
			if ( appExCode.getAppStatusCode().equals( appStatusCode ) ) {
				return appExCode;
			}
		}
		return null;
	}
}
