package com.sdjr2.rest_contact_meanssb.service;

import com.sdjr2.rest_contact_meanssb.mapper.AddressDtoReqMapper;
import com.sdjr2.rest_contact_meanssb.model.dto.AddressDtoReq;
import com.sdjr2.rest_contact_meanssb.model.entity.AddressEntity;
import com.sdjr2.rest_contact_meanssb.repository.IAddressRepository;
import com.sdjr2.rest_contact_meanssb.service.impl.AddressServiceImpl;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * {@link AddressServiceImpl} class.
 * <p>
 * Logic - Represents an interface service that manages business logic about Addresses, it uses the repository {@link IAddressRepository}
 * <p>
 * This Service maps the addresses of the request {@link AddressDtoReq} to database {@link AddressEntity} with {@link AddressDtoReqMapper}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @since 23/06/10
 * @upgrade 23/06/11
 */
public interface IAddressService {

    /**
     * Gets a list of addresses
     *
     * @return a list of {@link AddressEntity} object.
     */
    List<AddressEntity> getAddresses();

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
     * Gets an address entity for its pk
     *
     * @param id is the primary key.
     * @return an {@link AddressEntity} object.
     */
    AddressEntity getAddressById(Integer id);

    /**
     * Save an address entity
     *
     * @param addressDtoReq address request object
     * @return an {@link AddressEntity} object.
     */
    AddressEntity createAddress(AddressDtoReq addressDtoReq);

    /**
     * Update an address entity
     *
     * @param id            is the primary key.
     * @param addressDtoReq address request object
     * @return an {@link AddressEntity} object.
     */
    AddressEntity updateAddress(Integer id, AddressDtoReq addressDtoReq);

    /**
     * Delete an address entity for its pk
     *
     * @param id is the primary key.
     */
    void deleteAddress(Integer id);

}
