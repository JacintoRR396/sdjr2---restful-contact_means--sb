package com.sdjr2.rest_contact_meanssb.models.mappers.auth;

import com.sdjr2.rest_contact_meanssb.models.dto.auth.UserDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AuditableEntity;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.UserEntity;
import com.sdjr2.rest_contact_meanssb.models.mappers.BaseMapper;
import com.sdjr2.rest_contact_meanssb.utils.UDateTimeService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * {@link UserMapper} class.
 * <p>
 * <strong>Mapper</strong> - Represents a converter about User DTO and User Entity, implements to
 * {@link BaseMapper}.
 * <p>
 * It uses the classes : <br> 01. Level Access -> the dto {@link UserDTO} <br> 02. Level Data -> the entity
 * {@link UserEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 24/08/02
 * @since 24/08/01
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class UserMapper implements BaseMapper<UserDTO, UserEntity> {

	/**
	 * Role mapper object
	 */
	@Autowired
	protected RoleMapper roleMapper;

	/**
	 * DateTime service object
	 */
	@Autowired
	protected UDateTimeService uDateTimeService;

	/**
	 * Map user entity to user request object, it does not apply {@link AuditableEntity}.
	 *
	 * @param entity user entity
	 * @return UserDTO {@link UserDTO}
	 */
	@Mapping(source = "entity.id", target = "id")
	@Mapping(source = "entity.username", target = "username")
	@Mapping(target = "pwd", ignore = true)
	@Mapping(source = "entity.nickname", target = "nickname")
	@Mapping(source = "entity.email", target = "email")
	@Mapping(source = "entity.enabled", target = "isEnabled")
	@Mapping(target = "lastAccess", ignore = true)
	@Mapping(expression = "java(this.roleMapper.toDTOs(entity.getRoles()))", target = "roles")
	@Override
	public abstract UserDTO toDTO ( UserEntity entity );

	/**
	 * Map user entity to user request object with additional logic
	 *
	 * @param entity user entity
	 * @param dtoRes user dto about response
	 * @return UserDTO {@link UserDTO}
	 */
	@AfterMapping
	protected UserDTO afterMappingToDTO ( UserEntity entity, @MappingTarget UserDTO dtoRes ) {
		dtoRes.setPwd( "******" );
		dtoRes.setLastAccess( this.uDateTimeService.parseLocalDateTimeToStringAboutFrontend( entity.getLastAccess() ) );

		return dtoRes;
	}

	/**
	 * Map user request object to user entity.
	 *
	 * @param dto          user request object
	 * @param usernameRole role of the username
	 * @param entityDB     user entity in db
	 * @return UserEntity {@link UserEntity}
	 */
	@Mapping(source = "dto.id", target = "id")
	@Mapping(source = "dto.username", target = "username")
	@Mapping(target = "pwd", ignore = true)
	@Mapping(source = "dto.nickname", target = "nickname")
	@Mapping(source = "dto.email", target = "email")
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "lastAccess", ignore = true)
	@Mapping(expression = "java(this.roleMapper.toEntities(dto.getRoles()))", target = "roles")
	@Mapping(target = "auditableEntity", ignore = true)
	public abstract UserEntity toEntity ( UserDTO dto, String usernameRole, UserEntity entityDB );

	/**
	 * Map user request object to user entity with additional logic
	 *
	 * @param dto          user request object
	 * @param usernameRole role of the username
	 * @param entityDB     user entity in db
	 * @param entityReq    user entity about req
	 * @return UserEntity {@link UserEntity}
	 */
	@AfterMapping
	protected UserEntity afterMappingToEntity ( UserDTO dto, String usernameRole, UserEntity entityDB,
																							@MappingTarget UserEntity entityReq ) {
		// TODO ::
		//  pwd > with crypt
		//  lastAccess > with format backend
		entityReq.setEnabled( true );
		entityReq.setAuditableEntity( this.mapAuditableEntity( dto.getId(), usernameRole, entityDB ) );

		return entityReq;
	}
}
