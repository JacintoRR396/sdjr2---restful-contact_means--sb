package com.sdjr2.rest_contact_meanssb.services.impl;

import com.sdjr2.rest_contact_meanssb.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.repositories.IAddressRepository;
import com.sdjr2.rest_contact_meanssb.repositories.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.services.IAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * {@link AddressServiceImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a class service that implements to {@link IAddressService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/06/13
 * @since 23/06/10
 */
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {

	/**
	 * Address mapper object
	 */
	private final AddressMapper addressMapper;

	/**
	 * Address repository object
	 */
	private final IAddressRepository addressRepo;

	@Override
	public List<AddressEntity> getAddresses () {
		return this.addressRepo.findAll();
	}

	@Override
	public Page<AddressEntity> getAddressesWithPagination ( Integer pageNum, Integer pageSize ) {
		return this.addressRepo.findAll( PageRequest.of( pageNum, pageSize ) );
	}

	@Override
	public List<AddressEntity> getAddressesWithOrder ( String attribute, boolean isAsc ) {
		final Sort.Direction typeOrder = ( isAsc ) ? Sort.Direction.ASC : Sort.Direction.DESC;
		return this.addressRepo.findAll( Sort.by( typeOrder, attribute ) );
	}

	@Override
	public Page<AddressEntity> getAddressesWithPaginationAndOrder ( Integer pageNum, Integer pageSize,
																																	String attribute, boolean isAsc ) {
		final Sort.Direction typeOrder = ( isAsc ) ? Sort.Direction.ASC : Sort.Direction.DESC;
		return this.addressRepo.findAll( PageRequest.of( pageNum, pageSize, typeOrder, attribute ) );
	}

	/**
	 * Gets a list of towns about addresses
	 *
	 * @return a list of {@link String} object.
	 */
	public List<String> getTowns () {
		return this.addressRepo.findAllTowns();
	}

	@Override
	public AddressEntity getAddressById ( Integer id ) {
		return this.checkExistsAddress( id );
	}

	@Override
	public AddressEntity createAddress ( AddressDTO addressDTO ) {
		AddressEntity entity = this.addressMapper.toEntity( addressDTO, true );
		try {
			this.checkExistsAddress( entity.getId() );
			throw new ResponseStatusException( HttpStatus.BAD_REQUEST,
							String.format( "Address with ID '%d' already exists", entity.getId() ) );
		} catch ( ResponseStatusException rse ) {
			return this.addressRepo.save( entity );
		}
	}

	@Override
	public AddressEntity updateAddress ( Integer id, AddressDTO addressDTO ) {
		AddressEntity entity = this.addressMapper.toEntity( addressDTO, false );
		this.checkExistsAddress( entity.getId() );
		return this.addressRepo.save( entity );
	}

	@Override
	public void deleteAddress ( Integer id ) {
		AddressEntity entityDB = this.checkExistsAddress( id );
		this.addressRepo.delete( entityDB );
	}

	/**
	 * Check if exists an address entity by its primary key
	 *
	 * @param id is the primary key.
	 */
	private AddressEntity checkExistsAddress ( final Integer id ) {
		return this.addressRepo.findById( id ).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND,
						String.format( "Address with ID '%d' not found", id ) ) );
	}
}
