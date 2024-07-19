package com.sdjr2.rest_contact_meanssb.services;

import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.BaseEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.BaseMapper;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * {@link BaseService} interface.
 * <p>
 * <strong>Service</strong> - Interface with the common service.
 * <p>
 * This Service maps the addresses of the request {@link BaseDTO} to database {@link BaseEntity} with
 * {@link BaseMapper}.
 * <br>
 * It uses the classes : <br> 01. Level Access -> the dto {@link BaseDTO} <br> 02. Level Logic -> the mapper
 * {@link BaseMapper} <br> 03. Level Data -> the entity {@link BaseEntity}.
 *
 * @param <T> the parameter of the dto class, extends from {@link BaseDTO}
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/07/17
 * @since 23/06/10
 */
public interface BaseService<T extends BaseDTO> {

	/**
	 * Gets a collection with elements dto.
	 *
	 * @return a collection {@link List} of elements {@link T}.
	 */
	List<T> getAll ();

	/**
	 * Gets a collection with elements dto through pagination.
	 *
	 * @param offset index of the page to obtain.
	 * @param limit  limit of values to obtain.
	 * @return a page {@link Page} of elements {@link T}.
	 */
	Page<T> getAllWithPagination ( Integer offset, Integer limit );

	/**
	 * Gets a collection with elements dto through search.
	 *
	 * @param searchBodyDTO request body with the config to search.
	 * @return a page {@link Page} of elements {@link T}.
	 */
	Page<T> getAllWithSearch ( SearchBodyDTO searchBodyDTO );

	/**
	 * Gets an element for its identifier
	 *
	 * @param id is the primary key.
	 * @return an {@link T} object.
	 */
	T getOneById ( Integer id );

	/**
	 * Save an element entity
	 *
	 * @param dto element request object.
	 * @return an {@link T} object created in db and mapped to response.
	 */
	T create ( T dto );

	/**
	 * Update an element entity
	 *
	 * @param id  is the primary key.
	 * @param dto element request object.
	 * @return an {@link T} object updated in db and mapped to response.
	 */
	T update ( T dto, Integer id );

	/**
	 * Delete an element entity for its pk
	 *
	 * @param id is the primary key.
	 */
	void delete ( Integer id );
}
