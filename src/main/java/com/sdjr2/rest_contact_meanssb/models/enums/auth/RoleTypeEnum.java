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
 * @upgrade 24/08/01
 * @since 24/08/01
 */
@Getter
public enum RoleTypeEnum {
	ROLE_ADMIN,
	ROLE_MEMBER,
	ROLE_USER;

	public static RoleTypeEnum fromValue(String value) {
		for ( RoleTypeEnum type : RoleTypeEnum.values() ) {
			if ( type.name().equals( value.toUpperCase()) ) {
				return type;
			}
		}

		throw new CustomException( AppExceptionCodeEnum.STATUS_50001 );
	}
}
