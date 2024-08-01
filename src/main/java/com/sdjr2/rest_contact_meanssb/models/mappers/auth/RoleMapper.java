package com.sdjr2.rest_contact_meanssb.models.mappers.auth;

import com.sdjr2.rest_contact_meanssb.models.dto.auth.RoleDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AuditableEntity;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.RoleEntity;
import com.sdjr2.rest_contact_meanssb.models.enums.auth.RoleTypeEnum;
import com.sdjr2.rest_contact_meanssb.models.mappers.BaseMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

/**
 * {@link RoleMapper} class.
 * <p>
 * <strong>Mapper</strong> - Represents a converter about Role DTO and Role Entity, implements to
 * {@link BaseMapper}.
 * <p>
 * It uses the classes : <br> 01. Level Access -> the dto {@link RoleDTO} <br> 02. Level Data -> the entity
 * {@link RoleEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 24/08/01
 * @since 24/08/01
 */
@Mapper(componentModel = "spring", imports = { RoleTypeEnum.class })
public abstract class RoleMapper implements BaseMapper<RoleDTO, RoleEntity> {

	/**
	 * Map role entity to role request object, it does not apply {@link AuditableEntity}.
	 *
	 * @param entity role entity
	 * @return RoleDTO {@link RoleDTO}
	 */
	@Mapping(source = "entity.id", target = "id")
	@Mapping(source = "entity.name", target = "name")
	@Mapping(source = "entity.description", target = "description")
	@Override
	public abstract RoleDTO toDTO ( RoleEntity entity );

	/**
	 * Map role request object to role entity.
	 *
	 * @param dto      role request object
	 * @param usernameRole role of the username
	 * @param entityDB role entity in db
	 * @return RoleEntity {@link RoleEntity}
	 */
	@Mapping(source = "dto.id", target = "id")
	@Mapping(source = "dto.name", target = "name")
	@Mapping(source = "dto.description", target = "description")
	@Mapping(target = "auditableEntity", ignore = true)
	public abstract RoleEntity toEntity ( RoleDTO dto, String usernameRole, RoleEntity entityDB );

	/**
	 * Map role request object to role entity with additional logic
	 *
	 * @param dto      role request object
	 * @param usernameRole role of the username
	 * @param entityDB role entity in db
	 * @param entityReq role entity about req
	 * @return RoleEntity {@link RoleEntity}
	 */
	@AfterMapping
	protected RoleEntity afterMappingToEntity ( RoleDTO dto, String usernameRole, RoleEntity entityDB,
																								 @MappingTarget RoleEntity entityReq ) {
		AuditableEntity auditableEntity;

		if ( dto.getId().equals( 0L ) ) {
			auditableEntity = AuditableEntity.builder()
					.createdAt( LocalDateTime.now() )
					.createdBy( usernameRole )
					.updatedAt( LocalDateTime.now() )
					.updatedBy( usernameRole )
					.build();
		} else {
			auditableEntity = entityDB.getAuditableEntity();
			auditableEntity.setUpdatedAt( LocalDateTime.now() );
			auditableEntity.setUpdatedBy( usernameRole );
		}
		entityReq.setAuditableEntity( auditableEntity );

		return entityReq;
	}
}
