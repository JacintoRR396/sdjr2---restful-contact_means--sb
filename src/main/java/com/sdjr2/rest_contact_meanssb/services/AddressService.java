package com.sdjr2.rest_contact_meanssb.services;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;

/**
 * {@link AddressService} interface.
 * <p>
 * <strong>Service</strong> - Represents an interface service that manages business logic about Addresses, this
 * extends from {@link BaseService}.
 * <p>
 * This Service maps the addresses of the request {@link AddressDTO} to database {@link AddressEntity} with
 * {@link AddressMapper}.
 * <br>
 * It uses the classes : <br> 01. Level Access : the dto {@link AddressDTO} <br> 02. Level Logic : the mapper
 * {@link AddressMapper} <br> 03. Level Data : the repo {@link AddressJpaRepository} and the entity
 * {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/07/19
 * @since 23/06/10
 */
public interface AddressService extends BaseService<AddressDTO> {
}
