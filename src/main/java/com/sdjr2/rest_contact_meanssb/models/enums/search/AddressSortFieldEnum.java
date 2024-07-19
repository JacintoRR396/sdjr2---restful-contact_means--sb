package com.sdjr2.rest_contact_meanssb.models.enums.search;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import lombok.Getter;

/**
 * {@link AddressSortFieldEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the allowed address attributes used by the sorts in
 * advanced search.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/07/18
 * @since 24/07/18
 */
@Getter
public enum AddressSortFieldEnum {
	STREET( "street", AddressDTO.ATTR_STREET, AddressEntity.ATTR_STREET ),
	TOWN( "town", AddressDTO.ATTR_TOWN, AddressEntity.ATTR_TOWN ),
	CITY( "city", AddressDTO.ATTR_CITY, AddressEntity.ATTR_CITY ),
	COUNTRY( "country", AddressDTO.ATTR_COUNTRY, AddressEntity.ATTR_COUNTRY ),
	POSTAL_CODE( "postal_code", AddressDTO.ATTR_POSTAL_CODE, AddressEntity.ATTR_POSTAL_CODE );

	private final String value;
	private final String fieldFront;
	private final String fieldMySQL;

	AddressSortFieldEnum ( String value, String fieldFront, String fieldMySQL ) {
		this.value = value;
		this.fieldFront = fieldFront;
		this.fieldMySQL = fieldMySQL;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static AddressSortFieldEnum fromValue ( String value ) {
		for ( AddressSortFieldEnum type : AddressSortFieldEnum.values() ) {
			if ( type.getValue().equals( value ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static AddressSortFieldEnum fromFieldFront ( String fieldFront ) {
		for ( AddressSortFieldEnum type : AddressSortFieldEnum.values() ) {
			if ( type.getFieldFront().equals( fieldFront ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static AddressSortFieldEnum fromFieldMySQL ( String fieldMySQL ) {
		for ( AddressSortFieldEnum type : AddressSortFieldEnum.values() ) {
			if ( type.getFieldMySQL().equals( fieldMySQL ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}
}
