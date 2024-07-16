package com.sdjr2.rest_contact_meanssb.services;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * {@link AddressService} interface.
 * <p>
 * <strong>Service</strong> - Represents an interface service that manages business logic about Addresses, this
 * extends from {@link BaseService}.
 * <p>
 * This Service maps the addresses of the request {@link AddressDTO} to database {@link AddressEntity} with
 * {@link AddressMapper}.
 * <br>
 * It uses the classes : <br> 01. Level Access -> the dto {@link AddressDTO} <br> 02. Level Logic -> the mapper
 * {@link AddressMapper} <br> 03. Level Data -> the repo {@link AddressJpaRepository} and the entity
 * {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/07/16
 * @since 23/06/10
 */
public interface AddressService extends BaseService<AddressDTO> {

	/**
	 * Gets a list of addresses with order
	 *
	 * @param attribute attribute of the entity to order.
	 * @param isAsc     if is Asc or Desc.
	 * @return a page of {@link AddressEntity} object.
	 */
	List<AddressEntity> getAddressesWithOrder ( String attribute, boolean isAsc );

	/**
	 * Gets a list of addresses with pagination and order
	 *
	 * @param pageNum   number of the page.
	 * @param pageSize  size of the page, as so, number of addresses.
	 * @param attribute attribute of the entity to order.
	 * @param isAsc     if is Asc or Desc.
	 * @return a page of {@link AddressEntity} object.
	 */
	Page<AddressEntity> getAddressesWithPaginationAndOrder ( Integer pageNum, Integer pageSize,
																													 String attribute, boolean isAsc );
}
