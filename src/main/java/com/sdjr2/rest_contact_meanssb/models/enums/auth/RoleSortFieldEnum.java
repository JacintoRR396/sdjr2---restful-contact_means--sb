package com.sdjr2.rest_contact_meanssb.models.enums.auth;

import com.sdjr2.rest_contact_meanssb.models.dto.auth.RoleDTO;
import com.sdjr2.rest_contact_meanssb.models.entities.auth.RoleEntity;
import com.sdjr2.sb.library_commons.exceptions.AppExceptionCodeEnum;
import com.sdjr2.sb.library_commons.exceptions.CustomException;
import lombok.Getter;

/**
 * {@link RoleSortFieldEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the allowed roles attributes used by the sorts in
 * advanced search.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/08/01
 * @since 24/08/01
 */
@Getter
public enum RoleSortFieldEnum {
	NAME( "NAME", RoleDTO.ATTR_NAME, RoleEntity.ATTR_NAME );

	private final String value;
	private final String fieldFront;
	private final String fieldMySQL;

	RoleSortFieldEnum ( String value, String fieldFront, String fieldMySQL ) {
		this.value = value;
		this.fieldFront = fieldFront;
		this.fieldMySQL = fieldMySQL;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static RoleSortFieldEnum fromValue ( String value ) {
		for ( RoleSortFieldEnum type : RoleSortFieldEnum.values() ) {
			if ( type.getValue().equals( value ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static RoleSortFieldEnum fromFieldFront ( String fieldFront ) {
		for ( RoleSortFieldEnum type : RoleSortFieldEnum.values() ) {
			if ( type.getFieldFront().equals( fieldFront ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static RoleSortFieldEnum fromFieldMySQL ( String fieldMySQL ) {
		for ( RoleSortFieldEnum type : RoleSortFieldEnum.values() ) {
			if ( type.getFieldMySQL().equals( fieldMySQL ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}
}
