package com.sdjr2.rest_contact_meanssb.controllers;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import jakarta.servlet.http.HttpServletRequest;
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
 * @upgrade 24/07/19
 * @see HttpServletRequest 01. HttpServletRequest : provides request information for HTTP servlets.
 * @see BindingResult 02. BindingResult : check validations.
 * @see ResponseEntity 03. ResponseEntity : response with status and json.
 * @since 24/07/15
 */
public interface BaseController<T extends BaseDTO> {

	/**
	 * Handler method to perform a GET operation on a collection with elements dto.
	 *
	 * @param httpServletRequest http servlet request.
	 * @return a response {@link ResponseEntity} with a collection {@link List} of elements dto {@link BaseDTO}.
	 */
	ResponseEntity<List<T>> getAll ( HttpServletRequest httpServletRequest );

	/**
	 * Handler method to perform a GET operation on a collection with elements dto through pagination.
	 *
	 * @param httpServletRequest http servlet request.
	 * @param offset             index of the page to obtain.
	 * @param limit              limit of values to obtain.
	 * @return a response {@link ResponseEntity} with a page {@link Page} of elements dto {@link BaseDTO}.
	 */
	ResponseEntity<Page<T>> getAllWithPagination ( HttpServletRequest httpServletRequest, Integer offset,
																								 Integer limit );

	/**
	 * Handler method to perform a GET operation on a collection with elements dto through search (pagination, sort and
	 * filter).
	 *
	 * @param httpServletRequest http servlet request.
	 * @param searchBodyDTO      dto with search parameters about pagination, sort and filter.
	 * @param resValidation      binding result about validations.
	 * @return a response {@link ResponseEntity} with a page {@link Page} of elements dto {@link BaseDTO}.
	 */
	ResponseEntity<Page<T>> getAllWithSearch ( HttpServletRequest httpServletRequest, SearchBodyDTO searchBodyDTO,
																						 BindingResult resValidation );

	/**
	 * Handler method to perform a GET operation of an element dto.
	 *
	 * @param id element identifier.
	 * @return a response {@link ResponseEntity} with an element dto {@link BaseDTO}.
	 */
	ResponseEntity<T> getOneById ( Integer id );

	/**
	 * Handler method to perform a POST operation of an element dto.
	 *
	 * @param dto           request body with element dto.
	 * @param resValidation check validations of the element dto.
	 * @return a response {@link ResponseEntity} with an element dto {@link BaseDTO}.
	 */
	ResponseEntity<T> create ( T dto, BindingResult resValidation );

	/**
	 * Handler method to perform a PUT operation of an element dto.
	 *
	 * @param id            element identifier.
	 * @param dto           request body with element dto.
	 * @param resValidation check validations of the element dto.
	 * @return a response {@link ResponseEntity} with an element dto {@link BaseDTO}.
	 */
	ResponseEntity<T> update ( Integer id, T dto, BindingResult resValidation );

	/**
	 * Handler method to perform a DELETE operation of an element dto.
	 *
	 * @param id element identifier.
	 */
	ResponseEntity<Void> delete ( Integer id );

	/**
	 * Check if validation has errors throw an exception STATUS_40001 with errors.
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
