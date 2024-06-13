package com.sdjr2.rest_contact_meanssb.services.mock;

import com.sdjr2.rest_contact_meanssb.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.repositories.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.services.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * {@link MAddressServiceImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a service mock that implements to {@link IAddressService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/06/11
 * @since 23/06/10
 */
//@Service
public class MAddressServiceImpl implements IAddressService {

	private static final String TOWN = "El Viso del Alcor";
	private static final String CITY = "Sevilla";
	private static final String COUNTRY = "España";

	/**
	 * Address dto request mapper object
	 */
	private final AddressMapper addressMapper;

	/**
	 * Address repository object
	 */
	private final List<AddressEntity> addressRepo;

	@Autowired
	public MAddressServiceImpl ( AddressMapper addressMapper, List<AddressEntity> addressRepo ) {
		this.addressMapper = addressMapper;
		this.addressRepo = addressRepo;
		this.initData();
	}

	private void initData () {
		this.addressRepo.add( new AddressEntity( 1, "Corredera", "155", "A", TOWN, CITY, COUNTRY, "41520", -5.7199300, 37.391060, "Vivo actualmente en el Piso" ) );
		this.addressRepo.add( new AddressEntity( 2, "Velázquez", "17", null, TOWN, CITY, COUNTRY, "41520", -5.7199300, 37.391060, "Bajo" ) );
		this.addressRepo.add( new AddressEntity( 3, "Avda Republica Nicaragua", "1", null, TOWN, CITY, COUNTRY, "41520", 37.391060, -5.7199300, "Piso" ) );
	}

	@Override
	public List<AddressEntity> getAddresses () {
		return this.addressRepo;
	}

	@Override
	public Page<AddressEntity> getAddressesWithPagination ( Integer pageNum, Integer pageSize ) {
		return Page.empty();
	}

	@Override
	public List<AddressEntity> getAddressesWithOrder ( String attribute, boolean isAsc ) {
		return this.addressRepo;
	}

	@Override
	public Page<AddressEntity> getAddressesWithPaginationAndOrder ( Integer pageNum, Integer pageSize,
																																	String attribute, boolean isAsc ) {
		return Page.empty();
	}

	/**
	 * Gets a list of towns about addresses
	 *
	 * @return a list of {@link String} object.
	 */
	public List<String> getTowns () {
		List<String> listTowns = new ArrayList<>();
		this.addressRepo.forEach( a -> listTowns.add( a.getTown() ) );
		return listTowns;
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
			this.addressRepo.add( entity );
			return entity;
		}
	}

	@Override
	public AddressEntity updateAddress ( Integer id, AddressDTO addressDTO ) {
		AddressEntity entity = this.addressMapper.toEntity( addressDTO, false );
		this.checkExistsAddress( entity.getId() );
		this.addressRepo.add( id - 1, entity );
		return entity;
	}

	@Override
	public void deleteAddress ( Integer id ) {
		AddressEntity entityDB = this.checkExistsAddress( id );
		this.addressRepo.remove( entityDB );
	}

	/**
	 * Check if exists an address entity by its primary key
	 *
	 * @param id is the primary key.
	 */
	private AddressEntity checkExistsAddress ( final Integer id ) {
		AddressEntity entity = this.addressRepo.get( id );
		if ( Objects.isNull( entity ) ) {
			throw new ResponseStatusException( HttpStatus.NOT_FOUND, String.format( "Address with ID '%d' not found", id ) );
		}
		return entity;
	}
}
