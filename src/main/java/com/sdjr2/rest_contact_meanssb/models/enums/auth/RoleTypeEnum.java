package com.sdjr2.rest_contact_meanssb.models.enums.auth;

import com.sdjr2.rest_contact_meanssb.exceptions.AppExceptionCodeEnum;
import com.sdjr2.rest_contact_meanssb.exceptions.CustomException;
import lombok.Getter;

/**
 * {@link RoleTypeEnum} enum.
 * <p>
 * <strong>Enum (Model)</strong> - Represents an enum regarding the allowed roles about the authentication.
 *
 * @author Jacinto R^2
 * @version 1.0
 * @category Enum (Model)
 * @upgrade 24/08/04
 * @since 24/08/04
 */
@Getter
public enum RoleTypeEnum {
	ROLE_ADMIN( "ROLE_ADMIN", "ADMIN" ),
	ROLE_MEMBER( "ROLE_MEMBER", "MEMBER" ),
	ROLE_USER( "ROLE_USER", "USER" );

	private final String value;
	private final String valueSimple;

	RoleTypeEnum ( String value, String valueSimple ) {
		this.value = value;
		this.valueSimple = valueSimple;
	}

	@Override
	public String toString () {
		return this.value;
	}

	public static RoleTypeEnum fromValue ( String value ) {
		for ( RoleTypeEnum type : RoleTypeEnum.values() ) {
			if ( type.getValue().equals( value.toUpperCase() ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}

	public static RoleTypeEnum fromValueSimple ( String value ) {
		for ( RoleTypeEnum type : RoleTypeEnum.values() ) {
			if ( type.getValueSimple().equals( value.toUpperCase() ) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}
}
