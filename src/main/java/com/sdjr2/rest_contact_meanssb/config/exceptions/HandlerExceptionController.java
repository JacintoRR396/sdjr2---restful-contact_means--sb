package com.sdjr2.rest_contact_meanssb.config.exceptions;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.mappers.RespEntityErrorMapper;
import com.sdjr2.rest_contact_meanssb.models.errors.RespEntityErrorDTO;
import com.sdjr2.rest_contact_meanssb.utils.UConstants;
import com.sdjr2.rest_contact_meanssb.utils.UDateTimeService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Map;
import java.util.Objects;

/**
 * {@link HandlerExceptionController} class.
 * <p>
 * <strong>Config</strong> - Global configuration about handler exception.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/06/18
 * @since 24/06/14
 */
@RestControllerAdvice
@AllArgsConstructor
public class HandlerExceptionController {

	private static final Logger LOGGER = LoggerFactory.getLogger( HandlerExceptionController.class );

	private final UDateTimeService uDateTimeService;
	private final RespEntityErrorMapper respEntityErrorMapper;

	@ExceptionHandler({
			CustomException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleExCustom ( CustomException ex ) {
		return this.createRespEntityError( ex, null, null );
	}

	@ExceptionHandler({
			MissingServletRequestParameterException.class,
			MissingServletRequestPartException.class,
			ServletRequestBindingException.class,
			TypeMismatchException.class,
			HttpMessageNotReadableException.class,
			MethodArgumentNotValidException.class,
			// HandlerMethodValidationException
			BindException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx400 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40000, null );
	}

	@ExceptionHandler({
			NoHandlerFoundException.class,
			NoResourceFoundException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx404 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40400, null );
	}

	@ExceptionHandler({
			HttpRequestMethodNotSupportedException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx405 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40500, null );
	}

	@ExceptionHandler({
			HttpMediaTypeNotAcceptableException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx406 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_40600, null );
	}

	@ExceptionHandler({
			HttpMediaTypeNotSupportedException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx415 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_41500, null );
	}

	@ExceptionHandler({
			ArithmeticException.class,
			MissingPathVariableException.class,
			ConversionNotSupportedException.class,
			HttpMessageNotWritableException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx500 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_50000, null );
	}

	@ExceptionHandler({
			AsyncRequestTimeoutException.class
	})
	public ResponseEntity<RespEntityErrorDTO> handleEx503 ( Exception ex ) {
		return this.createRespEntityError( ex, AppExceptionCodeEnum.STATUS_50300, null );
	}

	private ResponseEntity<RespEntityErrorDTO> createRespEntityError ( Exception ex, AppExceptionCodeEnum appExCode,
																																		 Map<String, String> validationErrors ) {
		CustomException customEx = Objects.nonNull( appExCode )
				? new CustomException( ex, appExCode, validationErrors ) : ( CustomException ) ex;
		RespEntityErrorDTO error = this.respEntityErrorMapper.toDTO( customEx, this.uDateTimeService.getTimestamp() );
		LOGGER.error( UConstants.MSG_BASE_ERROR + error.getErrorCode() + " : " + error.getExMessage() );

		return new ResponseEntity<>( error, customEx.getAppExCode().getHttpStatusCode() );
	}
}
