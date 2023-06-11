package com.sdjr2.rest_contact_meanssb.mapper;

import com.sdjr2.rest_contact_meanssb.model.dto.AddressDtoReq;
import com.sdjr2.rest_contact_meanssb.model.entity.AddressEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * {@link AddressDtoReqMapper} class.
 * <p>
 * Mapper - Represents a converter about Address DTO Request {@link AddressDtoReq} to Entity {@link AddressEntity}.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Mapper
 * @upgrade 23/06/11
 * @since 23/06/11
 */
@Mapper(componentModel = "spring")
public abstract class AddressDtoReqMapper {

    /**
     * Map address request object to address entity.
     *
     * @param dtoReq    address request object
     * @param isCreated indicate if is created or updated
     * @return AddressEntity {@link AddressEntity}
     */
    @Mapping(source = "dtoReq.street", target = "street")
    @Mapping(source = "dtoReq.number", target = "number")
    @Mapping(source = "dtoReq.letter", target = "letter")
    @Mapping(source = "dtoReq.town", target = "town")
    @Mapping(source = "dtoReq.city", target = "city")
    @Mapping(source = "dtoReq.country", target = "country")
    @Mapping(source = "dtoReq.postalCode", target = "postalCode")
    @Mapping(source = "dtoReq.latitude", target = "latitude")
    @Mapping(source = "dtoReq.longitude", target = "longitude")
    @Mapping(source = "dtoReq.additionalInfo", target = "additionalInfo")
    public abstract AddressEntity toEntity(AddressDtoReq dtoReq, boolean isCreated);

    /**
     * Map address request object to address entity with additional logic
     *
     * @param dtoReq        address request object
     * @param isCreated     indicate if is created or updated
     * @param addressEntity address entity
     * @return AddressEntity {@link AddressEntity}
     */
    @AfterMapping
    protected AddressEntity afterMappingEntity(AddressDtoReq dtoReq, boolean isCreated,
                                               @MappingTarget AddressEntity addressEntity) {
        if (isCreated) {
            addressEntity.setId(dtoReq.getId());
        }
        return addressEntity;
    }

}
