package com.sdjr2.rest_contact_meanssb.services.auth;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.auth.UserDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.SearchBodyDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.UserEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.auth.RoleTypeEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.auth.UserFilterFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.enums.auth.UserSortFieldEnum;
import com.sdjr2.rest_contact_meanssb.models.mappers.auth.UserMapper;
import com.sdjr2.rest_contact_meanssb.repositories.auth.UserJpaRepository;
import com.sdjr2.rest_contact_meanssb.repositories.auth.UserSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

/**
 * {@link UserServiceImpl} class.
 * <p>
 * <strong>Service</strong> - Represents a class service that implements to {@link RoleService}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Service
 * @upgrade 24/08/04
 * @since 24/08/02
 */
@Service
@RequiredArgsConstructor
@Primary
public class UserServiceImpl implements UserService {

	/**
	 * User mapper object
	 */
	private final UserMapper userMapper;

	/**
	 * User specifications object
	 */
	private final UserSpecifications userSpecs;

	/**
	 * User repository object
	 */
	private final UserJpaRepository userRepo;

	@Override
	@Transactional(readOnly = true)
	public List<UserDTO> getAll () {
		return this.userMapper.toDTOs( this.userRepo.findAll() );
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserDTO> getAllWithPagination ( Integer offset, Integer limit ) {
		Page<UserEntity> pageEntities = this.userRepo.findAll( PageRequest.of( offset, limit ) );

		return new PageImpl<>( this.userMapper.toDTOs( pageEntities.getContent() ), pageEntities.getPageable(),
				pageEntities.getTotalElements() );
	}

	@Override
	@Transactional(readOnly = true)
	public Page<UserDTO> getAllWithSearch ( SearchBodyDTO searchBodyDTO ) {
		// Create a page request according to a search body dto (offset, limit and orders)
		PageRequest pageReq = this.createPageRequestWithPaginationAndSort( searchBodyDTO );

		// Create a specification according to a search body dto (filters)
		Specification<UserEntity> specification = this.createSpecificationAboutFilters( searchBodyDTO );

		// Create a page with entities according to a search body dto (page request and specification)
		Page<UserEntity> pageEntities;
		if ( Objects.nonNull( specification ) ) {
			pageEntities = this.userRepo.findAll( specification, pageReq );
		} else {
			pageEntities = this.userRepo.findAll( pageReq );
		}

		return new PageImpl<>( this.userMapper.toDTOs( pageEntities.getContent() ), pageEntities.getPageable(),
				pageEntities.getTotalElements() );
	}

	@Override
	public Sort.Order createSortOrder ( String field, Sort.Direction direction ) {
		UserSortFieldEnum sortFieldEnum = UserSortFieldEnum.fromValue( field );
		return new Sort.Order( direction, sortFieldEnum.getFieldMySQL() );
	}

	/**
	 * Create a specification with entities according to a search body dto.
	 *
	 * @param searchBodyDTO dto with search parameters about pagination, sort and filter.
	 * @return a specification {@link Page} about users {@link UserEntity}.
	 */
	private Specification<UserEntity> createSpecificationAboutFilters ( SearchBodyDTO searchBodyDTO ) {
		Specification<UserEntity> specification = null;

		if ( Objects.nonNull( searchBodyDTO.getFilters() ) && !searchBodyDTO.getFilters().isEmpty() ) {
			UserFilterFieldEnum.UserFiltersRequest filtersRequest =
					UserFilterFieldEnum.getFiltersReqFromSearchDTO( searchBodyDTO.getFilters() );
			specification = Specification
					.where( this.userSpecs.hasValuesInt(
							UserFilterFieldEnum.ID.getFieldMySQL(), filtersRequest.getOpIds(), filtersRequest.getIds() ) )
					.and( this.userSpecs.hasValuesStr(
							UserFilterFieldEnum.USERNAME.getFieldMySQL(), filtersRequest.getOpUsernames(), filtersRequest.getUsernames() ) )
					.and( this.userSpecs.hasValuesStr(
							UserFilterFieldEnum.NICKNAME.getFieldMySQL(), filtersRequest.getOpNicknames(), filtersRequest.getNicknames() ) )
					.and( this.userSpecs.hasValuesStr(
							UserFilterFieldEnum.EMAIL.getFieldMySQL(), filtersRequest.getOpEmails(), filtersRequest.getEmails() ) )
					.and( this.userSpecs.hasValuesBool(
							UserFilterFieldEnum.IS_ENABLED.getFieldMySQL(), filtersRequest.getOpIsEnableds(), filtersRequest.getIsEnableds() ) )
					.and( this.userSpecs.hasValuesLocalDateTime(
							UserFilterFieldEnum.LAST_ACCESS.getFieldMySQL(), filtersRequest.getOpLastAccesses(), filtersRequest.getLastAccesses() ) );
		}

		return specification;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO getOneById ( Long id ) {
		UserEntity entityDB = this.checkExistsById( id );

		return this.userMapper.toDTO( entityDB );
	}

	/**
	 * Check if an entity exists by its id, otherwise throw an exception STATUS_40401.
	 *
	 * @param id element identifier.
	 * @return a database record {@link UserEntity}.
	 */
	private UserEntity checkExistsById ( Long id ) {
		try {
			return this.userRepo.findById( id ).orElseThrow();
		} catch ( NoSuchElementException ex ) {
			throw new CustomException( ex, AppExceptionCodeEnum.STATUS_40401 );
		}
	}

	@Override
	@Transactional(readOnly = true)
	public UserDTO getOneByUserName ( String username ) {
		Optional<UserEntity> entityDBOpt = this.userRepo.findByUsername( username );

		if ( entityDBOpt.isPresent() ) {
			return this.userMapper.toDTO( entityDBOpt.get() );
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_40402 );
	}

	@Override
	@Transactional
	public UserDTO create ( UserDTO dto ) {
		// Validation its id in the DTO through @UserExistsById
		this.checkNotExistsByUniqueAttrs( dto.getId(), dto.getUsername() );

		UserEntity entityReq = this.userMapper.toEntity( dto, RoleTypeEnum.ROLE_ADMIN.name(), null );
		UserEntity entityDB = this.userRepo.save( entityReq );

		return this.userMapper.toDTO( entityDB );
	}

	/**
	 * Check if an entity not exists by its unique attributes, otherwise throw an exception STATUS_40011.
	 *
	 * @param id   element identifier.
	 * @param name first element of the pk.
	 */
	private void checkNotExistsByUniqueAttrs ( Long id, String name ) {
		this.userRepo.findByUsername( name )
				.ifPresent( entityDB -> {
					// 1º about create and 2º about update
					if ( id == 0L || !Objects.equals( id, entityDB.getId() ) ) {
						throw new CustomException( AppExceptionCodeEnum.STATUS_40011 );
					}
				} );
	}

	@Override
	@Transactional
	public UserDTO update ( Long id, UserDTO dto ) {
		dto.setId( id );

		// Validation its id in the DTO through @UserExistsById
		UserEntity entityDB = this.checkExistsById( dto.getId() );
		this.checkNotExistsByUniqueAttrs( dto.getId(), dto.getUsername() );

		UserEntity entityReq = this.userMapper.toEntity( dto, RoleTypeEnum.ROLE_MEMBER.name(), entityDB );
		entityDB = this.userRepo.save( entityReq );

		return this.userMapper.toDTO( entityDB );
	}

	@Override
	@Transactional
	public void delete ( Long id ) {
		// Validation its id in the DTO through @UserExistsById
		UserEntity entityDB = this.checkExistsById( id );

		this.userRepo.delete( entityDB );
	}
}
