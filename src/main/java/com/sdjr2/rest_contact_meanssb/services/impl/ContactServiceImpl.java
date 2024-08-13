package com.sdjr2.rest_contact_meanssb.services.impl;

import com.sdjr2.rest_contact_meanssb.models.dto.ContactDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.ContactEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.search.ContactFilterFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.search.ContactSortFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.mappers.ContactMapper;
import com.sdjr2.rest_contact_meanssb.repositories.ContactJpaRepository;
import com.sdjr2.rest_contact_meanssb.repositories.filters.ContactSpecifications;
import com.sdjr2.rest_contact_meanssb.services.ContactService;
import com.sdjr2.sb.library_commons.exceptions.AppExceptionCodeEnum;
import com.sdjr2.sb.library_commons.exceptions.CustomException;
import com.sdjr2.sb.library_commons.models.dto.search.SearchBodyDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * {@link ContactServiceImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a class service that implements to {@link ContactService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/08/13
 * @since 24/08/12
 */
@Service
@RequiredArgsConstructor
@Primary
public class ContactServiceImpl implements ContactService {

	/**
	 * Contact mapper object
	 */
	private final ContactMapper contactMapper;

	/**
	 * Contact specifications object
	 */
	private final ContactSpecifications contactSpecs;

	/**
	 * Contact repository object
	 */
	private final ContactJpaRepository contactRepo;

	@Override
	@Transactional(readOnly = true)
	public List<ContactDTO> getAll () {
		return this.contactMapper.toDTOs( this.contactRepo.findAll() );
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ContactDTO> getAllWithPagination ( Integer offset, Integer limit ) {
		Page<ContactEntity> pageEntities = this.contactRepo.findAll( PageRequest.of( offset, limit ) );

		return new PageImpl<>( this.contactMapper.toDTOs( pageEntities.getContent() ), pageEntities.getPageable(),
				pageEntities.getTotalElements() );
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ContactDTO> getAllWithSearch ( SearchBodyDTO searchBodyDTO ) {
		// Create a page request according to a search body dto (offset, limit and orders)
		PageRequest pageReq = this.createPageRequestWithPaginationAndSort( searchBodyDTO );

		// Create a specification according to a search body dto (filters)
		Specification<ContactEntity> specification = this.createSpecificationAboutFilters( searchBodyDTO );

		// Create a page with entities according to a search body dto (page request and specification)
		Page<ContactEntity> pageEntities;
		if ( Objects.nonNull( specification ) ) {
			pageEntities = this.contactRepo.findAll( specification, pageReq );
		} else {
			pageEntities = this.contactRepo.findAll( pageReq );
		}

		return new PageImpl<>( this.contactMapper.toDTOs( pageEntities.getContent() ), pageEntities.getPageable(),
				pageEntities.getTotalElements() );
	}

	@Override
	public Sort.Order createSortOrder ( String field, Sort.Direction direction ) {
		ContactSortFieldEnum sortFieldEnum = ContactSortFieldEnum.fromValue( field );
		return new Sort.Order( direction, sortFieldEnum.getFieldMySQL() );
	}

	/**
	 * Create a specification with entities according to a search body dto.
	 *
	 * @param searchBodyDTO dto with search parameters about pagination, sort and filter.
	 * @return a specification {@link Page} about contact {@link ContactEntity}.
	 */
	private Specification<ContactEntity> createSpecificationAboutFilters ( SearchBodyDTO searchBodyDTO ) {
		Specification<ContactEntity> specification = null;

		if ( Objects.nonNull( searchBodyDTO.getFilters() ) && !searchBodyDTO.getFilters().isEmpty() ) {
			ContactFilterFieldEnum.ContactFiltersRequest filtersRequest =
					ContactFilterFieldEnum.getFiltersReqFromSearchDTO( searchBodyDTO.getFilters() );
			specification = Specification
					.where( this.contactSpecs.hasValuesInt(
							ContactFilterFieldEnum.ID.getFieldFront(), filtersRequest.getOpIds(), filtersRequest.getIds() ) )
					.and( this.contactSpecs.hasValuesStr(
							ContactFilterFieldEnum.EMAIL.getFieldFront(), filtersRequest.getOpEmails(), filtersRequest.getEmails() ) )
					.and( this.contactSpecs.hasValuesStr(
							ContactFilterFieldEnum.PHONE_MOBILE.getFieldFront(), filtersRequest.getOpPhoneMobiles(), filtersRequest.getPhoneMobiles() ) );
		}

		return specification;
	}

	@Override
	@Transactional(readOnly = true)
	public ContactDTO getOneById ( Long id ) {
		ContactEntity entity = this.checkExistsById( id );

		return this.contactMapper.toDTO( entity );
	}

	/**
	 * Check if an entity exists by its id, otherwise throw an exception STATUS_40401.
	 *
	 * @param id element identifier.
	 * @return a database record {@link ContactEntity}.
	 */
	private ContactEntity checkExistsById ( Long id ) {
		try {
			return this.contactRepo.findById( id ).orElseThrow();
		} catch ( NoSuchElementException ex ) {
			throw new CustomException( ex, AppExceptionCodeEnum.STATUS_40401 );
		}
	}

	@Override
	@Transactional
	public ContactDTO create ( ContactDTO contactDTO ) {
		// Validation its id in the DTO through @ContactExistsById
		this.checkNotExistsByUniqueAttrs( contactDTO.getId(), contactDTO.getEmail() );

		String role = this.getRoleFromRequest( SecurityContextHolder.getContext() );
		ContactEntity entityReq = this.contactMapper.toEntity( contactDTO, role, null );
		ContactEntity entityDB = this.contactRepo.save( entityReq );

		return this.contactMapper.toDTO( entityDB );
	}

	/**
	 * Check if an entity not exists by its unique attributes, otherwise throw an exception STATUS_40011.
	 *
	 * @param id    element identifier.
	 * @param email first element of the pk.
	 */
	private void checkNotExistsByUniqueAttrs ( Long id, String email ) {
		this.contactRepo.findByEmail( email )
				.ifPresent( entityDB -> {
					// 1º about create and 2º about update
					if ( id == 0L || !Objects.equals( id, entityDB.getId() ) ) {
						throw new CustomException( AppExceptionCodeEnum.STATUS_40011 );
					}
				} );
	}

	@Override
	@Transactional
	public ContactDTO update ( Long id, ContactDTO contactDTO ) {
		contactDTO.setId( id );

		// Validation its id in the DTO through @ContactExistsById
		ContactEntity entityDB = this.checkExistsById( contactDTO.getId() );
		this.checkNotExistsByUniqueAttrs( contactDTO.getId(), contactDTO.getEmail() );

		String role = this.getRoleFromRequest( SecurityContextHolder.getContext() );
		ContactEntity entityReq = this.contactMapper.toEntity( contactDTO, role, entityDB );
		entityDB = this.contactRepo.save( entityReq );

		return this.contactMapper.toDTO( entityDB );
	}

	@Override
	@Transactional
	public void delete ( Long id ) {
		ContactEntity entityDB = this.checkExistsById( id );

		this.contactRepo.delete( entityDB );
	}
}
