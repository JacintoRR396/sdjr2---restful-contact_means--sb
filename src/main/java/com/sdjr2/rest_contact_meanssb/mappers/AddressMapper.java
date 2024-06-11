package com.sdjr2.rest_contact_meanssb.mappers;

import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.repositories.entities.AddressEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * {@link AddressMapper} class.
 * <p>
 * <strong>Service</strong> - Represents a converter about Address DTO Request {@link AddressDTO} to Entity
 * {@link AddressEntity}.
 * <p>
 * It uses the classes : 01. Level Access -> the dto {@link AddressDTO} 02. Level Data -> the entity
 * {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 24/06/11
 * @since 23/06/11
 */
@Mapper(componentModel = "spring")
public abstract class AddressMapper {

	/**
	 * Map address request object to address entity.
	 *
	 * @param dto    address request object
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
	@Mapping(source = "dto.latitude", target = "latitude")
	@Mapping(source = "dto.longitude", target = "longitude")
	@Mapping(source = "dto.additionalInfo", target = "additionalInfo")
	public abstract AddressEntity toEntity ( AddressDTO dto, boolean isCreated );

	/**
	 * Map address request object to address entity with additional logic
	 *
	 * @param dto    address request object
	 * @param isCreated     indicate if is created or updated
	 * @param addressEntity address entity
	 * @return AddressEntity {@link AddressEntity}
	 */
	@AfterMapping
	protected AddressEntity afterMappingEntity ( AddressDTO dto, boolean isCreated,
																							 @MappingTarget AddressEntity addressEntity ) {
		if ( isCreated ) {
			addressEntity.setId( dto.getId() );
		}
		return addressEntity;
	}
}
