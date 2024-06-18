package com.sdjr2.rest_contact_meanssb.services;

import com.sdjr2.rest_contact_meanssb.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;
import com.sdjr2.rest_contact_meanssb.repositories.entities.AddressEntity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * {@link AddressService} interface.
 * <p>
 * <strong>Service</strong> - Represents an interface service that manages business logic about Addresses.
 * <p>
 * This Service maps the addresses of the request {@link AddressDTO} to database {@link AddressEntity} with
 * {@link AddressMapper}. It uses the classes : 01. Level Access -> the dto {@link AddressDTO} 02. Level Logic -> the
 * mapper {@link AddressMapper} 03. Level Data -> the repo {@link AddressJpaRepository} and the entity
 * {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/06/18
 * @since 23/06/10
 */
public interface AddressService {

  /**
   * Gets a list of addresses
   *
   * @return a list of {@link AddressDTO} object.
   */
  List<AddressDTO> getAddresses();

  /**
   * Gets a list of addresses with pagination
   *
   * @param pageNum  number of the page.
   * @param pageSize size of the page, as so, number of addresses.
   * @return a page of {@link AddressEntity} object.
   */
  Page<AddressEntity> getAddressesWithPagination(Integer pageNum, Integer pageSize);

  /**
   * Gets a list of addresses with order
   *
   * @param attribute attribute of the entity to order.
   * @param isAsc     if is Asc or Desc.
   * @return a page of {@link AddressEntity} object.
   */
  List<AddressEntity> getAddressesWithOrder(String attribute, boolean isAsc);

  /**
   * Gets a list of addresses with pagination and order
   *
   * @param pageNum   number of the page.
   * @param pageSize  size of the page, as so, number of addresses.
   * @param attribute attribute of the entity to order.
   * @param isAsc     if is Asc or Desc.
   * @return a page of {@link AddressEntity} object.
   */
  Page<AddressEntity> getAddressesWithPaginationAndOrder(Integer pageNum, Integer pageSize,
                                                         String attribute, boolean isAsc);

  /**
   * Gets an address for its identifier
   *
   * @param id is the primary key.
   * @return an {@link AddressDTO} object.
   */
  AddressDTO getAddressById(Integer id);

  /**
   * Save an address entity
   *
   * @param addressDTO address request object
   * @return an {@link AddressEntity} object.
   */
  AddressEntity createAddress(AddressDTO addressDTO);

  /**
   * Update an address entity
   *
   * @param id         is the primary key.
   * @param addressDTO address request object
   * @return an {@link AddressEntity} object.
   */
  AddressEntity updateAddress(AddressDTO addressDTO, Integer id);

  /**
   * Delete an address entity for its pk
   *
   * @param id is the primary key.
   */
  void deleteAddress(Integer id);
}
