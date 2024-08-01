package com.sdjr2.rest_contact_meanssb.services.impl;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.auth.RoleTypeEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressFilterFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressSortFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.mappers.AddressMapper;
import com.sdjr2.rest_contact_meanssb.repositories.AddressJpaRepository;
import com.sdjr2.rest_contact_meanssb.repositories.filters.AddressSpecifications;
import com.sdjr2.rest_contact_meanssb.services.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link AddressServiceImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a class service that implements to {@link AddressService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/07/30
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
	@Transactional(readOnly = true)
	public Page<AddressDTO> getAllWithPagination ( Integer offset, Integer limit ) {
		Page<AddressEntity> pageEntities = this.addressRepo.findAll( PageRequest.of( offset, limit ) );

		return new PageImpl<>( this.addressMapper.toDTOs( pageEntities.getContent() ), pageEntities.getPageable(),
				pageEntities.getTotalElements() );
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AddressDTO> getAllWithSearch ( SearchBodyDTO searchBodyDTO ) {
		// Create a page request according to a search body dto (offset, limit and orders)
		PageRequest pageReq = this.createPageRequestWithPaginationAndSort( searchBodyDTO );

		// Create a specification according to a search body dto (filters)
		Specification<AddressEntity> specification = this.createSpecificationAboutFilters( searchBodyDTO );

		// Create a page with entities according to a search body dto (page request and specification)
		Page<AddressEntity> pageEntities;
		if ( Objects.nonNull( specification ) ) {
			pageEntities = this.addressRepo.findAll( specification, pageReq );
		} else {
			pageEntities = this.addressRepo.findAll( pageReq );
		}

		return new PageImpl<>( this.addressMapper.toDTOs( pageEntities.getContent() ), pageEntities.getPageable(),
				pageEntities.getTotalElements() );
	}

	/**
	 * Create a page request according to a search body dto.
	 *
	 * @param searchBodyDTO dto with search parameters about pagination, sort and filter.
	 * @return a page request {@link PageRequest} for pagination with possible ordering.
	 */
	private PageRequest createPageRequestWithPaginationAndSort ( SearchBodyDTO searchBodyDTO ) {
		if ( Objects.nonNull( searchBodyDTO.getSorts() ) && !searchBodyDTO.getSorts().isEmpty() ) {
			List<Sort.Order> orders = new ArrayList<>();
			searchBodyDTO.getSorts().forEach( sortDTO -> {
				try {
					AddressSortFieldEnum sortFieldEnum = AddressSortFieldEnum.fromValue( sortDTO.getField() );
					orders.add( new Sort.Order( sortDTO.getDirection(), sortFieldEnum.getFieldMySQL() ) );
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
	 * Create a specification with entities according to a search body dto.
	 *
	 * @param searchBodyDTO dto with search parameters about pagination, sort and filter.
	 * @return a specification {@link Page} about address {@link AddressEntity}.
	 */
	private Specification<AddressEntity> createSpecificationAboutFilters ( SearchBodyDTO searchBodyDTO ) {
		Specification<AddressEntity> specification = null;

		if ( Objects.nonNull( searchBodyDTO.getFilters() ) && !searchBodyDTO.getFilters().isEmpty() ) {
			AddressFilterFieldEnum.AddressFiltersRequest filtersRequest =
					AddressFilterFieldEnum.getFiltersReqFromSearchDTO( searchBodyDTO.getFilters() );
			specification = Specification
					.where( AddressSpecifications.hasValuesInt(
							AddressFilterFieldEnum.ID.getFieldMySQL(), filtersRequest.getOpIds(), filtersRequest.getIds() ) )
					.and( AddressSpecifications.hasValuesStr(
							AddressFilterFieldEnum.STREET.getFieldMySQL(), filtersRequest.getOpStreets(), filtersRequest.getStreets() ) )
					.and( AddressSpecifications.hasValuesStr(
							AddressFilterFieldEnum.TOWN.getFieldMySQL(), filtersRequest.getOpTowns(), filtersRequest.getTowns() ) )
					.and( AddressSpecifications.hasValuesStr(
							AddressFilterFieldEnum.CITY.getFieldMySQL(), filtersRequest.getOpCities(), filtersRequest.getCities() ) )
					.and( AddressSpecifications.hasValuesStr(
							AddressFilterFieldEnum.COUNTRY.getFieldMySQL(), filtersRequest.getOpCountries(), filtersRequest.getCountries() ) )
					.and( AddressSpecifications.hasValuesStr(
							AddressFilterFieldEnum.POSTAL_CODE.getFieldMySQL(), filtersRequest.getOpPostalCodes(), filtersRequest.getPostalCodes() ) );
		}

		return specification;
	}

	@Override
	@Transactional(readOnly = true)
	public AddressDTO getOneById ( Long id ) {
		AddressEntity entity = this.checkExistsById( id );

		return this.addressMapper.toDTO( entity );
	}

	/**
	 * Check if an entity exists by its id, otherwise throw an exception STATUS_40401.
	 *
	 * @param id element identifier.
	 * @return a database record {@link AddressEntity}.
	 */
	private AddressEntity checkExistsById ( Long id ) {
		try {
			return this.addressRepo.findById( id ).orElseThrow();
		} catch ( NoSuchElementException ex ) {
			throw new CustomException( ex, AppExceptionCodeEnum.STATUS_40401 );
		}
	}

	@Override
	@Transactional
	public AddressDTO create ( AddressDTO addressDTO ) {
		this.checkNotExistsByUniqueAttrs( addressDTO.getId(), addressDTO.getStreet(), addressDTO.getNumber().toString(),
				addressDTO.getLetter(), addressDTO.getPostalCode().toString() );

		AddressEntity entityReq = this.addressMapper.toEntity( addressDTO, RoleTypeEnum.ROLE_ADMIN.name(), null);
		AddressEntity entityDB = this.addressRepo.save( entityReq );

		return this.addressMapper.toDTO( entityDB );
	}

	/**
	 * Check if an entity not exists by its unique attributes, otherwise throw an exception STATUS_40010.
	 *
	 * @param id         element identifier.
	 * @param street     first element of the pk.
	 * @param number     second element of the pk.
	 * @param letter     third element of the pk.
	 * @param postalCode fourth element of the pk.
	 */
	private void checkNotExistsByUniqueAttrs ( Long id, String street, String number, String letter, String postalCode ) {
		this.addressRepo.findByStreetAndNumberAndLetterAndPostalCode( street, number, letter, postalCode )
				.ifPresent( entityDB -> {
					// 1º about create and 2º about update
					if ( id == 0L || !Objects.equals( id, entityDB.getId() ) ) {
						throw new CustomException( AppExceptionCodeEnum.STATUS_40010 );
					}
				} );
	}

	@Override
	@Transactional
	public AddressDTO update ( Long id, AddressDTO addressDTO ) {
		addressDTO.setId(id);

		this.checkNotExistsByUniqueAttrs( addressDTO.getId(), addressDTO.getStreet(), addressDTO.getNumber().toString(),
				addressDTO.getLetter(), addressDTO.getPostalCode().toString() );
		AddressEntity entityDB = this.checkExistsById( addressDTO.getId() );

		AddressEntity entityReq = this.addressMapper.toEntity( addressDTO, RoleTypeEnum.ROLE_MEMBER.name(), entityDB );
		entityDB = this.addressRepo.save( entityReq );

		return this.addressMapper.toDTO( entityDB );
	}

	@Override
	@Transactional
	public void delete ( Long id ) {
		AddressEntity entityDB = this.checkExistsById( id );

		this.addressRepo.delete( entityDB );
	}
}
