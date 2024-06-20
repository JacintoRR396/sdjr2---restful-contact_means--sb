package com.sdjr2.rest_contact_meanssb.models.mappers;

import com.sdjr2.rest_contact_meanssb.models.dto.BaseDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.BaseEntity;

import java.util.List;

/**
 * {@link BaseMapper} class.
 * <p>
 * <strong>Mapper</strong> - Interface with the common mappers contract.
 * <p>
 * It uses the classes : 01. Level Access -> the dto {@link BaseDTO} 02. Level Data -> the entity {@link BaseEntity}.
 *
 * @param <D> the parameter of the dto class
 * @param <E> the parameter of the entity class
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 24/06/20
 * @since 23/06/20
 */
public interface BaseMapper<D extends BaseDTO, E extends BaseEntity> {

	/**
	 * Map entity to dto
	 *
	 * @param entity entity object
	 * @return {@link D} dto object.
	 */
	D toDTO ( E entity );

	/**
	 * Map dto to entity
	 *
	 * @param dto dto object
	 * @return {@link E} entity object.
	 */
	E toEntity ( D dto );

	/**
	 * Map entities to dtos
	 *
	 * @param entities entities object list
	 * @return {@link List<D>} dtos object list.
	 */
	List<D> toDTOs ( List<E> entities );

	/**
	 * Map dtos to entities
	 *
	 * @param dtos dtos object list
	 * @return {@link List<E>} entities object list.
	 */
	List<E> toEntities ( List<D> dtos );
}
