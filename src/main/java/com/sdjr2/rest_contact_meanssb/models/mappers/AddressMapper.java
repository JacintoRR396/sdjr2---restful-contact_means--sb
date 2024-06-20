package com.sdjr2.rest_contact_meanssb.models.mappers;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import com.sdjr2.rest_contact_meanssb.models.entities.AuditableEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

/**
 * {@link AddressMapper} class.
 * <p>
 * <strong>Mapper</strong> - Represents a converter about Address DTO and Address Entity, implements to
 * {@link BaseMapper}.
 * <p>
 * It uses the classes : 01. Level Access -> the dto {@link AddressDTO} 02. Level Data -> the entity
 * {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 24/06/20
 * @since 23/06/11
 */
@Mapper(componentModel = "spring")
public abstract class AddressMapper implements BaseMapper<AddressDTO, AddressEntity> {

	/**
	 * Map address entity to address request object.
	 *
	 * @param entity address entity
	 * @return AddressDTO {@link AddressDTO}
	 */
	@Mapping(source = "entity.id", target = "id")
	@Mapping(source = "entity.street", target = "street")
	@Mapping(source = "entity.number", target = "number")
	@Mapping(source = "entity.letter", target = "letter")
	@Mapping(source = "entity.town", target = "town")
	@Mapping(source = "entity.city", target = "city")
	@Mapping(source = "entity.country", target = "country")
	@Mapping(source = "entity.postalCode", target = "postalCode")
	@Mapping(source = "entity.longitude", target = "longitude")
	@Mapping(source = "entity.latitude", target = "latitude")
	@Mapping(source = "entity.additionalInfo", target = "additionalInfo")
	@Override
	public abstract AddressDTO toDTO ( AddressEntity entity );

	/**
	 * Map address request object to address entity.
	 *
	 * @param dto       address request object
	 * @param isCreated indicate if is created or updated
	 * @return AddressEntity {@link AddressEntity}
	 */
	@Mapping(source = "dto.street", target = "street")
	@Mapping(source = "dto.number", target = "number")
	@Mapping(source = "dto.letter", target = "letter")
	@Mapping(source = "dto.town", target = "town")
	@Mapping(source = "dto.city", target = "city")
	@Mapping(source = "dto.country", target = "country")
	@Mapping(source = "dto.postalCode", target = "postalCode")
	@Mapping(source = "dto.longitude", target = "longitude")
	@Mapping(source = "dto.latitude", target = "latitude")
	@Mapping(source = "dto.additionalInfo", target = "additionalInfo")
	@Mapping(target = "auditableEntity", ignore = true)
	public abstract AddressEntity toEntity ( AddressDTO dto, boolean isCreated );

	/**
	 * Map address request object to address entity with additional logic
	 *
	 * @param dto           address request object
	 * @param isCreated     indicate if is created or updated
	 * @param addressEntity address entity
	 * @return AddressEntity {@link AddressEntity}
	 */
	@AfterMapping
	protected AddressEntity afterMappingToEntity ( AddressDTO dto, boolean isCreated,
																								 @MappingTarget AddressEntity addressEntity ) {
		if ( isCreated ) {
			addressEntity.setId( dto.getId() );
		}
		AuditableEntity auditableEntity = AuditableEntity.builder()
				.createdAt( LocalDateTime.now() )
				.createdBy( "admin" )
				.updatedAt( LocalDateTime.now() )
				.updatedBy( "admin" )
				.build();
		addressEntity.setAuditableEntity( auditableEntity );

		return addressEntity;
	}
}
