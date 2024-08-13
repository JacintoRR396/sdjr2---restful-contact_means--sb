package com.sdjr2.rest_contact_meanssb.models.enums.search;

import com.sdjr2.rest_contact_meanssb.models.dto.ContactDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.ContactEntity;
import com.sdjr2.sb.library_commons.exceptions.AppExceptionCodeEnum;
import com.sdjr2.sb.library_commons.exceptions.CustomException;
import com.sdjr2.sb.library_commons.models.dto.search.FilterDTO;
import com.sdjr2.sb.library_commons.models.enums.OperatorFilterEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.List;

/**
 * {@link ContactFilterFieldEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the allowed contact attributes used by the filters in
 * advanced search.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/08/12
 * @since 24/08/12
 */
@Getter
public enum ContactFilterFieldEnum {
	ID( "ID", ContactDTO.ATTR_ID, ContactEntity.ATTR_ID ),
	EMAIL( "EMAIL", ContactDTO.ATTR_EMAIL, ContactEntity.ATTR_EMAIL ),
	PHONE_MOBILE( "PHONE_MOBILE", ContactDTO.ATTR_PHONE_MOBILE, ContactEntity.ATTR_PHONE_MOBILE );

	private final String value;
	private final String fieldFront;
	private final String fieldMySQL;

	ContactFilterFieldEnum ( String value, String fieldFront, String fieldMySQL ) {
		this.value = value;
		this.fieldFront = fieldFront;
		this.fieldMySQL = fieldMySQL;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static ContactFilterFieldEnum fromValue ( String value ) {
		for ( ContactFilterFieldEnum type : ContactFilterFieldEnum.values() ) {
			if ( type.getValue().equals( value ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static ContactFilterFieldEnum fromFieldFront ( String fieldFront ) {
		for ( ContactFilterFieldEnum type : ContactFilterFieldEnum.values() ) {
			if ( type.getFieldFront().equals( fieldFront ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static ContactFilterFieldEnum fromFieldMySQL ( String fieldMySQL ) {
		for ( ContactFilterFieldEnum type : ContactFilterFieldEnum.values() ) {
			if ( type.getFieldMySQL().equals( fieldMySQL ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static ContactFiltersRequest getFiltersReqFromSearchDTO ( List<FilterDTO> filtersDTO ) {
		ContactFiltersRequest.ContactFiltersRequestBuilder builder = ContactFiltersRequest.builder();
		for ( FilterDTO filterDTO : filtersDTO ) {
			switch ( ContactFilterFieldEnum.fromValue( filterDTO.getField() ) ) {
				case ID -> {
					builder.ids( filterDTO.getValues().stream().map( Integer::valueOf ).toList() );
					builder.opIds( filterDTO.getOperatorType() );
				}
				case EMAIL -> {
					builder.emails( filterDTO.getValues() );
					builder.opEmails( filterDTO.getOperatorType() );
				}
				case PHONE_MOBILE -> {
					builder.phoneMobiles( filterDTO.getValues() );
					builder.opPhoneMobiles( filterDTO.getOperatorType() );
				}
				default -> throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
			}
		}

		return builder.build();
	}

	@Data
	@Builder
	public static class ContactFiltersRequest {
		private List<Integer> ids;
		private OperatorFilterEnum opIds;
		private List<String> emails;
		private OperatorFilterEnum opEmails;
		private List<String> phoneMobiles;
		private OperatorFilterEnum opPhoneMobiles;
	}
}
