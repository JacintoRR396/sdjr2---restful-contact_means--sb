package com.sdjr2.rest_contact_meanssb.services.auth;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.auth.RoleDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.RoleEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.auth.RoleTypeEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressFilterFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.search.AddressSortFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.mappers.auth.RoleMapper;
import com.sdjr2.rest_contact_meanssb.repositories.auth.RoleJpaRepository;
import com.sdjr2.rest_contact_meanssb.repositories.filters.AddressSpecifications;
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
 * {@link RoleServiceImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a class service that implements to {@link RoleService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/08/01
 * @since 24/08/01
 */
@Service
@RequiredArgsConstructor
@Primary
public class RoleServiceImpl implements RoleService {

	/**
	 * Address mapper object
	 */
	private final RoleMapper roleMapper;

	/**
	 * Address repository object
	 */
	private final RoleJpaRepository roleRepo;

	@Override
	@Transactional(readOnly = true)
	public List<RoleDTO> getAll() {
		return this.roleMapper.toDTOs( this.roleRepo.findAll() );
	}

	@Override
	@Transactional(readOnly = true)
	public Page<RoleDTO> getAllWithPagination ( Integer offset, Integer limit ) {
		Page<RoleEntity> pageEntities = this.roleRepo.findAll( PageRequest.of( offset, limit));

		return new PageImpl<>( this.roleMapper.toDTOs( pageEntities.getContent() ), pageEntities.getPageable(),
				pageEntities.getTotalElements() );
	}

	@Override
	@Transactional(readOnly = true)
	public Page<RoleDTO> getAllWithSearch ( SearchBodyDTO searchBodyDTO ) {
		// TODO
		// Create a page request according to a search body dto (offset, limit and orders)
		PageRequest pageReq = this.createPageRequestWithPaginationAndSort( searchBodyDTO );

		// Create a specification according to a search body dto (filters)
		Specification<AddressEntity> specification = this.createSpecificationAboutFilters( searchBodyDTO );

		// Create a page with entities according to a search body dto (page request and specification)
		/*
		Page<AddressEntity> pageEntities;
		if ( Objects.nonNull( specification ) ) {
			pageEntities = this.addressRepo.findAll( specification, pageReq );
		} else {
			pageEntities = this.addressRepo.findAll( pageReq );
		}

		return new PageImpl<>( this.addressMapper.toDTOs( pageEntities.getContent() ), pageEntities.getPageable(),
				pageEntities.getTotalElements() );
		 */
		return null;
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
	public RoleDTO getOneById ( Long id ) {
		RoleEntity entity = this.checkExistsById( id );

		return this.roleMapper.toDTO( entity );
	}

	/**
	 * Check if an entity exists by its id, otherwise throw an exception STATUS_40401.
	 *
	 * @param id element identifier.
	 * @return a database record {@link RoleEntity}.
	 */
	private RoleEntity checkExistsById ( Long id ) {
		try {
			return this.roleRepo.findById( id ).orElseThrow();
		} catch ( NoSuchElementException ex ) {
			throw new CustomException( ex, AppExceptionCodeEnum.STATUS_40401 );
		}
	}

	@Override
	@Transactional
	public RoleDTO create ( RoleDTO dto ) {
		this.checkNotExistsByUniqueAttrs( dto.getId(), dto.getName() );

		RoleEntity entityReq = this.roleMapper.toEntity( dto, RoleTypeEnum.ROLE_ADMIN.name(), null);
		RoleEntity entityDB = this.roleRepo.save( entityReq );

		return this.roleMapper.toDTO( entityDB );
	}

	/**
	 * Check if an entity not exists by its unique attributes, otherwise throw an exception STATUS_40010.
	 *
	 * @param id         element identifier.
	 * @param name     first element of the pk.
	 */
	private void checkNotExistsByUniqueAttrs ( Long id, String name ) {
		this.roleRepo.findByName( name )
				.ifPresent( entityDB -> {
					// 1º about create and 2º about update
					if ( id == 0L || !Objects.equals( id, entityDB.getId() ) ) {
						throw new CustomException( AppExceptionCodeEnum.STATUS_40010 );
					}
				} );
	}

	@Override
	@Transactional
	public RoleDTO update ( Long id, RoleDTO dto ) {
		dto.setId(id);

		RoleEntity entityDB = this.checkExistsById( dto.getId() );
		this.checkNotExistsByUniqueAttrs( dto.getId(), dto.getName() );

		RoleEntity entityReq = this.roleMapper.toEntity( dto, RoleTypeEnum.ROLE_MEMBER.name(), entityDB );
		entityDB = this.roleRepo.save( entityReq );

		return this.roleMapper.toDTO( entityDB );
	}

	@Override
	@Transactional
	public void delete ( Long id ) {
		RoleEntity entityDB = this.checkExistsById( id );

		this.roleRepo.delete( entityDB );
	}
}
