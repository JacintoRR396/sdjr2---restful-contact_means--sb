package com.sdjr2.rest_contact_meanssb.services;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.BaseEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.BaseMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@link BaseService} interface.
 * <p>
 * <strong>Service</strong> - Interface with the common service.
 * <p>
 * This Service maps the addresses of the request {@link BaseDTO} to database {@link BaseEntity} with
 * {@link BaseMapper}.
 * <br>
 * It uses the classes : <br> 01. Level Access : the dto {@link BaseDTO} <br> 02. Level Logic : the mapper
 * {@link BaseMapper} <br> 03. Level Data : the entity {@link BaseEntity}.
 *
 * @param <T> the parameter of the dto class, extends from {@link BaseDTO}
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/07/19
 * @since 23/06/10
 */
public interface BaseService<T extends BaseDTO> {

	/**
	 * Gets a collection with elements dto.
	 *
	 * @return a collection {@link List} of elements dto {@link T}.
	 */
	List<T> getAll ();

	/**
	 * Gets a collection with elements dto through pagination.
	 *
	 * @param offset index of the page to obtain.
	 * @param limit  limit of values to obtain.
	 * @return a page {@link Page} of elements dto {@link T}.
	 */
	Page<T> getAllWithPagination ( Integer offset, Integer limit );

	/**
	 * Gets a collection with elements dto through search (pagination, sort and filter).
	 *
	 * @param searchBodyDTO dto with search parameters about pagination, sort and filter.
	 * @return a page {@link Page} of elements dto {@link T}.
	 */
	Page<T> getAllWithSearch ( SearchBodyDTO searchBodyDTO );

	/**
	 * Create a page request according to a search body dto.
	 *
	 * @param searchBodyDTO dto with search parameters about pagination, sort and filter.
	 * @return a page request {@link PageRequest} for pagination with possible ordering.
	 */
	default PageRequest createPageRequestWithPaginationAndSort ( SearchBodyDTO searchBodyDTO ) {
		if ( Objects.nonNull( searchBodyDTO.getSorts() ) && !searchBodyDTO.getSorts().isEmpty() ) {
			List<Sort.Order> orders = new ArrayList<>();
			searchBodyDTO.getSorts().forEach( sortDTO -> {
				try {
					orders.add( this.createSortOrder( sortDTO.getField(), sortDTO.getDirection() ) );
				} catch ( CustomException ex ) {
					throw new CustomException( ex, AppExceptionCodeEnum.STATUS_40002 );
				}
			} );
			return PageRequest.of( searchBodyDTO.getOffset(), searchBodyDTO.getLimit(), Sort.by( orders ) );
		} else {
			return PageRequest.of( searchBodyDTO.getOffset(), searchBodyDTO.getLimit() );
		}
	}

	/**
	 * Create a sort order according to a field of the sort dto in the search body dto.
	 *
	 * @param field field in the database.
	 * @param direction direction about sort the field.
	 * @return a sort order {@link Sort.Order}.
	 */
	Sort.Order createSortOrder ( String field, Sort.Direction direction );

	/**
	 * Gets an element dto for its identifier.
	 *
	 * @param id element identifier.
	 * @return an element dto {@link T}.
	 */
	T getOneById ( Long id );

	/**
	 * Save an element dto.
	 *
	 * @param dto element request object.
	 * @return an element dto {@link T}.
	 */
	T create ( T dto );

	/**
	 * Update an element dto for its identifier.
	 *
	 * @param id  element identifier.
	 * @param dto element request object.
	 * @return an element dto {@link T}.
	 */
	T update ( Long id, T dto );

	/**
	 * Delete an element dto for its identifier.
	 *
	 * @param id element identifier.
	 */
	void delete ( Long id );
}
