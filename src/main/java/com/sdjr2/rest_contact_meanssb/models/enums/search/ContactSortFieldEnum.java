package com.sdjr2.rest_contact_meanssb.models.enums.search;

import com.sdjr2.rest_contact_meanssb.models.dto.ContactDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.ContactEntity;
import com.sdjr2.sb.library_commons.exceptions.AppExceptionCodeEnum;
import com.sdjr2.sb.library_commons.exceptions.CustomException;
import lombok.Getter;

/**
 * {@link ContactSortFieldEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the allowed contact attributes used by the sorts in
 * advanced search.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/08/12
 * @since 24/08/12
 */
@Getter
public enum ContactSortFieldEnum {
	EMAIL( "EMAIL", ContactDTO.ATTR_EMAIL, ContactEntity.ATTR_EMAIL ),
	PHONE_MOBILE( "PHONE_MOBILE", ContactDTO.ATTR_PHONE_MOBILE, ContactEntity.ATTR_PHONE_MOBILE );

	private final String value;
	private final String fieldFront;
	private final String fieldMySQL;

	ContactSortFieldEnum ( String value, String fieldFront, String fieldMySQL ) {
		this.value = value;
		this.fieldFront = fieldFront;
		this.fieldMySQL = fieldMySQL;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static ContactSortFieldEnum fromValue ( String value ) {
		for ( ContactSortFieldEnum type : ContactSortFieldEnum.values() ) {
			if ( type.getValue().equals( value ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static ContactSortFieldEnum fromFieldFront ( String fieldFront ) {
		for ( ContactSortFieldEnum type : ContactSortFieldEnum.values() ) {
			if ( type.getFieldFront().equals( fieldFront ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static ContactSortFieldEnum fromFieldMySQL ( String fieldMySQL ) {
		for ( ContactSortFieldEnum type : ContactSortFieldEnum.values() ) {
			if ( type.getFieldMySQL().equals( fieldMySQL ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}
}
