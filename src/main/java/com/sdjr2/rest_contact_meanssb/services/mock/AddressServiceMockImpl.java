package com.sdjr2.rest_contact_meanssb.services.mock;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.repositories.AddressRepository;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link AddressServiceMockImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a class service mock that implements to {@link AddressService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/07/17
 * @since 23/06/10
 */
@Service
@RequiredArgsConstructor
public class AddressServiceMockImpl implements AddressService {

	/**
	 * Address dto request mapper object
	 */
	private final AddressMapper addressMapper;

	/**
	 * Address repository object
	 */
	private final AddressRepository<AddressEntity, Integer> addressRepo;

	@Override
	public List<AddressDTO> getAll () {
		return this.addressRepo.findAll().stream().map( this.addressMapper::toDTO ).toList();
	}

	@Override
	public Page<AddressDTO> getAllWithPagination ( Integer offset, Integer limit ) {
		return Page.empty();
	}

	@Override
	public Page<AddressDTO> getAllWithSearch ( SearchBodyDTO searchBodyDTO ) {
		return Page.empty();
	}

	/**
	 * Gets a list of towns about addresses
	 *
	 * @return a list of {@link String} object.
	 */
	public List<String> getTowns () {
		return new ArrayList<>();
	}

	@Override
	public AddressDTO getOneById ( Integer id ) {
		AddressEntity entity = this.checkExistsAddress( id );
		return this.addressMapper.toDTO( entity );
	}

	@Override
	public AddressDTO create ( AddressDTO addressDTO ) {
		AddressEntity entityReq = this.addressMapper.toEntity( addressDTO );
		AddressEntity entityDB = this.addressRepo.save( entityReq );

		return this.addressMapper.toDTO( entityDB );
	}

	@Override
	public AddressDTO update ( Integer id, AddressDTO addressDTO ) {
		return null;
	}

	@Override
	public void delete ( Integer id ) {

	}

	private AddressEntity checkExistsAddress ( Integer id ) {
		return this.addressRepo.findById( id ).orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND,
				String.format( "Address with ID '%d' not found", id ) ) );
	}
}
