package com.sdjr2.rest_contact_meanssb.controllers;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * {@link BaseController} interface.
 * <p>
 * <strong>Controller</strong> - Interface with the common controller.
 * <br>
 * It uses the classes : <br> 01. Level Access -> the dto {@link BaseDTO}
 *
 * @param <T> the parameter of the dto class, extends from {@link BaseDTO}
 * @author Jacinto R^2
 * @version 1.0
 * @category Controller
 * @upgrade 24/07/16
 * @see BindingResult Binding result about validations.
 * @since 24/07/15
 */
public interface BaseController<T extends BaseDTO> {

	ResponseEntity<List<T>> getAll ();

	ResponseEntity<Page<T>> getAllWithPagination ( Integer pageNum, Integer pageSize );

	ResponseEntity<T> getOneById ( Integer id );

	ResponseEntity<T> create ( T dto, BindingResult resValidation );

	ResponseEntity<T> update ( T dto, BindingResult resValidation, Integer id );

	ResponseEntity<Void> delete ( Integer id );

	/**
	 * Check if validation has errors
	 *
	 * @param resValidation set of ConstraintViolation object
	 */
	default void checkValidation ( BindingResult resValidation ) {
		if ( resValidation.hasFieldErrors() ) {
			Map<String, String> validationErrors = new HashMap<>();
			resValidation.getFieldErrors().forEach(
					error -> validationErrors.put( error.getField(), "The field " + error.getField() + " " + error.getDefaultMessage() ) );

			throw new CustomException( null, AppExceptionCodeEnum.STATUS_40001, validationErrors );
		}
	}
}
