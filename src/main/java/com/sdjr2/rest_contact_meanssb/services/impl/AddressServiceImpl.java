package com.sdjr2.rest_contact_meanssb.services.impl;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * {@link AddressServiceImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a class service that implements to {@link AddressService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/07/16
 * @since 23/06/10
 */
@Service
@RequiredArgsConstructor
@Primary
public class AddressServiceImpl implements AddressService {

	/**
	 * Address mapper object
	 */
	private final AddressMapper addressMapper;

	/**
	 * Address repository object
	 */
	private final AddressJpaRepository addressRepo;

	@Override
	@Transactional(readOnly = true)
	public List<AddressDTO> getAll () {
		return this.addressMapper.toDTOs( this.addressRepo.findAll() );
	}

	@Override
	public Page<AddressDTO> getAllWithPagination ( Integer pageNum, Integer pageSize ) {
		// TODO : refactor
		//return this.addressRepo.findAll( PageRequest.of( pageNum, pageSize ) );
		return null;
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

	public List<String> getTowns () {
		return this.addressRepo.findAllTowns();
	}

	@Override
	@Transactional(readOnly = true)
	public AddressDTO getOneById ( Integer id ) {
		AddressEntity entity = this.checkExistsAddress( id );

		return this.addressMapper.toDTO( entity );
	}

	@Override
	@Transactional
	public AddressDTO create ( AddressDTO addressDTO ) {
		this.checkExistsAddress( addressDTO.getStreet(), addressDTO.getNumber().toString(), addressDTO.getPostalCode().toString() );

		AddressEntity entityReq = this.addressMapper.toEntity( addressDTO, null, null );
		AddressEntity entityDB = this.addressRepo.save( entityReq );

		return this.addressMapper.toDTO( entityDB );
	}

	@Override
	@Transactional
	public AddressDTO update ( AddressDTO addressDTO, Integer id ) {
		AddressEntity entityDB = this.checkExistsAddress( addressDTO.getId() );

		// TODO :: from user in token
		AddressEntity entityReq = this.addressMapper.toEntity( addressDTO, entityDB, "SDJR2" );
		entityDB = this.addressRepo.save( entityReq );

		return this.addressMapper.toDTO( entityDB );
	}

	@Override
	@Transactional
	public void delete ( Integer id ) {
		AddressEntity entityDB = this.checkExistsAddress( id );
		this.addressRepo.delete( entityDB );
	}

	private void checkExistsAddress ( String street, String number, String postalCode ) {
		this.addressRepo.findByStreetAndNumberAndPostalCode( street, number, postalCode ).ifPresent( entityDB -> {
			throw new CustomException( AppExceptionCodeEnum.STATUS_40002 );
		} );
	}

	private AddressEntity checkExistsAddress ( final Integer id ) {
		try {
			return this.addressRepo.findById( id ).orElseThrow();
		} catch ( NoSuchElementException ex ) {
			throw new CustomException( ex, AppExceptionCodeEnum.STATUS_40401 );
		}
	}
}
