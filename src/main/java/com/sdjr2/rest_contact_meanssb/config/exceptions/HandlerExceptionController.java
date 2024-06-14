package com.sdjr2.rest_contact_meanssb.config.exceptions;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.mappers.RespEntityErrorMapper;
import com.sdjr2.rest_contact_meanssb.models.errors.RespEntityErrorDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.boot.context.properties.bind.BindException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * {@link HandlerExceptionController} class.
 * <p>
 * <strong>Config</strong> - Global configuration about handler exception.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Config
 * @upgrade 24/06/13
 * @since 24/06/14
 */
@RestControllerAdvice
@AllArgsConstructor
public class HandlerExceptionController {

  private final RespEntityErrorMapper respEntityErrorMapper;

  @ExceptionHandler({
    MissingServletRequestParameterException.class,
    MissingServletRequestPartException.class,
    ServletRequestBindingException.class,
    TypeMismatchException.class,
    HttpMessageNotReadableException.class,
    MethodArgumentNotValidException.class,
    BindException.class
  })
  public ResponseEntity<RespEntityErrorDTO> handleEx400(NoHandlerFoundException ex) {
    return this.createRespEntityError(AppExceptionCodeEnum.STATUS_40000, ex);
  }

  @ExceptionHandler({NoHandlerFoundException.class})
  public ResponseEntity<RespEntityErrorDTO> handleEx404(NoHandlerFoundException ex) {
    return this.createRespEntityError(AppExceptionCodeEnum.STATUS_40400, ex);
  }

  @ExceptionHandler({
    ArithmeticException.class,
    MissingPathVariableException.class,
    ConversionNotSupportedException.class,
    HttpMessageNotWritableException.class,
    Throwable.class
  })
  public ResponseEntity<RespEntityErrorDTO> handleEx500(Exception ex) {
    return this.createRespEntityError(AppExceptionCodeEnum.STATUS_50000, ex);
  }

  private ResponseEntity<RespEntityErrorDTO> createRespEntityError(AppExceptionCodeEnum appExCode, Exception ex) {
    CustomException customEx = new CustomException(appExCode, ex);
    RespEntityErrorDTO error = this.respEntityErrorMapper.toDTO(customEx);
    return new ResponseEntity<>(error, customEx.getAppExCode().getHttpStatusCode());
  }
}
