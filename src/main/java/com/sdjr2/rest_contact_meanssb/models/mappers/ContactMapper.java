package com.sdjr2.rest_contact_meanssb.models.mappers;

import com.sdjr2.rest_contact_meanssb.models.dto.ContactDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.ContactEntity;
import com.sdjr2.sb.library_commons.models.entities.AuditableEntity;
import com.sdjr2.sb.library_commons.models.mappers.BaseMapper;
import org.mapstruct.*;

/**
 * {@link ContactMapper} class.
 * <p>
 * <strong>Mapper</strong> - Represents a converter about Contact DTO and Contact Entity, implements to
 * {@link BaseMapper}.
 * <p>
 * It uses the classes : <br> 01. Level Access -> the dto {@link ContactDTO} <br> 02. Level Data -> the entity
 * {@link ContactEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 24/08/12
 * @since 24/08/12
 */
@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public abstract class ContactMapper implements BaseMapper<ContactDTO, ContactEntity> {

	/**
	 * Map contact entity to contact request object, it does not apply {@link AuditableEntity}.
	 *
	 * @param entity contact entity
	 * @return ContactDTO {@link ContactDTO}
	 */
	@Mapping(source = "entity.id", target = "id")
	@Mapping(source = "entity.email", target = "email")
	@Mapping(source = "entity.phoneMobile", target = "phoneMobile")
	@Mapping(source = "entity.phoneHome", target = "phoneHome")
	@Override
	public abstract ContactDTO toDTO ( ContactEntity entity );

	/**
	 * Map contact request object to contact entity.
	 *
	 * @param dto          contact request object
	 * @param usernameRole role of the username
	 * @param entityDB     contact entity in db
	 * @return ContactEntity {@link ContactEntity}
	 */
	@Mapping(source = "dto.id", target = "id")
	@Mapping(source = "dto.email", target = "email")
	@Mapping(source = "dto.phoneMobile", target = "phoneMobile")
	@Mapping(source = "dto.phoneHome", target = "phoneHome")
	@Mapping(target = "auditableEntity", ignore = true)
	public abstract ContactEntity toEntity ( ContactDTO dto, String usernameRole, ContactEntity entityDB );

	/**
	 * Map contact request object to contact entity with additional logic
	 *
	 * @param dto          contact request object
	 * @param usernameRole role of the username
	 * @param entityDB     contact entity in db
	 * @param entityReq    contact entity about req
	 * @return ContactEntity {@link ContactEntity}
	 */
	@AfterMapping
	protected ContactEntity afterMappingToEntity ( ContactDTO dto, String usernameRole, ContactEntity entityDB,
																								 @MappingTarget ContactEntity entityReq ) {
		entityReq.setAuditableEntity( this.mapAuditableEntity( dto.getId(), usernameRole, entityDB ) );

		return entityReq;
	}
}
