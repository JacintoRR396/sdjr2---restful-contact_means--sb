package com.sdjr2.rest_contact_meanssb.models.enums.search;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import com.sdjr2.rest_contact_meanssb.models.dto.AddressDTO;
import com.sdjr2.rest_contact_meanssb.models.dto.search.FilterDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.AddressEntity;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * {@link AddressFilterFieldEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the allowed address attributes used by the filters in
 * advanced search.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/07/21
 * @since 24/07/18
 */
@Getter
public enum AddressFilterFieldEnum {
	ID( "ID", AddressDTO.ATTR_ID, AddressEntity.ATTR_ID ),
	STREET( "STREET", AddressDTO.ATTR_STREET, AddressEntity.ATTR_STREET ),
	TOWN( "TOWN", AddressDTO.ATTR_TOWN, AddressEntity.ATTR_TOWN ),
	CITY( "CITY", AddressDTO.ATTR_CITY, AddressEntity.ATTR_CITY ),
	COUNTRY( "COUNTRY", AddressDTO.ATTR_COUNTRY, AddressEntity.ATTR_COUNTRY ),
	POSTAL_CODE( "POSTAL_CODE", AddressDTO.ATTR_POSTAL_CODE, AddressEntity.ATTR_POSTAL_CODE );

	private final String value;
	private final String fieldFront;
	private final String fieldMySQL;

	AddressFilterFieldEnum ( String value, String fieldFront, String fieldMySQL ) {
		this.value = value;
		this.fieldFront = fieldFront;
		this.fieldMySQL = fieldMySQL;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static AddressFilterFieldEnum fromValue ( String value ) {
		for ( AddressFilterFieldEnum type : AddressFilterFieldEnum.values() ) {
			if ( type.getValue().equals( value ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static AddressFilterFieldEnum fromFieldFront ( String fieldFront ) {
		for ( AddressFilterFieldEnum type : AddressFilterFieldEnum.values() ) {
			if ( type.getFieldFront().equals( fieldFront ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static AddressFilterFieldEnum fromFieldMySQL ( String fieldMySQL ) {
		for ( AddressFilterFieldEnum type : AddressFilterFieldEnum.values() ) {
			if ( type.getFieldMySQL().equals( fieldMySQL ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static AddressFiltersRequest getFiltersReqFromSearchDTO ( List<FilterDTO> filtersDTO ) {
		AddressFiltersRequest.AddressFiltersRequestBuilder builder = AddressFiltersRequest.builder();
		for ( FilterDTO filterDTO : filtersDTO ) {
			switch ( AddressFilterFieldEnum.fromValue( filterDTO.getField() ) ) {
				case ID -> {
					builder.ids( filterDTO.getValues().stream().map( Integer::valueOf ).toList() );
					builder.opIds( filterDTO.getOperatorType() );
				}
				case STREET -> {
					builder.streets( filterDTO.getValues() );
					builder.opStreets( filterDTO.getOperatorType() );
				}
				case TOWN -> {
					builder.towns( filterDTO.getValues() );
					builder.opTowns( filterDTO.getOperatorType() );
				}
				case CITY -> {
					builder.cities( filterDTO.getValues() );
					builder.opCities( filterDTO.getOperatorType() );
				}
				case COUNTRY -> {
					builder.countries( filterDTO.getValues() );
					builder.opCountries( filterDTO.getOperatorType() );
				}
				case POSTAL_CODE -> {
					builder.postalCodes( filterDTO.getValues() );
					builder.opPostalCodes( filterDTO.getOperatorType() );
				}
				default -> throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
			}
		}

		return builder.build();
	}

	@Data
	@Builder
	public static class AddressFiltersRequest {
		private List<Integer> ids;
		private OperatorFilterEnum opIds;
		private List<String> streets;
		private OperatorFilterEnum opStreets;
		private List<String> towns;
		private OperatorFilterEnum opTowns;
		private List<String> cities;
		private OperatorFilterEnum opCities;
		private List<String> countries;
		private OperatorFilterEnum opCountries;
		private List<String> postalCodes;
		private OperatorFilterEnum opPostalCodes;
	}
}
